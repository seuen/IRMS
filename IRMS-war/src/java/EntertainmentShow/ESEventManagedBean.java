/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.DayTime;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.session.EventManagementSessionBeanLocal;
import ConventionExhibition.session.FacilityManagementSessionBeanLocal;
import ConventionExhibition.session.VenueManagementSessionBeanLocal;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.Theater;
import EntertainmentShow.session.ESInfoManagementSessionBeanLocal;
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

    
    
    /**
 *
 * @author Acer
 */
@ManagedBean
@SessionScoped
public class ESEventManagedBean {

    
    @EJB
    private VenueManagementSessionBeanLocal vmsbl;
    @EJB
    private EventManagementSessionBeanLocal emsbl;
    @EJB
    private FacilityManagementSessionBeanLocal fmsbl;
    @EJB
    private ESInfoManagementSessionBeanLocal esmsbl;
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
    private float commissionrate;
    
    
        private List<Auditorium> auditoriums = new ArrayList();
        private List<Theater> theaters=new ArrayList();
        private Theater selecttheater=new Theater();
        private Auditorium selectAuditorium=new Auditorium();
        
         private List<Client> clients = new ArrayList();
    private Client selectClient = new Client();
    private EventOrder event = new EventOrder();
    private DayTime daytime = new DayTime();
    private HalfDayTime halfdaytime = new HalfDayTime();
    private HourTime hourtime = new HourTime();
    
     private float deposit;
    private List<ConventionSchedule> schedules = new ArrayList();
    private ConventionSchedule schedule = new ConventionSchedule();
    private int createclick = 0;
    private ShowInfo showinfo=new ShowInfo();

    
    public ESEventManagedBean() {
    }
    
     public void createEvent() {
        if (createclick == 0) {
            if (getDeposit() < getEvent().getVenueprice()) {
                this.dispalyMessage("The Deposit cannot be less than venue price");
            } else {
                getEvent().setTotalcharge(getEvent().getTotalprice() - getDeposit());
                 Long eventid =getEmsbl().createEventDay(getEvent(), schedules);
                 showinfo.setCommissionFee(0f);
                 showinfo.setCommissionRate(commissionrate);
                 showinfo.setEventId(eventid);
                esmsbl.createShowInfo(startingDate, endingDate, startTime, endTime, showinfo);
                    createclick++;
                    this.dispalyMessage("event has been booked successfully");
            }
        } else {
            this.dispalyMessage("Event has been created");
        }
    }
    
     public void onEdit(RowEditEvent event) {
        Client editclient = (Client) event.getObject();
        getEmsbl().updateclient(editclient);
        this.dispalyMessage("Client Edited");
    }

    public void onCancel(RowEditEvent event) {
        this.dispalyMessage("Client Cancelled");
    }
    
    public void checkNewClient() throws IOException {
        if (getSelectClient() != null) {
            if (!emsbl.addNewClient(selectClient)) {
                setSelectClient(new Client());
                FacesContext.getCurrentInstance().getExternalContext().redirect("ESSelectClient.xhtml");
            } else {
                getEvent().setClient(getSelectClient());
                event.setFoodprice(0);
                event.setEmployeeprice(0);
                event.setFacilityprice(0);
                event.setFacilitiesNeed(null);
                event.setEmployeesNeed(null);
                event.setCreateDate(new Date());
                event.setItemorder(null);
                event.setType("Entertainment");
                FacesContext.getCurrentInstance().getExternalContext().redirect("ESAddDetail.xhtml");
            }
        }
    }
     public void setEventClient() throws IOException {
        getEvent().setClient(getSelectClient());
        getEvent().setClient(getSelectClient());
                event.setFoodprice(0);
                event.setVenueprice(0);
                event.setFacilityprice(0);
                event.setFacilitiesNeed(null);
                event.setEmployeesNeed(null);
                event.setCreateDate(new Date());
                event.setItemorder(null);
                event.setType("Entertainment");
        FacesContext.getCurrentInstance().getExternalContext().redirect("ESAddDetail.xhtml");
    }
      public void emptyselectclient() throws IOException {
        setSelectClient(new Client());
        FacesContext.getCurrentInstance().getExternalContext().redirect("ESAddClient.xhtml");
    }
   
    
    public void setEventTheater() throws IOException{
          setEvent(new EventOrder());
          showinfo=new ShowInfo();
          showinfo.setAuditorium(null);
          showinfo.setTheater(selecttheater);
        getEvent().setOpenspace(null);
        getEvent().setAuditorium(null);
        getEvent().setExhibitionsections(null);
        getEvent().setOthervenue(null);
        getEvent().setBanquethall(null);
        getEvent().setTheater(getSelecttheater());
        this.createDayTime();
        for (ConventionSchedule t : getSchedules()) {
            t.setOpenspace(null);
            t.setAuditorium(null);
            t.setBanquethall(null);
            t.setExhibitionsection(null);
            t.setOthervenue(null);
            t.setTheater(getSelecttheater());
            t.setVenuetype("Theater");
            t.setRoomnum(getSelecttheater().getRoomNo());
        }
        getEvent().setDaytime(getDaytime());
        getEvent().setHalfdaytime(null);
        getEvent().setHourtime(null);
        int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        
        getEvent().setVenueprice(dura * getSelecttheater().getDayPrice());
        getEvent().setVenuetype("Theater");
        event.setTotalprice(dura*selecttheater.getDayPrice());
        getEvent().setRoomnum(getSelecttheater().getRoomNo());
        getEvent().setCapacity(getSelecttheater().getCapacity());
        getEvent().setDatetype("Day");
        setDaytime(new DayTime());
        setSelecttheater(new Theater());

        FacesContext.getCurrentInstance().getExternalContext().redirect("ESSelectClient.xhtml");
        
    }
    
