/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.EventOrder;
import EntertainmentShow.entity.ShowInfo;
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
public class EventRevenueManagementSessionBean implements EventRevenueManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<EventOrder> getCEevents(Date start, Date end) {
        Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type='ConventionExhibition'");
        List<EventOrder> qlist = q.getResultList();
        List<EventOrder> list = new ArrayList();
        for(EventOrder e:qlist) {
            if(e.getCreateDate().compareTo(start)>=0 && e.getCreateDate().compareTo(end)<=0)
                list.add(e);
        }
        return list;
    }

    @Override
    public List<EventOrder> getBanquets(Date start, Date end) {
        Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type='Banquet'");
        List<EventOrder> qlist = q.getResultList();
        List<EventOrder> list = new ArrayList();
        for(EventOrder e:qlist) {
            if(e.getCreateDate().compareTo(start)>=0 && e.getCreateDate().compareTo(end)<=0)
                list.add(e);
        }
        return list;
    }

    @Override
    public List<EventOrder> getEnterns(Date start, Date end) {
        Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type='Entertainment'");
        List<EventOrder> qlist = q.getResultList();
        List<EventOrder> list = new ArrayList();
        for(EventOrder e:qlist) {
            if(e.getCreateDate().compareTo(start)>=0 && e.getCreateDate().compareTo(end)<=0)
                list.add(e);
        }
        return list;
    }

    @Override
    public float calculateTotalRevenue(List<EventOrder> eo) {
        float revenue=0;
        for(EventOrder o: eo) {
            revenue+=o.getTotalprice();
            revenue+=this.calculateCommissionRevenue(o.getId());
        }
        return revenue;
    }
    
    @Override
    public float calculateCommissionRevenue(Long orderID) {
        Query q = em.createQuery("SELECT s FROM ShowInfo s");
        List<ShowInfo> info = q.getResultList();
        float commission=0;
        for(ShowInfo i: info) {
            if(i.getEventId()==orderID) {
                commission = i.getCommissionFee();
                break;
            }
        }
        return commission;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
