/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.Seat;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.ShowOrder;
import EntertainmentShow.entity.ShowTicket;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zsy
 */
@Stateless
public class ESTicketManagementSessionBean implements ESTicketManagementSessionBeanLocal {
    @PersistenceContext
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<ShowInfo> listAllShowInfo() {
        List<ShowInfo> showInfos;
        Query q = em.createQuery("SELECT s FROM ShowInfo s");
        showInfos = q.getResultList();
        return showInfos;
    }

    @Override
    public List<SectionTicket> listAllSectionTicket(ESShow show) {
        List<SectionTicket> sectionTicket = (List<SectionTicket>) show.getSectionTickets();
        return sectionTicket;
    }

    @Override
    public List<Seat> listAllSeat() {
        List<Seat> seats = new ArrayList();
        return seats;
    }

    @Override
    public Long blockInSeats(List<Seat> seats, SectionTicket sectionTicket) {
        System.err.println("inside blockInSeats sessionbean");
        Long confirmNum;

        if (em.find(SectionTicket.class, sectionTicket.getId()) != null && sectionTicket.getAvailableNum()>=seats.size()) {
            System.err.println("process to block seats");
            
            List<ShowTicket> showTickets = new ArrayList();
            ShowOrder showOrder = new ShowOrder();

            for (Seat seat : seats) {
                List<ShowTicket> sts = (List<ShowTicket>) seat.getShowTickets();

                //create a show ticket
                ShowTicket showTicket = new ShowTicket();
                showTicket.setSeat(seat);
                showTicket.setPrice(sectionTicket.getPrice());
                showTicket.setSectionTicket(sectionTicket);
                showTicket.setStatus("Valid");
                showTickets.add(showTicket);
                em.persist(showTicket);

                //update relationship of seat and showTickets
                sts.add(showTicket);
                seat.setShowTickets(showTickets);
                em.merge(seat);
            }

            //update showOrder
            showOrder.setShowTickets(showTickets);
            showOrder.setQuantity(showTickets.size());
            showOrder.setTotalPrice(showTickets.size() * sectionTicket.getPrice());

            em.persist(showOrder);

            //update sectionTicket
            if (sectionTicket.getAvailableNum() >= showTickets.size()) {
                sectionTicket.setAvailableNum(sectionTicket.getAvailableNum()-showTickets.size());
                if(sectionTicket.getAvailableNum()==0){
                    sectionTicket.setStatus("Unavailable");
                }
            }
            em.merge(sectionTicket);
            
            //update showTickets associated with showOrder
            for (ShowTicket st : showTickets) {
                st.setShowOrder(showOrder);
                em.merge(st);
            }
            
            em.flush();
            
            confirmNum = showOrder.getId();
            System.err.println(confirmNum);
            
            return confirmNum;
        }
        
        else{
            System.err.println("unable to block seats");
            return null;
        }
    }
    
    
    @Override
    public Long blockExSeats(List<Seat> seats, SectionTicket sectionTicket) {
        System.err.println("inside blockExSeats sessionbean");
        Long confirmNum;

        if (em.find(SectionTicket.class, sectionTicket.getId()) != null && sectionTicket.getAvailableNum()>=seats.size()) {
            System.err.println("process to block seats");
            
            List<ShowTicket> showTickets = new ArrayList();
            ShowOrder showOrder = new ShowOrder();

            for (Seat seat : seats) {
                List<ShowTicket> sts = (List<ShowTicket>) seat.getShowTickets();

                //create a show ticket
                ShowTicket showTicket = new ShowTicket();
                showTicket.setSeat(seat);
                showTicket.setPrice(sectionTicket.getPrice());
                showTicket.setSectionTicket(sectionTicket);
                showTicket.setStatus("Valid");
                showTickets.add(showTicket);
                em.persist(showTicket);

                //update relationship of seat and showTickets
                sts.add(showTicket);
                seat.setShowTickets(showTickets);
                em.merge(seat);
            }

            //update showOrder
            showOrder.setShowTickets(showTickets);
            showOrder.setQuantity(showTickets.size());
            showOrder.setTotalPrice(showTickets.size() * sectionTicket.getPrice());

            em.persist(showOrder);

            //update sectionTicket
            if (sectionTicket.getAvailableNum() >= showTickets.size()) {
                sectionTicket.setAvailableNum(sectionTicket.getAvailableNum()-showTickets.size());
                if(sectionTicket.getAvailableNum()==0){
                    sectionTicket.setStatus("Unavailable");
                }
            }
            em.merge(sectionTicket);
            
            //update showTickets associated with showOrder
            for (ShowTicket st : showTickets) {
                st.setShowOrder(showOrder);
                em.merge(st);
            }
            
            em.flush();
            
            confirmNum = showOrder.getId();
            System.err.println(confirmNum);
            
            return confirmNum;
        }
        
        else{
            System.err.println("unable to block seats");
            return null;
        }
    }
    
    @Override
    public ShowOrder getThisOrder(Long confirmNum){
        return em.find(ShowOrder.class, confirmNum);
    }
}
