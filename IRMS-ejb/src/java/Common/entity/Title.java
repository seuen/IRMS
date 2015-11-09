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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author yifeng
 */
@Entity
public class Title implements Serializable {
    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    private String locationPosition;
    @ManyToMany(mappedBy="titles", cascade={CascadeType.PERSIST})
    private List<Staff> staff = new ArrayList();
    private String workLocation;
    private String workspaceUrl;
    private String position;
    private boolean shifts;
    @ManyToOne
    private Department department;  
    
    public Title(){
        
    }
    
    public Title(String locationPosition, String workLocation, String position, String workSpaceUrl, boolean shifts){
        this.locationPosition = locationPosition;
        this.workLocation = workLocation;
        this.position = position;
        this.workspaceUrl = workSpaceUrl;
        this.shifts = shifts;
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
     * @return the workspaceUrl
     */
    public String getWorkspaceUrl() {
        return workspaceUrl;
    }

    /**
     * @param workspaceUrl the workspaceUrl to set
     */
    public void setWorkspaceUrl(String workspaceUrl) {
        this.workspaceUrl = workspaceUrl;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLocationPosition() {
        return locationPosition;
    }

    public void setLocationPosition(String locationPosition) {
        this.locationPosition = locationPosition;
    }

    public boolean isShifts() {
        return shifts;
    }

    public void setShifts(boolean shifts) {
        this.shifts = shifts;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
    
}
