/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.DayTime;
import ConventionExhibition.entity.EmployeeNeed;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.FacilityType;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import ConventionExhibition.session.EventManagementSessionBeanLocal;
import ConventionExhibition.session.FacilityManagementSessionBeanLocal;
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
import org.primefaces.event.RowEditEvent;

@ManagedBean
@SessionScoped
public class EventManagedBean {

    @EJB
    private VenueManagementSessionBeanLocal vmsbl;
    @EJB
    private EventManagementSessionBeanLocal emsbl;
    @EJB
    private FacilityManagementSessionBeanLocal fmsbl;
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
    private OpenSpace selectOpenspace = new OpenSpace();
    private OtherVenue selectOtherVenue = new OtherVenue();
    private List<ExhibitionSection> selectExhibitionSections = new ArrayList();
    private Auditorium selectAuditorium=new Auditorium();
    private List<Client> clients = new ArrayList();
    private Client selectClient = new Client();
    private EventOrder event = new EventOrder();
    private DayTime daytime = new DayTime();
    private HalfDayTime halfdaytime = new HalfDayTime();
    private HourTime hourtime = new HourTime();
    private List<String> selectfacilityTypenames = new ArrayList();
    private List<FacilityNeed> facilityNeed = new ArrayList();
    private FacilityNeed facilityneed = new FacilityNeed();
    private float facilityprice = 0;
    private List<String> employeeTitleNames = new ArrayList();
    private List<EmployeeNeed> employeeNeed = new ArrayList();
    private EmployeeNeed employeeneed = new EmployeeNeed();
    private float employeeprice = 0;
    private float deposit;
    private List<ConventionSchedule> schedules = new ArrayList();
    private ConventionSchedule schedule = new ConventionSchedule();
    private int createclick = 0;

    public EventManagedBean() {
    }

    public void skipaddfacility() throws IOException {
        event.setFacilitiesNeed(null);
        event.setFacilityprice(0);
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddEmployee.xhtml");
    }

    public void skipaddemployee() throws IOException {
        event.setEmployeeprice(0);
        event.setEmployeesNeed(null);
        float totalprice = getEvent().getFacilityprice() + getEvent().getEmployeeprice() + getEvent().getVenueprice();
        System.err.println("total price is " + totalprice);
        getEvent().setTotalprice(totalprice);
        getEvent().setFoodprice(0);
        getEvent().setCreateDate(new Date());
        getEvent().setType("ConventionExhibition");
        getEvent().setStatus("Before");
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmEventDetails.xhtml");
    }

    public void createEvent() {
        if (createclick == 0) {
            if (getDeposit() < getEvent().getVenueprice()) {
                this.dispalyMessage("The Deposit cannot be less than venue price");
            } else {
                getEvent().setTotalcharge(getEvent().getTotalprice() - getDeposit());
                if (event.getDaytime() != null || event.getExhibitionsections() != null) {
                    getEmsbl().createEventDay(getEvent(), schedules);
                    createclick++;
                    this.dispalyMessage("event has been booked successfully");
                } else {
                    emsbl.createEvent(event, schedule);
                    createclick++;
                    this.dispalyMessage("event has been booked successfully");
                }
                

            }
        } else {
            this.dispalyMessage("Event has been created");
        }
    }

    public void finishAddEmployeeNeed() throws IOException {
        int i = 0;
        setEmployeeprice(getEmployeeprice() + getEmployeeneed().getPrice());
        for (EmployeeNeed en : getEmployeeNeed()) {
            if (en.getType().equals(getEmployeeneed().getType())) {
                i++;
                en.setAmount(en.getAmount() + getEmployeeneed().getAmount());
                en.setPrice(en.getPrice() + getEmployeeneed().getPrice());
            }
        }
        if (i == 0) {
            getEmployeeNeed().add(getEmployeeneed());
        }
        setEmployeeneed(new EmployeeNeed());
        getEvent().setEmployeesNeed(getEmployeeNeed());
        getEvent().setEmployeeprice(getEmployeeprice());
        float totalprice = getEvent().getFacilityprice() + getEvent().getEmployeeprice() + getEvent().getVenueprice();
        System.err.println("total price is " + totalprice);
        getEvent().setTotalprice(totalprice);
        getEvent().setFoodprice(0);
        getEvent().setCreateDate(new Date());
        getEvent().setType("ConventionExhibition");
        getEvent().setStatus("Before");
        setEmployeeNeed((List<EmployeeNeed>) new ArrayList());
        setEmployeeprice(0);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ConfirmEventDetails.xhtml");
    }

