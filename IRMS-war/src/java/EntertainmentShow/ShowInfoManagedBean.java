/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow;

import ConventionExhibition.entity.Auditorium;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.Theater;
import EntertainmentShow.session.ESInfoManagementSessionBeanLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author xing zhe
 */
@ManagedBean
@SessionScoped
public class ShowInfoManagedBean {
    //entity instances

    private ShowInfo showInfo = new ShowInfo();
    private ShowInfo theShowInfo = new ShowInfo();
    private Auditorium theAuditorium = new Auditorium();
    private Theater theTheater = new Theater();
    private ESShow show = new ESShow();
    private ESShow theShow = new ESShow();
    private SectionTicket sectionTicket = new SectionTicket();
    private SectionTicket theSectionTicket = new SectionTicket();
    
    //attributes
    private String venue = null;
    private String timeSlot = null;//9-1, 1-5, 5-9
    private Date dateFrom;
    private Date dateTo;
    private Date startTimeShow;
    private Date endTimeShow;
    private int capacity;
    private boolean displayTheater = false;
    private boolean displayAuditorium = false;
    //all lists
    private List<ShowInfo> allShowInfos = new ArrayList();
    private List<Theater> theaters = new ArrayList();
    private List<Auditorium> auditoriums = new ArrayList();
    
    private List<ESShow> shows = new ArrayList();
    private List<SectionTicket> sectionTickets = new ArrayList();
    @EJB
    private ESInfoManagementSessionBeanLocal essb;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of ShowInfoManagedBean
     */
    public ShowInfoManagedBean() {
    }

