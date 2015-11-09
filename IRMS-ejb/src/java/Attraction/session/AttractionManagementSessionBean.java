/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.Attraction;
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
public class AttractionManagementSessionBean implements AttractionManagementSessionBeanLocal {
    @PersistenceContext
    EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean updateAttractionInfo(Attraction attraction){
        if(attraction==null){
            return false;
        }
        else{
            if(em.find(Attraction.class, attraction.getName())!=null){
                em.merge(attraction);
                return true;
            }
            else
                return false;
        }
    }
    
    @Override
    public List<Attraction> getAllAttraction(){
      //  System.err.println("inside sessionbean");
        List<Attraction> attractions;
        Query q = em.createQuery("SELECT a FROM Attraction a");
        attractions = q.getResultList();
        return attractions;
    }
}
