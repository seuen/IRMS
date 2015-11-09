/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.HalfDayTime;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.entity.DayTime;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.SeatSection;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.Theater;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author xing zhe
 */
@Stateless
public class ESInfoManagementSessionBean implements ESInfoManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean addShowInfo(ShowInfo showInfo) {
        em.persist(showInfo);
        return true;
    }

    @Override
    public boolean deleteShowInfo(Long showInfoId) {
        ShowInfo showInfo = em.find(ShowInfo.class, showInfoId);
        em.remove(showInfo);
        em.flush();
        return true;
    }

    @Override
    public boolean updateShowInfo(ShowInfo theShowInfo) {
        em.merge(theShowInfo);
        return true;
    }

    @Override
    public boolean updateSectionTicket(SectionTicket st, ESShow show) {
        em.merge(st);
        return true;
    }

    @Override
    public List<ShowInfo> listAllShowInfos() {
        Query q = em.createQuery("Select s from ShowInfo s ");
        return q.getResultList();
    }

    @Override
    public List<ESShow> listAllShows() {
        Query q = em.createQuery("SELECT s FROM ESShow s");
        return q.getResultList();
    }

    @Override
    public boolean createShowInfo(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, ShowInfo theShowInfo) {
        System.out.println(startTimeShow + " " + endTimeShow);
        theShowInfo.setDuration((float) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600));
//        theShowInfo.setTheater(theTheater);
        System.err.println("start date is "+startDate);
        System.err.println("end date is "+endDate);
        theShowInfo.setStartDate(startDate);
        theShowInfo.setEndDate(endDate);
        theShowInfo.setType("External");
        em.persist(theShowInfo);
