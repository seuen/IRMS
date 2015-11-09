/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import ConventionExhibition.entity.Auditorium;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.Theater;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author xing zhe
 */
@Local
public interface ESInfoManagementSessionBeanLocal {

    public boolean addShowInfo(ShowInfo showInfo);

    public boolean deleteShowInfo(Long showInfoId);

    public boolean updateShowInfo(ShowInfo theShowInfo);
    
    public boolean updateSectionTicket(SectionTicket st, ESShow show);

    public List<ShowInfo> listAllShowInfos();
    
    public List<ESShow> listAllShows();

    public List<Theater> searchTheaters(int capacity, Date startDate, Date endDate, Date startTime, Date endTime);

    public List<Auditorium> searchAuditoriums(int capacity, Date startDate, Date endDate, Date startTime, Date endTime);

    public boolean addAuditoriumShow(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, Date startTime, Date endTime, ShowInfo theShowInfo, Auditorium theAuditorium);

    public boolean addTheaterShow(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, Date startTime, Date endTime, ShowInfo theShowInfo, Theater theTheater);

    public boolean createShowInfo(Date startDate, Date endDate, Date startTimeShow, Date endTimeShow, ShowInfo theShowInfo);
}
