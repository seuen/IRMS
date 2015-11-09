/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.DayTime;
import ConventionExhibition.entity.EmployeeNeed;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.OtherVenue;
import FoodBeverage.entity.BanquetHall;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface EventManagementSessionBeanLocal {
    public List<Client> getAllclient();
    public void updateclient(Client client);
    public boolean addNewClient(Client client);
    public DayTime createDayTime(Date StartingDate, Date EndingDate);
   public void createEvent(EventOrder event, ConventionSchedule schedule);
   public void createEventBanquet(EventOrder banquet);
   public Long createEventDay(EventOrder event, List<ConventionSchedule> schedules);
   public void createSchedules(List<ConventionSchedule> schedules);
   public void createSchedule(ConventionSchedule schedule);
   public ConventionSchedule checkEventdatescheduleOtherVenue(OtherVenue othervenue,Date eventDate);
   public ConventionSchedule checkEventdatescheduleBanquet(BanquetHall banquethall,Date eventDate);
   
   public List<EventOrder> listCEevents();
   public List<EventOrder> listBanqeutevents();
   public List<EventOrder> listCEtodayevents();
   public List<EventOrder> listBanquettodayevents();
   public void cancelEvent(EventOrder event);
   public void updateevent(List<FacilityNeed> facilities,EventOrder event,float facilityprice);
   public void updateeventemployee(List<EmployeeNeed> employees,EventOrder vent, float employeeprice);
   public List<EventOrder> geteventbanquets(Long eventId);
   
   public void eventend(EventOrder eo);
   
}
