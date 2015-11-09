/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.Enquiry;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface EnquiryManagementSessionBeanLocal {
     public void createEnquiry(Enquiry enquiry, Client client);
     public List<Enquiry> listUnreadEnquiry();
     public List<Enquiry> listFBUnreadEnquiry();
             public List<Enquiry> listEntertainmentUnreadEnquiry();
}
