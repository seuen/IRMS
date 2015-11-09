/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Schedule;
import Common.entity.ScheduleDay;
import Common.entity.Shift;
import Common.entity.Staff;
import Common.entity.Title;
import Common.entity.WorkGroup;
import Common.session.StaffManagementSessionBeanLocal;
import Common.session.StaffScheduleSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author yifeng
 */
@ManagedBean
@SessionScoped
public class ScheduleManagedBean {

    /**
     * Creates a new instance of ScheduleManagedBean
     */
    @EJB
    StaffScheduleSessionBeanLocal sssbl;
    @EJB
    StaffManagementSessionBeanLocal smsbl;
    @ManagedProperty(value = "#{staffManagedBean}")
    private StaffManagedBean smb;
    private String welcome = "Hello, Ms Hou Yifeng. Have a good day at work. :)";
    private String workLocation;
    private String staffposition;
    private String newHotelShiftPattern;
    private ScheduleModel eventModel;
    private ScheduleEvent event;
    private Schedule schedule;
    private List<Schedule> schedules = new ArrayList();
    private WorkGroup workGroup;
    private List<Staff> staffOnDuty = new ArrayList();
    private Date tempDate;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    //fetch from session bean later
    //private List<String> shiftsfWorkLocations;
    //private List<String> shiftsPositions;
    private List<String> hotelShiftPatterns;
    //private String[] hotelShiftPatterns = {"TwoShifts", "ThreeShifts"};
    private List<String> scheduleOffset = new ArrayList();
    private String offsetSelected;

    public ScheduleManagedBean() {
        eventModel = new DefaultScheduleModel();
        event = new DefaultScheduleEvent();
    }

    @PostConstruct
    public void init() {
        //shiftsfWorkLocations = smsbl.getShiftsWorkLocations();
        //shiftsPositions = smsbl.getShiftPositions(shiftsfWorkLocations.get(0));
        schedules = sssbl.getAllSchedules();
        hotelShiftPatterns = sssbl.getHotelShiftPatterns();
        scheduleOffset.add("Current Month");
        scheduleOffset.add("Next Month");
        scheduleOffset.add("Month After Next");
    }

    public void deleteShiftSchedule(Schedule tempSchedule) {
        sssbl.deleteStaffShiftSchedule(tempSchedule);
        schedules = sssbl.getAllSchedules();
    }

    public void setUpShiftSchedule(Schedule tempSchedule) throws IOException {
        List<ScheduleDay> tempScheduleDays = tempSchedule.getScheduleDays();
        int totalDays = tempScheduleDays.size();

        //to be removed later
        setTempDate(tempScheduleDays.get(0).getShifts().get(0).getStartDate());

        eventModel = new DefaultScheduleModel();
        for (int i = 0; i < totalDays; i++) {
            List<Shift> tempShifts = tempScheduleDays.get(i).getShifts();
            int totalShift = tempShifts.size();
            for (int j = 0; j < totalShift; j++) {
                String eventTitle = "Shift" + (j + 1) + "/" + tempShifts.get(j).getWorkGroup().getGroupName();
                event = new DefaultScheduleEvent(eventTitle, tempShifts.get(j).getStartDate(), tempShifts.get(j).getEndDate(), tempShifts.get(j).getWorkGroup());
                eventModel.addEvent(event);
            }
        }

        ec.redirect("showHotelSchedule.xhtml");
    }

//    public void retrieveShiftsPositionsForLocation() {
//        System.out.println("enter retrieveshiftsPostions " + workLocation);
//        shiftsPositions = smsbl.getShiftPositions(workLocation);
//    }

    public void retrieveSchedules() {
        schedules = sssbl.getAllSchedules();
    }

    public void createHotelSchedule() throws IOException {
        //call session bean to create new schedule and store the returned schedule in schedule
//        schedule = sssbl.createNewHotelSchedule(offset, workLocation, staffposition);

        Title titleCriteria = smb.getTitle();
        sssbl.updateHotelPattern(newHotelShiftPattern);
        System.out.println("Title get " + titleCriteria.getWorkLocation());
        System.out.println("offset Selected " + offsetSelected + "index " + scheduleOffset.indexOf(offsetSelected));
        schedule = sssbl.createNewHotelSchedule(scheduleOffset.indexOf(offsetSelected), titleCriteria);

        if (schedule == null) {
            this.displayFaceMessage("schedule has already been created");
        } else {

            this.displayFaceMessage("schedule is created successfully");
            this.retrieveSchedules();
            this.setUpShiftSchedule(schedule);
        }


    }

