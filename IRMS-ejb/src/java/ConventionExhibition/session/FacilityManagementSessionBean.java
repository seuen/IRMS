/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Facility;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.FacilityType;
import java.util.ArrayList;
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
public class FacilityManagementSessionBean implements FacilityManagementSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateeventfacility(FacilityNeed fn, List<Facility> facilities,String status){
        List<Long> faids=fn.getFacilities();
        for(Facility fa:facilities){
            faids.add(fa.getId());
            fa.setStatus(status);
            em.merge(fa);
        }
        fn.setFacilities(faids);
        em.merge(fn);
    }
    
    
    @Override
    public List<Facility> getonetypefacility(String type){
        List<Facility> temp=new ArrayList();
        Query q=em.createQuery("SELECT f FROM Facility f");
        for(Object o: q.getResultList()){
            Facility fa=(Facility) o;
            if(fa.getFacilityType().getType().equals(type)){
                temp.add(fa);
            }
        }
        return temp;
    }
    
    @Override
    public FacilityType findfacilityType(String type){
        FacilityType temp=new FacilityType();
        Query q=em.createQuery("SELECT f FROM FacilityType f");
       for(Object o:q.getResultList()){
           FacilityType ft=(FacilityType) o;
           if(ft.getType().equals(type))
               temp=ft;
       }
        return temp;
    }
    
    
    @Override
    public void addFacilityType(FacilityType facilityType, String location) {
        int i = 0;
        List<Facility> facilities = new ArrayList();
        if (facilityType != null) {
            facilityType.setFacilities(null);
            em.persist(facilityType);
            for (i = 0; i < facilityType.getQuantity(); i++) {
                Facility facility = new Facility();
                facility.setFacilityType(facilityType);
                facility.setLocation(location);
                facility.setStatus("Free");
                em.persist(facility);
                facilities.add(facility);
            }
            facilityType.setFacilities(facilities);
            em.merge(facilityType);
            em.flush();
        }
    }

    @Override
    public void addFacility(Facility facility, int quantity) {
        int i = 0;
        if (facility != null) {
            String location = facility.getLocation();
            FacilityType ft = facility.getFacilityType();
            List<Facility> facilities = ft.getFacilities();
            for (i = 0; i < quantity; i++) {
                Facility currentfacility = new Facility();
                currentfacility.setLocation(location);
                currentfacility.setStatus("Free");
                currentfacility.setFacilityType(ft);
                em.persist(currentfacility);
                facilities.add(currentfacility);
            }
            ft.setFacilities(facilities);
            int fquantity=ft.getQuantity()+quantity;
            ft.setQuantity(fquantity);
            em.merge(ft);
            em.flush();
        }
    }

    @Override
    public List<Facility> getAllFacility() {
        List<Facility> facilities = new ArrayList();
        Query q = em.createQuery("SELECT f FROM Facility f");
        for (Object o : q.getResultList()) {
            Facility f = (Facility) o;
            facilities.add(f);
        }
        return facilities;
    }

    @Override
    public List<FacilityType> getAllFacilityType() {
        List<FacilityType> facilityTypes = new ArrayList();
        Query q = em.createQuery("SELECT f FROM FacilityType f");
        for (Object o : q.getResultList()) {
            FacilityType f = (FacilityType) o;
            facilityTypes.add(f);
        }
        return facilityTypes;
    }
    
    @Override
    public void returnFacility(List<Facility> facilities){
        for(Facility facility: facilities){
            Facility temp=em.find(Facility.class, facility.getId());
            temp.setStatus("Free");
            em.merge(temp);
        }
    }
            

//
//    @Override
//    public boolean updateFacilityDetail(Facility facility, String detail){
//        if(em.find(Facility.class,facility.getId())!=null){
//            facility.setDetail(detail);
//            Hotel hotel=facility.getHotel();
//            List<Facility> facilities = (List<Facility>)hotel.getFacilities();
//            int i = facilities.indexOf(facility);
//            facilities.set(i, facility);
//            hotel.setFacilities(facilities);
//            em.merge(facility);
//            em.merge(hotel);
//            System.err.println("detail successfully updated");
//            return true;
//        }
//        else{
//            System.err.println("not successful");
//            return false;
//        }    
//    }
//    
//    @Override
//    public boolean updateFacilityStatus(Facility facility, String status){
//        if(em.find(Facility.class, facility.getId())!=null){
//            facility.setStatus(status);
//            Hotel hotel=facility.getHotel();
//            List<Facility> facilities = (List<Facility>)hotel.getFacilities();
//            int i = facilities.indexOf(facility);
//            facilities.set(i, facility);
//            hotel.setFacilities(facilities);
//            em.merge(facility);
//            em.merge(hotel);
//            em.merge(facility);
//            System.err.println("status succesfully updated");
//            return true;
//        }
//        else{
//            System.err.println("not successful");
//            return false;
//        }
//    }
//    

    @Override
    public void deleteFacility(List<Facility> facilities) {
        for (Facility facility : facilities) {
            FacilityType ft = facility.getFacilityType();
            if (ft.getQuantity() == 1) {
                em.remove(ft);
            } else {

                Facility tobeRemoved = em.merge(facility);
                List<Facility> currentfacilities = ft.getFacilities();
                currentfacilities.remove(facility);
                ft.setFacilities(currentfacilities);
                int fquantity=ft.getQuantity()-1;
                ft.setQuantity(fquantity);
                em.merge(ft);
                em.remove(tobeRemoved);
                em.flush();
            }
        }

    }
}
