/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ClientBill;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.DayTime;
import ConventionExhibition.entity.EmployeeNeed;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.Facility;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Acer
 */
@Stateless
public class EventManagementSessionBean implements EventManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void eventend(EventOrder eo){
        em.merge(eo);
        for(FacilityNeed fn:eo.getFacilitiesNeed()){
            for(Long id: fn.getFacilities()){
                Facility ft=em.find(Facility.class, id);
                ft.setStatus("Free");
                em.merge(ft);
            }
        }
    }
    
    @Override
      public List<EventOrder> geteventbanquets(Long eventId){
          List<EventOrder> banquets=new ArrayList();
          EventOrder eo=em.find(EventOrder.class, eventId);
          Query q=em.createQuery("SELECT e FROM EventOrder e");
          for(Object o: q.getResultList()){
              EventOrder temp=(EventOrder) o;
              if(temp.getType().equals("Banquet")){
                  if(temp.getEventorderid()==eventId){
                      banquets.add(temp);
                  }
              }
          }
          return banquets;
      }

    @Override
    public ConventionSchedule checkEventdatescheduleOtherVenue(OtherVenue othervenue, Date eventDate) {
        List<ConventionSchedule> temp = othervenue.getCschedules();
        Query q = em.createQuery("SELECT c FROM ConventionSchedule c");
        for (ConventionSchedule cs : temp) {
            if (cs.getEventdate().equals(eventDate)) {
                return cs;
            }
        }
        return null;
    }
    
    @Override
     public ConventionSchedule checkEventdatescheduleBanquet(BanquetHall banquethall,Date eventDate){
         List<ConventionSchedule> temp = banquethall.getCschedules();
        Query q = em.createQuery("SELECT c FROM ConventionSchedule c");
        for (ConventionSchedule cs : temp) {
            if (cs.getEventdate().equals(eventDate)) {
                return cs;
            }
        }
        return null;
     }

    @Override
    public void updateeventemployee(List<EmployeeNeed> employees, EventOrder event, float employeeprice) {
        for (EmployeeNeed fn : employees) {
            em.persist(fn);
            System.err.println("after persist facility need in for loop");
        }
        System.err.println("after the for loop");

        event.setEmployeeprice(employeeprice);
        float eventprice = employeeprice + event.getFacilityprice() + event.getVenueprice()+event.getFoodprice();
        event.setTotalprice(eventprice);
        float eventcharges = employeeprice + event.getEmployeeprice()+event.getFoodprice();
        event.setTotalcharge(eventcharges);
        event.setEmployeesNeed(employees);
        em.merge(event);
    }

    @Override
    public void updateevent(List<FacilityNeed> facilities, EventOrder event, float facilityprice) {
        Client client=event.getClient();
        System.err.println("client is "+client);
        System.err.println("the ic of client is "+client.getIc());
//                Client client=event.getClient();
//                List<ClientBill> clientbills=client.getClientbills();
//                List<EventOrder> events=new ArrayList();
//                events.remove(event);
        for (FacilityNeed fn : facilities) {
            em.persist(fn);
            System.err.println("after persist facility need in for loop");
        }
        System.err.println("after the for loop");

        event.setFacilityprice(facilityprice);
        float eventprice = facilityprice + event.getEmployeeprice() + event.getVenueprice()+event.getFoodprice();
        event.setTotalprice(eventprice);
        float eventcharges = facilityprice + event.getEmployeeprice()+event.getFoodprice();
        event.setTotalcharge(eventcharges);
        event.setFacilitiesNeed(facilities);
//        events.add(event);
//        client.setEvents(events);
//        for(ClientBill temp: clientbills){
//            temp.setClient(client);
//            em.merge(temp);
//        }
//        em.merge(client);
        em.merge(event);
    }

    @Override
    public void cancelEvent(EventOrder event) {
        System.err.println("got into cancelevent in sessionbean");
        List<ConventionSchedule> rightones = new ArrayList();
        if (event.getOpenspace() != null) {
            OpenSpace op = event.getOpenspace();
            Date start = event.getDaytime().getStartingDate();
            Date end = event.getDaytime().getEndingDate();
            List<ConventionSchedule> css = op.getCschedules();
            for (ConventionSchedule cs : css) {
                if (cs.getEventdate().compareTo(end) <= 0 && cs.getEventdate().compareTo(start) >= 0) {
                    rightones.add(cs);
                }
            }
            System.err.println("the size of removed schedule is " + rightones.size());
            for (ConventionSchedule con : rightones) {
                css.remove(con);
                op.setCschedules(css);
                ConventionSchedule toberemoved = em.merge(con);
                em.remove(toberemoved);
            }
            System.err.println("before go into the merge op");
            em.merge(op);
            System.err.println("after merge the openspace");
        } else if (event.getAuditorium() != null) {
            Auditorium au = event.getAuditorium();
            Date start = event.getDaytime().getStartingDate();
            Date end = event.getDaytime().getEndingDate();
            List<ConventionSchedule> css = au.getCschedules();
            for (ConventionSchedule cs : css) {
                if (cs.getEventdate().compareTo(end) <= 0 && cs.getEventdate().compareTo(start) >= 0) {
                    rightones.add(cs);
                }
            }
            for (ConventionSchedule con : rightones) {
                css.remove(con);
                au.setCschedules(css);
                ConventionSchedule toberemoved = em.merge(con);
                em.remove(toberemoved);
            }
            em.merge(au);

        } else if (event.getOthervenue() != null) {
            if (event.getDaytime() != null) {
                OtherVenue ov = event.getOthervenue();
                Date start = event.getDaytime().getStartingDate();
                Date end = event.getDaytime().getEndingDate();
                List<ConventionSchedule> css = ov.getCschedules();
                for (ConventionSchedule cs : css) {
                    if (cs.getEventdate().compareTo(end) <= 0 && cs.getEventdate().compareTo(start) >= 0) {
                        rightones.add(cs);

                    }
                }
                for (ConventionSchedule con : rightones) {
                    css.remove(con);
                    ov.setCschedules(css);
                    ConventionSchedule toberemoved = em.merge(con);
                    em.remove(toberemoved);
                }
                em.merge(ov);

            } else if (event.getHalfdaytime() != null) {
                OtherVenue ov = event.getOthervenue();
                List<ConventionSchedule> css = ov.getCschedules();
                for (ConventionSchedule cs : css) {
                    if (cs.getHalfdaytimes().contains(event.getHalfdaytime())) {
                        rightones.add(cs);

                    }
                }
                for (ConventionSchedule con : rightones) {
                    css.remove(con);
                    if (con.getHalfdaytimes().size() == 1) {
                        ConventionSchedule toberemoved = em.merge(con);
                        em.remove(toberemoved);
                    } else {
                        List<HalfDayTime> temp = con.getHalfdaytimes();
                        temp.remove(event.getHalfdaytime());
                        con.setHalfdaytimes(temp);
                        em.merge(con);
                        css.add(con);
                    }
                    ov.setCschedules(css);
                }
                em.merge(ov);

            } else{
                OtherVenue ov = event.getOthervenue();
                List<ConventionSchedule> css = ov.getCschedules();
                for (ConventionSchedule cs : css) {
                    if (cs.getHourtimes().contains(event.getHourtime())) {
                        rightones.add(cs);
                    }
                }
                for (ConventionSchedule con : rightones) {
                    css.remove(con);
                    if (con.getHourtimes().size() == 1) {
                        ConventionSchedule toberemoved = em.merge(con);
                        em.remove(toberemoved);
                    } else {
                        List<HourTime> temp = con.getHourtimes();
                        temp.remove(event.getHourtime());
                        con.setHourtimes(temp);
                        em.merge(con);
                        css.add(con);
                    }
                    ov.setCschedules(css);
                }
                em.merge(ov);
            }
        } else{
            System.err.println("the event exhibitionsection is " + event.getExhibitionsections());
            Date start = event.getDaytime().getStartingDate();
            Date end = event.getDaytime().getEndingDate();
            for (ExhibitionSection es : event.getExhibitionsections()) {
                rightones = new ArrayList();
                List<ConventionSchedule> css = es.getCschedules();
                for (ConventionSchedule cs : css) {
                    if (cs.getEventdate().compareTo(end) <= 0 && cs.getEventdate().compareTo(start) >= 0) {
                        rightones.add(cs);
                    }
                }
                for (ConventionSchedule con : rightones) {
                    css.remove(con);
                    es.setCschedules(css);
                    ConventionSchedule toberemoved = em.merge(con);
                    em.remove(toberemoved);
                }

                em.merge(es);
            }

        }
        System.err.println("before merge client and remove client bill");

        Client client = event.getClient();
        //  event.setClient(null);
        List<EventOrder> events = client.getEvents();
        events.remove(event);
        client.setEvents(events);

        List<ClientBill> bills = client.getClientbills();
        ClientBill rightone = new ClientBill();
        Iterator<ClientBill> v = bills.iterator();
        while (v.hasNext()) {
            ClientBill temp = v.next();
            if (temp.getEvents().contains(event)) {
                rightone = temp;
                //        
            }
        }
        List<EventOrder> allbillevents = rightone.getEvents();
        bills.remove(rightone);
        client.setClientbills(bills);
        em.merge(client);
        ClientBill toremove = em.merge(rightone);
        em.remove(toremove);

        for (EventOrder eo : allbillevents) {
            EventOrder toberemoved = em.merge(eo);
            em.remove(toberemoved);
            System.err.println("after remove the event");
        }
    }

    @Override
    public List<EventOrder> listCEevents() {
        List<EventOrder> temp = new ArrayList();
        Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type=:type");
        q.setParameter("type", "ConventionExhibition");
        for (Object o : q.getResultList()) {
            EventOrder event = (EventOrder) o;
            temp.add(event);
        }
        return temp;
    }
    
    
    @Override
     public List<EventOrder> listBanqeutevents(){
           List<EventOrder> temp = new ArrayList();
        Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type=:type");
        q.setParameter("type", "Banquet");
        for (Object o : q.getResultList()) {
            EventOrder event = (EventOrder) o;
            temp.add(event);
        }
        return temp;
         
     }
    
    @Override
    public List<EventOrder> listBanquettodayevents(){
          List<EventOrder> temp = new ArrayList();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date today = sdf.parse(sdf.format(new Date()));
            Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type=:type");
            q.setParameter("type", "Banquet");
            for (Object o : q.getResultList()) {
                EventOrder event = (EventOrder) o;
                switch (event.getDatetype()) {
                    case "Day":
                        if (event.getDaytime().getStartingDate().compareTo(today) <= 0 && event.getDaytime().getEndingDate().compareTo(today) >= 0) {
                            temp.add(event);
                        }
                        break;
                    case "HalfDay":
                        if (event.getHalfdaytime().getEventdate().equals(today)) {
                            temp.add(event);
                        }
                        break;
                    default:
                        if (event.getHourtime().getEventDate().equals(today)) {
                            temp.add(event);
                        }
                        break;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(EventManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    @Override
    public List<EventOrder> listCEtodayevents() {
        List<EventOrder> temp = new ArrayList();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date today = sdf.parse(sdf.format(new Date()));
            Query q = em.createQuery("SELECT e FROM EventOrder e WHERE e.type=:type");
            q.setParameter("type", "ConventionExhibition");
            for (Object o : q.getResultList()) {
                EventOrder event = (EventOrder) o;
                switch (event.getDatetype()) {
                    case "Day":
                        System.err.println("today is "+today);
                        System.err.println("startingdate is "+event.getDaytime().getStartingDate().compareTo(today));
                        System.err.println("endingdate is "+event.getDaytime().getEndingDate().compareTo(today));
                        if (event.getDaytime().getStartingDate().compareTo(today) <= 0 && event.getDaytime().getEndingDate().compareTo(today) >= 0) {
                            temp.add(event);
                        }
                        break;
                    case "HalfDay":
                        if (event.getHalfdaytime().getEventdate().equals(today)) {
                            temp.add(event);
                        }
                        break;
                    default:
                        if (event.getHourtime().getEventDate().equals(today)) {
                            temp.add(event);
                        }
                        break;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(EventManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    @Override
    public void createEvent(EventOrder event, ConventionSchedule schedule) {
        //client in event do not have clientbill
        em.persist(event);
        System.err.println("after persist event");

        //set event to client except clientbill
        Client client = event.getClient();
        List<EventOrder> events = client.getEvents();
        events.add(event);
        client.setEvents(events);
        em.merge(client);
        System.err.println("client updated");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        ClientBill clientbill = new ClientBill();
        clientbill.setBillDate(today);
        clientbill.setBillDateString(sdf.format(today));
        clientbill.setClient(client);
        List<EventOrder> billevents = new ArrayList();
        billevents.add(event);
        clientbill.setEvents(billevents);
        clientbill.setTotalprice(event.getTotalprice());
        clientbill.setTotalcharges(event.getTotalcharge());
        clientbill.setStatus("FirstPaid");
        clientbill.setPayer(client.getFirstname() + " " + client.getLastname());
        clientbill.setReceiver("Coral Island Resort");
        em.persist(clientbill);
        System.err.println("after persist client bill");


        List<ClientBill> temp = client.getClientbills();
        temp.add(clientbill);
        client.setClientbills(temp);
        em.merge(client);
        System.err.println("after merge the client");

        event.setClient(client);
        em.merge(event);
        this.createSchedule(schedule);  //for other venue
    }
    
    public void createEventBanquet(EventOrder banquet){
        em.persist(banquet);
        
         Client client = banquet.getClient();
        List<EventOrder> events = client.getEvents();
        events.add(banquet);
        client.setEvents(events);
        em.merge(client);
      List<ClientBill> clientbill=client.getClientbills();
        System.err.println("client updated");
        
        EventOrder event=em.find(EventOrder.class, banquet.getEventorderid());
        ClientBill temp=new ClientBill();
        List<ClientBill> bills=client.getClientbills();
        for(ClientBill bill:bills){
            if(bill.getEvents().contains(event)){
                temp=bill;
            }
        }
        clientbill.remove(temp);
        List<EventOrder> billevents=temp.getEvents();
        billevents.add(banquet);
        temp.setEvents(billevents);
        em.merge(temp);
        System.err.println("after updat the clientbill");
        
        clientbill.add(temp);
        client.setClientbills(clientbill);
        em.merge(client);
        System.err.println("after update the client");
        
        banquet.setClient(client);
        em.merge(banquet);
        System.err.println("after update the banquet");
       
    }

    @Override
    public Long createEventDay(EventOrder event, List<ConventionSchedule> schedules) {
        //client in event do not have clientbill
//        if(!event.getExhibitionsections().isEmpty()){
//            System.err.println("event exhibition secitions are "+event.getExhibitionsections());
//        }
        
        System.err.println("the event exhibition sections are "+event.getExhibitionsections());
        em.persist(event);
        System.err.println("after persist event");

        //set event to client except clientbill
        Client client = event.getClient();
        List<EventOrder> events = client.getEvents();
        events.add(event);
        client.setEvents(events);
        em.merge(client);
        System.err.println("client updated");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        ClientBill clientbill = new ClientBill();
        clientbill.setBillDate(today);
        clientbill.setBillDateString(sdf.format(today));
        clientbill.setClient(client);
        List<EventOrder> billevents = new ArrayList();
        billevents.add(event);
        clientbill.setEvents(billevents);
        clientbill.setTotalprice(event.getTotalprice());
        clientbill.setTotalcharges(event.getTotalcharge());
        clientbill.setStatus("FirstPaid");
        clientbill.setPayer(client.getFirstname() + " " + client.getLastname());
        clientbill.setReceiver("Coral Island Resort");
        em.persist(clientbill);
        System.err.println("after persist client bill");


        List<ClientBill> temp = client.getClientbills();
        temp.add(clientbill);
        client.setClientbills(temp);
        em.merge(client);
        System.err.println("after merge the client");

        event.setClient(client);
        em.merge(event);
        this.createSchedules(schedules);
        return event.getId();
    }

    @Override
    public void createSchedules(List<ConventionSchedule> schedules) {

        if (schedules.get(0).getOpenspace() != null) {
            for (ConventionSchedule temp : schedules) {
                em.persist(temp);
            }
            OpenSpace op = schedules.get(0).getOpenspace();
            List<ConventionSchedule> temp = op.getCschedules();
            temp.addAll(schedules);
            op.setCschedules(temp);
            em.merge(op);
        } else if (schedules.get(0).getAuditorium() != null) {
            for (ConventionSchedule temp : schedules) {
                em.persist(temp);
            }
            Auditorium au = schedules.get(0).getAuditorium();
            List<ConventionSchedule> temp = au.getCschedules();
            temp.addAll(schedules);
            au.setCschedules(temp);
            em.merge(au);
        } else if (schedules.get(0).getOthervenue() != null) {
            for (ConventionSchedule temp : schedules) {
                em.persist(temp);
            }
            OtherVenue ov = schedules.get(0).getOthervenue();
            List<ConventionSchedule> temp = ov.getCschedules();
            temp.addAll(schedules);
            ov.setCschedules(temp);
            em.merge(ov);
        }else if(schedules.get(0).getTheater()!=null){
            for(ConventionSchedule temp: schedules){
                em.persist(temp);
            }
            Theater th=schedules.get(0).getTheater();
            List<ConventionSchedule> temp=th.getCschedules();
            temp.addAll(schedules);
            em.merge(th);
        } 
        else{
            List<ConventionSchedule> tempschedule = new ArrayList();
            ConventionSchedule temp = new ConventionSchedule();
            for (ConventionSchedule cs : schedules) {
                if (cs.getId() != null) {
                    em.merge(cs);
                } else {
                    em.persist(cs);
                    ExhibitionSection es = cs.getExhibitionsection();
                    List<ConventionSchedule> tempschedules = es.getCschedules();
                    if (!temp.equals(cs)) {
                        temp = cs;
                        tempschedules.addAll(tempschedule);
                        es.setCschedules(tempschedules);
                        em.merge(es);
                        tempschedule = new ArrayList();
                        tempschedule.add(cs);
                    } else {
                        tempschedule.add(cs);
                    }
                }
            }
            ExhibitionSection ese = tempschedule.get(0).getExhibitionsection();
            List<ConventionSchedule> css = ese.getCschedules();
            css.addAll(tempschedule);
            ese.setCschedules(css);
            em.merge(ese);

        }
    }

    @Override
    public void createSchedule(ConventionSchedule schedule) throws IllegalArgumentException {

        if (schedule.getId() != null) {
            em.merge(schedule);
        } else {
            em.persist(schedule);
            if (schedule.getOthervenue() != null) {
                OtherVenue ov = schedule.getOthervenue();
                List<ConventionSchedule> temp = ov.getCschedules();
                temp.add(schedule);
                ov.setCschedules(temp);
                em.merge(ov);
            }
        }
    }

    @Override
    public DayTime createDayTime(Date StartingDate, Date EndingDate) {
        DayTime dayt = new DayTime();
        dayt.setStartingDate(StartingDate);
        dayt.setEndingDate(EndingDate);
        em.persist(dayt);
        return dayt;
    }

    @Override
    public boolean addNewClient(Client client) {
        Client clientfind = em.find(Client.class, client.getIc());
        if (clientfind == null) {
            em.persist(client);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateclient(Client client) {
        em.merge(client);
    }

    @Override
    public List<Client> getAllclient() {
        List<Client> clients = new ArrayList();
        Query q = em.createQuery("SELECT c FROM Client c");
        for (Object o : q.getResultList()) {
            Client c = (Client) o;
            clients.add(c);
        }
        return clients;
    }
}
