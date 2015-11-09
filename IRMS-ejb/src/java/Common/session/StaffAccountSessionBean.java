/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Title;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless(name = "StaffAccountSession")
public class StaffAccountSessionBean implements StaffAccountSessionBeanLocal, StaffAccountSessionBeanRemote {

    @PersistenceContext
    EntityManager em;
    StaffAccount staffAccount;
    Title title;
    Staff staff;

    @Override
    public Staff findStaff(Long staffAccountId) {
        if (staffAccountId != null) {
            staffAccount = em.find(StaffAccount.class, staffAccountId);
            Long staffId = staffAccount.getStaff().getId();
            staff = em.find(Staff.class, staffId);
            return staff;
        } else {
            return null;
        }
    }

    @Override
    public List<Title> getAllTitles(Long staffAccountId) {
        try{
        StaffAccount sa = em.find(StaffAccount.class, staffAccountId);
        if (sa != null) {
            return sa.getStaff().getTitles();
        } else {
            return null;
        }
        }catch(Exception ex){
            return null;
        }

    }

    /**
     *
     * @param staff
     */
    @Override
    public boolean updateStaff(Staff staff) {
        try {
            System.err.println("has get into the updateStaff method");
            System.err.println("the email of staff is " + staff.getEmail());
            Staff s = em.find(Staff.class, staff.getId());
            s.setEmail(staff.getEmail());
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateEmail(Long id, String email) {
        try {
            staffAccount = em.find(StaffAccount.class, id);
            Long staffId = staffAccount.getStaff().getId();
            staff = em.find(Staff.class, staffId);
            staff.setEmail(email);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
