/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.Address;
import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless
public class MemberAccountManagementSessionBean implements MemberAccountManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;
    private int passLength = 8;

    public MemberAccountManagementSessionBean() {
    }

   
    @Override
    public String addMember(MemberAccount member, Address address, Membership membership) throws NoSuchAlgorithmException {
            membership.setCardValue(0.0);
            membership.setLoyaltyPoints(0);
            membership.setMemberType("Classic");
            membership.setProfilePhoto("default");
            membership.setImageType("jpg");
            membership.setCardNum("0000");
            String psw = this.generatePassword();
            System.err.println("Session Bean Password "+psw); 
            MessageDigest md = MessageDigest.getInstance("MD5");
            String pswHash = "" + psw;
            membership.setPassword(this.byteArrayToHexString(md.digest(pswHash.getBytes())));
            
            member.setMembership(membership);
            member.setAddress(address);
            System.out.println("new member added to the system");
            em.persist(member);
            return pswHash;
   
    }

    @Override
    public MemberAccount searchMember(Long id) {
        member = new MemberAccount();
        member = em.find(MemberAccount.class, id);
        if (member != null) {
            return member;
        } else {
            return null;
        }
    }

 
    @Override
    public boolean verifyLogin(Long memberId, String password) throws NoSuchAlgorithmException  {
        member = new MemberAccount();
        member = em.find(MemberAccount.class, memberId);
        em.flush();
        if (member == null) {
            return false;
        } else {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String pswInput=""+password;
            String pswInputHash=this.byteArrayToHexString(md.digest(pswInput.getBytes()));
            String psw = member.getMembership().getPassword();
            if (pswInputHash.equals(psw)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void EditProfilePhoto(String name, String type) {
        Long memberID = Long.valueOf(name);
        member = new MemberAccount();
        member = em.find(MemberAccount.class, memberID);
        member.getMembership().setProfilePhoto(name);
        member.getMembership().setImageType(type);
        em.merge(member);
        em.merge(member.getMembership());
    }

    @Override
    public void updateMember(MemberAccount member) {
        em.merge(member);
        em.merge(member.getMembership());
        em.merge(member.getAddress());
    }

    private String generatePassword() throws NoSuchAlgorithmException {
        String password = "";

        SecureRandom wheel = SecureRandom.getInstance("SHA1PRNG");

        char[] alphaNumberic = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < getPassLength(); i++) {
            int random = wheel.nextInt(alphaNumberic.length);
            password += alphaNumberic[random];
        }


        return password;
    }

    private String byteArrayToHexString(byte[] bytes) {
        int lo = 0;
        int hi = 0;
        String hexString = "";

        for (int i = 0; i < bytes.length; i++) {
            lo = bytes[i];
            lo = lo & 0xff;
            hi = lo >> 4;
            lo = lo & 0xf;
            if (hi == 0) {
                hexString += "0";
            } else if (hi == 1) {
                hexString += "1";
            } else if (hi == 2) {
                hexString += "2";
            } else if (hi == 3) {
                hexString += "3";
            } else if (hi == 4) {
                hexString += "4";
            } else if (hi == 5) {
                hexString += "5";
            } else if (hi == 6) {
                hexString += "6";
            } else if (hi == 7) {
                hexString += "7";
            } else if (hi == 8) {
                hexString += "8";
            } else if (hi == 9) {
                hexString += "9";
            } else if (hi == 10) {
                hexString += "a";
            } else if (hi == 11) {
                hexString += "b";
            } else if (hi == 12) {
                hexString += "c";
            } else if (hi == 13) {
                hexString += "d";
            } else if (hi == 14) {
                hexString += "e";
            } else if (hi == 15) {
                hexString += "f";
            }

            if (lo == 0) {
                hexString += "0";
            } else if (lo == 1) {
                hexString += "1";
            } else if (lo == 2) {
                hexString += "2";
            } else if (lo == 3) {
                hexString += "3";
            } else if (lo == 4) {
                hexString += "4";
            } else if (lo == 5) {
                hexString += "5";
            } else if (lo == 6) {
                hexString += "6";
            } else if (lo == 7) {
                hexString += "7";
            } else if (lo == 8) {
                hexString += "8";
            } else if (lo == 9) {
                hexString += "9";
            } else if (lo == 10) {
                hexString += "a";
            } else if (lo == 11) {
                hexString += "b";
            } else if (lo == 12) {
                hexString += "c";
            } else if (lo == 13) {
                hexString += "d";
            } else if (lo == 14) {
                hexString += "e";
            } else if (lo == 15) {
                hexString += "f";
            }
        }

        return hexString;
    }

    @Override
    public int getPassLength() {
        return passLength;
    }

    /**
     * @param passLength the passLength to set
     */
    @Override
    public void setPassLength(int passLength) {
        this.passLength = passLength;
    }
    
    @Override
    public String resetMemberPassword(MemberAccount member, String psw1, String psw2) throws NoSuchAlgorithmException{
        MessageDigest md=MessageDigest.getInstance("MD5");
        psw1=""+psw1;
        psw2=""+psw2;
        
        String oldHash=this.byteArrayToHexString(md.digest(psw1.getBytes()));
        System.err.println(oldHash);
        System.err.println(member.getMembership().getPassword());
        if(oldHash.equals(member.getMembership().getPassword())){ 
            String newHash=this.byteArrayToHexString(md.digest(psw2.getBytes()));
            member.getMembership().setPassword(newHash);
            System.err.println("test");
            em.merge(member);
            em.merge(member.getMembership());
            em.flush();
            System.err.println("Password is changed!");
            return "Your password changed successfully!";
        }else{
            return "Your old passwords is not correct!";
        }
    }
}
