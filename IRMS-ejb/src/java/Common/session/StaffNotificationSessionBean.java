/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Department;
import Common.entity.Message;
import Common.entity.Notification;
import Common.entity.NotificationRecord;
import Common.entity.StaffAccount;
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
@Stateless(name = "StaffNotificationSession")
public class StaffNotificationSessionBean implements StaffNotificationSessionBeanLocal, StaffNotificationSessionBeanRemote {

    @PersistenceContext
    EntityManager em;
    Message message;
    StaffAccount staffAccount;
    Notification notification;
    Department department;
    NotificationRecord notificationRecord;

    public StaffNotificationSessionBean() {
    }

    @Override
    public Notification createNotification(Long sender, String content) {
        try {
            notification = new Notification();
            Date thisTime = new Date();
            department = em.find(Department.class, Long.valueOf("3"));
            notification.createNotification(content);
            staffAccount = em.find(StaffAccount.class, sender);
            notification.setSender(staffAccount);
            notification.setNotifyTime(thisTime);
            notification.setReceiver(department);

            em.persist(notification);
            return notification;

        } catch (Exception ex) {
            return null;
        }
    }

    private NotificationRecord createRecord(Long receiver, Long notificationId) {
        notificationRecord = new NotificationRecord();
        System.out.println("Test4");
        notification = em.find(Notification.class, notificationId);
        staffAccount = em.find(StaffAccount.class, receiver);

        Date thisTime = notification.getNotifyTime();
        System.out.println("Get time = " + thisTime);
        Long sender = notification.getSender().getStaffId();
        System.out.println("Get sender ID= " + sender);
        String senderName = notification.getSender().getStaff().getFirstName() + " "
                + notification.getSender().getStaff().getLastName();
        System.out.println(senderName);
        String content = notification.getContent();
        System.out.println(content);
        String departmentName = notification.getReceiver().getDepartmentName();
        System.out.println(departmentName);
        notificationRecord.createNotificationRecord(sender, senderName, content, thisTime, departmentName);
        System.err.println("A notification reocord is created!!!!ID is " + notificationRecord.getId());

        notificationRecord.setStaffAccount(staffAccount);

        em.persist(notificationRecord);
        return notificationRecord;

    }

    @Override
    public List<NotificationRecord> getNotficationRecords(Long receiver) {
        try {
            System.out.println("Test1");
            List<NotificationRecord> results = new ArrayList<>();
            NotificationRecord temp;
            Long departmentId = Long.valueOf("3");
            department = em.find(Department.class, departmentId);
            System.out.println("Test2");
            Query q = em.createQuery("SELECT n FROM Notification n WHERE n.receiver=:department");
            q.setParameter("department", department);
            System.out.println(q.getResultList());
            System.out.println("Test3");
            List<Notification> notifications = q.getResultList();
            for (Notification nf : notifications) {
                System.err.println("Notification is found. ID = " + nf.getId());
                temp = createRecord(receiver, nf.getId());
                results.add(temp);
            }
            return results;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean deleteNotificationRecord(Long id) {
        try {
            notificationRecord = em.find(NotificationRecord.class, id);
            em.remove(notificationRecord);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void deleteMessage(Long id) {
        Message message = em.find(Message.class, id);
        System.out.println("Message is found, ready to delete.");
        em.remove(message);
        System.out.println("Message is removed.");
        em.flush();
    }
}
