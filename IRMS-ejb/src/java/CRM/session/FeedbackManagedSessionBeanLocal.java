/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.Feedback;
import CRM.entity.MemberAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface FeedbackManagedSessionBeanLocal {

    public Feedback createFeedback(MemberAccount member, String content, String subject, String subject2, int rating);

    public List<Feedback> listAllFeedbacks();

    public List<Integer> countFeedback();

    public List<Double> feedbackRating();

    public void feedbackRewardPoints(MemberAccount member);
    
}
