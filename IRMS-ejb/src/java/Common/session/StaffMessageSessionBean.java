/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Announcement;
import Common.entity.Department;
import Common.entity.Message;
import Common.entity.StaffAccount;
import Common.entity.Title;
import Common.entity.WorkGroup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless(name = "StaffMessageSession")
public class StaffMessageSessionBean implements StaffMessageSessionBeanLocal, StaffMessageSessionBeanRemote {

    @PersistenceContext
    EntityManager em;
    Message message;
    StaffAccount staffAccount;
    Announcement announcement;
    WorkGroup workGroup;
    Department department;
    Title title;

    public StaffMessageSessionBean() {
    }

    @Override
    public Announcement createAnnouncement(Long sender, String title, String content, Long departmentId) {
        try {
            announcement = new Announcement();
            staffAccount = em.find(StaffAccount.class, sender);
            department = em.find(Department.class, departmentId);
            announcement.createAnnouncement(title, content);
            announcement.setStaffAccount(staffAccount);
            announcement.setDepartment(department);

            em.persist(announcement);
            return announcement;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Message createNewMessage(Long sender, Long receiver, String title, String content) {
        try {
            message = new Message();
            Date thisTime = new Date();
            message.CreateMessage(title, content, receiver);
            staffAccount = em.find(StaffAccount.class, sender);
            message.setStaffAccount(staffAccount);
            message.setMessageTime(thisTime);

            em.persist(message);
            return message;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Message> getAllMessages(Long receiver) {
        try {
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.ReceiverId=:receiver");
            q.setParameter("receiver", receiver);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     *
     * @param staffAccountId
     * @return
     */
    @Override
    public List<Announcement> getAllAnnouncements(Long staffAccountId) {
        try {
            List<Announcement> announcements = new ArrayList<>();
            List<Title> titles = new ArrayList<>();
            staffAccount = em.find(StaffAccount.class, staffAccountId);
            titles = staffAccount.getStaff().getTitles();

            for (Title t : titles) {
                Long departmentID = t.getDepartment().getId();
                Query q = em.createQuery("SELECT a FROM Announcement a WHERE a.department.id=:departmentID");
                q.setParameter("departmentID", departmentID);
                List<Announcement> temp = q.getResultList();
                for (Announcement a : temp) {
                    announcements.add(a);
                }
            }
            return announcements;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Message> getUnreadMessages(Long receiver) {
        try {
            boolean notRead = false;
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.ReceiverId=:receiver AND m.ReadorNot=:notRead");
            q.setParameter("notRead", notRead);
            q.setParameter("receiver", receiver);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean deleteMessage(Long id) {
        try {
            Message message = em.find(Message.class, id);
            System.out.println("Message is found, ready to delete.");
            em.remove(message);
            System.out.println("Message is removed.");
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean deleteAnnouncement(Long id) {
        try {
            Announcement announcement = em.find(Announcement.class, id);
            System.out.println("Announcement is found, ready to delete.");
            em.remove(announcement);
            System.out.println("Announcemnet is removed.");
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public boolean readMessage(Long id) {
        try {
            message = em.find(Message.class, id);
            message.setReadorNot(true);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public int unreadCount(Long receiver) {
        int count = 0;
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.ReceiverId=:receiver");
        q.setParameter("receiver", receiver);
        List<Message> tempList = q.getResultList();

        for (Message m : tempList) {
            if (!m.isReadorNot()) {
                count++;
            }
        }
        return count;
    }
}
