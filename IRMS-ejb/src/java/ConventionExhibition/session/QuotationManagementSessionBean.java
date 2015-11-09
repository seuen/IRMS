/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Enquiry;
import ConventionExhibition.entity.Quotation;
import javax.ejb.EJB; 
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.session.EmailManager2;

/**
 *
 * @author Acer
 */
@Stateless
public class QuotationManagementSessionBean implements QuotationManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    
    EmailManager2 em2 =new EmailManager2();
   

    @Override
    public void sendQuotation(Quotation quotation, Enquiry enquiry) {
        quotation.setRequestparty(enquiry.getClient().getEmailAddr());
       
        if(quotation.getOpenspace()!=null){
        }
        if(quotation.getAuditorium()!=null){
             em.merge(quotation.getAuditorium());
            em.persist(quotation);
        }
        
          Enquiry edata= em.find(Enquiry.class, enquiry.getId());
        System.err.println("find the enquiry in session bean");
        edata.setStatus(true);
        em.merge(edata);
      //  em.flush();
        if(edata.isStatus())
        System.err.println("the edata status is true");
        System.err.println("after change the enquiry in sessoion bean");
        String subject = "[Coral Island Resort Venue Management Center] Quotation for " + enquiry.getClient().getFirstname()
                + " " + enquiry.getClient().getLastname();
        String text = "Dear Client " + enquiry.getClient().getFirstname() + " " + enquiry.getClient().getLastname() + ",\n\n";
        text += "This is the respond for your proposal submitted on " + enquiry.getEnquiryDate() + ".\n";
        text += "Base on your demand, we have one " + enquiry.getRoomtype() + " fit. \n";
        text += "The total price for this venue is " + quotation.getVenueprice()+" .\n";
        text += "*******************************************************************\n\n\n";
        text += "Thank you very much for your cooporation!\n\n";
        text += "Best regards,\n";
        text += "Coral Island Resort Venue management Center\n";
        System.err.println("the request party is "+quotation.getRequestparty());
        em2.sendEmail(quotation.getRequestparty(), subject, text);
      
    }
}
