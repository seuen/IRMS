/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Announcement;
import Common.entity.Department;
import Common.entity.Message;
import Common.entity.StaffAccount;
import Common.session.StaffMessageSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@ViewScoped
public class MessageManagedBean {

    @EJB
    private StaffMessageSessionBeanLocal smsbl;
    private List<Message> allMessage = new ArrayList<Message>();
    private List<Message> unreadMessage = new ArrayList<Message>();
    private List<Announcement> allAnnouncement = new ArrayList<Announcement>();
    private List<Announcement> todayAnnouncement = new ArrayList<Announcement>();
    private Message message = new Message();
    private Announcement announcement = new Announcement();
    private StaffAccount staffAccount;
    private Department department = new Department();
    private Message selectedMessage;
    private Message selectedUnreadMessage;
    private Announcement selectedAnnouncement;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of MessageManagedBean
     */
    public MessageManagedBean() {
    }

    public void SendMessage(ActionEvent action) {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        System.out.println("get ID= " + Id);
        System.out.println("New message is created");
        Long sender = Long.valueOf(Id);
        System.err.println("Sender is " + sender);
        Long receiver = getMessage().getReceiverId();
        String title = getMessage().getTitle();
        String content = getMessage().getContent();
        this.setMessage(getSmsbl().createNewMessage(sender, receiver, title, content));
        System.err.println("New message is created. Message ID = " + getMessage().getId());
        FacesMessage msg = new FacesMessage("Message is successfully send to" + receiver);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.err.println("Message is successfully send to " + receiver);
    }

    public void SendAnnouncement(ActionEvent action) {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long sender = Long.valueOf(Id);
        System.err.println("New announcement will be created");
        Long departmentID = getDepartment().getId();
        String title = getAnnouncement().getTitle();
        String content = getAnnouncement().getContent();
        this.setAnnouncement(getSmsbl().createAnnouncement(sender, title, content, departmentID));
        System.out.println("New Announcement is created. Announcement ID = " + getAnnouncement().getId());
        FacesMessage msg = new FacesMessage("Announcement is successfully send to the department " + departmentID);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void readMessage(Message message) {
        System.err.println("READ MESSAGE!!");
        Long Id=message.getId();
        smsbl.readMessage(Id);
    }

    public void removeAnnouncement(Announcement announcement) {
        getAllAnnouncement().remove(announcement);
        Long ID = announcement.getId();
        System.out.println("Announcement Id = " + ID + " will be removed.");
        getSmsbl().deleteAnnouncement(ID);
    }

    public void removeMessage(Message message) {
        getAllMessage().remove(message);
        System.out.println("Message Id = " + message.getId() + " will be removed.");
        getSmsbl().deleteMessage(message.getId());
    }

    public int countUnread(ActionEvent actionEvent) {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long receiver = Long.valueOf(Id);
        int count;
        count = getSmsbl().unreadCount(receiver);
        return count;
    }

    public List<Message> getUnreadMessage() {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long receiver = Long.valueOf(Id);
        if ((unreadMessage == null) || unreadMessage.isEmpty()) {
            unreadMessage = getSmsbl().getUnreadMessages(receiver);
        }
        return unreadMessage;
    }

    public List<Announcement> getAllAnnouncement() {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long receiver = Long.valueOf(Id);
        if ((allAnnouncement == null) || allAnnouncement.isEmpty()) {
            allAnnouncement = getSmsbl().getAllAnnouncements(receiver);
        }
        return allAnnouncement;
    }

    public void setAllAnnouncement(List<Announcement> allAnnouncement) {
        this.allAnnouncement = allAnnouncement;
    }

    public List<Message> getAllMessage() {
        String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long receiver = Long.valueOf(Id);
        System.err.println("Get Staff ID = " + receiver);
        if ((allMessage == null) || allMessage.isEmpty()) {
            allMessage = getSmsbl().getAllMessages(receiver);
        }
        return allMessage;
    }

    public void setAllMessage(List<Message> allMessage) {
        this.allMessage = allMessage;
    }

    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the staffAccount
     */
    public StaffAccount getStaffAccount() {
        return staffAccount;
    }

    /**
     * @param staffAccount the staffAccount to set
     */
    public void setStaffAccount(StaffAccount staffAccount) {
        this.staffAccount = staffAccount;
    }

    /**
     * @return the smsbl
     */
    public StaffMessageSessionBeanLocal getSmsbl() {
        return smsbl;
    }

    /**
     * @param smsbl the smsbl to set
     */
    public void setSmsbl(StaffMessageSessionBeanLocal smsbl) {
        this.smsbl = smsbl;
    }

    /**
     * @param unreadMessage the unreadMessage to set
     */
    public void setUnreadMessage(List<Message> unreadMessage) {
        this.unreadMessage = unreadMessage;
    }

    /**
     * @return the announcement
     */
    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * @param announcement the announcement to set
     */
    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    /**
     * @return the workgroup
     */
    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public Announcement getSelectedAnnouncement() {
        return selectedAnnouncement;
    }

    public void setSelectedAnnouncement(Announcement selectedAnnouncement) {
        this.selectedAnnouncement = selectedAnnouncement;
    }

    public List<Announcement> getTodayAnnouncement() {
        return todayAnnouncement;
    }

    public void setTodayAnnouncement(List<Announcement> todayAnnouncement) {
        this.todayAnnouncement = todayAnnouncement;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Message getSelectedUnreadMessage() {
        return selectedUnreadMessage;
    }

    public void setSelectedUnreadMessage(Message selectedUnreadMessage) {
        this.selectedUnreadMessage = selectedUnreadMessage;
    }
}
