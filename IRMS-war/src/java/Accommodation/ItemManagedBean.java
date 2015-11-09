/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation;

import Accommodation.entity.Item;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import Accommodation.session.ItemManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class ItemManagedBean implements Serializable{
    private Item item =new Item();
    private String hotelId;
    
    @EJB
    ItemManagementSessionBeanLocal imsbl;
  

    public ItemManagedBean() {
    }
    
    public void navigateItemInfo(ActionEvent event) throws IOException{
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        if(getHotelId().contains("Hotel")){
            System.out.println(getHotelId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("addItem.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
        }
    }
    
    public void addItem(ActionEvent actionEvent){
        System.err.println(this);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        String message=imsbl.addItem(getItem(),hotelId);
        
        if(message.equals("Item added successfully")){
            FacesMessage msg = new FacesMessage("Successfully added " + getItem().getName(),"");  
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        }else{
            FacesMessage msg = new FacesMessage(message+" "+hotelId, getItem().getName());  
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        }
        item=new Item();
    }

   
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    
    

}
