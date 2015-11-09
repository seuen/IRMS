package Accommodation;

import Accommodation.entity.Guest;
import Accommodation.entity.Stay;
import Accommodation.session.CheckOutSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CheckOutManagedBean implements Serializable{

    @EJB
    CheckOutSessionBeanLocal cosbl;
    private List<Stay> stays = new ArrayList();
    private List<Guest> guests = new ArrayList();
    private Date checkout =new Date();

    public CheckOutManagedBean() {
    }

    public void clear() throws IOException {
        guests = new ArrayList();
        FacesContext.getCurrentInstance().getExternalContext().redirect("CheckOut.xhtml");
    }

    public void payCharges(Stay stay) {
        float charges = stay.getTotalCharges();
        if (charges == 0) {
            FacesMessage msg1 = new FacesMessage("No Charges, no need to pay");
            FacesContext.getCurrentInstance().addMessage(null, msg1);
        } else {
            cosbl.payCharges(stay);
            FacesMessage msg = new FacesMessage("Total charges " + charges + " has been paid");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void ViewGuest(Stay stay) throws IOException {
        System.out.println("has get into the view guest");
        if (!stay.isStatus()) {
            System.out.println("the staus of stay is true");
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            FacesMessage msg = new FacesMessage("This room " + stay.getRoom().getRoomNum() + " has already check out");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            setGuests((List<Guest>) stay.getGuests());
            FacesContext.getCurrentInstance().getExternalContext().redirect("GuestInfo.xhtml");
        }
    }

    public void CheckOut(Stay stay) throws IOException {
        if (stay.getTotalCharges() > 0) {
            System.out.println("there is still charges");
            FacesMessage msg = new FacesMessage("Please pay charges first");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (!stay.isStatus()) {
            FacesMessage msg2 = new FacesMessage("Room " + stay.getRoom().getRoomNum() + " has been checked out");
            FacesContext.getCurrentInstance().addMessage(null, msg2);
        } else {
            System.out.println("before get into session bean");
            cosbl.checkout(stay);
            FacesMessage msg1 = new FacesMessage("Room " + stay.getRoom().getRoomNum() + " Check out done");
            FacesContext.getCurrentInstance().addMessage(null, msg1);
      //      FacesContext.getCurrentInstance().getExternalContext().redirect("CheckOut.xhtml");
        }
    }

    public List<Stay> getStays() {
        stays = cosbl.getCheckOutStay(checkout);
        System.out.println("the chekout date is "+checkout);
        return stays;
    }

    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }

    public List<Guest> getGuests() {
        System.out.println("get into getguests function");
        System.out.println("the size of guests is " + guests.size());
        for (Guest currentGuest : guests) {
            System.out.println("the firstname of guest is " + currentGuest.getFirstName());
            System.out.println("the ic of guest is " + currentGuest.getIc());
        }
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}
