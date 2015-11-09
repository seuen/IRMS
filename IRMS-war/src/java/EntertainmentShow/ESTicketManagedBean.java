/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow;

import EntertainmentShow.entity.Seat;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.ShowOrder;
import EntertainmentShow.entity.ShowTicket;
import EntertainmentShow.session.ESTicketManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author zsy
 */
@ManagedBean
@SessionScoped
public class ESTicketManagedBean {

    private ShowInfo showInfo = new ShowInfo();
    private ShowInfo selectShowInfo = new ShowInfo();
    private ESShow show = new ESShow();
    private ESShow selectShow = new ESShow();
    private SectionTicket sectionTicket = new SectionTicket();
    private SectionTicket selectSectionTicket = new SectionTicket();
    private Seat seat = new Seat();
    private ShowOrder thisOrder = new ShowOrder();
    private List<ShowTicket> tickets = new ArrayList();
    private List<Seat> selectSeats = new ArrayList();
    private List<ShowInfo> showInfos = new ArrayList();
    private List<ESShow> shows = new ArrayList();
    private List<SectionTicket> sectionTickets = new ArrayList();
    private List<Seat> seats = new ArrayList();
    private boolean displayShow = false;
    private boolean displaySection = false;
    private boolean displaySeat = false;
    
    @EJB
    private ESTicketManagementSessionBeanLocal estmsbl;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of ESTicketManagedBean
     */
    public ESTicketManagedBean() {
    }

    public List<ShowInfo> viewAllShowInfo() {
        setShowInfos(getEstmsbl().listAllShowInfo());
        return getShowInfos();
    }

    public void viewAllShow() throws IOException {
        System.err.println("view showInfos");
        setShows((List<ESShow>) getSelectShowInfo().getShows());
        displayShow = true;
        displaySection = false;
        displaySeat = false;
        getEc().redirect("ViewAllShows.xhtml");
    }

    public void viewAllSectionTicket() throws IOException {
        setSectionTickets((List<SectionTicket>) getSelectShow().getSectionTickets());
        displayShow = false;
        displaySection = true;
        displaySeat = false;
        getEc().redirect("ViewAllShows.xhtml");
    }

    public void viewAllSeat() throws IOException {
        System.err.println("inside view all seats");

        if (selectSectionTicket.getAvailableNum() == 0 || selectSectionTicket.getStatus().equals("Unavailable")) {
            FacesMessage msg = new FacesMessage("Selected section not available, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            setSeats((List<Seat>) getSelectSectionTicket().getSeatSection().getSeats());
            displayShow = false;
            displaySection = false;
            displaySeat = true;

            for (Seat s : seats) {
                List<ShowTicket> showTickets = (List<ShowTicket>) s.getShowTickets();

                if (!s.getShowTickets().isEmpty()) {
                    System.out.println("not empty");
                    ESShow es = showTickets.get(showTickets.size() - 1).getSectionTicket().getShow();
                    if (es.getId().equals(selectShow.getId()) || selectSectionTicket.getStatus().equals("Unavailable")) {
                        s.setStatus("Unavailable");
                    }

                }
            }
            getEc().redirect("ViewAllShows.xhtml");
        }
    }

    public void blockSeats() throws IOException {
        System.err.println("inside blockSeats managedbean");
        //refresh all instances
        setSelectShowInfo(new ShowInfo());
        setSelectShow(new ESShow());

        int test = 1;
        for (Seat s : selectSeats) {
            if (s.getStatus().equals("Unavailable")) {
                test = 0;
            }
        }

        //refresh all display
        displayShow = false;
        displaySection = false;
        displaySeat = false;

        if (test == 0) {
            FacesMessage msg = new FacesMessage("Select seats not available, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            //refresh all instances
            selectSeats = new ArrayList();

        } else if (test == 1) {
            Long number = estmsbl.blockInSeats(selectSeats, selectSectionTicket);
            System.err.println("confirmation number is " + number);
            selectSeats = new ArrayList();
            FacesMessage msg = new FacesMessage("Successful with number " + number + "!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
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
     * @return the selectSeat
     */
    public List<Seat> getSelectSeats() {
        return selectSeats;
    }

    /**
     * @param selectSeat the selectSeat to set
     */
    public void setSelectSeats(List<Seat> selectSeats) {
        this.selectSeats = selectSeats;
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
     * @return the thisOrder
     */
    public ShowOrder getThisOrder() {
        return thisOrder;
    }

    /**
     * @param thisOrder the thisOrder to set
     */
    public void setThisOrder(ShowOrder thisOrder) {
        this.thisOrder = thisOrder;
    }

    /**
     * @return the tickets
     */
    public List<ShowTicket> getTickets() {
        return tickets;
    }

    /**
     * @param tickets the tickets to set
     */
    public void setTickets(List<ShowTicket> tickets) {
        this.tickets = tickets;
    }

}