    public void addShowInfo() throws IOException {
        System.err.println("inside ShowInfoManagedBean addShowInfo()");

        if (getEssb().addShowInfo(getShowInfo())) {
            String statusMessage = "Show Info Added Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Error: cannot add show";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
        this.resetShowInfo(getShowInfo());
        //  FacesContext.getCurrentInstance().getExternalContext().redirect("AddShowInfo.xhtml");

    }

    public void resetShowInfo(ShowInfo showInfo) {
        showInfo.setName(null);
        showInfo.setStartDate(null);
        showInfo.setEndDate(null);
        showInfo.setDuration(null);
        showInfo.setType(null);
    }

    public void displayMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteShowInfo() {
        System.err.println("inside showInfManagedBean: deleteShowInfo() ");
//        FacesContext fc = FacesContext.getCurrentInstance();
//           ExternalContext ec = fc.getExternalContext();
//           Long showInfoId = Long.parseLong(event.getComponent().getAttributes().get("showInfoId").toString());
        Long showInfoId = getTheShowInfo().getId();
        if (getEssb().deleteShowInfo(showInfoId)) {
            String statusMessage = "Show Infomation deleted from database Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Error: cannot delete showInfomation";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }

    public void updateShowInfo() {
        System.err.println("ManagedBean: updateTIcketType()");
        boolean result = getEssb().updateShowInfo(getTheShowInfo());
        if (result == true) {
            String statusMessage = "Information Updated Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Information Update Failed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }
    
    public void updateSectionTicket(SectionTicket sectionTicket) throws IOException{
        System.err.println("ManagedBean: updateTicketType()");
        System.out.println("sectionTicket is "+sectionTicket);
        
        boolean result = getEssb().updateSectionTicket(sectionTicket, theShow);
        if (result == true) {
            String statusMessage = "Information Updated Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Information Update Failed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }
    
    public void reloadShowInfo() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("ViewAllShowInfo.xhtml");
    }

    public void searchVenue1() throws IOException {
        System.err.println("managedBean: searchVenue1 " + getTheShowInfo().getName());
        FacesContext.getCurrentInstance().getExternalContext().redirect("SearchShowVenue.xhtml");
    }

    public void searchShowVenue() throws ParseException, IOException {
        System.err.println("managedBean: searchShowVenue " + getVenue() + " " + getTimeSlot() + " " + getDateFrom() + " " + getDateTo() + " " + getCapacity());
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

        if (!dateFrom.before(dateTo)) {
            this.displayMessage("Invalid input, please try again!");
        } else {

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot1start = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot1end = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot2start = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 17);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot2end = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 17);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot3start = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date slot3end = cal.getTime();

            switch (getVenue()) {
                case "Theater":
                    if (getTimeSlot().equals("slot1")) {
                        System.err.println("slot1");
                        setTheaters(getEssb().searchTheaters(getCapacity(), getDateFrom(), getDateTo(), slot1start, slot1end));
                    } else if (getTimeSlot().equals("slot2")) {
                        System.err.println("slot2");
                        setTheaters(getEssb().searchTheaters(getCapacity(), getDateFrom(), getDateTo(), slot2start, slot2end));
                    } else {
                        System.err.println("slot3");
                        setTheaters(getEssb().searchTheaters(getCapacity(), getDateFrom(), getDateTo(), slot3start, slot3end));
                    }

                    if (getTheaters().isEmpty()) {
                        this.displayMessage("There are no available venues theater");
                    } else {
                        //to test
                        for (Theater t : getTheaters()) {
                            System.err.println(t);
                        }
                        setDisplayTheater(true);
                        getEc().redirect("AddVenueSchedule.xhtml");
                    }
                    break;

                //need to test    
                case "Auditorium":
                    if (getTimeSlot().equals("slot1")) {
                        setAuditoriums(getEssb().searchAuditoriums(getCapacity(), getDateFrom(), getDateTo(), slot1start, slot1end));
                    } else if (getTimeSlot().equals("slot2")) {
                        setAuditoriums(getEssb().searchAuditoriums(getCapacity(), getDateFrom(), getDateTo(), slot2start, slot2end));
                    } else {
                        setAuditoriums(getEssb().searchAuditoriums(getCapacity(), getDateFrom(), getDateTo(), slot3start, slot3end));
                    }

                    if (getAuditoriums().isEmpty()) {
                        this.displayMessage("There are no available venues auditorium");
                    } else {
                        for (Auditorium a : getAuditoriums()) {
                            System.err.println(a);
                        }
                        setDisplayAuditorium(true);
                        getEc().redirect("AddVenueSchedule.xhtml");
                    }
                    break;
            }

        }


    }

    public void addTheaterSchedule() {
        System.err.println("inside addTheaterSchedule");
        System.out.println("startTimeShow " + getStartTimeShow());
        System.out.println("endTimeShow " + getEndTimeShow());
        System.out.println("dateFrom " + getDateFrom());
        System.out.println("dateTo " + getDateTo());
        System.out.println("theShowInfo name" + getTheShowInfo().getName());
        System.out.println("theTheater " + getTheTheater());
        System.out.println("timeSlot " + getTimeSlot());

        Date startTime;
        Date endTime;
        Calendar cal = Calendar.getInstance();

        if (getTimeSlot().equals("slot1")) {
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            endTime = cal.getTime();
        } else if (getTimeSlot().equals("slot2")) {
            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 17);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            endTime = cal.getTime();
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 17);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            endTime = cal.getTime();
        }

        System.out.println("startTime " + startTime);
        System.out.println("endTime " + endTime);

        if (getStartTimeShow().compareTo(getEndTimeShow()) >= 0) {
            this.displayMessage("Error: time selection is not correct");
        } else {
            if (getEssb().addTheaterShow(getDateFrom(), getDateTo(), getStartTimeShow(), getEndTimeShow(), startTime, endTime, getTheShowInfo(), getTheTheater())) {
                setTheShowInfo(new ShowInfo());
                setDateFrom(new Date());
                setDateTo(new Date());
                setTimeSlot(null);
                setCapacity(0);

                this.displayMessage("Theater Schedule Added Successfully");
            } else {
                this.displayMessage("Error: cannot add theater schedule");
            }
        }
    }

    public void addAuditoriumSchedule() throws IOException {
        Date startTime;
        Date endTime;
        Calendar cal = Calendar.getInstance();

        if (getTimeSlot().equals("slot1")) {
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 13);
            endTime = cal.getTime();
        } else if (getTimeSlot().equals("slot2")) {
            cal.set(Calendar.HOUR_OF_DAY, 13);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 17);
            endTime = cal.getTime();
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 17);
            startTime = cal.getTime();
            cal.set(Calendar.HOUR_OF_DAY, 21);
            endTime = cal.getTime();
        }

        if (getStartTimeShow().compareTo(getEndTimeShow()) >= 0) {
            this.displayMessage("Error: time selection is not correct");
        } else {
            if (getEssb().addAuditoriumShow(dateFrom, dateTo, getStartTimeShow(), getEndTimeShow(), startTime, endTime, getTheShowInfo(), getTheAuditorium())) {
                this.displayMessage("Auditorium Schedule Added Successfully");
                getEc().redirect("AddShowInfo.xhtml");
            } else {
                this.displayMessage("Error: cannot add auditorium schedule");
            }
        }
    }
    
    public void viewAllShow() throws IOException {
        System.err.println("view showInfos");
        setShows((List<ESShow>) getTheShowInfo().getShows());
        getEc().redirect("AddShowInfo.xhtml");
    }
    
    public void viewAllSectionTicket() throws IOException {
        System.err.println("view sectionTickets");
        setSectionTickets((List<SectionTicket>) theShow.getSectionTickets());
        getEc().redirect("AddShowInfo.xhtml");
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
     * @return the allShowInfos
     */
    public List<ShowInfo> getAllShowInfos() {
        allShowInfos = getEssb().listAllShowInfos();
        return allShowInfos;
    }

    /**
     * @param allShowInfos the allShowInfos to set
     */
    public void setAllShowInfos(List<ShowInfo> allShowInfos) {
        this.allShowInfos = allShowInfos;
    }

    /**
     * @return the theShowInfo
     */
    public ShowInfo getTheShowInfo() {
        return theShowInfo;
    }

    /**
     * @param theShowInfo the theShowInfo to set
     */
    public void setTheShowInfo(ShowInfo theShowInfo) {
        this.theShowInfo = theShowInfo;
    }

    /**
     * @return the venueInfo
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venueInfo the venueInfo to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the timeSlot
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * @param timeSlot the timeSlot to set
     */
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * @return the theaters
     */
    public List<Theater> getTheaters() {
        return theaters;
    }

    /**
     * @param theaters the theaters to set
     */
    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }

    /**
     * @return the auditoriums
     */
    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    /**
     * @param auditoriums the auditoriums to set
     */
    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    /**
     * @return the theAuditorium
     */
    public Auditorium getTheAuditorium() {
        return theAuditorium;
    }

    /**
     * @param theAuditorium the theAuditorium to set
     */
    public void setTheAuditorium(Auditorium theAuditorium) {
        this.theAuditorium = theAuditorium;
    }

    /**
     * @return the dateFrom
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the theTheater
     */
    public Theater getTheTheater() {
        return theTheater;
    }

    /**
     * @param theTheater the theTheater to set
     */
    public void setTheTheater(Theater theTheater) {
        this.theTheater = theTheater;
    }

    /**
     * @return the displayTheater
     */
    public boolean isDisplayTheater() {
        return displayTheater;
    }

    /**
     * @param displayTheater the displayTheater to set
     */
    public void setDisplayTheater(boolean displayTheater) {
        this.displayTheater = displayTheater;
    }

    /**
     * @return the displayAuditorium
     */
    public boolean isDisplayAuditorium() {
        return displayAuditorium;
    }

    /**
     * @param displayAuditorium the displayAuditorium to set
     */
    public void setDisplayAuditorium(boolean displayAuditorium) {
        this.displayAuditorium = displayAuditorium;
    }

    /**
     * @return the startTimeShow
     */
    public Date getStartTimeShow() {
        return startTimeShow;
    }

    /**
     * @param startTimeShow the startTimeShow to set
     */
    public void setStartTimeShow(Date startTimeShow) {
        this.startTimeShow = startTimeShow;
    }

    /**
     * @return the endTimeShow
     */
    public Date getEndTimeShow() {
        return endTimeShow;
    }

    /**
     * @param endTimeShow the endTimeShow to set
     */
    public void setEndTimeShow(Date endTimeShow) {
        this.endTimeShow = endTimeShow;
    }

    /**
     * @return the essb
     */
    public ESInfoManagementSessionBeanLocal getEssb() {
        return essb;
    }

    /**
     * @param essb the essb to set
     */
    public void setEssb(ESInfoManagementSessionBeanLocal essb) {
        this.essb = essb;
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
     * @return the theShow
     */
    public ESShow getTheShow() {
        return theShow;
    }

    /**
     * @param theShow the theShow to set
     */
    public void setTheShow(ESShow theShow) {
        this.theShow = theShow;
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
     * @return the theSectionTicket
     */
    public SectionTicket getTheSectionTicket() {
        return theSectionTicket;
    }

    /**
     * @param theSectionTicket the theSectionTicket to set
     */
    public void setTheSectionTicket(SectionTicket theSectionTicket) {
        this.theSectionTicket = theSectionTicket;
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
}
