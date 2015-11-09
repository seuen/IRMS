/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation;

import Accommodation.entity.HotelFoodMaterial;
import FoodBeverage.entity.FoodMaterialOrder;
import Accommodation.session.FoodMaterialManagementSessionBeanLocal;
import FoodBeverage.entity.FoodOrder;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author zsy
 */
@ManagedBean
@ViewScoped
public class FoodMaterialManagedBean implements Serializable{

    @EJB
    private FoodMaterialManagementSessionBeanLocal fmmsbl;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    
    private HotelFoodMaterial foodMaterial = new HotelFoodMaterial();
    private FoodMaterialOrder foodMaterialOrder = new FoodMaterialOrder();
    private FoodOrder foodOrder = new FoodOrder();
    private float amount;
    private float a;
    private String hotelId = "Singland Hotel";
    private List<HotelFoodMaterial> selectedMaterials = new ArrayList();
    private List<Float> amounts = new ArrayList();
    private List<FoodOrder> foodOrders = new ArrayList();
    


    /**
     * Creates a new instance of FoodMaterialManagedBean
     */
    public FoodMaterialManagedBean() {
    }
    
    
    

    public void addFoodMaterial(ActionEvent action) {
        if (foodMaterial.getAmount() == 0) {
            FacesMessage msg = new FacesMessage("Quantity cannot be zero!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            int temp = getFmmsbl().addFoodMaterial(foodMaterial, hotelId);
            if (temp == 1) {
                FacesMessage msg = new FacesMessage("Successfully add new food material!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == 2) {
                FacesMessage msg = new FacesMessage("Sorry, this food material is already in the system!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("input is invalid, please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            foodMaterial = new HotelFoodMaterial();
        }
    }
    
    public List<HotelFoodMaterial> listAllFoodMaterials(){
        return fmmsbl.getAllFoodMaterial(hotelId);
    }
    
    public List<HotelFoodMaterial> listAllUnavailableFoodMaterials(){
        return fmmsbl.getAllUnavailableMaterial(hotelId);
    }
    
    public void deleteFoodMaterial() throws IOException {
        int i = 1;
        for(HotelFoodMaterial fm: selectedMaterials){
            System.err.println(fm);
            boolean temp = fmmsbl.deleteFoodMaterial(fm);
            if (temp) {
                continue;
            } else {
                i = 0;
                break;
            }
        }

        if (i == 1) {
            ec.redirect("ViewAllFoodMaterial.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to delete, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void updateAmount(HotelFoodMaterial foodMaterial){
        if(!fmmsbl.updateAmount(foodMaterial)){
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Successfully updated!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void redirect() throws IOException {
        ec.redirect("ViewAllFoodMaterial.xhtml");
    }

    public void amountChangeListener(ValueChangeEvent event) {
        setA(getAmount());
        System.err.println("inside amount change");
        System.err.println(getA());
        System.err.println(getAmount());
    }
    
 
    
    public void deleteFoodMaterialOrder() throws IOException{
        if(fmmsbl.deleteFoodMaterialOrder(foodMaterialOrder)){
            ec.redirect("ViewAllFoodMaterialOrder.xhtml");
        }
        else{
            FacesMessage msg = new FacesMessage("Fail to delete order, please try again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the fmmsbl
     */
    public FoodMaterialManagementSessionBeanLocal getFmmsbl() {
        return fmmsbl;
    }

    /**
     * @param fmmsbl the fmmsbl to set
     */
    public void setFmmsbl(FoodMaterialManagementSessionBeanLocal fmmsbl) {
        this.fmmsbl = fmmsbl;
    }

    /**
     * @return the foodMaterial
     */
    public HotelFoodMaterial getFoodMaterial() {
        return foodMaterial;
    }

    /**
     * @param foodMaterial the foodMaterial to set
     */
    public void setFoodMaterial(HotelFoodMaterial foodMaterial) {
        this.foodMaterial = foodMaterial;
    }

    /**
     * @return the foodMaterialOrder
     */
    public FoodMaterialOrder getFoodMaterialOrder() {
        return foodMaterialOrder;
    }

    /**
     * @param foodMaterialOrder the foodMaterialOrder to set
     */
    public void setFoodMaterialOrder(FoodMaterialOrder foodMaterialOrder) {
        this.foodMaterialOrder = foodMaterialOrder;
    }

    /**
     * @return the selectedMaterials
     */
    public List<HotelFoodMaterial> getSelectedMaterials() {
        return selectedMaterials;
    }

    /**
     * @param selectedMaterials the selectedMaterials to set
     */
    public void setSelectedMaterials(List<HotelFoodMaterial> selectedMaterials) {
        this.selectedMaterials = selectedMaterials;
    }

    /**
     * @return the amounts
     */
    public List<Float> getAmounts() {
        return amounts;
    }

    /**
     * @param amounts the amounts to set
     */
    public void setAmounts(List<Float> amounts) {
        this.amounts = amounts;
    }

    /**
     * @return the foodOrders
     */
    public List<FoodOrder> getFoodOrders() {
        return foodOrders;
    }

    /**
     * @param foodOrders the foodOrders to set
     */
    public void setFoodOrders(List<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    /**
     * @return the foodOrder
     */
    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    /**
     * @param foodOrder the foodOrder to set
     */
    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    /**
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * @return the a
     */
    public float getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(float a) {
        this.a = a;
    }



}
