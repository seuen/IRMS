/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Announcement;
import Common.entity.Message;
import java.util.List;
import javax.ejb.Remote;
 
/**
 *
 * @author ARIEL CHENG
 */ 
@Remote
public interface StaffMessageSessionBeanRemote {

    public Message createNewMessage(Long sender, Long receiver, String title, String content);
    public List<Message> getAllMessages(Long receiver);
    public boolean deleteMessage(Long id);
    public int unreadCount(Long receiver);
    public List<Message> getUnreadMessages(Long receiver);
    public Announcement createAnnouncement(Long sender, String title, String content, Long departmentID);
    public List<Announcement> getAllAnnouncements(Long staffAccountId);
    public boolean deleteAnnouncement(Long id);
    public boolean readMessage(Long id);
    
}
