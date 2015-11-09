/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.HotelFoodMaterial;
import Accommodation.entity.Hotel;
import FoodBeverage.entity.FoodMaterialOrder;
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
public class FoodMaterialManagementSessionBean implements FoodMaterialManagementSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int addFoodMaterial(HotelFoodMaterial foodMaterial, String hotelName) {
        if (foodMaterial != null && hotelName != null && !"".equals(hotelName)) {
            Hotel hotel = em.find(Hotel.class, hotelName);
            Query q = em.createQuery("SELECT f FROM HotelFoodMaterial f WHERE f.name=:name");
            q.setParameter("name", foodMaterial.getName());

            List<HotelFoodMaterial> fms = q.getResultList();
            if (fms.isEmpty()) {
                foodMaterial.setHotel(hotel);
                foodMaterial.checkStatus();
                getEm().persist(foodMaterial);
                List<HotelFoodMaterial> hotelFms = (List<HotelFoodMaterial>) hotel.getFoodMaterials();
                hotelFms.add(foodMaterial);
                em.merge(hotel);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }
 
    @Override
    public boolean updateAmount(HotelFoodMaterial foodMaterial){
        if (foodMaterial == null) {
            return false;
        } else {
            Hotel hotel = foodMaterial.getHotel();
            foodMaterial.checkStatus();
            List<HotelFoodMaterial> foodMaterials=(List<HotelFoodMaterial>)hotel.getFoodMaterials();
            foodMaterials.set(foodMaterials.indexOf(foodMaterial), foodMaterial);
            hotel.setFoodMaterials(foodMaterials);
            em.merge(foodMaterial);
            em.merge(hotel);
            em.flush();
            return true;
        }
    }
    
    @Override
    public boolean deleteFoodMaterial(HotelFoodMaterial foodMaterial) {
        if (foodMaterial == null) {
            return false;
        } else {
            if(em.find(HotelFoodMaterial.class, foodMaterial.getId())!=null){
                Hotel hotel = foodMaterial.getHotel();
                HotelFoodMaterial todelete = em.merge(foodMaterial);
                em.remove(todelete);
                List<HotelFoodMaterial> fms = (List<HotelFoodMaterial>)hotel.getFoodMaterials();
                fms.remove(foodMaterial);
                hotel.setFoodMaterials(fms);
                em.merge(hotel);
                em.flush();
                return true;
            }
            else
                return true;
        }
    }

    @Override
    public List<HotelFoodMaterial> getAllFoodMaterial(String hotelId) {
        List<HotelFoodMaterial> foodMaterials = new ArrayList();
        if (hotelId != null && !"".equals(hotelId)) {
            Hotel hotel = em.find(Hotel.class, hotelId);
//            if(hotel!=null)
            foodMaterials = (List<HotelFoodMaterial>) hotel.getFoodMaterials();
        }
        return foodMaterials;
    }

    @Override
    public List<HotelFoodMaterial> getAllUnavailableMaterial(String hotelId) {
        List<HotelFoodMaterial> foodMaterials = new ArrayList();
        if (hotelId != null && !"".equals(hotelId)) {
            Hotel hotel = em.find(Hotel.class, hotelId);
            if (hotel != null) {
                Query q = em.createQuery("SELECT f FROM HotelFoodMaterial f WHERE f.status='Unavailable'");
                foodMaterials = q.getResultList();
            }
        }
        return foodMaterials;
    }

    @Override
    public boolean createFoodMaterialOrder(FoodMaterialOrder foodMaterialOrder) {
        return true;
    }

    @Override
    public boolean updateFoodMaterialOrder(int quantity) {
        return true;
    }

    @Override
    public boolean deleteFoodMaterialOrder(FoodMaterialOrder foodMaterialOrder) {
        return true;
    }

    @Override
    public List<FoodMaterialOrder> getAllFoodMaterialOrder(String hotelId, String status) {
        List<FoodMaterialOrder> foodMaterialOrders = new ArrayList();
        return foodMaterialOrders;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}


