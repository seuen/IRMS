/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.Address;
import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface MemberAccountManagementSessionBeanLocal {
    
 public String addMember(MemberAccount member, Address address, Membership membership) throws NoSuchAlgorithmException;
    public boolean verifyLogin(Long memberId, String password) throws NoSuchAlgorithmException;

    public MemberAccount searchMember(Long id);

    public void updateMember(MemberAccount member);


    public int getPassLength();

    public void setPassLength(int passLength);

    public String resetMemberPassword(MemberAccount member, String psw1, String psw2) throws NoSuchAlgorithmException;

    public void EditProfilePhoto(String name, String type);
    
}
