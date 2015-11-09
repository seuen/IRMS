/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Schedule;
import Common.entity.Title;
import java.util.List;
import javax.ejb.Local;

/**
 * 
 * @author yifeng
 */
@Local
public interface StaffScheduleSessionBeanLocal {
    public Schedule createNewHotelSchedule(int offset, Title titleCriteria);
    public Schedule getHotelSchedule(int offset,String staffLocation, String staffPosition);
    public List<Schedule> getAllSchedules();
    public boolean deleteStaffShiftSchedule(Schedule tempSchedule);
    public List<String> getHotelShiftPatterns();
    public int updateHotelPattern(String choice);
}
