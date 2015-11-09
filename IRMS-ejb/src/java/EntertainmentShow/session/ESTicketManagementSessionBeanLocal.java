/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntertainmentShow.session;

import EntertainmentShow.entity.Seat;
import EntertainmentShow.entity.SectionTicket;
import EntertainmentShow.entity.ESShow;
import EntertainmentShow.entity.ShowInfo;
import EntertainmentShow.entity.ShowOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zsy
 */
@Local
public interface ESTicketManagementSessionBeanLocal {
    public List<ShowInfo> listAllShowInfo();
    public List<SectionTicket> listAllSectionTicket(ESShow show);
    public List<Seat> listAllSeat();
    public Long blockInSeats(List<Seat> seats, SectionTicket sectionSeat);
    public Long blockExSeats(List<Seat> seats, SectionTicket sectionSeat);
    public ShowOrder getThisOrder(Long confirmNum);
}