    public void setEventAuditoirum() throws IOException{
        showinfo=new ShowInfo();
        setEvent(new EventOrder());
         showinfo.setAuditorium(selectAuditorium);
         showinfo.setTheater(null);
        getEvent().setOpenspace(null);
        getEvent().setAuditorium(getSelectAuditorium());
        getEvent().setExhibitionsections(null);
        getEvent().setOthervenue(null);
        this.createDayTime();
        for (ConventionSchedule t : getSchedules()) {
            t.setOpenspace(null);
            t.setAuditorium(getSelectAuditorium());
            t.setBanquethall(null);
            t.setExhibitionsection(null);
            t.setOthervenue(null);
            t.setVenuetype("Auditorium");
            t.setRoomnum(getSelectAuditorium().getRoomNo());
        }
        getEvent().setDaytime(getDaytime());
        getEvent().setHalfdaytime(null);
        getEvent().setHourtime(null);
        int dura = (int) ((getEndingDate().getTime() - getStartingDate().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        
        getEvent().setVenueprice(dura * getSelectAuditorium().getDayprice());
        getEvent().setVenuetype("Auditorium");
        event.setTotalprice(dura*selectAuditorium.getDayprice());
        getEvent().setRoomnum(getSelectAuditorium().getRoomNo());
        getEvent().setCapacity(getSelectAuditorium().getCapacity());
        getEvent().setDatetype("Day");
        setDaytime(new DayTime());
        setSelectAuditorium(new Auditorium());

        FacesContext.getCurrentInstance().getExternalContext().redirect("ESSelectClient.xhtml");
    }

     public void createDayTime() {
        setDaytime(new DayTime());
        setSchedules((List<ConventionSchedule>) new ArrayList());
        getDaytime().setStartingDate(getStartingDate());
        getDaytime().setEndingDate(getEndingDate());
        Date tempdate = getStartingDate();
        while (!tempdate.after(endingDate)) {
            ConventionSchedule temp = new ConventionSchedule();
            temp.setEventdate(tempdate);
            temp.setDaytime(getDaytime());
            temp.setHalfdaytimes(null);
            temp.setHourtimes(null);
            getSchedules().add(temp);
            tempdate = new Date(tempdate.getTime() + (1000 * 60 * 60 * 24));
        }
    }
    
    
      public void dispalyMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     public void searchvenue(ActionEvent event) throws IOException, ParseException {
        setCreateclick(0);
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date today = sd.parse(sd.format(new Date()));


        System.err.println("the veunetype is " + getVenuetype());
        switch (getVenuetype()) {

            case "Auditorium":
             
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
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("ESAvailableAuditorium.xhtml");
                                    }
                                }
                            }
                        }
                    } else {
                        this.dispalyMessage("Starting Date and Ending Date cannot be empty");
                    }
              
                break;

                    default:
                     
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
                                    setTheaters(getVmsbl().searchTheater(getCapacity(), getStartingDate(), getEndingDate()));
                                    if (getTheaters().isEmpty()) {
                                        this.dispalyMessage("There are no avaiable Theaters");
                                    } else {
                                        setEventdate(null);
                                        setTimeslot(null);
                                        setStartTime(null);
                                        setEndTime(null);
                                        FacesContext.getCurrentInstance().getExternalContext().redirect("ESAvailableTheater.xhtml");
                                    }
                                }
                            }
                        }
                    } else {
                        this.dispalyMessage("Starting Date and Ending Date cannot be empty");
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
     * @return the createclick
     */
    public int getCreateclick() {
        return createclick;
    }

    /**
     * @param createclick the createclick to set
     */
    public void setCreateclick(int createclick) {
        this.createclick = createclick;
    }

    /**
     * @return the selecttheater
     */
    public Theater getSelecttheater() {
        return selecttheater;
    }

    /**
     * @param selecttheater the selecttheater to set
     */
    public void setSelecttheater(Theater selecttheater) {
        this.selecttheater = selecttheater;
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

    /**
     * @return the commissionrate
     */
    public float getCommissionrate() {
        return commissionrate;
    }

    /**
     * @param commissionrate the commissionrate to set
     */
    public void setCommissionrate(float commissionrate) {
        this.commissionrate = commissionrate;
    }

    /**
     * @return the showinfo
     */
    public ShowInfo getShowinfo() {
        return showinfo;
    }

    /**
     * @param showinfo the showinfo to set
     */
    public void setShowinfo(ShowInfo showinfo) {
        this.showinfo = showinfo;
    }
    }


