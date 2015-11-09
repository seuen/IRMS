/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import EntertainmentShow.entity.SectionTicket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class ESRevenueManagementSessionBean implements ESRevenueManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<SectionTicket> getInternalTicketSections() {
        Query q = em.createQuery("SELECT t FROM SectionTicket t WHERE t.show.showInfo.type='Internal'");
        List<SectionTicket> st = q.getResultList();
        return st;
    }

    @Override
    public List<SectionTicket> getExternalTicketSections() {
        Query q = em.createQuery("SELECT t FROM SectionTicket t WHERE t.show.showInfo.type='External'");
        List<SectionTicket> st = q.getResultList();
        return st;
    }

    @Override
    public float calcualteTotalRevenue(List<SectionTicket> st) {
        float revenue = 0;
        for(SectionTicket t: st) {
            revenue += (t.getSeatSection().getTotalNum()-t.getAvailableNum())*t.getPrice();
        }
        return revenue;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
