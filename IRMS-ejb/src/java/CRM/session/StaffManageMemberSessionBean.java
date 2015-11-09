/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.session.EmailManager2;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless
public class StaffManageMemberSessionBean implements StaffManageMemberSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;
    Membership membership;

    public StaffManageMemberSessionBean() {
    }

    @Override
    public List<MemberAccount> getAllMembers() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        return q.getResultList();
    }

    @Override
    public void sendBirthdayEmail() {
        EmailManager2 emailManager = new EmailManager2();
        List<MemberAccount> members = new ArrayList();
        List<MemberAccount> birthdayMembers = new ArrayList();
        Date thisDate = new Date();

        System.err.println("Date of Today :" + thisDate);
        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        members = q.getResultList();
        for (MemberAccount m : members) {
            if ((m.getDateOfBirth().getDate()==thisDate.getDate())&&(m.getDateOfBirth().getMonth()==thisDate.getMonth())) {
                System.err.println("Sending birthday email to member " + m.getId());
                birthdayMembers.add(m);
                this.rewardBirthdayMember(m);
            }
        }
        emailManager.sendBirthdayEmail(birthdayMembers);
    }

    @Override
    public void rewardBirthdayMember(MemberAccount m) {
        double cardvalue1 = m.getMembership().getCardValue();
        System.out.println("[member ID " + m.getId() + "]Before reward card value: " + cardvalue1);
        double cardvalue2 = cardvalue1 + 25;
        m.getMembership().setCardValue(cardvalue2);
        em.merge(m);
        System.out.println("[member ID " + m.getId() + "]After reward card value: " + m.getMembership().getCardValue());
    }
}