    public void addoneemployeeneed() throws IOException {
        int i = 0;
        setEmployeeprice(getEmployeeprice() + getEmployeeneed().getPrice());
        for (EmployeeNeed en : getEmployeeNeed()) {
            if (en.getType().equals(getEmployeeneed().getType())) {
                i++;
                en.setAmount(en.getAmount() + getEmployeeneed().getAmount());
                en.setPrice(en.getPrice() + getEmployeeneed().getPrice());
            }
        }
        if (i == 0) {
            getEmployeeNeed().add(getEmployeeneed());
        }
        setEmployeeneed(new EmployeeNeed());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddEmployee.xhtml");
    }

    public void finishAddFacility() throws IOException {
        int i = 0;
        float total = getFacilityneed().getAmount() * getFacilityneed().getPrice();
        getFacilityneed().setTotalprice(total);
        setFacilityprice(getFacilityprice() + total);
        for (FacilityNeed fn : getFacilityNeed()) {
            if (fn.getType().equals(getFacilityneed().getType())) {
                i++;
                fn.setAmount(fn.getAmount() + getFacilityneed().getAmount());
                fn.setTotalprice(fn.getTotalprice() + getFacilityneed().getTotalprice());
            }
        }
        if (i == 0) {
            getFacilityNeed().add(getFacilityneed());
        }
        setFacilityneed(new FacilityNeed());
        getEvent().setFacilitiesNeed(getFacilityNeed());
        getEvent().setFacilityprice(getFacilityprice());
        setFacilityNeed((List<FacilityNeed>) new ArrayList());
        setFacilityprice(0);
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddEmployee.xhtml");
    }

    public void addonefacilityneed() throws IOException {
        int i = 0;
        float total = getFacilityneed().getAmount() * getFacilityneed().getPrice();
        getFacilityneed().setTotalprice(total);
        setFacilityprice(getFacilityprice() + total);
        for (FacilityNeed fn : getFacilityNeed()) {
            if (fn.getType().equals(getFacilityneed().getType())) {
                i++;
                fn.setAmount(fn.getAmount() + getFacilityneed().getAmount());
                fn.setTotalprice(fn.getTotalprice() + getFacilityneed().getTotalprice());
            }
        }
        if (i == 0) {
            getFacilityNeed().add(getFacilityneed());
        }
        setFacilityneed(new FacilityNeed());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddFacility.xhtml");
    }

    public void setfacilitneedPrice() {
        String type = getFacilityneed().getType();
        getFacilityneed().setPrice(getFmsbl().findfacilityType(type).getPrice());
    }

