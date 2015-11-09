/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation;

import Accommodation.entity.Item;
import Accommodation.entity.ItemOrder;
import Accommodation.entity.RSOrder;
import Accommodation.session.HotelManagementSessionBeanLocal;
import Accommodation.session.RoomServiceManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author zsy
 */
@ManagedBean
@SessionScoped
public class RoomServiceManagedBean implements Serializable {

    @EJB
    private RoomServiceManagementSessionBeanLocal rsmsbl;
    @EJB
    private HotelManagementSessionBeanLocal hmsbl;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    private int q=1;
    private int quantity = 1;
    private int qua=1;
    private String roomNum;
    private RSOrder order;
    private String hotelId = "Singland Hotel";
    private List<RSOrder> orders = new ArrayList();
    private List<ItemOrder> itemOrders = new ArrayList();

    /**
     * Creates a new instance of RoomServiceManagedBean
     */
    public RoomServiceManagedBean() {
    }

    public List<Item> listAllItems() throws IOException {
        return hmsbl.listroomserviceitem(hotelId);
    }

    public List<RSOrder> listAllOrders() throws IOException {
        return rsmsbl.getAllOrder(hotelId);
    }

    public void quantityChangeListener(ValueChangeEvent event) {
        q = getQuantity();
        System.err.println(q);
    }
    
    public void updateOrder(ItemOrder itemOrder) throws IOException {
        System.err.println(itemOrder);
        if(rsmsbl.updateOrder(itemOrder,qua)!=null){
            System.out.println(qua);
            ec.redirect("viewAllRoomOrder.xhtml");
        }
        else{
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        qua=1;
    }   

    public void deleteOrder() throws IOException {
        System.err.println(order);
        if(rsmsbl.deleteOrder(order))
            ec.redirect("viewAllRoomOrder.xhtml");
        else{
            FacesMessage msg = new FacesMessage("Fail to delete, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void addItemOrder(Item item) {
        System.err.println("inside add order item");
        quantity = getQ();

        if (item.getHotel().getName().equals(hotelId)) {
            System.out.println("inside if");
            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setItem(item);
            itemOrder.setQuantity(quantity);
            itemOrders.add(itemOrder);
            FacesMessage msg = new FacesMessage("Successfully add item!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.err.println("finish");
            
        } else {
            FacesMessage msg = new FacesMessage("Fail to add item!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            itemOrders = new ArrayList();
            roomNum = null;
        }
        quantity = 1;
        q = 1;
    }

    public void refresh() throws IOException {
        quantity = 1;
        q = 1;
        itemOrders = new ArrayList();
        roomNum = null;
        ec.redirect("addRoomOrder.xhtml");
    }

    public void addRoomOrder() {
        if (roomNum != null &&(!roomNum.equals("")) && itemOrders != null) {
            System.err.println("inside add order");
            
            int temp = rsmsbl.addRoomOrder(itemOrders, roomNum);

            if (temp == 1) {
                FacesMessage msg = new FacesMessage("Successfully order room service!");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } else if (temp == 2) {
                FacesMessage msg = new FacesMessage("Room is not found, please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (temp == 3) {
                FacesMessage msg = new FacesMessage("Selected Room is not occupied currently, please check again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Fail to order room service!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        else{
            FacesMessage msg = new FacesMessage("Please enter valid input!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        q = 1;
        quantity=1;
        itemOrders = new ArrayList();
        roomNum = null;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the order
     */
    public RSOrder getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(RSOrder order) {
        this.order = order;
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

    /**
     * @return the orders
     */
    public List<RSOrder> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<RSOrder> orders) {
        this.orders = orders;
    }

    /**
     * @return the rsmsbl
     */
    public RoomServiceManagementSessionBeanLocal getRsmsbl() {
        return rsmsbl;
    }

    /**
     * @param rsmsbl the rsmsbl to set
     */
    public void setRsmsbl(RoomServiceManagementSessionBeanLocal rsmsbl) {
        this.rsmsbl = rsmsbl;
    }

    /**
     * @return the hmsbl
     */
    public HotelManagementSessionBeanLocal getHmsbl() {
        return hmsbl;
    }

    /**
     * @param hmsbl the hmsbl to set
     */
    public void setHmsbl(HotelManagementSessionBeanLocal hmsbl) {
        this.hmsbl = hmsbl;
    }

    /**
     * @return the q
     */
    public int getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(int q) {
        this.q = q;
    }

    /**
     * @return the roomNum
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * @param roomNum the roomNum to set
     */
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * @return the itemOrders
     */
    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    /**
     * @param itemOrders the itemOrders to set
     */
    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    /**
     * @return the qua
     */
    public int getQua() {
        return qua;
    }

    /**
     * @param qua the qua to set
     */
    public void setQua(int qua) {
        this.qua = qua;
    }
}
