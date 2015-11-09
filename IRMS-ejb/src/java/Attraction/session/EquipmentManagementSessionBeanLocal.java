/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction.session;

import Attraction.entity.AttraSection;
import Attraction.entity.Attraction;
import Attraction.entity.Equipment;
import Attraction.entity.Outlet;
import Attraction.entity.RetailOutlet;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author zsy
 */
@Local
public interface EquipmentManagementSessionBeanLocal {
    public boolean updateAttractionInfo(Attraction attraction);
    public List<Attraction> listAllAttractions();
    public List<Equipment> listAllEquipments(String attraName);
    public List<Equipment> listAllAttraSectionEquipments(AttraSection attraSection);
    public List<AttraSection> listAttraSections(String attraName);
    public List<AttraSection> listAllAttraSections();
    public List<Outlet> listAllOutlets(String attraName);
    public List<RetailOutlet> listAllRetailOutlets(String attraName);
    public boolean updateEquipment(Equipment equipment);
    public boolean maintainSectionEquipment(AttraSection attraSection);
    public boolean maintainEquipment(Equipment equipment);
    public boolean activateEquipment(Equipment equipment);
    public boolean updateEventSchedule(AttraSection attraSection, Date d);
    public boolean updateOutlet(Outlet outlet);
    public boolean updateRetailOutlet(RetailOutlet retailOutlet);
}