    public Date sethalfdaytimemorstart() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventdate);
        ca.set(Calendar.HOUR_OF_DAY, 9);
        ca.set(Calendar.MINUTE, 0);
        return ca.getTime();
    }

    public Date sethalfdaytimemorend() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventdate);
        ca.set(Calendar.HOUR_OF_DAY, 13);
        ca.set(Calendar.MINUTE, 0);
        return ca.getTime();
    }

    public Date sethalfdaytimeaftstart() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventdate);
        ca.set(Calendar.HOUR_OF_DAY, 15);
        ca.set(Calendar.MINUTE, 0);
        return ca.getTime();
    }

    public Date sethalfdaytimeaftend() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventdate);
        ca.set(Calendar.HOUR_OF_DAY, 21);
        ca.set(Calendar.MINUTE, 0);
        return ca.getTime();
    }

    public void sethourdaytime() {
        Calendar ca = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        ca.setTime(eventdate);
        cal.setTime(startTime);
        cal.set(Calendar.YEAR, ca.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, ca.get(Calendar.MONTH));
        cal.set(Calendar.DATE, ca.get(Calendar.DATE));
        startTime = cal.getTime();
        System.err.println("the startitme is " + startTime);
        cal.setTime(endTime);
        cal.set(Calendar.YEAR, ca.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, ca.get(Calendar.MONTH));
        cal.set(Calendar.DATE, ca.get(Calendar.DATE));
        endTime = cal.getTime();
        System.err.println("the endtime is " + endTime);
    }

    public void createDayTime() {
        daytime = new DayTime();
        schedules = new ArrayList();
        getDaytime().setStartingDate(getStartingDate());
        getDaytime().setEndingDate(getEndingDate());
        Date tempdate = startingDate;
        while (!tempdate.after(endingDate)) {
            ConventionSchedule temp = new ConventionSchedule();
            temp.setEventdate(tempdate);
            temp.setDaytime(daytime);
            temp.setHalfdaytimes(null);
            temp.setHourtimes(null);
            schedules.add(temp);
            tempdate = new Date(tempdate.getTime() + (1000 * 60 * 60 * 24));
        }
    }

    public void createHalfDayTime() {
        List<HalfDayTime> hdtimes = new ArrayList();
        halfdaytime = new HalfDayTime();
        schedule = new ConventionSchedule();
        halfdaytime.setEventdate(eventdate);
        halfdaytime.setEventPeriod(timeslot);
        if (timeslot.equals("morning")) {
            System.err.println("go into serstarttime endtime in half day");
            halfdaytime.setStartTime(this.sethalfdaytimemorstart());
            halfdaytime.setEndTime(this.sethalfdaytimemorend());
        } else {
            System.err.println("go into set startime end time in halfday");
            halfdaytime.setStartTime(this.sethalfdaytimeaftstart());
            halfdaytime.setEndTime(this.sethalfdaytimeaftend());
        }
        schedule.setEventdate(eventdate);
        hdtimes.add(halfdaytime);
        schedule.setDaytime(null);
        schedule.setHalfdaytimes(hdtimes);
        schedule.setHourtimes(null);
    }

    public void createHourTime() {
        this.sethourdaytime();
        List<HourTime> htimes = new ArrayList();
        hourtime = new HourTime();
        schedule = new ConventionSchedule();
        hourtime.setEventDate(eventdate);
        hourtime.setStartTime(startTime);
        hourtime.setEndTime(endTime);
        schedule.setEventdate(eventdate);
        htimes.add(hourtime);
        schedule.setHalfdaytimes(null);
        schedule.setHourtimes(htimes);
        schedule.setDaytime(null);
    }

    public void setEventOtherVenue() throws IOException {
        System.err.println("go into seteventother venue");
        event = new EventOrder();
        event.setOpenspace(null);
        event.setAuditorium(null);
        event.setExhibitionsections(null);
        event.setOthervenue(selectOtherVenue);
        event.setBanquethall(null);
        event.setTheater(null);
        switch (datetype) {
            case "day":
                this.createDayTime();
                for (ConventionSchedule t : schedules) {
                    t.setOpenspace(null);
                    t.setAuditorium(null);
                    t.setBanquethall(null);
                    t.setExhibitionsection(null);
                    t.setTheater(null);
                    t.setOthervenue(selectOtherVenue);
                    t.setVenuetype(selectOtherVenue.getType());
                    t.setRoomnum(selectOtherVenue.getRoomNo());
                }
                getEvent().setDaytime(getDaytime());
                getEvent().setHalfdaytime(null);
                getEvent().setHourtime(null);
                int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
                getEvent().setVenueprice(dura * selectOtherVenue.getDayprice());
                getEvent().setVenuetype(selectOtherVenue.getType());
                getEvent().setRoomnum(selectOtherVenue.getRoomNo());
                getEvent().setCapacity(selectOtherVenue.getCapacity());
                getEvent().setDatetype("Day");
                setDaytime(new DayTime());
                break;
            case "halfday":
                schedule = emsbl.checkEventdatescheduleOtherVenue(selectOtherVenue, eventdate);
                if (schedule != null) {
                    halfdaytime = new HalfDayTime();
                    halfdaytime.setEventdate(eventdate);
                    halfdaytime.setEventPeriod(timeslot);
                    System.err.println("the timeslot is " + timeslot);
                    if (timeslot.equals("morning")) {
                        System.err.println("go into serstarttime endtime in half day");
                        halfdaytime.setStartTime(this.sethalfdaytimemorstart());
                        halfdaytime.setEndTime(this.sethalfdaytimemorend());
                    } else {
                        System.err.println("go into set startime end time in halfday");
                        halfdaytime.setStartTime(this.sethalfdaytimeaftstart());
                        halfdaytime.setEndTime(this.sethalfdaytimeaftend());
                    }
                    List<HalfDayTime> hdtimes = schedule.getHalfdaytimes();
                    if (hdtimes != null) {
                        hdtimes.add(halfdaytime);
                    } else {
                        hdtimes = new ArrayList();
                        hdtimes.add(halfdaytime);
                    }
                    schedule.setHalfdaytimes(hdtimes);
                } else {
                    this.createHalfDayTime();
                }
                schedule.setAuditorium(null);
                schedule.setBanquethall(null);
                schedule.setExhibitionsection(null);
                schedule.setOpenspace(null);
                schedule.setTheater(null);
                schedule.setOthervenue(selectOtherVenue);
                event.setHalfdaytime(halfdaytime);
                event.setDaytime(null);
                event.setHourtime(null);
                event.setVenueprice(selectOtherVenue.getHalfdayprice());
                event.setVenuetype(selectOtherVenue.getType());
                event.setRoomnum(selectOtherVenue.getRoomNo());
                event.setCapacity(selectOtherVenue.getCapacity());
                event.setDatetype("HalfDay");
                halfdaytime = new HalfDayTime();
                break;
            default:
                this.sethourdaytime();
                schedule = emsbl.checkEventdatescheduleOtherVenue(selectOtherVenue, eventdate);
                if (schedule != null) {
                    hourtime = new HourTime();
                    hourtime.setEventDate(eventdate);
                    hourtime.setStartTime(startTime);
                    hourtime.setEndTime(endTime);
                    List<HourTime> hdtimes = schedule.getHourtimes();
                    if (hdtimes != null) {
                        hdtimes.add(hourtime);
                    } else {
                        hdtimes = new ArrayList();
                        hdtimes.add(hourtime);
                    }
                    schedule.setHourtimes(hdtimes);
                } else {
                    this.createHourTime();
                }
                schedule.setAuditorium(null);
                schedule.setBanquethall(null);
                schedule.setExhibitionsection(null);
                schedule.setOpenspace(null);
                schedule.setTheater(null);
                schedule.setOthervenue(selectOtherVenue);
                event.setHalfdaytime(null);
                event.setDaytime(null);
                event.setHourtime(hourtime);
                int duration;
                duration = (int) ((getEndTime().getTime() - getStartTime().getTime()) / (1000 * 60 * 60));
                event.setVenueprice(duration * selectOtherVenue.getHourprice());
                event.setVenuetype(selectOtherVenue.getType());
                event.setRoomnum(selectOtherVenue.getRoomNo());
                event.setCapacity(selectOtherVenue.getCapacity());
                event.setDatetype("Hour");
                halfdaytime = new HalfDayTime();
                break;
        }
        selectOtherVenue = new OtherVenue();
        FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectClient.xhtml");
    }

    public void setEventOpenSpace() throws IOException {
        event = new EventOrder();
        getEvent().setOpenspace(getSelectOpenspace());
        getEvent().setAuditorium(null);
        getEvent().setExhibitionsections(null);
        getEvent().setOthervenue(null);
        event.setBanquethall(null);
        event.setTheater(null);
        this.createDayTime();
        for (ConventionSchedule t : schedules) {
            t.setOpenspace(selectOpenspace);
            t.setAuditorium(null);
            t.setBanquethall(null);
            t.setExhibitionsection(null);
            t.setOthervenue(null);
            t.setTheater(null);
            t.setVenuetype("Open Space");
            t.setRoomnum(selectOpenspace.getLocation());
        }
        getEvent().setDaytime(getDaytime());
        getEvent().setHalfdaytime(null);
        getEvent().setHourtime(null);
        int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        getEvent().setVenueprice(dura * getSelectOpenspace().getDayprice());
        getEvent().setVenuetype("Open Space");
        getEvent().setRoomnum(getSelectOpenspace().getLocation());
        getEvent().setCapacity(getSelectOpenspace().getCapacity());
        getEvent().setDatetype("Day");
        setDaytime(new DayTime());
        setSelectOpenspace(new OpenSpace());

        FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectClient.xhtml");
    }
    
    public void setEventAuditoirum() throws IOException{
         event = new EventOrder();
        getEvent().setOpenspace(null);
        event.setBanquethall(null);
        event.setTheater(null);
        getEvent().setAuditorium(selectAuditorium);
        getEvent().setExhibitionsections(null);
        getEvent().setOthervenue(null);
        this.createDayTime();
        for (ConventionSchedule t : schedules) {
            t.setOpenspace(null);
            t.setAuditorium(selectAuditorium);
            t.setBanquethall(null);
            t.setExhibitionsection(null);
            t.setOthervenue(null);
            t.setTheater(null);
            t.setVenuetype("Auditorium");
            t.setRoomnum(selectAuditorium.getRoomNo());
        }
        getEvent().setDaytime(getDaytime());
        getEvent().setHalfdaytime(null);
        getEvent().setHourtime(null);
        int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        
        getEvent().setVenueprice(dura * selectAuditorium.getDayprice());
        getEvent().setVenuetype("Auditorium");
        getEvent().setRoomnum(selectAuditorium.getRoomNo());
        getEvent().setCapacity(selectAuditorium.getCapacity());
        getEvent().setDatetype("Day");
        setDaytime(new DayTime());
        selectAuditorium=new Auditorium();

        FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectClient.xhtml");
    }

    public void setExhibitionSections() throws IOException {
        event = new EventOrder();
        int j = 0;
        for (ExhibitionSection es : selectExhibitionSections) {
            int i = 0;
            for (ExhibitionSection belongsection : es.getNeigbours()) {
                if (selectExhibitionSections.contains(belongsection)) {
                    i++;
                }
            }
            if (i == 0) {
                j++;
            }
        }
        if (j != 0&&selectExhibitionSections.size()>1) {
            this.dispalyMessage("Chosen Exhibition Sections are not together.Please choose again!");
        } else {
            float exhibitionvenueprice=0;
            int exhibitioncapacity=0;
            String exhibitionroomnum=" ";
  
            System.err.println("in managed bean the selectexhibitionSections are "+selectExhibitionSections);
            getEvent().setExhibitionsections(selectExhibitionSections);
            getEvent().setAuditorium(null);
            getEvent().setOthervenue(null);
            event.setOpenspace(null);
            event.setTheater(null);
            event.setBanquethall(null);

            daytime = new DayTime();
            schedules = new ArrayList();
            getDaytime().setStartingDate(getStartingDate());
            getDaytime().setEndingDate(getEndingDate());

            for (ExhibitionSection esection : selectExhibitionSections) {
                List<ConventionSchedule> esschedules=new ArrayList();
                Date tempdate = startingDate;
                while (!tempdate.after(endingDate)) {
                    ConventionSchedule temp = new ConventionSchedule();
                    temp.setEventdate(tempdate);
                    temp.setDaytime(daytime);
                    temp.setHalfdaytimes(null);
                    temp.setHourtimes(null);
                    esschedules.add(temp);
                    tempdate = new Date(tempdate.getTime() + (1000 * 60 * 60 * 24));
                }
                for (ConventionSchedule t : esschedules) {
                    t.setOpenspace(null);
                    t.setAuditorium(null);
                    t.setBanquethall(null);
                    t.setTheater(null);
                    t.setExhibitionsection(esection);
                    t.setOthervenue(null);
                    t.setVenuetype("Exhibition Hall");
                    t.setRoomnum(esection.getExhibitionhall().getRoomNo());
                }
                schedules.addAll(esschedules);
                getEvent().setDaytime(getDaytime());
                getEvent().setHalfdaytime(null);
                getEvent().setHourtime(null);
                int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
                exhibitionvenueprice+=dura*esection.getDayprice();
                exhibitioncapacity+=esection.getCapacity();
                exhibitionroomnum+=esection.getExhibitionhall().getRoomNo()+" "+esection.getSectionNo()+"; ";
            }
                getEvent().setVenueprice(exhibitionvenueprice);
                getEvent().setVenuetype("Exhibition Hall");
                getEvent().setRoomnum(exhibitionroomnum);
                getEvent().setCapacity(exhibitioncapacity);
                getEvent().setDatetype("Day");
                setDaytime(new DayTime());
                selectExhibitionSections=new ArrayList();
                FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectClient.xhtml");
            
        }
    }

    public void setEventClient() throws IOException {
        getEvent().setClient(getSelectClient());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddFacility.xhtml");
    }

    public void onEdit(RowEditEvent event) {
        Client editclient = (Client) event.getObject();
        getEmsbl().updateclient(editclient);
        this.dispalyMessage("Client Edited");
    }

    public void onCancel(RowEditEvent event) {
        this.dispalyMessage("Client Cancelled");
    }

    public void emptyselectclient() throws IOException {
        setSelectClient(new Client());
        FacesContext.getCurrentInstance().getExternalContext().redirect("AddClient.xhtml");
    }

    public void checkNewClient() throws IOException {
        if (getSelectClient() != null) {
            if (!emsbl.addNewClient(selectClient)) {
                setSelectClient(new Client());
                FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectClient.xhtml");
            } else {
                getEvent().setClient(getSelectClient());
                FacesContext.getCurrentInstance().getExternalContext().redirect("AddFacility.xhtml");
            }
        }
    }

    public void dispalyMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void searchvenue(ActionEvent event) throws IOException, ParseException {
        createclick = 0;
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date today = sd.parse(sd.format(new Date()));


        System.err.println("the veunetype is " + venuetype);
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
                                        setEventdate(null);
                                        setTimeslot(null);
                                        setStartTime(null);
                                        setEndTime(null);
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableExhibitionSections.xhtml");
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
                                        setEventdate(null);
                                        setTimeslot(null);
                                        setStartTime(null);
                                        setEndTime(null);
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableAuditoriums.xhtml");
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
                System.err.println("go into openspace");
                System.err.println("the date type is " + getDatetype());
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
                                        setEventdate(null);
                                        setTimeslot(null);
                                        setStartTime(null);
                                        setEndTime(null);
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableOpenSpace.xhtml");
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
                System.err.println("go into open space");
                System.err.println("the datetype is " + datetype);
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
                                            setEventdate(null);
                                            setTimeslot(null);
                                            setStartTime(null);
                                            setEndTime(null);
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableOtherVenue.xhtml");
                                        }
                                    }
                                }
                            }
                        } else {
                            this.dispalyMessage("Starting Date and Ending Date cannot be empty");
                        }
                        break;
                    case "halfday":
                        System.err.println("go into eventmanagebean search venue");
                        System.err.println("the timeslot is " + timeslot);
                        System.err.println("the evetn date is " + eventdate);

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
                                    if (getTimeslot().equals("morning")) {
                                        setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), morningstart, morningend, getVenuetype()));
                                        if (getOthervenues().isEmpty()) {
                                            this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                        } else {
                                            setStartTime(null);
                                            setEndTime(null);
                                            setStartingDate(null);
                                            setEndingDate(null);
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableOtherVenue.xhtml");
                                        }
                                    } else {
                                        setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), afternoonstart, afternoonend, getVenuetype()));
                                        if (getOthervenues().isEmpty()) {
                                            this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                        } else {
                                            FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableOtherVenue.xhtml");
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
                                        if ((getEndTime().getTime() - getStartTime().getTime()) / (1000 * 60 * 60) < 2) {
                                            this.dispalyMessage("The period cannot be less than 2 hours");
                                        } else {
                                            System.err.println("before go to session bean in othervenuesearch eventmanagedbean");
                                            this.sethourdaytime();
                                            setOthervenues(getVmsbl().searchOtherVenueHour(getCapacity(), getEventdate(), getStartTime(), getEndTime(), getVenuetype()));
                                            if (getOthervenues().isEmpty()) {
                                                this.dispalyMessage("There are no avaiable " + getVenuetype() + "s");
                                            } else {
                                                setStartingDate(null);
                                                setEndingDate(null);
                                                setTimeslot(null);
                                                FacesContext.getCurrentInstance().getExternalContext().redirect("EventAvailableOtherVenue.xhtml");
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
     * @return the selectOpenspace
     */
    public OpenSpace getSelectOpenspace() {
        return selectOpenspace;
    }

    /**
     * @param selectOpenspace the selectOpenspace to set
     */
    public void setSelectOpenspace(OpenSpace selectOpenspace) {
        this.selectOpenspace = selectOpenspace;
    }

    /**
     * @return the clients
     */
    public List<Client> getClients() {
        clients = getEmsbl().getAllclient();
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * @return the selectClient
     */
    public Client getSelectClient() {
        return selectClient;
    }

    /**
     * @param selectClient the selectClient to set
     */
    public void setSelectClient(Client selectClient) {
        this.selectClient = selectClient;
    }

    /**
     * @return the emsbl
     */
    public EventManagementSessionBeanLocal getEmsbl() {
        return emsbl;
    }

    /**
     * @param emsbl the emsbl to set
     */
    public void setEmsbl(EventManagementSessionBeanLocal emsbl) {
        this.emsbl = emsbl;
    }

    /**
     * @return the event
     */
    public EventOrder getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(EventOrder event) {
        this.event = event;
    }

    /**
     * @return the daytime
     */
    public DayTime getDaytime() {
        return daytime;
    }

    /**
     * @param daytime the daytime to set
     */
    public void setDaytime(DayTime daytime) {
        this.daytime = daytime;
    }

    /**
     * @return the selectfacilityTypenames
     */
    public List<String> getSelectfacilityTypenames() {
        selectfacilityTypenames = new ArrayList();
        List<FacilityType> fts = getFmsbl().getAllFacilityType();
        for (FacilityType currentft : fts) {
            selectfacilityTypenames.add(currentft.getType());
        }
        return selectfacilityTypenames;
    }

    /**
     * @param selectfacilityTypenames the selectfacilityTypenames to set
     */
    public void setSelectfacilityTypenames(List<String> selectfacilityTypenames) {
        this.selectfacilityTypenames = selectfacilityTypenames;
    }

    /**
     * @return the fmsbl
     */
    public FacilityManagementSessionBeanLocal getFmsbl() {
        return fmsbl;
    }

    /**
     * @param fmsbl the fmsbl to set
     */
    public void setFmsbl(FacilityManagementSessionBeanLocal fmsbl) {
        this.fmsbl = fmsbl;
    }

    /**
     * @return the facilityNeed
     */
    public List<FacilityNeed> getFacilityNeed() {
        return facilityNeed;
    }

    /**
     * @param facilityNeed the facilityNeed to set
     */
    public void setFacilityNeed(List<FacilityNeed> facilityNeed) {
        this.facilityNeed = facilityNeed;
    }

    /**
     * @return the facilityneed
     */
    public FacilityNeed getFacilityneed() {
        return facilityneed;
    }

    /**
     * @param facilityneed the facilityneed to set
     */
    public void setFacilityneed(FacilityNeed facilityneed) {
        this.facilityneed = facilityneed;
    }

    /**
     * @return the facilityprice
     */
    public float getFacilityprice() {
        return facilityprice;
    }

    /**
     * @param facilityprice the facilityprice to set
     */
    public void setFacilityprice(float facilityprice) {
        this.facilityprice = facilityprice;
    }

    /**
     * @return the employeeTitleNames
     */
    public List<String> getEmployeeTitleNames() {
        employeeTitleNames = new ArrayList();
        employeeTitleNames.add("Security");
        employeeTitleNames.add("Usher");
        employeeTitleNames.add("Emcee");
        employeeTitleNames.add("IT personnel");
        employeeTitleNames.add("Audio Visual");
        return employeeTitleNames;
    }

    /**
     * @param employeeTitleNames the employeeTitleNames to set
     */
    public void setEmployeeTitleNames(List<String> employeeTitleNames) {
        this.employeeTitleNames = employeeTitleNames;
    }

    /**
     * @return the employeeNeed
     */
    public List<EmployeeNeed> getEmployeeNeed() {
        return employeeNeed;
    }

    /**
     * @param employeeNeed the employeeNeed to set
     */
    public void setEmployeeNeed(List<EmployeeNeed> employeeNeed) {
        this.employeeNeed = employeeNeed;
    }

    /**
     * @return the employeeneed
     */
    public EmployeeNeed getEmployeeneed() {
        return employeeneed;
    }

    /**
     * @param employeeneed the employeeneed to set
     */
    public void setEmployeeneed(EmployeeNeed employeeneed) {
        this.employeeneed = employeeneed;
    }

    /**
     * @return the employeeprice
     */
    public float getEmployeeprice() {
        return employeeprice;
    }

    /**
     * @param employeeprice the employeeprice to set
     */
    public void setEmployeeprice(float employeeprice) {
        this.employeeprice = employeeprice;
    }

    /**
     * @return the deposit
     */
    public float getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the schedules
     */
    public List<ConventionSchedule> getSchedules() {
        return schedules;
    }

    /**
     * @param schedules the schedules to set
     */
    public void setSchedules(List<ConventionSchedule> schedules) {
        this.schedules = schedules;
    }

    /**
     * @return the schedule
     */
    public ConventionSchedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(ConventionSchedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the selectOtherVenue
     */
    public OtherVenue getSelectOtherVenue() {
        return selectOtherVenue;
    }

    /**
     * @param selectOtherVenue the selectOtherVenue to set
     */
    public void setSelectOtherVenue(OtherVenue selectOtherVenue) {
        this.selectOtherVenue = selectOtherVenue;
    }

    /**
     * @return the halfdaytime
     */
    public HalfDayTime getHalfdaytime() {
        return halfdaytime;
    }

    /**
     * @param halfdaytime the halfdaytime to set
     */
    public void setHalfdaytime(HalfDayTime halfdaytime) {
        this.halfdaytime = halfdaytime;
    }

    /**
     * @return the hourtime
     */
    public HourTime getHourtime() {
        return hourtime;
    }

    /**
     * @param hourtime the hourtime to set
     */
    public void setHourtime(HourTime hourtime) {
        this.hourtime = hourtime;
    }

    /**
     * @return the selectExhibitionSections
     */
    public List<ExhibitionSection> getSelectExhibitionSections() {
        return selectExhibitionSections;
    }

    /**
     * @param selectExhibitionSections the selectExhibitionSections to set
     */
    public void setSelectExhibitionSections(List<ExhibitionSection> selectExhibitionSections) {
        this.selectExhibitionSections = selectExhibitionSections;
    }

    /**
     * @return the selectAuditorium
     */
    public Auditorium getSelectAuditorium() {
        return selectAuditorium;
    }

    /**
     * @param selectAuditorium the selectAuditorium to set
     */
    public void setSelectAuditorium(Auditorium selectAuditorium) {
        this.selectAuditorium = selectAuditorium;
    }
}
