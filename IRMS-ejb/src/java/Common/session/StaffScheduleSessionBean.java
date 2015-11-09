/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Schedule;
import Common.entity.ScheduleDay;
import Common.entity.Shift;
import Common.entity.Staff;
import Common.entity.Title;
import Common.entity.WorkGroup;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yifeng
 */
@Stateless(name = "StaffScheduleSession")
public class StaffScheduleSessionBean implements StaffScheduleSessionBeanLocal, StaffScheduleSessionBeanRemote {

    @PersistenceContext
    private EntityManager em;
    private Schedule schedule;
    private int hotelPattern = 3;
    private int hotelNumberOfStaffPerShift = 2;
    private List<String> hotelShiftPatterns = new ArrayList();

    public StaffScheduleSessionBean() {
        hotelShiftPatterns.add("Two Shifts");
        hotelShiftPatterns.add("Three Shifts");
    }

    @Override
    public int updateHotelPattern(String choice) {
        try {
            if (choice.equals("Two Shifts")) {
                System.out.println("hotel pattern changed to 2");
                hotelPattern = 2;
            } else if (choice.equals("Three Shifts")) {
                System.out.println("hotel pattern changed to 3");
                hotelPattern = 3;
            } else {
                System.out.println("sth is wrong with updating hotel pattern");
                hotelPattern = 0;
            }
            return hotelPattern;
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public List<String> getHotelShiftPatterns() {
        return hotelShiftPatterns;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        try {
            Query query = em.createQuery("SELECT s FROM Schedule s");
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean deleteStaffShiftSchedule(Schedule tempSchedule) {
        try {
            tempSchedule = em.find(Schedule.class, tempSchedule.getScheduleId());
            List<ScheduleDay> tempSDays = tempSchedule.getScheduleDays();
            for (ScheduleDay tempSD : tempSDays) {
                List<Shift> tempShifts = tempSD.getShifts();
                for (Shift tempShift : tempShifts) {
                    WorkGroup tempWorkgroup = tempShift.getWorkGroup();
                    tempWorkgroup.getShifts().remove(tempShift);
                    tempShift.setWorkGroup(null);
                    tempShift.setScheduleDay(null);
                    em.remove(tempShift);
                }
                tempSD.setShifts(null);
                tempSD.setSchedule(null);
                em.remove(tempSD);
            }
            tempSchedule.setScheduleDays(null);
            em.remove(tempSchedule);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private String getScheduleId(Calendar tempCalendar, String residue1, String residue2) {
        String tempScheduleId = "" + (tempCalendar.get(Calendar.YEAR) * 100 + tempCalendar.get(Calendar.MONTH)) + "_" + residue1 + "_" + residue2;
        System.out.println("this is the tempScheduleId generated in getScheduleId() " + tempScheduleId);
        return tempScheduleId;
    }

    @Override
    public Schedule getHotelSchedule(int offset, String staffLocation, String staffPosition) {
        try {
            Calendar calendar = this.getCalendarForMonth(offset);
            String tempScheduleId = this.getScheduleId(calendar, staffLocation, staffPosition);
            schedule = em.find(Schedule.class, tempScheduleId);
            return schedule;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Schedule createNewHotelSchedule(int offset, Title criteria) {
        try {

            String titleKey = criteria.getWorkLocation() + criteria.getPosition();
            System.out.println("title key is " + titleKey);
            Title temp = em.find(Title.class, titleKey);

            //check whether schedule already exist
            Calendar calendar = this.getCalendarForMonth(offset);
            String tempScheduleId = this.getScheduleId(calendar, temp.getWorkLocation(), temp.getPosition());
            schedule = em.find(Schedule.class, tempScheduleId);

            if (schedule == null) {// schedule does not exist, create new schedule
                System.err.println("schedule does not exist");
                // instantiate new schedule
                schedule = new Schedule();
                //set basic information in schedule
                schedule.setScheduleId(tempScheduleId);
                schedule.setScheduleYear(calendar.get(Calendar.YEAR));
                schedule.setScheduleMonth((calendar.get(Calendar.MONTH) + 1));
                schedule.setPosition(temp.getPosition());
                schedule.setWorkLocation(temp.getWorkLocation());

                List<ScheduleDay> tempScheduleDays = schedule.getScheduleDays();
                int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                //fetch available workgroup
                List<WorkGroup> availableWorkGroups = this.getAvailableWorkGroup(temp);

                // set up Schedule
                for (int i = 1; i <= daysOfMonth; i++) {
                    ScheduleDay tempScheduleDay = new ScheduleDay();
                    List<Shift> tempShifts = tempScheduleDay.getShifts();

                    // set up scheduleDay
                    for (int j = 1; j < availableWorkGroups.size(); j++) {
                        Shift tempShift = new Shift();

                        for (WorkGroup workGroup : availableWorkGroups) {
                            if (workGroup.getLastShift() == j) {
                                tempShift.setWorkGroup(workGroup);
                                workGroup.getShifts().add(tempShift);
                            }
                        }



                        tempShifts.add(tempShift);
                        tempShift.setScheduleDay(tempScheduleDay);
                        if (hotelPattern == 3) {
                            tempShift.setStartDate(this.getHotelThreeShiftStartDate(j, calendar));
                            tempShift.setEndDate(this.getHotelThreeShiftEndDate(j, calendar));
                        } else if (hotelPattern == 2) {
                            tempShift.setStartDate(this.getHotelTwoShiftStartDate(j, calendar));
                            tempShift.setEndDate(this.getHotelTwoShiftEndDate(j, calendar));
                        } else {
                            System.out.println("the hotel shift pattern is not valid " + hotelPattern);
                        }
                        em.persist(tempShift);
                    }// end of setting up tempShift

                    //update last shift in workGroups
                    for (WorkGroup workGroup : availableWorkGroups) {
                        workGroup.setLastShift((workGroup.getLastShift() + 1) % availableWorkGroups.size());
                        //System.out.println("workGroup last shift is changed to " + workGroup.getLastShift());
                    }

                    tempScheduleDay.setShifts(tempShifts);
                    tempScheduleDay.setSchedule(schedule);
                    em.persist(tempScheduleDay);
                    tempScheduleDays.add(tempScheduleDay);
                    calendar.add(Calendar.DATE, 1);
                }// end of setting up scheduleDay

                schedule.setScheduleDays(tempScheduleDays);
                em.persist(schedule);
                for (WorkGroup workGroup : availableWorkGroups) {
                    em.merge(workGroup);
                }
            } // end of setting up schedule
            else {
                System.err.println("schedule already exist");
                return null;
            }

            return schedule;
        } catch (Exception ex) {
            return null;
        }
    }

    private Date getHotelTwoShiftStartDate(int shiftNo, Calendar calendar) {
        Calendar t = (Calendar) calendar.clone();
        t.set(Calendar.MINUTE, 0);
        switch (shiftNo) {
            case 1:
                t.set(Calendar.AM_PM, Calendar.AM);
                t.set(Calendar.HOUR, 8);
                break;
            case 2:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 3);
                break;
        }
        return t.getTime();
    }

    private Date getHotelTwoShiftEndDate(int shiftNo, Calendar calendar) {

        Calendar t = (Calendar) calendar.clone();
        t.set(Calendar.MINUTE, 0);
        switch (shiftNo) {
            case 1:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 4);
                break;
            case 2:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 10);
                break;
        }
        return t.getTime();
    }

    private Date getHotelThreeShiftStartDate(int shiftNo, Calendar calendar) {

        Calendar t = (Calendar) calendar.clone();
        t.set(Calendar.MINUTE, 0);
        switch (shiftNo) {
            case 1:
                t.set(Calendar.AM_PM, Calendar.AM);
                t.set(Calendar.HOUR, 6);
                break;
            case 2:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 2);
                break;
            case 3:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 10);
                break;

        }
        return t.getTime();

    }

    private Date getHotelThreeShiftEndDate(int shiftNo, Calendar calendar) {

        Calendar t = (Calendar) calendar.clone();
        t.set(Calendar.MINUTE, 0);
        switch (shiftNo) {
            case 1:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 3);
                break;
            case 2:
                t.set(Calendar.AM_PM, Calendar.PM);
                t.set(Calendar.HOUR, 11);
                break;
            case 3:
                t.set(Calendar.AM_PM, Calendar.AM);
                t.add(Calendar.DATE, 1);
                t.set(Calendar.HOUR, 7);
                break;

        }
        return t.getTime();

    }

    private List<WorkGroup> getAvailableWorkGroup(Title criteria) {

        System.err.println("enter getAvailableWorkGroup");

        //check whether workgroup exist with given title
        Query query = em.createQuery("SELECT w FROM WorkGroup w WHERE w.location = :inLocation AND w.position = :inPosition AND w.shiftPattern = :inShiftPattern");
        query.setParameter("inLocation", criteria.getWorkLocation());
        query.setParameter("inPosition", criteria.getPosition());
        query.setParameter("inShiftPattern", hotelPattern);

        List<WorkGroup> workGroups = query.getResultList();
        //workGroups already exist, check validation, if not valid create again
        if (workGroups != null) {
            System.out.println("the hotel pattern is " + hotelPattern + "number of workgroup is " + workGroups.size());
            if (workGroups.size() == (getHotelPattern() + 1)) {// if group is validated print out info and send back to caller
                for (WorkGroup workGroup : workGroups) {
                    System.out.println("workGroup information " + workGroup.getGroupName() + workGroup.getLocation() + workGroup.getPosition());
                    return workGroups;
                }
            } else {// not valid, dismiss group
                // dismiss the groups and newly instantiate workGroups
                System.out.println("the size of workgroups are wrong, dismiss workgroups");
                workGroups = new ArrayList();
            }
        }

        List<Staff> availableStaff = criteria.getStaff();
        // workGroups not valid or is not created
        for (int i = 0; i < (getHotelPattern() + 1); i++) {
            WorkGroup tempWorkGroup = new WorkGroup();
            tempWorkGroup.setGroupName("Team" + (i + 1));
            tempWorkGroup.setLastShift(i);
            tempWorkGroup.setLocation(criteria.getWorkLocation());
            tempWorkGroup.setPosition(criteria.getPosition());
            workGroups.add(tempWorkGroup);
        }

        int dem = getHotelPattern() + 1;
        for (int j = 0; j < availableStaff.size(); j++) {
            //add staff to workgroup
            workGroups.get(j % dem).getStaffCrew().add(availableStaff.get(j));
            //add workgroup to staff
            availableStaff.get(j).getWorkGroups().add(workGroups.get(j % dem));
            availableStaff.get(j).setWorkingStatus("Idle");
        }

        for (WorkGroup workGroup : workGroups) {

            //to be removed later
            List<Staff> tempStaff = workGroup.getStaffCrew();
            System.out.println("number of staff in this group " + tempStaff.size());
            for (Staff staff : tempStaff) {
                System.out.println("staff information before persisting workgroup " + staff.getFirstName() + staff.getLastName());
            }

            em.persist(workGroup);
        }
        return workGroups;
    }

    private Calendar getCalendarForMonth(int offset) {
        Calendar calendar = Calendar.getInstance();
        int calYear = calendar.get(Calendar.YEAR) + (calendar.get(Calendar.MONTH) + offset) / 12;
        int calMonth = (calendar.get(Calendar.MONTH) + offset) % 12;
        calendar.set(calYear, calMonth, 1);
        //System.err.println("maximum days in month " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //System.err.println("Calendar year " + calendar.get(Calendar.YEAR) + "month " + calendar.get(Calendar.MONTH) + "day " + calendar.get(Calendar.DAY_OF_MONTH));
        return calendar;
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
     * @return the hotelNumberOfStaffPerShift
     */
    public int getHotelNumberOfStaffPerShift() {
        return hotelNumberOfStaffPerShift;
    }

    /**
     * @param hotelNumberOfStaffPerShift the hotelNumberOfStaffPerShift to set
     */
    public void setHotelNumberOfStaffPerShift(int hotelNumberOfStaffPerShift) {
        this.hotelNumberOfStaffPerShift = hotelNumberOfStaffPerShift;
    }

    public int getHotelPattern() {
        return hotelPattern;
    }

    public void setHotelPattern(int hotelPattern) {
        this.hotelPattern = hotelPattern;
    }
}
