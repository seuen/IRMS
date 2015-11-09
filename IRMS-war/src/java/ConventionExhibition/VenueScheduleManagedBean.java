/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.session.VenueManagementSessionBeanLocal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Acer
 */
@ManagedBean
@ViewScoped
public class VenueScheduleManagedBean {
    
    @EJB 
    VenueManagementSessionBeanLocal vmsbl;

    private String venue;
    private List<String> venues;
    private List<EventOrder> events;
    private ScheduleModel eventmodel;
    private ScheduleEvent event;
    
    public VenueScheduleManagedBean() {
        eventmodel=new DefaultScheduleModel();
    }
    
    public void createEventModel(){
        System.err.println("has got into createeventmodel() in managedbean");
        Calendar cal = Calendar.getInstance();
        events=vmsbl.listOneVenueEvent(venue);
        event=new DefaultScheduleEvent();
        eventmodel=new DefaultScheduleModel();
        System.err.println("got the one venue event in managedbean");
        for(EventOrder currentevent: events){
            System.err.println("there is events for this venue in managedbean");
            String eventTitle=currentevent.getId()+" "+currentevent.getDescription();
            if(currentevent.getDaytime()!=null){
                System.err.println("this event is held in day in managedbean");
                cal.setTime(currentevent.getDaytime().getStartingDate());
                cal.set(Calendar.DATE, cal.get(Calendar.DATE));
                cal.set(Calendar.HOUR_OF_DAY, 9);
                Date morningstart=cal.getTime();
                System.err.println("the morningstart is "+morningstart);
                
                cal.setTime(currentevent.getDaytime().getEndingDate());
                cal.set(Calendar.DATE, cal.get(Calendar.DATE));
                cal.set(Calendar.HOUR_OF_DAY, 21);
                Date afternoonend=cal.getTime();
                System.err.println("the afternoonend is "+afternoonend);
                event=new DefaultScheduleEvent(eventTitle,morningstart,afternoonend);
                eventmodel.addEvent(event);
            }else if(currentevent.getHalfdaytime()!=null){
              
                System.err.println("this event is held in half day in managedbean");
                event=new DefaultScheduleEvent(eventTitle, currentevent.getHalfdaytime().getStartTime(),currentevent.getHalfdaytime().getEndTime());
                eventmodel.addEvent(event);
            }else{
                System.err.println("this event is held in hours in managedbean");
                event=new DefaultScheduleEvent(eventTitle, currentevent.getHourtime().getStartTime(),currentevent.getHourtime().getEndTime());
                eventmodel.addEvent(event);
            }
        }
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the venues
     */
    public List<String> getVenues() {
       venues=vmsbl.getvenueName();
        return venues;
    }

    /**
     * @param venues the venues to set
     */
    public void setVenues(List<String> venues) {
        this.venues = venues;
    }

    /**
     * @return the eventmodel
     */
    public ScheduleModel getEventmodel() {
        return eventmodel;
    }

    /**
     * @param eventmodel the eventmodel to set
     */
    public void setEventmodel(ScheduleModel eventmodel) {
        this.eventmodel = eventmodel;
    }

    /**
     * @return the event
     */
    public ScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
}
