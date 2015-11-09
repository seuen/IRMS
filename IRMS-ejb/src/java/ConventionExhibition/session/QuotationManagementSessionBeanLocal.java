/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Enquiry;
import ConventionExhibition.entity.Quotation;
import javax.ejb.Local;

@Local
public interface QuotationManagementSessionBeanLocal {
    public void sendQuotation(Quotation quotation, Enquiry enquiry);
    
}