//        theTheater.getShowInfos().add(theShowInfo);
//        em.merge(theTheater);

        //make sure everyday already has a schedule entity success
        Date d = startDate;
        List<ESShow> shows = new ArrayList();

        System.err.println("do while loop for schedule entity");
        System.out.println(d);

        while (!d.after(endDate)) {
            int duration = (int) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600);
            ESShow show = new ESShow();
            show.setDuration(duration);
            show.setShowInfo(theShowInfo);
            show.setsDate(d);
            show.setStartTime(startTimeShow);
            em.persist(show);
            shows.add(show);
            
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);
            d = c.getTime();
        }

        theShowInfo.setShows(shows);
        em.merge(theShowInfo);


        List<ESShow> esshows = (List<ESShow>) theShowInfo.getShows();

        for (ESShow sw : theShowInfo.getShows()) {
            System.err.println("showwwww");
            List<SectionTicket> sts = new ArrayList();

//            switch (getVenue()) {
//                case "Theater":
//                    for (SeatSection ss : theTheater.getSeatSections()) {
//                        System.out.println("add sectionTicket");
//                        SectionTicket st = new SectionTicket();
//                        st.setAvailableNum(ss.getTotalNum());
//                        st.setSeatSection(ss);
//                        st.setShow(sw);
//                        st.setStatus("Available");
//                        em.persist(st);
//                        sts.add(st);
//                    }
//                case "Auditorium":
//                    for (SeatSection ss : theAuditorium.getSeatSections()) {
//                        System.out.println("add sectionTicket");
//                        SectionTicket st = new SectionTicket();
//                        st.setAvailableNum(ss.getTotalNum());
//                        st.setSeatSection(ss);
//                        st.setShow(sw);
//                        st.setStatus("Available");
//                        em.persist(st);
//                        sts.add(st);
//                    }
//
//            }


            sw.setSectionTickets(sts);
            em.merge(sw);
            esshows.set(esshows.indexOf(sw), sw);
        }

        em.merge(theShowInfo);
        em.flush();

        return true;
    }

    @Override
    public List<Theater> searchTheaters(int capacity, Date startDate, Date endDate, Date startTime, Date endTime) {
        List<Theater> temp = new ArrayList();
        List<Theater> allTheaters = new ArrayList();
        Calendar ca = Calendar.getInstance();

        ca.set(Calendar.HOUR_OF_DAY, 9);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date morningstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 13);
        Date morningend = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 17);
        Date afternoonstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 21);
        Date afternoonend = ca.getTime();

        Query q = em.createQuery("SELECT t FROM Theater t");
        allTheaters = q.getResultList();

        for (Theater theater : allTheaters) {
            int i = 0;
            if (theater.getCapacity() >= capacity) {
                for (ConventionSchedule cs : theater.getCschedules()) {
                    if (cs.getEventdate().compareTo(startDate) >= 0 && cs.getEventdate().compareTo(endDate) <= 0) {
                        if (cs.getDaytime() != null) {
                            System.err.println("cs.getDayTime not null");
                            i++;
                        } else {
                            System.err.println("cs.getDayTime IS NULL");
                            for (HalfDayTime hd : cs.getHalfdaytimes()) {
                                if (hd.getEventPeriod().equals("morning")) {
                                    if (startTime.before(afternoonstart)) {
                                        i++;
                                    }
                                } else {
                                    if (endTime.before(morningend)) {
                                        i++;
                                    }
                                }
                            }

                            if (i == 0) {
                                System.err.println("cs.getHalf DayTime IS OK");
                                for (HourTime ht : cs.getHourtimes()) {
                                    ca.setTime(ht.getStartTime());
                                    Date htstart = ca.getTime();

                                    ca.setTime(ht.getEndTime());
                                    Date htend = ca.getTime();

                                    if (startTime.before(htend) && endTime.after(htstart)) {
                                        i++;
                                    }
                                }

                            }
                        }
                    }
                }

                if (i == 0) {
                    temp.add(theater);
                }
            }
        }
        System.err.println("it is the end here");
        return temp;
    }

    @Override
    public List<Auditorium> searchAuditoriums(int capacity, Date startDate, Date endDate, Date startTime, Date endTime) {
        List<Auditorium> temp = new ArrayList();
        List<Auditorium> allAuditoriums = new ArrayList();
        Calendar ca = Calendar.getInstance();

        ca.set(Calendar.HOUR_OF_DAY, 9);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        Date morningstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 13);
        Date morningend = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 17);
        Date afternoonstart = ca.getTime();

        ca.set(Calendar.HOUR_OF_DAY, 21);
        Date afternoonend = ca.getTime();

        Query q = em.createQuery("SELECT a FROM Auditorium a");
        allAuditoriums = q.getResultList();

        for (Auditorium auditorium : allAuditoriums) {
            int i = 0;
            if (auditorium.getCapacity() >= capacity) {
                for (ConventionSchedule cs : auditorium.getCschedules()) {
                    if (cs.getEventdate().compareTo(startDate) >= 0 && cs.getEventdate().compareTo(endDate) <= 0) {
                        if (cs.getDaytime() != null) {
                            System.err.println("cs.getDayTime not null");
                            i++;
                        } else {
                            System.err.println("cs.getDayTime IS NULL");
                            for (HalfDayTime hd : cs.getHalfdaytimes()) {
                                if (hd.getEventPeriod().equals("morning")) {
                                    if (startTime.before(afternoonstart)) {
                                        i++;
                                    }
                                } else {
                                    if (endTime.before(morningend)) {
                                        i++;
                                    }
                                }
                            }

                            if (i == 0) {
                                System.err.println("cs.getHalf DayTime IS OK");
                                for (HourTime ht : cs.getHourtimes()) {
                                    ca.setTime(ht.getStartTime());
                                    Date htstart = ca.getTime();

                                    ca.setTime(ht.getEndTime());
                                    Date htend = ca.getTime();

                                    if (startTime.before(htend) && endTime.after(htstart)) {
                                        i++;
                                    }
                                }

                            }
                        }
                    }
                }

                if (i == 0) {
                    temp.add(auditorium);
                }
            }
        }
        System.err.println("it is the end here");
        return temp;
    }

    @Override
    public boolean addAuditoriumShow(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, Date startTime, Date endTime, ShowInfo theShowInfo, Auditorium theAuditorium) {
        System.err.println("sessionBean: addTheaterShow");

        //add showInfo success
        System.out.println(startTimeShow + " " + endTimeShow);
        theShowInfo.setDuration((float) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600));
        theShowInfo.setAuditorium(theAuditorium);
        theShowInfo.setStartDate(startDate);
        theShowInfo.setEndDate(endDate);
        theShowInfo.setType("Internal");
        em.persist(theShowInfo);
        theAuditorium.getShowInfos().add(theShowInfo);
        em.merge(theAuditorium);

        System.out.println("start date is " + startDate);
        System.out.println("end date is " + endDate);

        //make sure everyday already has a schedule entity success
        Date d = startDate;
        List<ESShow> shows = new ArrayList();

        System.err.println("do while loop for schedule entity");

        while (!d.after(endDate)) {
            System.out.println("inside while loop");
            System.out.println("date is " + d);
            int temp = 0;
            for (ConventionSchedule cs : theAuditorium.getCschedules()) {
                if (cs.getEventdate().equals(d)) {
                    temp = 1;
                }
            }
            if (temp == 0) {
                ConventionSchedule s = new ConventionSchedule();
                s.setEventdate(d);
                s.setAuditorium(theAuditorium);
                em.persist(s);
                theAuditorium.getCschedules().add(s);
                em.merge(theAuditorium);
                em.flush();
            }

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);
            d = c.getTime();

        }

        //add schedule to venue
        for (ConventionSchedule cs : theAuditorium.getCschedules()) {
            System.err.println("for schedule " + cs);


            if (cs.getEventdate().compareTo(startDate) >= 0
                    && cs.getEventdate().compareTo(endDate) <= 0) {

                System.out.println("create ht");
                //persist hourtime
                HourTime ht = new HourTime();
                ht.setStartTime(startTime);
                ht.setEndTime(endTime);
                ht.setEventDate(cs.getEventdate());

                System.out.println("add show");
                //add show into showInfo
                int duration = (int) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600);
                ESShow show = new ESShow();
                show.setDuration(duration);
                show.setShowInfo(theShowInfo);
                show.setsDate(cs.getEventdate());
                show.setStartTime(startTimeShow);
                show.setHourTime(ht);
                em.persist(ht);
                em.persist(show);
                shows.add(show);

                System.out.println("update schedule and theater");
                //update schedule and theater
                cs.getHourtimes().add(ht);
                em.merge(cs);
                theAuditorium.getCschedules().set(theAuditorium.getCschedules().indexOf(cs), cs);
                em.merge(theAuditorium);
            }
        }

        System.err.println("update showInfo");
        //to update showInfo's shows
        theShowInfo.setShows(shows);
        em.merge(theShowInfo);

        List<ESShow> esshows = (List<ESShow>) theShowInfo.getShows();

        for (ESShow sw : theShowInfo.getShows()) {
            System.err.println("showwwww");
            List<SectionTicket> sts = new ArrayList();
            for (SeatSection ss : theAuditorium.getSeatSections()) {
                System.out.println("add sectionTicket");
                SectionTicket st = new SectionTicket();
                st.setAvailableNum(ss.getTotalNum());
                st.setSeatSection(ss);
                st.setShow(sw);
                st.setStatus("Available");
                em.persist(st);
                sts.add(st);
            }
            sw.setSectionTickets(sts);
            em.merge(sw);
            esshows.set(esshows.indexOf(sw), sw);
        }

        em.merge(theShowInfo);
        em.flush();

        return true;


    }

    @Override
    public boolean addTheaterShow(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, Date startTime, Date endTime, ShowInfo theShowInfo, Theater theTheater) {
        System.err.println("sessionBean: addTheaterShow");

        //add showInfo success
        System.out.println(startTimeShow + " " + endTimeShow);
        theShowInfo.setDuration((float) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600));
        theShowInfo.setTheater(theTheater);
        theShowInfo.setStartDate(startDate);
        theShowInfo.setEndDate(endDate);
        theShowInfo.setType("Internal");
        em.persist(theShowInfo);
        theTheater.getShowInfos().add(theShowInfo);
        em.merge(theTheater);

        //make sure everyday already has a schedule entity success
        Date d = startDate;
        List<ESShow> shows = new ArrayList();

        System.err.println("do while loop for schedule entity");

        while (!d.after(endDate)) {
            System.out.println("inside while loop");
            System.out.println("date is " + d);
            int temp = 0;
            for (ConventionSchedule cs : theTheater.getCschedules()) {
                if (cs.getEventdate().equals(d)) {
                    temp = 1;
                }
            }
            if (temp == 0) {
                ConventionSchedule s = new ConventionSchedule();
                s.setEventdate(d);
                s.setTheater(theTheater);
                em.persist(s);
                theTheater.getCschedules().add(s);
                em.merge(theTheater);
                em.flush();
            }

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);
            d = c.getTime();

        }

        //add schedule to venue
        for (ConventionSchedule cs : theTheater.getCschedules()) {
            System.err.println("for schedule " + cs);


            if (cs.getEventdate().compareTo(startDate) >= 0
                    && cs.getEventdate().compareTo(endDate) <= 0) {

                System.out.println("create ht");
                //persist hourtime
                HourTime ht = new HourTime();
                ht.setStartTime(startTime);
                ht.setEndTime(endTime);
                ht.setEventDate(cs.getEventdate());

                System.out.println("add show");
                //add show into showInfo
                int duration = (int) ((endTimeShow.getTime() - startTimeShow.getTime()) / 1000 / 3600);
                ESShow show = new ESShow();
                show.setDuration(duration);
                show.setShowInfo(theShowInfo);
                show.setsDate(cs.getEventdate());
                show.setStartTime(startTimeShow);
                show.setHourTime(ht);
                em.persist(ht);
                em.persist(show);
                shows.add(show);

                System.out.println("update schedule and theater");
                //update schedule and theater
                cs.getHourtimes().add(ht);
                em.merge(cs);
                theTheater.getCschedules().set(theTheater.getCschedules().indexOf(cs), cs);
                em.merge(theTheater);
            }
        }

        System.err.println("update showInfo");
        //to update showInfo's shows
        theShowInfo.setShows(shows);
        em.merge(theShowInfo);

        List<ESShow> esshows = (List<ESShow>) theShowInfo.getShows();

        for (ESShow sw : theShowInfo.getShows()) {
            System.err.println("showwwww");
            List<SectionTicket> sts = new ArrayList();
            for (SeatSection ss : theTheater.getSeatSections()) {
                System.out.println("add sectionTicket");
                SectionTicket st = new SectionTicket();
                st.setAvailableNum(ss.getTotalNum());
                st.setSeatSection(ss);
                st.setShow(sw);
                st.setStatus("Available");
                em.persist(st);
                sts.add(st);
            }
            sw.setSectionTickets(sts);
            em.merge(sw);
            esshows.set(esshows.indexOf(sw), sw);
        }

        em.merge(theShowInfo);
        em.flush();

        return true;
    }
}
