/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Staff;
import Common.entity.Title;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ARIEL CHENG
 */
@Remote
public interface StaffAccountSessionBeanRemote {
 
    public Staff findStaff(Long staffAccountId);
    public List<Title> getAllTitles(Long staffAccountId);
    public boolean updateStaff(Staff staff);
    public boolean updateEmail(Long id, String email);
    
}
