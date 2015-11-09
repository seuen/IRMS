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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author yifeng
 */
@Entity
public class WorkGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int lastShift;
    private String groupName;
    private String position;
    private String location;
    private int shiftPattern;
    
    @OneToMany
    private List<Shift> shifts = new ArrayList<Shift>();
    @ManyToMany(mappedBy="workGroups", cascade={CascadeType.PERSIST})
    private List<Staff> staffCrew = new ArrayList<Staff>();
 
    
    
    public WorkGroup(){
        
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
     * @return the staffCrew
     */
    public List<Staff> getStaffCrew() {
        return staffCrew;
    }

    /**
     * @param staffCrew the staffCrew to set
     */
    public void setStaffCrew(List<Staff> staffCrew) {
        this.staffCrew = staffCrew;
    }

    /**
     * @return the lastShift
     */
    public int getLastShift() {
        return lastShift;
    }

    /**
     * @param lastShift the lastShift to set
     */
    public void setLastShift(int lastShift) {
        this.lastShift = lastShift;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public int getShiftPattern() {
        return shiftPattern;
    }

    public void setShiftPattern(int shiftPattern) {
        this.shiftPattern = shiftPattern;
    }

}
