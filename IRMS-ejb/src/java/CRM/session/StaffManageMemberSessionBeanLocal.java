/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.MemberAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface StaffManageMemberSessionBeanLocal {

    public List<MemberAccount> getAllMembers();

    public void sendBirthdayEmail();

    public void rewardBirthdayMember(MemberAccount m);
    
}
