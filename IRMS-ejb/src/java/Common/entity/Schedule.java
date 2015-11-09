/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author yifeng
 */
@Entity
public class Schedule implements Serializable {
    
    @Id    
    private String scheduleId;
    @OneToMany(mappedBy="schedule", cascade={CascadeType.PERSIST})
    private List<ScheduleDay> scheduleDays = new ArrayList();
    private int scheduleYear;
    private int scheduleMonth;
    private String position;
    private String workLocation;
    
    public Schedule(){
        
    }

    /**
     * @return the scheduleDays
     */
    public List<ScheduleDay> getScheduleDays() {
        return scheduleDays;
    }

    /**
     * @param scheduleDays the scheduleDays to set
     */
    public void setScheduleDays(List<ScheduleDay> scheduleDays) {
        this.scheduleDays = scheduleDays;
    }

    /**
     * @return the scheduleId
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * @param scheduleId the scheduleId to set
     */
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
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

    public int getScheduleYear() {
        return scheduleYear;
    }

    public void setScheduleYear(int scheduleYear) {
        this.scheduleYear = scheduleYear;
    }

    public int getScheduleMonth() {
        return scheduleMonth;
    }

    public void setScheduleMonth(int scheduleMonth) {
        this.scheduleMonth = scheduleMonth;
    }

}
