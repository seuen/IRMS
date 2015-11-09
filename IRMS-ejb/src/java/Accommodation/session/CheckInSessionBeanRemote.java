/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.RTReservation;
import Accommodation.entity.Stay;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Acer
 */
@Remote
 
public interface CheckInSessionBeanRemote {
    public boolean checkCreservation(RTReservation reservation);
    public boolean updateReservation(RTReservation reservation);
    public List<Stay> getcheckinStay();
    public boolean updateReservationCheckIn();
}
