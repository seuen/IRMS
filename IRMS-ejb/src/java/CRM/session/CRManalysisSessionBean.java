/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.MemberAccount;
import CRM.entity.TransactionRecord;
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
@Stateless
public class CRManalysisSessionBean implements CRManalysisSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;

    public CRManalysisSessionBean() {
    }

    @Override
    public List<MemberAccount> ListAllMale() {
        String title = "Mr.";
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.title=:title");
        System.err.println("HELLO TEST");
        q.setParameter("title", title);
        System.err.println(q.getResultList());
        return q.getResultList();
    }

    @Override
    public List<MemberAccount> ListAllFemale() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.title='Miss' OR m.title='Mrs.'");
        return q.getResultList();
    }

    @Override
    public List<MemberAccount> ListAllClassic() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.membership.memberType='Classic'");
        return q.getResultList();
    }

    @Override
    public List<MemberAccount> ListAllSilver() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.membership.memberType='Silver'");
        return q.getResultList();
    }

    @Override
    public List<MemberAccount> ListAllGold() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.membership.memberType='Gold'");
        return q.getResultList();
    }

    @Override
    public List<MemberAccount> ListAllDiamond() {
        Query q = em.createQuery("SELECT m FROM MemberAccount m WHERE m.membership.memberType='Diamond'");
        return q.getResultList();
    }

//    Age<30
    @Override
    public List<MemberAccount> ListAgeGroup1() {
        List<MemberAccount> all;
        List<MemberAccount> temp = new ArrayList<>();
        Date thisTime = new Date();
        int thisYear = thisTime.getYear();
        thisTime.setYear(thisYear - 30);
        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        all = q.getResultList();
        for (MemberAccount m : all) {
            if (m.getDateOfBirth().after(thisTime)) {
                temp.add(m);
            }
        }
        return temp;
    }

    // Age range 30~40
    @Override
    public List<MemberAccount> ListAgeGroup2() {
        List<MemberAccount> all;
        List<MemberAccount> temp = new ArrayList<>();
        Date thisTime = new Date();
        int thisYear = thisTime.getYear();
        Date temp1 = new Date();
        Date temp2 = new Date();
        temp1.setYear(thisYear - 30);
        temp2.setYear(thisYear - 40);

        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        all = q.getResultList();
        for (MemberAccount m : all) {
            if (m.getDateOfBirth().after(temp2) && ((m.getDateOfBirth().equals(temp1)) || (m.getDateOfBirth().before(temp1)))) {
                temp.add(m);
            }
        }
        return temp;
    }

//Age range 40~50
    @Override
    public List<MemberAccount> ListAgeGroup3() {
        List<MemberAccount> all;
        List<MemberAccount> temp = new ArrayList<>();
        Date thisTime = new Date();
        int thisYear = thisTime.getYear();
        Date temp1 = new Date();
        Date temp2 = new Date();
        temp1.setYear(thisYear - 40);
        temp2.setYear(thisYear - 50);

        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        all = q.getResultList();
        for (MemberAccount m : all) {
            if (m.getDateOfBirth().after(temp2) && ((m.getDateOfBirth().equals(temp1)) || (m.getDateOfBirth().before(temp1)))) {
                temp.add(m);
            }
        }
        return temp;

    }

    //Age range is 50~60
    @Override
    public List<MemberAccount> ListAgeGroup4() {
        List<MemberAccount> all;
        List<MemberAccount> temp = new ArrayList<>();
        Date thisTime = new Date();
        int thisYear = thisTime.getYear();
        Date temp1 = new Date();
        Date temp2 = new Date();
        temp1.setYear(thisYear - 50);
        temp2.setYear(thisYear - 60);

        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        all = q.getResultList();
        for (MemberAccount m : all) {
            if (m.getDateOfBirth().after(temp2) && ((m.getDateOfBirth().equals(temp1)) || (m.getDateOfBirth().before(temp1)))) {
                temp.add(m);
            }
        }
        return temp;
    }

    //Age is above 60
    @Override
    public List<MemberAccount> ListAgeGroup5() {
        List<MemberAccount> all;
        List<MemberAccount> temp = new ArrayList<>();
        Date thisTime = new Date();
        int thisYear = thisTime.getYear();
        Date temp1 = new Date();
        temp1.setYear(thisYear - 60);

        Query q = em.createQuery("SELECT m FROM MemberAccount m");
        all = q.getResultList();
        for (MemberAccount m : all) {
            if (m.getDateOfBirth().before(temp1) || m.getDateOfBirth().equals(temp1)) {
                temp.add(m);
            }
        }
        return temp;
    }

    @Override
    public List<Double> transactionReport(MemberAccount member, int month, int year) {
        List<Double> result = new ArrayList();
        Double crm = 0.0;
        Double accommodation = 0.0;
        Double entertainmentShow = 0.0;
        Double attraction = 0.0;
        Double others = 0.0;
        Query q = em.createQuery("SELECT t FROM TransactionRecord t Where t.member=:member");
        q.setParameter("member", member);
        List<TransactionRecord> records = q.getResultList();
        for (TransactionRecord t : records) {
            if ((t.getTransactionTime().getMonth() == month) && (t.getTransactionTime().getYear() == year)) {
                if (t.getFromWhere().contains("CRM")) {
                    crm = crm + t.getTotalPrice();
                } else if (t.getFromWhere().contains("Entertainment")) {
                    entertainmentShow = entertainmentShow + t.getTotalPrice();
                } else if (t.getFromWhere().contains("Accommodation")) {
                    accommodation = accommodation + t.getTotalPrice();
                } else if (t.getFromWhere().contains("Attraction")) {
                    attraction = attraction + t.getTotalPrice();
                } else {
                    others = others + t.getTotalPrice();
                }
            }

        }
        result.add(crm);
        result.add(accommodation);
        result.add(entertainmentShow);
        result.add(attraction);
        result.add(others);
        
        return result;

    }
}