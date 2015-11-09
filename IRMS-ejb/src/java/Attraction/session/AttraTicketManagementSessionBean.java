/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.Attraction;
import Attraction.entity.AttractionOrder;
import Attraction.entity.Bundle;
import Attraction.entity.PkgTicket;
import Attraction.entity.AttractionTicket;
import Attraction.entity.AttractionTicketOrder;
import Attraction.entity.AvailableTicket;
import Attraction.entity.BundleOrder;
import Attraction.entity.TicketType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zsy
 */
@Stateless
public class AttraTicketManagementSessionBean implements AttraTicketManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void addTicketType(TicketType ticketType, String attractionName) {
        Attraction attraction = em.find(Attraction.class, attractionName);
        ticketType.setAttraction(attraction);
        em.persist(ticketType);
    }

    @Override
    public boolean updateTicketType(TicketType ticketType) {
        em.merge(ticketType);
        return true;

    }

    @Override
    public boolean updateBundle(Bundle theBundle) {
        em.merge(theBundle);
        return true;
    }

    @Override
    public List<TicketType> getAllTicketType(String attractionName) {
        Query q = em.createQuery("Select t from TicketType t where t.attraction.name=:attractionName");
        q.setParameter("attractionName", attractionName);
        return q.getResultList();
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        System.err.print("Inside session bean getAllTicketTypes()");
        Query query = em.createQuery("SELECT t FROM TicketType t");
        return query.getResultList();
    }

    @Override
    public List<Bundle> getAllBundles() {
        //   System.err.print("Inside session bean getAllBundles()");
        Query query = em.createQuery("SELECT b FROM Bundle b");
        return query.getResultList();
    }

    @Override
    public List<Attraction> getAllAttractions() {
        System.err.print("Inside session bean getAllAttractions()");
        Query query = em.createQuery("SELECT a FROM Attraction a");
        return query.getResultList();
    }

    @Override
    public TicketType findTicketType(Long ticketTypeId) {
        return em.find(TicketType.class, ticketTypeId);

    }

    @Override
    public boolean deleteTicketType(Long ticketTypeId) {
        System.err.println("sessionBean: deleteTicketType");
        TicketType ticketType = em.find(TicketType.class, ticketTypeId);
        System.err.println("pkgTickets: " + ticketType.getPkgTickets().size());
        System.err.println("82 " + ticketType.getPkgTickets());
        if (ticketType.getPkgTickets().isEmpty()) {
            // attraction
            String attractionId = ticketType.getAttraction().getName();
            Attraction attraction = em.find(Attraction.class, attractionId);
            attraction.getTicketTypes().remove(ticketType);
            System.err.println("83");
            System.err.println("86 " + attraction.getTicketTypes().contains(ticketType));
            ticketType.setAttraction(null);
            em.flush();
            System.err.println("86");
            //pkgTickets       

            Collection<PkgTicket> pts = ticketType.getPkgTickets();
            if (!(pts.isEmpty())) {
                for (PkgTicket pt : pts) {
                    pt.setTicketType(null);
                }
            }
            ticketType.setPkgTickets(null);
            em.flush();

            //ticket
            Collection<AttractionTicket> tickets = ticketType.getTickets();
            for (AttractionTicket t : tickets) {
                t.setTicketType(null);
            }
            ticketType.setTickets(null);
            System.err.println("95");
            em.flush();
            System.err.println("103");
            em.remove(ticketType);
            System.err.println("104");
            //    em.merge(ticketType);                                  System.err.println("105");
            em.flush();
            System.err.println("109");
            return true;
        } else {
            return false;
        }

    }

    @Override
    public TicketType searchTicketType(String type) {
        Query q = em.createQuery("Select t from TicketType t Where t.name=:type");
        q.setParameter("type", type);
        return (TicketType) q.getResultList().get(0);
    }

    @Override
    public boolean deleteBundle(Long bundleId) {
        Bundle bundle = em.find(Bundle.class, bundleId);
        if (bundle.getPkgTickets().isEmpty()) {
            em.remove(bundle);
            em.flush();
            return true;
        } else {
            Collection<PkgTicket> pkgTickets = bundle.getPkgTickets();
            for (PkgTicket pt : pkgTickets) {
                PkgTicket apt = em.find(PkgTicket.class, pt.getId());
                apt.setBundle(null);
                em.remove(apt);
                em.flush();
            }
            bundle.setPkgTickets(null);
            em.remove(bundle);
            em.flush();
            return true;
        }

    }

    @Override
    public boolean createBundle(Collection<PkgTicket> pkgTickets, String bundleName, String bundlePrice) {
        System.err.println("inside SessionBean: createBundle()");
        Bundle bundle = new Bundle();
        em.persist(bundle);
        for (PkgTicket pt : pkgTickets) {
            em.persist(pt);
            pt.setBundle(bundle);
        }
        bundle.setPkgTickets(pkgTickets);
        bundle.setName(bundleName);
        bundle.setPrice(bundlePrice);
        em.flush();

        return true;
    }

    @Override
    public List<AttractionTicketOrder> getAllTheTicketOrders(Attraction attraction) {
        System.err.println("inside session bean: getAllTheTIicketOrders");
        List<AttractionTicketOrder> allTheTicketOrders = new ArrayList();
        Collection<TicketType> ts = attraction.getTicketTypes();
        for (TicketType tt : ts) {
            AttractionTicketOrder ato = new AttractionTicketOrder();
            ato.setAttraction(attraction);
            attraction.getAttractionTicketOrder().add(ato);
            ato.setType(tt.getName());
            float price = Float.parseFloat(tt.getPrice());
            ato.setPrice(price);
            ato.setQuantity(0);
            System.err.println("518 " + ato.getPrice());
            allTheTicketOrders.add(ato);
            System.err.println("519 " + allTheTicketOrders.size());
        }
        System.err.println("521 " + allTheTicketOrders.size());
        return allTheTicketOrders;

    }

    @Override
    public List<AttractionTicketOrder> getDisneyTicketOrders() {
        System.err.println("inside session bean: getDisneyTIicketOrders");
        List<AttractionTicketOrder> allTheTicketOrders = new ArrayList();
        String attractionName = "Disney World";
        Query q = em.createQuery("Select t from TicketType t where t.attraction.name=:attractionName");
        q.setParameter("attractionName", attractionName);
        List<TicketType> ts = q.getResultList();

        Attraction attraction = ts.get(1).getAttraction();
        for (TicketType tt : ts) {
            AttractionTicketOrder ato = new AttractionTicketOrder();
            ato.setAttraction(attraction);
            attraction.getAttractionTicketOrder().add(ato);
            ato.setType(tt.getName());
            float price = Float.parseFloat(tt.getPrice());
            ato.setPrice(price);
            ato.setQuantity(0);
            System.err.println("214 " + ato.getPrice());
            allTheTicketOrders.add(ato);
            System.err.println("215 " + allTheTicketOrders.size());
        }
        System.err.println("217 " + allTheTicketOrders.size());
        return allTheTicketOrders;
    }

    @Override
    public boolean checkTicketAvailability(List<AttractionTicketOrder> atos, List<BundleOrder> bundleOrders, Date date) {
        try {     
            System.err.println("sessionBean: sellTickets" + atos.size());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date now = sdf.parse(sdf.format(new Date()));
            Date ticketDate = sdf.parse(sdf.format(date));
            
            
            //new
             List<TicketType> checkTypes = new ArrayList();
              for (AttractionTicketOrder ato : atos) {

                //get ticketType
                Attraction a = ato.getAttraction();
                TicketType theType = new TicketType();
                String ticketName = ato.getType();
                Collection<TicketType> tt = a.getTicketTypes();
                for (TicketType t : tt) {
                    if (t.getName().equals(ticketName)) {
                        theType = t;
                        checkTypes.add(theType);
                    }
                }
              }
              for(BundleOrder bo:bundleOrders){
                  
              }
            //old
            for (AttractionTicketOrder ato : atos) {

                //get ticketType
                Attraction a = ato.getAttraction();
                TicketType theType = new TicketType();
                String ticketName = ato.getType();
                Collection<TicketType> tt = a.getTicketTypes();
                for (TicketType t : tt) {
                    if (t.getName().equals(ticketName)) {
                        theType = t;
                    }
                }
                System.err.println("theTicketType " + theType);
                //check availability
                Collection<AvailableTicket> ats = theType.getAvailableTickets();
                System.err.println("236");
                AvailableTicket availableTicket;
                availableTicket = null;
                for (AvailableTicket at : ats) {
                    if (at.gettDate().equals(ticketDate)) {
                        availableTicket = at;
                    }
                }
                System.err.println("245 " + availableTicket);
                Long theTypeId = theType.getId();
                int quantityFromBundle = 0;
                for (BundleOrder bo : bundleOrders) {
                    Collection<PkgTicket> pts = bo.getBunlde().getPkgTickets();
                    for (PkgTicket pt : pts) {
                        if (pt.getTicketType().getId().equals(theTypeId)) {
                            quantityFromBundle += pt.getTicketNumber();
                        }
                    }
                }
                if (availableTicket == null) {
                    return false;
                }
                if ((availableTicket.getPurchasedNum() + ato.getQuantity() + quantityFromBundle) > Integer.parseInt(theType.getMaxNumber())) {
                    return false;
                }
            }
             return true;
        } catch (ParseException ex) {
            Logger.getLogger(AttraTicketManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public boolean sellTickets(List<AttractionTicketOrder> atos, List<BundleOrder> bundleOrders, Date date) {


        try {
            //check quantity
            //  int totalQuantity = 0;
            System.err.println("sessionBean: sellTickets" + atos.size());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date now = sdf.parse(sdf.format(new Date()));
            Date ticketDate = sdf.parse(sdf.format(date));
            
            boolean result = this.checkTicketAvailability(atos, bundleOrders, ticketDate);
            if(result==false) return false;
//            for (AttractionTicketOrder ato : atos) {
//
//                //get ticketType
//                Attraction a = ato.getAttraction();
//                TicketType theType = new TicketType();
//                String ticketName = ato.getType();
//                Collection<TicketType> tt = a.getTicketTypes();
//                for (TicketType t : tt) {
//                    if (t.getName().equals(ticketName)) {
//                        theType = t;
//                    }
//                }
//                System.err.println("theTicketType " + theType);
//                //check availability
//                Collection<AvailableTicket> ats = theType.getAvailableTickets();
//                System.err.println("236");
//                AvailableTicket availableTicket;
//                availableTicket = null;
//                for (AvailableTicket at : ats) {
//                    if (at.gettDate().equals(now)) {
//                        availableTicket = at;
//                    }
//                }
//                System.err.println("245 " + availableTicket);
//                Long theTypeId = theType.getId();
//                int quantityFromBundle = 0;
//                for (BundleOrder bo : bundleOrders) {
//                    Collection<PkgTicket> pts = bo.getBunlde().getPkgTickets();
//                    for (PkgTicket pt : pts) {
//                        if (pt.getTicketType().getId().equals(theTypeId)) {
//                            quantityFromBundle += pt.getTicketNumber();
//                        }
//                    }
//                }
//                if (availableTicket == null) {
//                    return false;
//                }
//                if ((availableTicket.getPurchasedNum() + ato.getQuantity() + quantityFromBundle) > Integer.parseInt(theType.getMaxNumber())) {
//                    return false;
//                }
//
//
//            }
            System.err.println("246");
            //create tickets
            AttractionOrder ao = new AttractionOrder();
            em.persist(ao);
              int quantity1 = 0;
        float totalPrice = 0; 
              for(AttractionTicketOrder ato: atos){
            quantity1+=ato.getQuantity();
            totalPrice+= ato.getQuantity()*ato.getPrice();
        }
         for(BundleOrder bo: bundleOrders){
            quantity1+=bo.getQuantity();
            totalPrice+= bo.getQuantity()*bo.getPrice();
        }
        
            ao.setAttractionTicketOrders(atos);
            ao.setBundleOrders(bundleOrders);
            ao.setQuantity(quantity1);
            ao.setTotalPrice(totalPrice);
            ao.setOrderDate(now);
            em.merge(ao);
            System.err.println("354");
            for (AttractionTicketOrder ato : atos) {
               
                ato.setAttractionOrder(ao);
                 em.persist(ato);
               // em.flush();
                em.merge(ato);
                int quantity = ato.getQuantity();
                System.err.println("267 " + quantity);
                //get ticketType
                Attraction a = ato.getAttraction();
                TicketType tt = new TicketType();
                String ticketName = ato.getType();
                Collection<TicketType> tts = a.getTicketTypes();
                for (TicketType t : tts) {
                    if (t.getName().equals(ticketName)) {
                        tt = t;
                    }
                }
                System.err.println("276");
                //get availableTicket
                Collection<AvailableTicket> ats = tt.getAvailableTickets();
                AvailableTicket availableTicket = new AvailableTicket();
                for (AvailableTicket at : ats) {
                    if (at.gettDate().equals(ticketDate)) {
                        availableTicket = at;
                    }
                }
                System.err.println("286");

                for (int i = 0; i < quantity; i++) {
                    AttractionTicket ticket = new AttractionTicket();
                    em.persist(ticket);
                    System.err.println("289");
                    //  TicketType tt = em.find(TicketType.class,ato.getType());
                    ticket.setAttractionTicketOrder(ato);;
                    System.err.println("291");
                    ticket.setTicketType(tt);
                    System.err.println("292");
                    ticket.setType(tt.getName());
                    System.err.println("292");
                    float price = Float.parseFloat(tt.getPrice());
                    ticket.setPrice(price);
                    int num = availableTicket.getPurchasedNum() + 1;
                    availableTicket.setPurchasedNum(num);

                    ticket.setStartDate(ticketDate);
                    System.err.println("300");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ticketDate);
                    String[] words = tt.getName().split(" ");
                    System.err.println("the test word is " + words[1]);
                    if (words[1].equals("One")) {
                        cal.add(Calendar.DAY_OF_YEAR, 1);
                        Date expire = cal.getTime();
                        System.err.println("the expire day is " + expire);
                        System.err.println("301");
                        ticket.setExpireDate(expire);
                        System.err.println("307 " + ato);
                      //  em.flush();
                        ato.getAttractionTicket().add(ticket);
                        tt.getTickets().add(ticket);
                        System.err.println("309");
                        em.merge(ato);
                        em.merge(ticket);
                        em.merge(availableTicket);

                    } else {
                        cal.add(Calendar.DAY_OF_YEAR, 365);
                        Date expire = cal.getTime();
                        ticket.setExpireDate(expire);
                        System.err.println("309");
                        em.merge(ato);
                         em.merge(ticket);
                            em.merge(availableTicket);
                    }

//                    em.flush();
                    System.err.println("316");

                }

            }
            
            for(BundleOrder bo:bundleOrders){
                em.persist(bo);
                bo.setAttractionOrder(ao);
              
                em.merge(bo);
                for(PkgTicket pt:bo.getBunlde().getPkgTickets()){
                    int quantity = pt.getTicketNumber();
                    TicketType tt= pt.getTicketType();
                    for(int i=0; i<quantity;i++){
                        AttractionTicket ticket = new AttractionTicket();
                        em.persist(ticket);
                        System.err.println("424");
                        ticket.setTicketType(tt);
                        float price = Float.parseFloat(pt.getTicketType().getPrice());
                        ticket.setPrice(price);
                         Collection<AvailableTicket> ats = tt.getAvailableTickets();
                AvailableTicket availableTicket = new AvailableTicket();
                for (AvailableTicket at : ats) {
                    if (at.gettDate().equals(ticketDate)) {
                        availableTicket = at;
                    }
                }
                
                        int num = availableTicket.getPurchasedNum() + 1;
                       availableTicket.setPurchasedNum(num);
                        ticket.setStartDate(ticketDate);
                    System.err.println("300");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(ticketDate);
                    String[] words = tt.getName().split(" ");
                    System.err.println("the test word is " + words[1]);
                    if (words[1].equals("One")) {
                        cal.add(Calendar.DAY_OF_YEAR, 1);
                        Date expire = cal.getTime();
                        System.err.println("the expire day is " + expire);
                        System.err.println("301");
                        ticket.setExpireDate(expire);
                        em.merge(ticket);
                      
                        tt.getTickets().add(ticket);
                        em.merge(tt);
                        System.err.println("309");

                    } else {
                        cal.add(Calendar.DAY_OF_YEAR, 365);
                        Date expire = cal.getTime();
                        ticket.setExpireDate(expire);
                        tt.getTickets().add(ticket);
                        em.merge(ticket);
                        em.merge(tt);
                        System.err.println("309");
                    }

                    
                    System.err.println("316");
                    }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AttraTicketManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
        System.err.println("324");
        return true;

    }
}
