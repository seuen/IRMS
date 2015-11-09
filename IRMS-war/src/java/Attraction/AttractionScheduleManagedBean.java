package Attraction;

import Attraction.entity.AttraSection;
import Attraction.entity.Attraction;
import Attraction.entity.Equipment;
import Attraction.session.EquipmentManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
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
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@SessionScoped
public class AttractionScheduleManagedBean implements Serializable {

    private Attraction attraction = new Attraction();
    private List<Equipment> equipments = new ArrayList();
    private List<AttraSection> attraSections = new ArrayList();
    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleModel emm = new DefaultScheduleModel();
    private ScheduleEvent event  = new DefaultScheduleEvent();;
    
    @EJB
    private EquipmentManagementSessionBeanLocal emsbl;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    public AttractionScheduleManagedBean() {
    }

    public void setAttractionInfo(Attraction attraction) {
        this.setAttraction(attraction);
        System.out.println("inside atraSchedule" + this.getAttraction());
    }
    
    public void setEventModelInfo(ScheduleModel eventModel) {
        System.err.println("inside eventModellllllll" + this.getAttraction());
        
        for(ScheduleEvent se: eventModel.getEvents()){
            System.out.println(se);
            emm.addEvent(se);
        }
        
    }

    public ScheduleModel initMaintenanceSchedule() throws IOException {
        System.err.println("inside send maintenance");

//        event = new DefaultScheduleEvent();
//        eventModel = new DefaultScheduleModel();
        System.err.println("attraction" + getAttraction());
        for (AttraSection as : getAttraSections()) {
            System.out.println("Attraction Section " + as.getNum());
            getEventModel().addEvent(new DefaultScheduleEvent("Attraction Section " + as.getNum(), as.getNextDate(), as.getNextDate(), as));
        }
        System.err.println(eventModel);
        System.out.println(getEventModel().getEvents());
        return getEventModel();
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (getEvent().getId() == null) {
            getEventModel().addEvent(getEvent());
        } else {
            getEventModel().updateEvent(getEvent());
        }

        setEvent(new DefaultScheduleEvent());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event date updated", "Day :" + event.getDayDelta());
        addMessage(message);

    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.err.println("enter onEventSelect");
        setEvent((ScheduleEvent) selectEvent.getObject());

    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the equipments
     */
    public List<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the attraSections
     */
    public List<AttraSection> getAttraSections() {
        return attraSections;
    }

    /**
     * @param attraSections the attraSections to set
     */
    public void setAttraSections(List<AttraSection> attraSections) {
        this.attraSections = attraSections;
    }

//    public ScheduleModel initMaintenanceSchedules() {
//        System.err.println("inside equipment Maintenance Schedule ManagedBean");
//        
//        attraSections = (List<AttraSection>) ec.getFlash().get("attraSections");
//        System.out.println(attraSections);
//        
//        eventModel.addEvent(new DefaultScheduleEvent("First maintenance", new Date(), new Date(), true));
//
//        System.out.println(eventModel);
//
//        return eventModel;
//    }
//
//    public void sendMaintenanceSchedule() throws IOException {
//        System.err.println("inside send maintenance schedule");
//        System.err.println(attraction.getName());
//        
//        attraSections = emsbl.listAttraSections(attraction.getName());
//        
//        for(AttraSection as: attraSections){
//            System.out.println(as);
//        }
//        
//        if (attraction.getName() != null) {
//            ec.getFlash().put("attraSections", attraSections);
//            ec.getFlash().put("attraction", attraction);
////            ec.redirect("ViewAttractionSchedule.xhtml");
//        } else {
//            FacesMessage msg = new FacesMessage("Fail to redirect, please try again!");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the emsbl
     */
    public EquipmentManagementSessionBeanLocal getEmsbl() {
        return emsbl;
    }

    /**
     * @param emsbl the emsbl to set
     */
    public void setEmsbl(EquipmentManagementSessionBeanLocal emsbl) {
        this.emsbl = emsbl;
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
     * @return the emm
     */
    public ScheduleModel getEmm() {
        return emm;
    }

    /**
     * @param em the em to set
     */
    public void setEmm(ScheduleModel emm) {
        this.emm = emm;
    }
}