/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.TicketType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zsy
 */
@Stateless
public class TicketManagementSessionBean implements TicketManagementSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    EntityManager em;
    
    @Override
    public boolean addTicketType(TicketType ticketType){
        if(ticketType!=null){
            em.persist(ticketType);
            return true;
        }
        else
            return false;
    }
    
    @Override
    public boolean updateTicketType(){
        return true;
    }
    
    @Override
    public boolean deleteTicketType(){
        return true;
    }
    
    @Override
    public List<TicketType> getAllTicketTypes(){
        List<TicketType> ticketTypes = new ArrayList();
        return ticketTypes;
    }
    

}
