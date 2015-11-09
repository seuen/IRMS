/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Enquiry;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import ConventionExhibition.entity.Quotation;
import ConventionExhibition.session.QuotationManagementSessionBeanLocal;
import ConventionExhibition.session.VenueManagementSessionBeanLocal;
import FoodBeverage.entity.BanquetHall;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@ManagedBean
@RequestScoped
public class FBManagedBean {

     @EJB
    private VenueManagementSessionBeanLocal vmsbl;
    @EJB
    private QuotationManagementSessionBeanLocal qmsbl;
    private String venuetype;
    private int capacity;
    private String datetype;//day, halfday, hour
    private Date startingDate; //for day
    private Date endingDate;  //for day
    private Date eventdate; //for halfday
    private String timeslot; //for half day
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endTime;
    private List<ExhibitionSection> exhibitionsections = new ArrayList();
    private List<Auditorium> auditoriums = new ArrayList();
    private List<OpenSpace> openspaces = new ArrayList();
    private List<BanquetHall> banquethalls=new ArrayList();
    private List<OtherVenue> othervenues = new ArrayList();
    private Auditorium selectauditorium = new Auditorium();
    private List<ExhibitionSection> selectexhibitions = new ArrayList();
    private OpenSpace selectopenspace = new OpenSpace();
    private OtherVenue selectothervenue = new OtherVenue();
    private Enquiry selectenquiry = new Enquiry();
    private Quotation quotation = new Quotation();
    
    /**
     * Creates a new instance of FBManagedBean
     */
    public FBManagedBean() {
    }
    
