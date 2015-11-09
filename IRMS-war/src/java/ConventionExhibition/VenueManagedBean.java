package ConventionExhibition;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Enquiry;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import ConventionExhibition.entity.Quotation;
import ConventionExhibition.session.QuotationManagementSessionBeanLocal;
import ConventionExhibition.session.VenueManagementSessionBeanLocal;
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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Temporal;

@ManagedBean
@SessionScoped
public class VenueManagedBean {

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
    private List<OtherVenue> othervenues = new ArrayList();
    private Auditorium selectauditorium = new Auditorium();
    private List<ExhibitionSection> selectexhibitions = new ArrayList();
    private OpenSpace selectopenspace = new OpenSpace();
    private OtherVenue selectothervenue = new OtherVenue();
    private Enquiry selectenquiry = new Enquiry();
    private Quotation quotation = new Quotation();

    public VenueManagedBean() {
    }

    public void sendQuotation() {
        System.err.println("go into sendquotation in managedbean");
        qmsbl.sendQuotation(quotation, selectenquiry);
        this.dispalyMessage("Quotation has been sent successfully");
    }
    
    public void createOtherVenueQuotation() throws IOException{
          quotation = new Quotation();
        Date today = new Date();
        quotation.setQuotationDate(today);
        quotation.setAuditorium(null);
        quotation.setOpenspace(null);
        quotation.setBanquethall(null);
        quotation.setTheater(null);
        quotation.setExhibitionsections(null);
        quotation.setOthervenue(selectothervenue);
        quotation.setReplyparty("Coral Island Resort");
        if(datetype.equals("day")){
             quotation.setEventDate(startingDate);
        
        int duras = (int) ((endingDate.getTime() - startingDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        float price = selectauditorium.getDayprice() * duras;
        quotation.setVenueprice(price);
        }else if(datetype.equals("halfday")){
            quotation.setEventDate(eventdate);
            quotation.setVenueprice(selectothervenue.getHalfdayprice());
        }else{
            quotation.setEventDate(eventdate);
            int dura=(int) ((endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60));
            float price=selectothervenue.getHourprice()*dura;
            quotation.setVenueprice(price);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./../QuotationManagement/ChoooseEnquiry.xhtml");
        }
    }
    
    public void createExhibitionQupotation() throws IOException{
        
        int j = 0;
        for (ExhibitionSection es : selectexhibitions) {
            int i = 0;
            for (ExhibitionSection belongsection : es.getNeigbours()) {
                if (selectexhibitions.contains(belongsection)) {
                    i++;
                }
            }
            if (i == 0) {
                j++;
            }
        }
        if (j != 0&&selectexhibitions.size()>1) {
            this.dispalyMessage("Chosen Exhibition Sections are not together.Please choose again!");
        }else{
           quotation = new Quotation();
        Date today = new Date();
        quotation.setQuotationDate(today);
        quotation.setAuditorium(null);
        quotation.setOpenspace(null);
        quotation.setBanquethall(null);
        quotation.setTheater(null);
        quotation.setExhibitionsections(selectexhibitions);
        quotation.setOthervenue(null);
        quotation.setEventDate(startingDate);
        quotation.setReplyparty("Coral Island Resort");
        int duras = (int) ((endingDate.getTime() - startingDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        float price = selectexhibitions.get(0).getDayprice()* duras*selectexhibitions.size();
        quotation.setVenueprice(price);
        // FacesContext.getCurrentInstance().getExternalContext().redirect("AddProposal.xhtml");
        FacesContext.getCurrentInstance().getExternalContext().redirect("./../QuotationManagement/ChoooseEnquiry.xhtml");
        
        }
    }
    
    public void createOpenSpaceQuotation() throws IOException{
          quotation = new Quotation();
        Date today = new Date();
        quotation.setQuotationDate(today);
        quotation.setAuditorium(null);
        quotation.setOpenspace(selectopenspace);
        quotation.setBanquethall(null);
        quotation.setTheater(null);
        quotation.setExhibitionsections(null);
        quotation.setOthervenue(null);
        quotation.setEventDate(startingDate);
        quotation.setReplyparty("Coral Island Resort");
        int duras = (int) ((endingDate.getTime() - startingDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        float price = selectopenspace.getDayprice() * duras;
        quotation.setVenueprice(price);
        // FacesContext.getCurrentInstance().getExternalContext().redirect("AddProposal.xhtml");
        FacesContext.getCurrentInstance().getExternalContext().redirect("./../QuotationManagement/ChoooseEnquiry.xhtml");
    }
    
    public void createAuditoriumQuotation() throws IOException {
        quotation = new Quotation();
        Date today = new Date();
        quotation.setQuotationDate(today);
        quotation.setAuditorium(selectauditorium);
        quotation.setOpenspace(null);
        quotation.setBanquethall(null);
        quotation.setExhibitionsections(null);
        quotation.setTheater(null);
        quotation.setOthervenue(null);
        quotation.setEventDate(startingDate);
        quotation.setReplyparty("Coral Island Resort");
        int duras = (int) ((endingDate.getTime() - startingDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
        float price = selectauditorium.getDayprice() * duras;
        quotation.setVenueprice(price);
        // FacesContext.getCurrentInstance().getExternalContext().redirect("AddProposal.xhtml");
        FacesContext.getCurrentInstance().getExternalContext().redirect("./../QuotationManagement/ChoooseEnquiry.xhtml");
    }

    public void dispalyMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void searchvenue(ActionEvent event) throws IOException, ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date today = sd.parse(sd.format(new Date()));

        Calendar cal = Calendar.getInstance();
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

            case "Exhibition Hall":
                if (getDatetype().equals("day")) {
                    if (getStartingDate() != null && getEndingDate() != null) {
                        if (today.compareTo(getStartingDate()) >= 0) {
                            setStartingDate(null);
                            setEndingDate(null);
                            setDatetype(null);
                            this.dispalyMessage("Starting Date must be after Today");
                        } else {
                            if (getStartingDate().after(getEndingDate())) {
                                setEndingDate(null);
                                setStartingDate(null);
                                setDatetype(null);
                                this.dispalyMessage("startingDate cannot be before endingDate");
                            } else {
                                Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                if (getStartingDate().compareTo(ca.getTime()) > 0) {
                                    setStartingDate(null);
                                    setEndingDate(null);
                                    setDatetype(null);
                                    this.dispalyMessage("Room can only be reserved half a year ahead");
                                } else {
                                    setExhibitionsections(getVmsbl().searchExhibitionhall(getCapacity(), getStartingDate(), getEndingDate()));
                                    if (getExhibitionsections().isEmpty()) {
                                        setStartingDate(null);
                                        setEndingDate(null);
                                        setDatetype(null);
                                        this.dispalyMessage("There are no avaiable Exhibition Halls");
                                    } else {
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableExhibitionHalls.xhtml");
                                    }
                                }
                            }
                        }
                    } else {
                        setStartingDate(null);
                        setEndingDate(null);
                        setDatetype(null);
                        this.dispalyMessage("Starting Date and Ending Date cannot be empty");

                    }
                } else {
                    setStartingDate(null);
                    setEndingDate(null);
                    setDatetype(null);
                    this.dispalyMessage("Exhibition Hall can only be booked by day");
                }
                break;


            case "Auditorium":
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
                                    setAuditoriums(getVmsbl().searchAuditorium(getCapacity(), getStartingDate(), getEndingDate()));
                                    if (getAuditoriums().isEmpty()) {
                                        this.dispalyMessage("There are no avaiable Auditoriums");
                                    } else {
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableAuditoriums.xhtml");
                                    }
                                }
                            }
                        }
                    } else {
                        this.dispalyMessage("Starting Date and Ending Date cannot be empty");
                    }
                } else {
                    this.dispalyMessage("Auditorium can only be booked by day");
                }
                break;

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
                                    System.err.println("the start date is"+startingDate );
                                    System.err.println("the end date is "+endingDate);
                                    setOpenspaces(getVmsbl().searchOpenSpace(getCapacity(), getStartingDate(), getEndingDate()));
                                    if (getOpenspaces().isEmpty()) {
                                        this.dispalyMessage("There is no avaiable OpenSpace");
                                    } else {
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableOpenSpace.xhtml");
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
                                        setOthervenues(getVmsbl().searchOtherVenueDay(getCapacity(), getStartingDate(), getEndingDate(), getVenuetype()));
                                        if (getOthervenues().isEmpty()) {
                                            this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableOtherVenue.xhtml");
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
                                        setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), morningstart, morningend, getVenuetype()));
                                        if (getOthervenues().isEmpty()) {
                                            this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableOtherVenue.xhtml");
                                        }
                                    } else {
                                        setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), afternoonstart, afternoonend, getVenuetype()));
                                        if (getOthervenues().isEmpty()) {
                                            this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableOtherVenue.xhtml");
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
                                            setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), getStartTime(), getEndTime(), getVenuetype()));
                                            if (getOthervenues().isEmpty()) {
                                                this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                            } else {
                                                FacesContext.getCurrentInstance().getExternalContext().redirect("AvailableOtherVenue.xhtml");
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
}
