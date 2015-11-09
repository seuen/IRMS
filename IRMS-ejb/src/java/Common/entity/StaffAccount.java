/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.entity;

import CRM.entity.CRMreport;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author yifeng
 */
@Entity
public class StaffAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long staffId;
    private String staffPassword;    
    private boolean frozen = false;
    
    @OneToOne(mappedBy="staffAccount")
    private Staff staff;
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "staffAccount")
    private List<Message> messages = new ArrayList();
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "staffAccount")
    private List<Announcement> announcements = new ArrayList();
    @OneToMany(mappedBy = "sender")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "staffAccount")
    private List<NotificationRecord> notificationRecords;
    @OneToMany
    private List<Task> tasks = new ArrayList();
    @OneToMany(mappedBy = "staffAccount")
    private List<CRMreport> cRMreports;

    
    public StaffAccount() throws NoSuchAlgorithmException{
        
    }

    /**
     * @return the staffId
     */
    public Long getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }


    /**
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }     

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * @return the announcements
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * @param announcements the announcements to set
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * @return the frozen
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * @param frozen the frozen to set
     */
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<NotificationRecord> getNotificationRecords() {
        return notificationRecords;
    }

    public void setNotificationRecords(List<NotificationRecord> notificationRecords) {
        this.notificationRecords = notificationRecords;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the cRMreports
     */
    public List<CRMreport> getcRMreports() {
        return cRMreports;
    }

    /**
     * @param cRMreports the cRMreports to set
     */
    public void setcRMreports(List<CRMreport> cRMreports) {
        this.cRMreports = cRMreports;
    }

  
}
