 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.Feedback;
import CRM.entity.MemberAccount;
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
public class FeedbackManagedSessionBean implements FeedbackManagedSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;
    Feedback feedback;

    public FeedbackManagedSessionBean() {
    }

    @Override
    public Feedback createFeedback(MemberAccount member, String content, String subject, String subject2, int rating) {
        feedback = new Feedback();

        Date feedbackTime = new Date();
        feedback.create(content, subject, subject2, rating);
        feedback.setMemberAccount(member);
        feedback.setFeedbackTime(feedbackTime);
        System.out.println(feedback);

        em.persist(feedback);
        return feedback;
    }
    
    @Override
    public void feedbackRewardPoints(MemberAccount member){
        int points=member.getMembership().getLoyaltyPoints();
        points=points+5;
        member.getMembership().setLoyaltyPoints(points);
        em.merge(member);
        em.merge(member.getMembership());              
    }

    @Override
    public List<Feedback> listAllFeedbacks() {
        Query q = em.createQuery("Select f From Feedback f");
        return q.getResultList();
    }
   
    @Override
    public List<Double> feedbackRating() {
        List<Double> rate = new ArrayList();
        Double temp = 0.0;
        String s1 = "Accommodation";
        String s2 = "Food and Beverage";
        String s3 = "Attractions";
        String s4 = "Shopping Mall";
        String s5 = "Entertainment Show";
        String s6 = "Exhibition and Convention Hall";
        String s7 = "Customer Relationship";
        String s8 = "Others";

        Query q1 = em.createQuery("Select f From Feedback f Where f.subject=:s1");
        q1.setParameter("s1", s1);
        List<Feedback> all = q1.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(0, temp / all.size());

        temp = 0.0;

        Query q2 = em.createQuery("Select f From Feedback f Where f.subject=:s2");
        q2.setParameter("s2", s2);
        all = q2.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(1, temp / all.size());

        temp = 0.0;

        Query q3 = em.createQuery("Select f From Feedback f Where f.subject=:s3");
        q3.setParameter("s3", s3);
        all = q3.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(2, temp / all.size());
        temp = 0.0;

        Query q4 = em.createQuery("Select f From Feedback f Where f.subject=:s4");
        q4.setParameter("s4", s4);
        all = q4.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(3, temp / all.size());
        temp = 0.0;

        Query q5 = em.createQuery("Select f From Feedback f Where f.subject=:s5");
        q5.setParameter("s5", s5);
        all = q5.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(4, temp / all.size());
        temp = 0.0;

        Query q6 = em.createQuery("Select f From Feedback f Where f.subject=:s6");
        q6.setParameter("s6", s6);
        all = q6.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(5, temp / all.size());
        temp = 0.0;

        Query q7 = em.createQuery("Select f From Feedback f Where f.subject=:s7");
        q7.setParameter("s7", s7);
        all = q7.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(6, temp / all.size());
        temp = 0.0;

        Query q8 = em.createQuery("Select f From Feedback f Where f.subject=:s8");
        q8.setParameter("s8", s8);
        all = q8.getResultList();
        for (Feedback fbk : all) {
            temp = temp + fbk.getRating();
        }
        rate.add(7, temp / all.size());

        return rate;
    }

    @Override
    public List<Integer> countFeedback() {
        List<Integer> count = new ArrayList();
        String s1 = "Accommodation";
        String s2 = "Food and Beverage";
        String s3 = "Attractions";
        String s4 = "Shopping Mall";
        String s5 = "Entertainment Show";
        String s6 = "Exhibition and Convention Hall";
        String s7 = "Customer Relationship";
        String s8 = "Others";

        Query q1 = em.createQuery("Select f From Feedback f Where f.subject=:s1");
        q1.setParameter("s1", s1);
        count.add(0, q1.getResultList().size());

        Query q2 = em.createQuery("Select f From Feedback f Where f.subject=:s2");
        q2.setParameter("s2", s2);
        count.add(1, q2.getResultList().size());

        Query q3 = em.createQuery("Select f From Feedback f Where f.subject=:s3");
        q3.setParameter("s3", s3);
        count.add(2, q3.getResultList().size());

        Query q4 = em.createQuery("Select f From Feedback f Where f.subject=:s4");
        q4.setParameter("s4", s4);
        count.add(3, q4.getResultList().size());

        Query q5 = em.createQuery("Select f From Feedback f Where f.subject=:s5");
        q5.setParameter("s5", s5);
        count.add(4, q5.getResultList().size());

        Query q6 = em.createQuery("Select f From Feedback f Where f.subject=:s6");
        q6.setParameter("s6", s6);
        count.add(5, q6.getResultList().size());

        Query q7 = em.createQuery("Select f From Feedback f Where f.subject=:s7");
        q7.setParameter("s7", s7);
        count.add(6, q7.getResultList().size());

        Query q8 = em.createQuery("Select f From Feedback f Where f.subject=:s8");
        q8.setParameter("s8", s8);
        count.add(7, q8.getResultList().size());

        return count;
    }
}
