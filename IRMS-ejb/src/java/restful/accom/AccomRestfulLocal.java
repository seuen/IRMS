/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.accom;

import Accommodation.entity.Hotel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yifeng
 */
@Local
public interface AccomRestfulLocal {
    List<Hotel> getAllHotels();
    String testCheck();
}