       public void dispalyMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void searchvenue(ActionEvent event) throws IOException, ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date today = sd.parse(sd.format(new Date()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(eventdate);
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date morningstart = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 13);
        Date morningend = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 15);
        Date afternoonstart = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY, 21);
        Date afternoonend = cal.getTime();
        switch (getVenuetype()) {

            case "Open Space":
                if (getDatetype().equals("day")) {
                    if (getStartingDate() != null && getEndingDate() != null) {
                        if (today.compareTo(getStartingDate()) >= 0) {
                            this.dispalyMessage("Starting Date must be after Today");
                        } else {
                            if (getStartingDate().after(getEndingDate())) {
                                this.dispalyMessage("startingDate cannot be before endingDate");
                            } else {
                                Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                if (getStartingDate().compareTo(ca.getTime()) > 0) {
                                    this.dispalyMessage("Room can only be reserved half a year ahead");
                                } else {
                                    setOpenspaces(getVmsbl().searchOpenSpace(getCapacity(), getStartingDate(), getEndingDate()));
                                    if (getOpenspaces().isEmpty()) {
                                        this.dispalyMessage("There is no avaiable OpenSpace");
                                    } else {
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("FBAvailableOpenSpace.xhtml");
                                    }
                                }
                            }
                        }
                    } else {
                        this.dispalyMessage("Starting Date and Ending Date cannot be empty");
                    }
                } else {
                    this.dispalyMessage("Open Space can only be booked by day");
                }
                break;

          

            default:
                switch (getDatetype()) {
                    case "day":
                        if (getStartingDate() != null && getEndingDate() != null) {
                            if (today.compareTo(getStartingDate()) >= 0) {
                                this.dispalyMessage("Starting Date must be after Today");
                            } else {
                                if (getStartingDate().after(getEndingDate())) {
                                    this.dispalyMessage("startingDate cannot be before endingDate");
                                } else {
                                    Calendar ca = new GregorianCalendar();
                                    ca.setTime(today);
                                    ca.add(Calendar.DATE, 180);
                                    if (getStartingDate().compareTo(ca.getTime()) > 0) {
                                        this.dispalyMessage("Room can only be reserved half a year ahead");
                                    } else {
                                        if (banquethalls.isEmpty()) {
                                            this.dispalyMessage("There are no avaiable banquet halls" + getVenuetype() + "s");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("FBAvailableBanquetHall.xhtml");
                                        }
                                    }
                                }
                            }
                        } else {
                            this.dispalyMessage("Starting Date and Ending Date cannot be empty");
                        }
                        break;
                    case "halfday":
                        if (getTimeslot() != null && getEventdate() != null) {
                            if (today.compareTo(getEventdate()) >= 0) {
                                this.dispalyMessage("Starting Date must be after Today");
                            } else {
                                Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                if (getEventdate().compareTo(ca.getTime()) > 0) {
                                    this.dispalyMessage("Room can only be reserved half a year ahead");
                                } else {
                                    if (getTimeslot().equals("morning")) {
                                        banquethalls=vmsbl.searchBanquetHallHour(capacity, eventdate, morningstart, morningend);
                                        if (banquethalls.isEmpty()) {
                                            this.dispalyMessage("There are no avaiable banquet halls");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("FBAvailableBanquetHall.xhtml");
                                        }
                                    } else {
                                        banquethalls=vmsbl.searchBanquetHallHour(capacity, eventdate, afternoonstart,afternoonend);
                                        if (banquethalls.isEmpty()) {
                                            this.dispalyMessage("There are no avaiable banquet halls");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("FBAvailableBanquetHall.xhtml");
                                        }

                                    }
                                }
                            }
                        } else {
                            this.dispalyMessage("Date and period cannot be empty choosing Half Day");
                        }
                        break;

                    default:
                        if (getEventdate() != null && getStartTime() != null && getEndTime() != null) {
                            if (today.compareTo(getEventdate()) >= 0) {
                                this.dispalyMessage("Starting Date must be after Today");
                            } else {
                                Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                if (getEventdate().compareTo(ca.getTime()) > 0) {
                                    this.dispalyMessage("Room can only be reserved half a year ahead");
                                } else {
                                    if (!startTime.before(endTime)) {
                                        this.dispalyMessage("StartTime must be after endTime");
                                    } else {
                                        if (getEndTime().getTime() - getStartTime().getTime() / 1000 * 60 * 60 < 2) {
                                            this.dispalyMessage("The period cannot be less than 2 hours");
                                        } else {
                                            Calendar cale=Calendar.getInstance();
                                            Calendar calen=Calendar.getInstance();
                                            cale.setTime(eventdate);
                                            calen.setTime(startTime);
                                            calen.set(Calendar.YEAR,cale.get(Calendar.YEAR));
                                            calen.set(Calendar.MONTH,cale.get(Calendar.MONTH));
                                            calen.set(Calendar.MONTH,cale.get(Calendar.MONTH));
                                            startTime=calen.getTime();
                                            calen.setTime(endTime);
                                             calen.set(Calendar.YEAR,cale.get(Calendar.YEAR));
                                            calen.set(Calendar.MONTH,cale.get(Calendar.MONTH));
                                            calen.set(Calendar.MONTH,cale.get(Calendar.MONTH));
                                            endTime=calen.getTime();
                                            banquethalls=vmsbl.searchBanquetHallHour(capacity, eventdate, startTime, endTime);
                                            if (getOthervenues().isEmpty()) {
                                                this.dispalyMessage("There are no avaiable banquet halls");
                                            } else {
                                                FacesContext.getCurrentInstance().getExternalContext().redirect("FBAvailableBanquetHall.xhtml");
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            this.dispalyMessage("Date and Time cannot be empty choosing Half Day");
                        }
                        break;
                }
                break;
        }
    }

    /**
     * @return the vmsbl
     */
    public VenueManagementSessionBeanLocal getVmsbl() {
        return vmsbl;
    }

    /**
     * @param vmsbl the vmsbl to set
     */
    public void setVmsbl(VenueManagementSessionBeanLocal vmsbl) {
        this.vmsbl = vmsbl;
    }

    /**
     * @return the qmsbl
     */
    public QuotationManagementSessionBeanLocal getQmsbl() {
        return qmsbl;
    }

    /**
     * @param qmsbl the qmsbl to set
     */
    public void setQmsbl(QuotationManagementSessionBeanLocal qmsbl) {
        this.qmsbl = qmsbl;
    }

    /**
     * @return the venuetype
     */
    public String getVenuetype() {
        return venuetype;
    }

    /**
     * @param venuetype the venuetype to set
     */
    public void setVenuetype(String venuetype) {
        this.venuetype = venuetype;
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
     * @return the datetype
     */
    public String getDatetype() {
        return datetype;
    }

    /**
     * @param datetype the datetype to set
     */
    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }

    /**
     * @return the startingDate
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDate the startingDate to set
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * @return the endingDate
     */
    public Date getEndingDate() {
        return endingDate;
    }

    /**
     * @param endingDate the endingDate to set
     */
    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * @return the eventdate
     */
    public Date getEventdate() {
        return eventdate;
    }

    /**
     * @param eventdate the eventdate to set
     */
    public void setEventdate(Date eventdate) {
        this.eventdate = eventdate;
    }

    /**
     * @return the timeslot
     */
    public String getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the exhibitionsections
     */
    public List<ExhibitionSection> getExhibitionsections() {
        return exhibitionsections;
    }

    /**
     * @param exhibitionsections the exhibitionsections to set
     */
    public void setExhibitionsections(List<ExhibitionSection> exhibitionsections) {
        this.exhibitionsections = exhibitionsections;
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
     * @return the openspaces
     */
    public List<OpenSpace> getOpenspaces() {
        return openspaces;
    }

    /**
     * @param openspaces the openspaces to set
     */
    public void setOpenspaces(List<OpenSpace> openspaces) {
        this.openspaces = openspaces;
    }

    /**
     * @return the othervenues
     */
    public List<OtherVenue> getOthervenues() {
        return othervenues;
    }

    /**
     * @param othervenues the othervenues to set
     */
    public void setOthervenues(List<OtherVenue> othervenues) {
        this.othervenues = othervenues;
    }

    /**
     * @return the selectauditorium
     */
    public Auditorium getSelectauditorium() {
        return selectauditorium;
    }

    /**
     * @param selectauditorium the selectauditorium to set
     */
    public void setSelectauditorium(Auditorium selectauditorium) {
        this.selectauditorium = selectauditorium;
    }

    /**
     * @return the selectexhibitions
     */
    public List<ExhibitionSection> getSelectexhibitions() {
        return selectexhibitions;
    }

    /**
     * @param selectexhibitions the selectexhibitions to set
     */
    public void setSelectexhibitions(List<ExhibitionSection> selectexhibitions) {
        this.selectexhibitions = selectexhibitions;
    }

    /**
     * @return the selectopenspace
     */
    public OpenSpace getSelectopenspace() {
        return selectopenspace;
    }

    /**
     * @param selectopenspace the selectopenspace to set
     */
    public void setSelectopenspace(OpenSpace selectopenspace) {
        this.selectopenspace = selectopenspace;
    }

    /**
     * @return the selectothervenue
     */
    public OtherVenue getSelectothervenue() {
        return selectothervenue;
    }

    /**
     * @param selectothervenue the selectothervenue to set
     */
    public void setSelectothervenue(OtherVenue selectothervenue) {
        this.selectothervenue = selectothervenue;
    }

    /**
     * @return the selectenquiry
     */
    public Enquiry getSelectenquiry() {
        return selectenquiry;
    }

    /**
     * @param selectenquiry the selectenquiry to set
     */
    public void setSelectenquiry(Enquiry selectenquiry) {
        this.selectenquiry = selectenquiry;
    }

    /**
     * @return the quotation
     */
    public Quotation getQuotation() {
        return quotation;
    }

    /**
     * @param quotation the quotation to set
     */
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    /**
     * @return the banquethalls
     */
    public List<BanquetHall> getBanquethalls() {
        return banquethalls;
    }

    /**
     * @param banquethalls the banquethalls to set
     */
    public void setBanquethalls(List<BanquetHall> banquethalls) {
        this.banquethalls = banquethalls;
    }
}
