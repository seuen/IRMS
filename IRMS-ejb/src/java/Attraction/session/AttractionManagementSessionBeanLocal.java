/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.Attraction;
import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author zsy
 */
@Local
public interface AttractionManagementSessionBeanLocal {
    public boolean updateAttractionInfo(Attraction attraction);
    public List<Attraction> getAllAttraction();
}
