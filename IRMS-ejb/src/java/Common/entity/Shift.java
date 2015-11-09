/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yifeng
 */
@Entity
public class Shift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private WorkGroup workGroup = new WorkGroup();
    @ManyToOne
    private ScheduleDay scheduleDay = new ScheduleDay();
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)    
    private Date endDate = new Date();
    
    public Shift(){
        
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the scheduleDay
     */
    public ScheduleDay getScheduleDay() {
        return scheduleDay;
    }

    /**
     * @param scheduleDay the scheduleDay to set
     */
    public void setScheduleDay(ScheduleDay scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
