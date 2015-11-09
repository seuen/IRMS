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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yifeng
 */
@Entity
public class ScheduleDay implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy="scheduleDay", cascade={CascadeType.PERSIST})
    private List<Shift> shifts = new ArrayList<Shift>();
    @ManyToOne
    private Schedule schedule = new Schedule();
    @Temporal(value = TemporalType.DATE)
    private Date dateOfSchedule;
    
    public ScheduleDay(){
        
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
     * @return the shifts
     */
    public List<Shift> getShifts() {
        return shifts;
    }

    /**
     * @param shifts the shifts to set
     */
    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
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
     * @return the dateOfSchedule
     */
    public Date getDateOfSchedule() {
        return dateOfSchedule;
    }

    /**
     * @param dateOfSchedule the dateOfSchedule to set
     */
    public void setDateOfSchedule(Date dateOfSchedule) {
        this.dateOfSchedule = dateOfSchedule;
    }

    
}
