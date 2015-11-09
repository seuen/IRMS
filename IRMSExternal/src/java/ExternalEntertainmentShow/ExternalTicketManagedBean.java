/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalEntertainmentShow;

import Accommodation.entity.Guest;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.Seat;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.ShowTicket;
import EntertainmentShow.session.ESTicketManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author zsy
 */
@ManagedBean
@SessionScoped
public class ExternalTicketManagedBean implements Serializable {
    
    
    
    //all entity instances
    private ShowInfo showInfo = new ShowInfo();
    private ShowInfo selectShowInfo = new ShowInfo();
    private ESShow show = new ESShow();
    private ESShow selectShow = new ESShow();
    private SectionTicket sectionTicket = new SectionTicket();
    private SectionTicket selectSectionTicket = new SectionTicket();
    private Seat seat = new Seat();
    private Guest guest = new Guest();
    //all list
    private List<Seat> selectSeats = new ArrayList();
    private List<ShowInfo> showInfos = new ArrayList();
    private List<ESShow> shows = new ArrayList();
    private List<SectionTicket> sectionTickets = new ArrayList();
    private List<Seat> seats = new ArrayList();
    private List<String> myShows = new ArrayList();
    //for display
    private boolean displayShow = false;
    private boolean displaySection = false;
    private boolean displaySelectSection = false;
    private boolean displaySeat = false;
    //for select value
    private String selectTime;
    private String selectSectionNum;
    
    //for selectOneMenu
//    private List<SelectItem> yourShows = new ArrayList();
    private String selectShowTime;
    private Map<String,String> yourShows = new HashMap();
    
    @EJB
    private ESTicketManagementSessionBeanLocal estmsbl;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of ExternalTicketManagedBean
     */
    public ExternalTicketManagedBean() {
    }

    public List<ShowInfo> viewAllShowInfo() {
        setShowInfos(getEstmsbl().listAllShowInfo());

        return getShowInfos();
    }

    public void viewAllShow() throws IOException {
        DateFormat df = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm");
        System.err.println("view shows");
        setShows((List<ESShow>) getSelectShowInfo().getShows());
        setMyShows((List<String>) new ArrayList());
        for (ESShow es : getShows()) {
            String temp = df.format(es.getStartTime());
            getMyShows().add(temp);

            if (!myShows.isEmpty()) {
                System.out.println(getMyShows().get(getMyShows().size() - 1));
            } else {
                System.out.println("size == 0");
            }
        }

        setDisplayShow(true);
        setDisplaySection(false);
        displaySelectSection = false;
        setDisplaySeat(false);
        getEc().redirect("ViewShows.xhtml");
    }
   
