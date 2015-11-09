/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Auditorium;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.ExhibitionSection;
import ConventionExhibition.entity.OpenSpace;
import ConventionExhibition.entity.OtherVenue;
import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Acer
 */
@Local
public interface VenueManagementSessionBeanLocal {
    public List<ExhibitionSection> searchExhibitionhall(int capacity,Date startingDate, Date endingDate);
    public List<Auditorium> searchAuditorium(int capacity,Date startingDate, Date endingDate);
    public List<Theater> searchTheater(int capacity,Date startingDate, Date endingDate);
    public List<OpenSpace> searchOpenSpace(int capacity, Date startingDate, Date endingDate);
    public List<BanquetHall> searchBanquetHallDay(int capacity,Date startingDate, Date endingDate);
    public List<OtherVenue> searchOtherVenueDay(int capacity, Date startingDate, Date endingDate, String venuetype);
    public List<OtherVenue> searchOtherVenueHour(int capacity, Date eventDate, Date startingTime, Date endingTime, String venuetype);
     public List<BanquetHall> searchBanquetHallHour(int capacity,Date eventDate, Date startingTime,Date endingTime);
    public List<ExhibitionSection> getAllExhibitionSection();
    public List<Auditorium> getAllAuditorium();
    public List<OpenSpace> getAllOpenSpace();
    public List<OtherVenue> getAllOtherVenue();
    public List<String> getvenueName();
    public List<EventOrder> listOneVenueEvent(String venue);
    
}
