/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation;

import Accommodation.entity.Hotel;
import Accommodation.session.HotelManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


@ManagedBean
@SessionScoped
public class ExHotelManagedBean implements Serializable{
    @EJB
    HotelManagementSessionBeanLocal hmsbl;
    private Hotel hotel=new Hotel();
    private String hotelId;

    public ExHotelManagedBean() {
    }
    
    public void navigateHotelInfo(ActionEvent event) throws IOException
    {
        setHotelId(event.getComponent().getAttributes().get("hotelId").toString());
        System.err.println("hotelId: " + getHotelId());
        setHotel(hmsbl.getHotel(getHotelId()));
       
        if(getHotelId().contains("Hotel")){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("hotelId", getHotelId());
            FacesContext.getCurrentInstance().getExternalContext().redirect("ExHotelInfo.xhtml");
        }
        else{
            System.out.println("hotelId does not get correctly");
        }
    } 

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * @return the hotelId
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
