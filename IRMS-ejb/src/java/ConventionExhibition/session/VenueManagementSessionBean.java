package ConventionExhibition.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.ExhibitionHall;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VenueManagementSessionBean implements VenueManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ExhibitionSection> searchExhibitionhall(int capacity, Date startingDate, Date endingDate) {
        List<ExhibitionSection> tempsection = new ArrayList();
        Query q = em.createQuery("SELECT e FROM ExhibitionHall e");

        for (Object o : q.getResultList()) {
            ExhibitionHall ex = (ExhibitionHall) o;
            if (ex.getCapacity() >= capacity) {
                List<ExhibitionSection> temp = ex.getSections();
                for (ExhibitionSection es : temp) {
                    int i = 0;
                    for (ConventionSchedule cs : es.getCschedules()) {
                        if (cs.getEventdate().compareTo(startingDate) >= 0 && cs.getEventdate().compareTo(endingDate) <= 0) {
                            i++;
                        }
                    }
                    if (i == 0) {
                        tempsection.add(es);
                    }
                }
            }
        }
        return tempsection;
    }

    @Override
    public List<Auditorium> searchAuditorium(int capacity, Date startingDate, Date endingDate) {
        List<Auditorium> temp = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Auditorium a");

        for (Object o : q.getResultList()) {
            int i = 0;
            Auditorium au = (Auditorium) o;
            if (au.getCapacity() >= capacity) {
                for (ConventionSchedule cs : au.getCschedules()) {
                    if (!cs.getEventdate().before(startingDate) && !cs.getEventdate().after(endingDate)) {
                        i++;
                    }
                }
                if (i == 0) {
                    temp.add(au);
                }
            }
        }
        return temp;
    }
    
    @Override
    public List<Theater> searchTheater(int capacity,Date startingDate, Date endingDate){
         List<Theater> temp = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Theater a");

        for (Object o : q.getResultList()) {
            int i = 0;
            Theater au = (Theater) o;
            if (au.getCapacity() >= capacity) {
                for (ConventionSchedule cs : au.getCschedules()) {
                    if (!cs.getEventdate().before(startingDate) && !cs.getEventdate().after(endingDate)) {
                        i++;
                    }
                }
                if (i == 0) {
                    temp.add(au);
                }
            }
        }
        return temp;
        
    }

    @Override
    public List<OpenSpace> searchOpenSpace(int capacity, Date startingDate, Date endingDate) {
        System.err.println("the startingDate is "+startingDate);
        System.err.println("the endingDate is "+endingDate);
        List<OpenSpace> temp = new ArrayList();
        Query q = em.createQuery("SELECT o FROM OpenSpace o");

        for (Object o : q.getResultList()) {
            int i = 0;
            OpenSpace op = (OpenSpace) o;
            if (op.getCapacity() >= capacity) {
                for (ConventionSchedule cs : op.getCschedules()) {
                    System.err.println("the cschedule data is "+cs.getEventdate());
                    if (!cs.getEventdate().before(startingDate) && !cs.getEventdate().after(endingDate)) {
                        System.err.println("this venue is not available");
                        i++;
                    }
                }
                if (i == 0) {
                    temp.add(op);
                }
            }
        }
        return temp;
    }
    
    @Override
    public List<BanquetHall> searchBanquetHallDay(int capacity,Date startingDate, Date endingDate){
          List<BanquetHall> temp = new ArrayList();
        Query q = em.createQuery("SELECT b FROM BanquetHall b");
        for (Object o : q.getResultList()) {
            int i = 0;
            BanquetHall banquet = (BanquetHall) o;
            if (banquet.getCapacity() >= capacity) {
                for (ConventionSchedule cs : banquet.getCschedules()) {
                    if (!cs.getEventdate().before(startingDate) && !cs.getEventdate().after(endingDate)) {
                        i++;
                        System.err.println("the conflict schedule is "+cs);
                    }
                }
                if (i == 0) {
                    temp.add(banquet);
                }
            }
        }
        return temp;
    }

    @Override
    public List<OtherVenue> searchOtherVenueDay(int capacity, Date startingDate, Date endingDate, String venuetype) {
        List<OtherVenue> temp = new ArrayList();
        Query q = em.createQuery("SELECT o FROM OtherVenue o WHERE o.type=:type");
        q.setParameter("type", venuetype);
        for (Object o : q.getResultList()) {
            int i = 0;
            OtherVenue ov = (OtherVenue) o;
            if (ov.getCapacity() >= capacity) {
                for (ConventionSchedule cs : ov.getCschedules()) {
                    if (!cs.getEventdate().before(startingDate) && !cs.getEventdate().after(endingDate)) {
                        i++;
                    }
                }
                if (i == 0) {
                    temp.add(ov);
                }
            }
        }
        return temp;
    }

    
    @Override
    public List<OtherVenue> searchOtherVenueHour(int capacity, Date eventDate, Date startingTime, Date endingTime, String venuetype) {
        System.err.println("go into searchothervenue hour session bean");
        List<OtherVenue> temp = new ArrayList();
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventDate);
        ca.set(Calendar.HOUR_OF_DAY, 9);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date morningstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 13);
        Date morningend = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 15);
        Date afternoonstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 21);
        Date afternoonend = ca.getTime();

        System.err.println("the venuetype is "+venuetype);
        Query q = em.createQuery("SELECT o FROM OtherVenue o WHERE o.type=:type");
        q.setParameter("type", venuetype);
        Query query=em.createQuery("SELECT o FROM OtherVenue o");
        for(Object obj:query.getResultList()){
            OtherVenue ove=(OtherVenue) obj;
            System.err.println("the ove type is "+ove.getType());
            if(ove.getType().equals(venuetype)){
                System.err.println("the other venue is "+ove);
            }
        }
        for (Object o : q.getResultList()) {
            System.err.println("go into one othervenue");
            int i = 0;
            OtherVenue ov = (OtherVenue) o;
            for (ConventionSchedule cs : ov.getCschedules()) {
                if (cs.getEventdate().compareTo(eventDate) == 0) {
                    if (cs.getDaytime() != null) {
                        System.err.println("go into daytime");
                        i++;
                    } else {
                        for (HalfDayTime hd : cs.getHalfdaytimes()) {
                            System.err.println(" go into halfdaytime");
                            if (hd.getEventPeriod().equals("morning")) {
                                System.err.println("in venue session bean start time is "+startingTime);
                                System.err.println("in venue session bean the afternoonstart is"+afternoonstart);
                                if (startingTime.before(afternoonstart)) {
                                    i++;
                                }
                            } else {
                                if (endingTime.after(morningend)) {
                                    i++;
                                }
                            }
                        }
                        if (i == 0) {
                            for (HourTime ht : cs.getHourtimes()) {
                                System.err.println("get into the hourtimes in session bean");

                                ca.setTime(ht.getStartTime());
                                ca.add(Calendar.HOUR_OF_DAY, -2);
                                Date htstart = ca.getTime();

                                ca.setTime(ht.getEndTime());
                                ca.add(Calendar.HOUR_OF_DAY, 2);
                                Date htend = ca.getTime();

                                if (startingTime.before(htend) && endingTime.after(htstart)) {
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
            if (i == 0) {
                temp.add(ov);
            }
        }
        return temp;
    }
    
    @Override
      public List<BanquetHall> searchBanquetHallHour(int capacity,Date eventDate, Date startingTime,Date endingTime){
           System.err.println("go into searchothervenue hour session bean");
        List<BanquetHall> temp = new ArrayList();
        Calendar ca = Calendar.getInstance();
        ca.setTime(eventDate);
        ca.set(Calendar.HOUR_OF_DAY, 9);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date morningstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 13);
        Date morningend = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 15);
        Date afternoonstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 21);
        Date afternoonend = ca.getTime();

        Query q = em.createQuery("SELECT b FROM BanquetHall b");
       
        for (Object o : q.getResultList()) {
            System.err.println("go into one othervenue");
            int i = 0;
            BanquetHall ov = (BanquetHall) o;
            for (ConventionSchedule cs : ov.getCschedules()) {
                if (cs.getEventdate().compareTo(eventDate) == 0) {
                    if (cs.getDaytime() != null) {
                        System.err.println("go into daytime");
                        i++;
                    } else {
                        for (HalfDayTime hd : cs.getHalfdaytimes()) {
                            System.err.println(" go into halfdaytime");
                            if (hd.getEventPeriod().equals("morning")) {
                                System.err.println("in venue session bean start time is "+startingTime);
                                System.err.println("in venue session bean the afternoonstart is"+afternoonstart);
                                if (startingTime.before(afternoonstart)) {
                                    i++;
                                }
                            } else {
                                if (endingTime.after(morningend)) {
                                    i++;
                                }
                            }
                        }
                        if (i == 0) {
                            for (HourTime ht : cs.getHourtimes()) {
                                System.err.println("get into the hourtimes in session bean");

                                ca.setTime(ht.getStartTime());
                                ca.add(Calendar.HOUR_OF_DAY, -2);
                                Date htstart = ca.getTime();

                                ca.setTime(ht.getEndTime());
                                ca.add(Calendar.HOUR_OF_DAY, 2);
                                Date htend = ca.getTime();

                                if (startingTime.before(htend) && endingTime.after(htstart)) {
                                    i++;
                                }
                            }
                        }
                    }
                }
            }
            if (i == 0) {
                temp.add(ov);
            }
        }
        return temp;
          
      }


    @Override
    public List<ExhibitionSection> getAllExhibitionSection() {
        List<ExhibitionSection> exs = new ArrayList();
        Query q = em.createQuery("SELECT e FROM ExhibitionSection e");
        for (Object o : q.getResultList()) {
            ExhibitionSection ex = (ExhibitionSection) o;
            exs.add(ex);
        }
        return exs;
    }

    @Override
    public List<Auditorium> getAllAuditorium() {
        List<Auditorium> aus = new ArrayList();
        Query q = em.createQuery("SELECT a FROM Auditorium a");
        for (Object o : q.getResultList()) {
            Auditorium au = (Auditorium) o;
            aus.add(au);
        }
        System.err.println("end of auditorium");
        return aus;
    }

    @Override
    public List<OpenSpace> getAllOpenSpace() {
        List<OpenSpace> ops = new ArrayList();
        Query q = em.createQuery("SELECT e FROM OpenSpace e");
        for (Object o : q.getResultList()) {
            OpenSpace op = (OpenSpace) o;
            ops.add(op);
        }
        return ops;
    }

    @Override
    public List<OtherVenue> getAllOtherVenue() {
        List<OtherVenue> exs = new ArrayList();
        Query q = em.createQuery("SELECT e FROM OtherVenue e");
        for (Object o : q.getResultList()) {
            OtherVenue ex = (OtherVenue) o;
            exs.add(ex);
        }
        return exs;
    }

   

    @Override
    public List<String> getvenueName() {
        List<String> venues = new ArrayList();
        for (Auditorium cau : this.getAllAuditorium()) {
            venues.add(cau.getId() + " Auditorium " + cau.getRoomNo());
        }
        for (OpenSpace cop : this.getAllOpenSpace()) {
            venues.add(cop.getId() + " Open Space " + cop.getLocation());
        }
        for (OtherVenue cov : this.getAllOtherVenue()) {
            venues.add(cov.getId() + " " + cov.getType() + " " + cov.getRoomNo());
        }
       
        for (ExhibitionSection ces : this.getAllExhibitionSection()) {
            venues.add(ces.getId() + " Exhibition Hall " + ces.getExhibitionhall().getRoomNo() + " " + ces.getSectionNo());
        }
        return venues;
    }

    @Override
    public List<EventOrder> listOneVenueEvent(String venue) {
        List<EventOrder> temp = new ArrayList();
        Long id;
        Query q = em.createQuery("SELECT e FROM EventOrder e");
        if (venue.contains("Auditorium")) {
            id = Long.parseLong(venue.split(" ")[0]);
            Auditorium au = em.find(Auditorium.class, id);
            for (Object o : q.getResultList()) {
                EventOrder eo = (EventOrder) o;
                if (eo.getAuditorium().equals(au)&&!eo.getStatus().equals("Cancelled")) {
                    temp.add(eo);
                }
            }
        } else if (venue.contains("Open Space")) {
            id = Long.parseLong(venue.split(" ")[0]);
            OpenSpace op = em.find(OpenSpace.class, id);
            for (Object o : q.getResultList()) {
                EventOrder eo = (EventOrder) o;
                if (eo.getOpenspace().equals(op)&&!eo.getStatus().equals("Cancelled")) {
                    temp.add(eo);
                }
            }
        } else if (venue.contains("Exhibition Hall")) {
            id = Long.parseLong(venue.split(" ")[0]);
            ExhibitionSection es = em.find(ExhibitionSection.class, id);
            for (Object o : q.getResultList()) {
                EventOrder eo = (EventOrder) o;
                for (ExhibitionSection ces : eo.getExhibitionsections()) {
                    if (ces.equals(es)&&!eo.getStatus().equals("Cancelled")) {
                        temp.add(eo);
                    }
                }
            }
        } else {
            id = Long.parseLong(venue.split(" ")[0]);
            OtherVenue es = em.find(OtherVenue.class, id);
            for (Object o : q.getResultList()) {
                EventOrder eo = (EventOrder) o;
                if (eo.getOthervenue().equals(es)&&!eo.getStatus().equals("Cancelled")) {
                    temp.add(eo);
                }
            }
        }
        System.err.println("before return event of one venue in session bean");
        return temp;
    }
}