    public void viewAllSectionTicket() throws IOException, ParseException {
        System.err.println("inside view sectionTickets and selectTime is "+selectTime);
        int temp = 0;
        Date sTime = new SimpleDateFormat("EEE MM/dd/yyyy hh:mm", Locale.ENGLISH).parse(getSelectTime());

        System.out.println("after format is " + sTime);

        for (ESShow es : getShows()) {
            System.out.println("show start time is " + es.getStartTime());
            int year = es.getStartTime().getYear();
            int month = es.getStartTime().getMonth();
            int day = es.getStartTime().getDay();

            if ((year == sTime.getYear()) && (month == sTime.getMonth()) && (day == sTime.getDay())) {
                System.out.println(es);
                setSelectShow(es);
                temp = 1;
            } else {
                continue;
            }
        }

        if (temp == 1) {
            setSectionTickets((List<SectionTicket>) getSelectShow().getSectionTickets());
            setDisplayShow(false);
            setDisplaySection(true);
            displaySelectSection = true;
            setDisplaySeat(false);
            
            for(SectionTicket st: sectionTickets){
                System.out.println(st);
            }

            getEc().redirect("ViewShows.xhtml");
        } else if (temp == 0) {
            FacesMessage msg = new FacesMessage("Fail to select time slot, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
 
    public void viewAllSeat() throws IOException {
        System.err.println("inside view all seats");
        System.err.println("selected section number is " + getSelectSectionNum());

        int temp = 0;

        for (SectionTicket st : getSectionTickets()) {
            if (st.getSeatSection().getNum().equals(getSelectSectionNum())) {
                setSelectSectionTicket(st);
                temp = 1;
                System.out.println("selectSectionTicket is " + getSelectSectionTicket());
            } else {
                continue;
            }
        }

        if (temp == 1) {
            setSeats((List<Seat>) getSelectSectionTicket().getSeatSection().getSeats());
            
            setDisplaySection(false);
            displaySelectSection = false;
            setDisplaySeat(true);

            for (Seat s : getSeats()) {
                System.err.println("view seats " + s);
                List<ShowTicket> showTickets = (List<ShowTicket>) s.getShowTickets();

                if (!s.getShowTickets().isEmpty()) {
                    System.out.println("not empty");
                    ESShow es = showTickets.get(showTickets.size() - 1).getSectionTicket().getShow();
                    if (es.getId().equals(getSelectShow().getId())) {
                        s.setStatus("Unavailable");
                    }
                }
            }
            getEc().redirect("ViewShows.xhtml");

        } else if (temp == 0) {
            FacesMessage msg = new FacesMessage("Fail to select section, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
   
    public void blockSeats() throws IOException {
        System.err.println("inside blockSeats managedbean with section Ticket" + selectSectionTicket);
        //refresh all instances
        setSelectShowInfo(new ShowInfo());
        setSelectShow(new ESShow());

        int test = 1;
        for (Seat s : getSelectSeats()) {
            System.out.println("selected seat is " + s);
            if (s.getStatus().equals("Unavailable")) {
                test = 0;
            }
        }

        if (test == 0) {
            System.err.println("not able to reserve");
            FacesMessage msg = new FacesMessage("Select seats not available, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (test == 1) {
            Long num = getEstmsbl().blockExSeats(selectSeats, selectSectionTicket);
            System.err.println("confirmation number is " + num);
            setSelectSectionTicket(new SectionTicket());
            setSelectSeats((List<Seat>) new ArrayList());
            selectTime = null;
            selectSectionNum = null;
            
            FacesMessage msg = new FacesMessage("Successful with number " + num + "!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("sucessful to reserve");
            //refresh all display
            setDisplayShow(false);
            setDisplaySection(false);
            displaySelectSection = false;
            setDisplaySeat(false);
        }
    }

    /**
     * @return the estmsbl
     */
    public ESTicketManagementSessionBeanLocal getEstmsbl() {
        return estmsbl;
    }

    /**
     * @param estmsbl the estmsbl to set
     */
    public void setEstmsbl(ESTicketManagementSessionBeanLocal estmsbl) {
        this.estmsbl = estmsbl;
    }

    /**
     * @return the fc
     */
    public FacesContext getFc() {
        return fc;
    }

    /**
     * @param fc the fc to set
     */
    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    /**
     * @return the ec
     */
    public ExternalContext getEc() {
        return ec;
    }

    /**
     * @param ec the ec to set
     */
    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

    /**
     * @return the selectShowInfo
     */
    public ShowInfo getSelectShowInfo() {
        return selectShowInfo;
    }

    /**
     * @param selectShowInfo the selectShowInfo to set
     */
    public void setSelectShowInfo(ShowInfo selectShowInfo) {
        this.selectShowInfo = selectShowInfo;
    }

    /**
     * @return the showInfos
     */
    public List<ShowInfo> getShowInfos() {
        return showInfos;
    }

    /**
     * @param showInfos the showInfos to set
     */
    public void setShowInfos(List<ShowInfo> showInfos) {
        this.showInfos = showInfos;
    }

    /**
     * @return the shows
     */
    public List<ESShow> getShows() {
        return shows;
    }

    /**
     * @param shows the shows to set
     */
    public void setShows(List<ESShow> shows) {
        this.shows = shows;
    }

    /**
     * @return the displayShow
     */
    public boolean isDisplayShow() {
        return displayShow;
    }

    /**
     * @param displayShow the displayShow to set
     */
    public void setDisplayShow(boolean displayShow) {
        this.displayShow = displayShow;
    }

    /**
     * @return the displaySection
     */
    public boolean isDisplaySection() {
        return displaySection;
    }

    /**
     * @param displaySection the displaySection to set
     */
    public void setDisplaySection(boolean displaySection) {
        this.displaySection = displaySection;
    }

    /**
     * @return the displaySeat
     */
    public boolean isDisplaySeat() {
        return displaySeat;
    }

    /**
     * @param displaySeat the displaySeat to set
     */
    public void setDisplaySeat(boolean displaySeat) {
        this.displaySeat = displaySeat;
    }

    /**
     * @return the showInfo
     */
    public ShowInfo getShowInfo() {
        return showInfo;
    }

    /**
     * @param showInfo the showInfo to set
     */
    public void setShowInfo(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    /**
     * @return the show
     */
    public ESShow getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(ESShow show) {
        this.show = show;
    }

    /**
     * @return the selectShow
     */
    public ESShow getSelectShow() {
        return selectShow;
    }

    /**
     * @param selectShow the selectShow to set
     */
    public void setSelectShow(ESShow selectShow) {
        this.selectShow = selectShow;
    }

    /**
     * @return the sectionTicket
     */
    public SectionTicket getSectionTicket() {
        return sectionTicket;
    }

    /**
     * @param sectionTicket the sectionTicket to set
     */
    public void setSectionTicket(SectionTicket sectionTicket) {
        this.sectionTicket = sectionTicket;
    }

    /**
     * @return the selectSectionTicket
     */
    public SectionTicket getSelectSectionTicket() {
        return selectSectionTicket;
    }

    /**
     * @param selectSectionTicket the selectSectionTicket to set
     */
    public void setSelectSectionTicket(SectionTicket selectSectionTicket) {
        this.selectSectionTicket = selectSectionTicket;
    }

    /**
     * @return the seat
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * @param seat the seat to set
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * @return the selectSeats
     */
    public List<Seat> getSelectSeats() {
        return selectSeats;
    }

    /**
     * @param selectSeats the selectSeats to set
     */
    public void setSelectSeats(List<Seat> selectSeats) {
        this.selectSeats = selectSeats;
    }

    /**
     * @return the sectionTickets
     */
    public List<SectionTicket> getSectionTickets() {
        return sectionTickets;
    }

    /**
     * @param sectionTickets the sectionTickets to set
     */
    public void setSectionTickets(List<SectionTicket> sectionTickets) {
        this.sectionTickets = sectionTickets;
    }

    /**
     * @return the seats
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    /**
     * @return the myShows
     */
    public List<String> getMyShows() {
        return myShows;
    }

    /**
     * @param myShows the myShows to set
     */
    public void setMyShows(List<String> myShows) {
        this.myShows = myShows;
    }

    /**
     * @return the selectTime
     */
    public String getSelectTime() {
        return selectTime;
    }

    /**
     * @param selectTime the selectTime to set
     */
    public void setSelectTime(String selectTime) {
        this.selectTime = selectTime;
    }

    /**
     * @return the selectSectionNum
     */
    public String getSelectSectionNum() {
        return selectSectionNum;
    }

    /**
     * @param selectSectionNum the selectSectionNum to set
     */
    public void setSelectSectionNum(String selectSectionNum) {
        this.selectSectionNum = selectSectionNum;
    }

    /**
     * @return the displaySelectSection
     */
    public boolean isDisplaySelectSection() {
        return displaySelectSection;
    }

    /**
     * @param displaySelectSection the displaySelectSection to set
     */
    public void setDisplaySelectSection(boolean displaySelectSection) {
        this.displaySelectSection = displaySelectSection;
    }


    /**
     * @return the selectShowTime
     */
    public String getSelectShowTime() {
        return selectShowTime;
    }

    /**
     * @param selectShowTime the selectShowTime to set
     */
    public void setSelectShowTime(String selectShowTime) {
        this.selectShowTime = selectShowTime;
    }

    /**
     * @return the yourShows
     */
    public Map<String,String> getYourShows() {
        return yourShows;
    }

    /**
     * @param yourShows the yourShows to set
     */
    public void setYourShows(Map<String,String> yourShows) {
        this.yourShows = yourShows;
    }

    /**
     * @return the guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * @param guest the guest to set
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
