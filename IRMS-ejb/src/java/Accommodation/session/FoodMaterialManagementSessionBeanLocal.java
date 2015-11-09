/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.session;

import Accommodation.entity.HotelFoodMaterial; 
import FoodBeverage.entity.FoodMaterialOrder;
import FoodBeverage.entity.FoodOrder;
import javax.ejb.Local;
import java.util.List;

/**
 *
 * @author zsy
 */
@Local
public interface FoodMaterialManagementSessionBeanLocal {
    public int addFoodMaterial(HotelFoodMaterial foodMaterial, String hotelName);
    public boolean updateAmount(HotelFoodMaterial foodMaterial);
    public boolean deleteFoodMaterial(HotelFoodMaterial foodMaterial);
    public List<HotelFoodMaterial> getAllFoodMaterial(String hotelId);
    public List<HotelFoodMaterial> getAllUnavailableMaterial(String hotelId);
    public boolean createFoodMaterialOrder(FoodMaterialOrder foodMaterialOrder);
    public boolean updateFoodMaterialOrder(int quantity);
    public boolean deleteFoodMaterialOrder(FoodMaterialOrder foodMaterialOrder);

    public List<FoodMaterialOrder> getAllFoodMaterialOrder(String hotelId, String status);
}