    public void onEventSelect(SelectEvent selectEvent) {
        System.err.println("enter onEventSelect");
        event = (ScheduleEvent) selectEvent.getObject();

        setWorkGroup((WorkGroup) event.getData());
        staffOnDuty = getWorkGroup().getStaffCrew();
        for (Staff staff : staffOnDuty) {
            System.out.println("workgroup " + getWorkGroup().getGroupName() + "staff " + staff.getFirstName() + staff.getLastName());
        }

    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setHotelShiftPattern() {
        System.out.println("hotel new shift pattern" + newHotelShiftPattern);
        sssbl.updateHotelPattern(newHotelShiftPattern);
        this.displayFaceMessage("new Hotel Shift Pattern " + newHotelShiftPattern + " recorded ");

    }

    //Getter and setter
    /**
     * @return the welcome
     */
    public String getWelcome() {
        return welcome;
    }

    /**
     * @param welcome the welcome to set
     */
    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * @return the event
     */
    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    /**
     * @return the schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the staffOnDuty
     */
    public List<Staff> getStaffOnDuty() {
        return staffOnDuty;
    }

    /**
     * @param staffOnDuty the staffOnDuty to set
     */
    public void setStaffOnDuty(List<Staff> staffOnDuty) {
        this.staffOnDuty = staffOnDuty;
    }

    /**
     * @return the workLocation
     */
    public String getWorkLocation() {
        return workLocation;
    }

    /**
     * @param workLocation the workLocation to set
     */
    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    /**
     * @return the staffposition
     */
    public String getStaffposition() {
        return staffposition;
    }

    /**
     * @param staffposition the staffposition to set
     */
    public void setStaffposition(String staffposition) {
        this.staffposition = staffposition;
    }

    /**
     * @return the workGroup
     */
    public WorkGroup getWorkGroup() {
        return workGroup;
    }

    /**
     * @param workGroup the workGroup to set
     */
    public void setWorkGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    /**
     * @return the newHotelShiftPattern
     */
    public String getNewHotelShiftPattern() {
        return newHotelShiftPattern;
    }

    /**
     * @param newHotelShiftPattern the newHotelShiftPattern to set
     */
    public void setNewHotelShiftPattern(String newHotelShiftPattern) {
        this.newHotelShiftPattern = newHotelShiftPattern;
    }

    /**
     * @return the hotelShiftPatterns
     */
    public List<String> getHotelShiftPatterns() {
        return hotelShiftPatterns;
    }

    /**
     * @param hotelShiftPatterns the hotelShiftPatterns to set
     */
    public void setHotelShiftPatterns(List<String> hotelShiftPatterns) {
        this.hotelShiftPatterns = hotelShiftPatterns;
    }

    /**
     * @return the tempDate
     */
    public Date getTempDate() {
        return tempDate;
    }

    /**
     * @param tempDate the tempDate to set
     */
    public void setTempDate(Date tempDate) {
        this.tempDate = tempDate;
    }

//    public List<String> getShiftsfWorkLocations() {
//        return shiftsfWorkLocations;
//    }
//
//    public void setShiftsfWorkLocations(List<String> shiftsfWorkLocations) {
//        this.shiftsfWorkLocations = shiftsfWorkLocations;
//    }
//
//    public List<String> getShiftsPositions() {
//        return shiftsPositions;
//    }
//
//    public void setShiftsPositions(List<String> shiftsPositions) {
//        this.shiftsPositions = shiftsPositions;
//    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public StaffManagedBean getSmb() {
        return smb;
    }

    public void setSmb(StaffManagedBean smb) {
        this.smb = smb;
    }

    public List<String> getScheduleOffset() {
        return scheduleOffset;
    }

    public void setScheduleOffset(List<String> scheduleOffset) {
        this.scheduleOffset = scheduleOffset;
    }

    public String getOffsetSelected() {
        return offsetSelected;
    }

    public void setOffsetSelected(String offsetSelected) {
        this.offsetSelected = offsetSelected;
    }
}
