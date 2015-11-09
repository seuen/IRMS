/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Notification;
import Common.entity.NotificationRecord;
import java.util.List;
import javax.ejb.Local;
 
/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface StaffNotificationSessionBeanLocal {

    public Notification createNotification(Long sender, String content);
    public List<NotificationRecord> getNotficationRecords(Long receiver);
    public boolean deleteNotificationRecord(Long id);
    
}
