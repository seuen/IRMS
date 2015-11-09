/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.accom;

import Accommodation.entity.Hotel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yifeng
 */
@Stateless
public class AccomRestful implements AccomRestfulLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public List<Hotel> getAllHotels(){
        
        Query query = em.createQuery("SELECT h FROM Hotel h");
        List<Hotel> allHotels = query.getResultList();
        System.out.println("allHotels" + allHotels);
        for(Hotel hotel: allHotels)
            System.out.println("hotel name " + hotel.getName());
        
//        List<Hotel> allHotels = new ArrayList();
//        Hotel hotel1 = new Hotel();
//        hotel1.setName("haha1");
//        hotel1.setAddress("SOC");
//        hotel1.setDescription("Excellent");
//        Hotel hotel2 = new Hotel();
//        hotel2.setName("haha2");
//        hotel2.setAddress("SOC");
//        hotel2.setDescription("Great");
//        Hotel hotel3 = new Hotel();
//        hotel3.setName("haha3");
//        hotel3.setAddress("SOC");
//        hotel3.setDescription("Good");
//        allHotels.add(hotel1);
//        allHotels.add(hotel2);
//        allHotels.add(hotel3);
        return allHotels;
    }   
    
    @Override 
    public String testCheck(){
        return "this is ok";
    }

}
