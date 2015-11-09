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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author zsy
 */
@Stateless
public class EquipmentManagementSessionBean implements EquipmentManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean updateEventSchedule(AttraSection attraSection, Date d) {
        System.err.println("inside equip sessionbean and date is "+d);
        if (em.find(AttraSection.class, attraSection.getNum()) != null) {
            attraSection.setNextDate(d);
            em.merge(attraSection);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAttractionInfo(Attraction attraction) {
        if (attraction == null) {
            return false;
        } else {
            if (em.find(Attraction.class, attraction.getName()) != null) {
                em.merge(attraction);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public List<Attraction> listAllAttractions() {
        List<Attraction> attractions;
        Query q = em.createQuery("SELECT a FROM Attraction a");
        attractions = q.getResultList();
        return attractions;
    }
    
    @Override
    public List<Outlet> listAllOutlets(String attraName){
        System.err.println("inside list all outlets session");
        List<Outlet> outlets = new ArrayList();
        Attraction attraction = em.find(Attraction.class, attraName);
        if (attraction != null) {
            outlets = (List<Outlet>) attraction.getOutlets();
        }
        System.out.println("finish");
        return outlets;
    }
    
    @Override
    public List<RetailOutlet> listAllRetailOutlets(String attraName){
        List<RetailOutlet> retailOutlets = new ArrayList();
        Attraction attraction = em.find(Attraction.class, attraName);
        if (attraction != null) {
            retailOutlets = (List<RetailOutlet>) attraction.getRetailOutlets();
        }
        return retailOutlets;
    }
    

    @Override
    public List<Equipment> listAllEquipments(String attraName) {
        List<Equipment> equipments = new ArrayList();

        Attraction attraction = em.find(Attraction.class, attraName);
        if (attraction != null) {
            equipments = (List<Equipment>) attraction.getEquipments();
        }
        return equipments;
    }

    @Override
    public List<Equipment> listAllAttraSectionEquipments(AttraSection attraSection) {
        List<Equipment> equipments = new ArrayList();

        if (em.find(AttraSection.class, attraSection.getNum()) != null) {
            equipments = (List<Equipment>) attraSection.getEquipments();
        }
        return equipments;
    }

    @Override
    public List<AttraSection> listAttraSections(String attraName) {
        List<AttraSection> attraSections = new ArrayList();
        Attraction attraction = em.find(Attraction.class, attraName);
        if (attraction != null) {
            attraSections = (List<AttraSection>) attraction.getAttraSections();
        }
        return attraSections;
    }

    @Override
    public List<AttraSection> listAllAttraSections() {
        List<AttraSection> attraSections;
        Query q = em.createQuery("SELECT a FROM AttraSection a");
        attraSections = q.getResultList();
        return attraSections;
    }

    @Override
    public boolean updateEquipment(Equipment equipment) {
        if (em.find(Equipment.class, equipment.getId()) != null) {
            em.merge(equipment);
            return true;
        }
        return false;
    }

    @Override
    public boolean maintainSectionEquipment(AttraSection attraSection) {
        if (em.find(AttraSection.class, attraSection.getNum()) != null) {
            List<Equipment> equipments = (List<Equipment>) attraSection.getEquipments();
            for (Equipment e : equipments) {
                e.setStatus("Inactive");
                em.merge(e);
            }
            attraSection.setEquipments(equipments);
            attraSection.setStatus("Inactive");
            em.merge(attraSection);

            return true;
        }
        return false;
    }

    @Override
    public boolean maintainEquipment(Equipment equipment) {
        if (em.find(Equipment.class, equipment.getId()) != null) {
            equipment.setStatus("Inactive");
            em.merge(equipment);

            AttraSection attraSection = equipment.getAttraSection();
            List<Equipment> equipments = (List<Equipment>) attraSection.getEquipments();
            int temp = 0;

            for (Equipment e : equipments) {
                if (e.getStatus().equals("Active")) {
                    temp = 1;
                }
            }
            if (temp == 1) {
                attraSection.setStatus("Inactive");
                em.merge(attraSection);
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean activateEquipment(Equipment equipment) {
        if (em.find(Equipment.class, equipment.getId()) != null) {
            equipment.setStatus("Active");
            em.merge(equipment);

            AttraSection attraSection = equipment.getAttraSection();
            List<Equipment> equipments = (List<Equipment>) attraSection.getEquipments();
            int temp = 1;

            for (Equipment e : equipments) {
                if (e.getStatus().equals("Inactive")) {
                    temp = 0;
                }
            }
            if (temp == 1) {
                attraSection.setStatus("Active");
                em.merge(attraSection);
            }

            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateOutlet(Outlet outlet){
        if(em.find(Outlet.class, outlet.getId())!=null){
            em.merge(outlet);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateRetailOutlet(RetailOutlet retailOutlet){
        if(em.find(RetailOutlet.class, retailOutlet.getId())!=null){
            em.merge(retailOutlet);
            return true;
        }
        return false;
    }
}
