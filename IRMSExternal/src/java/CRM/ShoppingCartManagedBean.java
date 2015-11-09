/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import Accommodation.ExReservationManagedBean;
import Attraction.ExTicketManagedBean;
import CRM.entity.BuyAttractionItem;
import CRM.entity.BuyCardValue;
import CRM.entity.BuyHotelItem;
import CRM.entity.BuyPkgItem;
import CRM.entity.BuyShowTicketItem;
import CRM.entity.MemberAccount;
import CRM.session.BuyItemSessionBeanLocal;
import ExternalEntertainmentShow.ExternalTicketManagedBean;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@SessionScoped
public class ShoppingCartManagedBean {

    @EJB
    BuyItemSessionBeanLocal bimbl;
    private double totalAmount;
    private MemberAccount memberAccount;
//    private BuyPkgItem buyPkgItem;=new BuyPkgItem() 
    private BuyCardValue buyCardValue = new BuyCardValue();
    private BuyShowTicketItem showItem = new BuyShowTicketItem();
    private BuyShowTicketItem selectShowItem = new BuyShowTicketItem();
    private BuyHotelItem hotelItem = new BuyHotelItem();
    private BuyHotelItem selectHotelItem = new BuyHotelItem();
    private BuyAttractionItem attractionItem = new BuyAttractionItem();
    private List<BuyHotelItem> hoteItems = new ArrayList();
    private List<BuyCardValue> cardItems = new ArrayList();
    private List<BuyCardValue> selectedCardItems = new ArrayList();
    private List<BuyShowTicketItem> showItems = new ArrayList();
    private List<BuyShowTicketItem> selectedShowItems = new ArrayList();
    private List<BuyPkgItem> pkgItems = new ArrayList();
    @ManagedProperty(value = "#{externalTicketManagedBean}")
    private ExternalTicketManagedBean ticketmanager;
    @ManagedProperty(value = "#{exReservationManagedBean}")
    private ExReservationManagedBean reservationmanager;
//    @ManagedProperty(value = "#{exTicketManagedBean}")
//    private ExTicketManagedBean attractionmanager;

    /**
     * Creates a new instance of ShoppingCartManagedBean
     */
    public ShoppingCartManagedBean() {
        this.ticketmanager = new ExternalTicketManagedBean();
    }

    public void createBuyCardItem(ActionEvent event) {
        System.err.println("TEST");
        FacesMessage msg = new FacesMessage("Your Transaction is succesfully added to Shopping Cart!");
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        System.err.println("Get memberID = " + getMemberAccount().getId());
        String valuePackage = getBuyCardValue().getTopupType();
        System.err.println(valuePackage);

        if (valuePackage.equals("1000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 1000));
            System.err.println("ValueCardValue ID = " + getBuyCardValue());
        } else if (valuePackage.equals("2000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 1960));
        } else if (valuePackage.equals("3000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 2850));
        } else if (valuePackage.equals("5000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 4600));
        } else if (valuePackage.equals("8000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 7200));
        } else if (valuePackage.equals("10000")) {
            this.setBuyCardValue(bimbl.addCardBuyItem(getMemberAccount(), valuePackage, 8800));
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void createBuyShowItem(ActionEvent event) throws IOException {
        System.err.println("test show item shopping cart!");
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        System.err.println("select section num" + getTicketmanager().getSelectSectionNum());
        System.err.println("Show name : " + getTicketmanager().getSelectShowInfo().getName());
        System.err.println("section ticket : " + getTicketmanager().getSelectSectionTicket());

        String orderName = getTicketmanager().getSelectShowInfo().getName() + " ( section : " + getTicketmanager().getSelectSectionTicket().getSeatSection().getNum() + " )";
        System.err.println("Order Name : " + orderName);
        int quantity = getTicketmanager().getSelectSeats().size();
        System.err.println("ticket quantity : " + quantity);
        float price = getTicketmanager().getSelectSectionTicket().getPrice();
        System.err.println("ticket unit price : " + price);
        this.setShowItem(bimbl.addBuyShowItem(getMemberAccount(), orderName, quantity, (double) price));
        FacesMessage msg = new FacesMessage("Your ticket order is succesfully added to Shopping Cart!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("../CRM/Payment/cart.xhtml");
    }

//    public void createAttractionItem(ActionEvent event) throws IOException {
//        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
//        double price = 150.0;
//        String name = "Attaction tickets";
//        this.setAttractionItem(bimbl.addBuyAttractionItem(memberAccount, name, 3, price));
//        FacesMessage msg = new FacesMessage("Your ticket order is succesfully added to Shopping Cart!");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//        ec.redirect("../CRM/Payment/cart.xhtml");
//    } 

    public void createHotelItem(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        getReservationmanager().addReservation2();
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        System.err.println("  hahah  ");

        System.err.println("test 1 " + getReservationmanager().getRtreservation().getRoomtype().getHotel().getName());
        String orderName = getReservationmanager().getRtreservation().getRoomtype().getHotel().getName() + " : "
                + getReservationmanager().getRtreservation().getRoomtype().getType();
        System.err.println("Order Name : " + orderName);
        Date start = getReservationmanager().getRtreservation().getDateFrom();
        Date end = getReservationmanager().getRtreservation().getDateTo();
        String description = "reservation period: " + start + " to " + end;
        System.err.println(description);
        System.err.println("duration " + getReservationmanager().getDuration());
        System.err.println("quantity " + getReservationmanager().getRoomQuantity());
        int quantity = getReservationmanager().getRtreservation().getDuration() * getReservationmanager().getRtreservation().getRoomQuantity();
        System.err.println(quantity);
//        double price = (double)reservationmanager.getRtreservation().getRoomtype().getPrice_l();

        String str = "" + getReservationmanager().getRtreservation().getRoomtype().getPrice_l();
        double price = Double.parseDouble(str);
        System.out.println("price " + price);

        System.err.println(price);
        this.setHotelItem(bimbl.addHotelItem(getMemberAccount(), orderName, quantity, price, description));
        System.err.println("add successfully!");
        FacesMessage msg = new FacesMessage("Your hotel order is succesfully added to Shopping Cart!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("../CRM/Payment/cart.xhtml");

    }

    public void removeShowTicketItem(ActionEvent event) {
        System.err.println("start to delete show ticket");
        BuyShowTicketItem showTicket = (BuyShowTicketItem) event.getComponent().getAttributes().get("showticket");
        System.err.println("show ticket " + showTicket.getTicketName());
        this.getShowItems().remove(showTicket);
        FacesMessage msg = new FacesMessage("Your item is deleted! ");
        bimbl.removeBuyShowItem(showTicket);
        System.err.println("delete done");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeBuyCardValue(BuyCardValue buyCardValue) {
        getCardItems().remove(buyCardValue);
        FacesMessage msg = new FacesMessage("Your item is deleted! ");
        bimbl.removeCardValueItem(buyCardValue);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeBuyHotelItem(ActionEvent event) {
        BuyHotelItem buyhotelItem = (BuyHotelItem) event.getComponent().getAttributes().get("hotelRsv");
        getHoteItems().remove(buyhotelItem);
        FacesMessage msg = new FacesMessage("Your item is deleted! ");
        bimbl.removeBuyHotelItem(buyhotelItem);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removePkgItem(ActionEvent event) {
        BuyPkgItem pkgItem = (BuyPkgItem) event.getComponent().getAttributes().get("pkgRsv");
        getPkgItems().remove(pkgItem);
        bimbl.removeBuyPkgItem(pkgItem);
        FacesMessage msg = new FacesMessage("Your item is deleted! ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public double getTotalAmount() {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        double amount = bimbl.calculateTotalCardValue(getMemberAccount());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("amount", amount);
        return amount;
    }

    public MemberAccount getMemberAccount() {
        return (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }

    public void directTo() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("paymentMode.xhtml");
    }

    public BuyCardValue getBuyCardValue() {
        return buyCardValue;
    }

    public void setBuyCardValue(BuyCardValue buyCardValue) {
        this.buyCardValue = buyCardValue;
    }

    public List<BuyCardValue> getCardItems() {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if (cardItems == null || cardItems.isEmpty()) {
            cardItems = bimbl.getAllCardItems(getMemberAccount());
        }
        return cardItems;
    }

    public void setCardItems(List<BuyCardValue> cardItems) {
        this.cardItems = cardItems;
    }

    public List<BuyCardValue> getSelectedCardItems() {
        return selectedCardItems;
    }

    public void setSelectedCardItems(List<BuyCardValue> selectedCardItems) {
        this.selectedCardItems = selectedCardItems;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the showItem
     */
    public BuyShowTicketItem getShowItem() {
        return showItem;
    }

    /**
     * @param showItem the showItem to set
     */
    public void setShowItem(BuyShowTicketItem showItem) {
        this.showItem = showItem;
    }

    /**
     * @return the showItems
     */
    public List<BuyShowTicketItem> getShowItems() {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if ((showItems == null) || showItems.isEmpty());
        {
            showItems = bimbl.getAllShowItems(getMemberAccount());
        }
        return showItems;
    }

    /**
     * @param showItems the showItems to set
     */
    public void setShowItems(List<BuyShowTicketItem> showItems) {
        this.showItems = showItems;
    }

    /**
     * @return the selectedShowItems
     */
    public List<BuyShowTicketItem> getSelectedShowItems() {
        return selectedShowItems;
    }

    /**
     * @param selectedShowItems the selectedShowItems to set
     */
    public void setSelectedShowItems(List<BuyShowTicketItem> selectedShowItems) {
        this.selectedShowItems = selectedShowItems;
    }

    /**
     * @return the ticketmanager
     */
    public ExternalTicketManagedBean getTicketmanager() {
        return ticketmanager;
    }

    /**
     * @param ticketmanager the ticketmanager to set
     */
    public void setTicketmanager(ExternalTicketManagedBean ticketmanager) {
        this.ticketmanager = ticketmanager;
    }

    /**
     * @return the selectShowItem
     */
    public BuyShowTicketItem getSelectShowItem() {
        return selectShowItem;
    }

    /**
     * @param selectShowItem the selectShowItem to set
     */
    public void setSelectShowItem(BuyShowTicketItem selectShowItem) {
        this.selectShowItem = selectShowItem;
    }

    /**
     * @return the hotelItem
     */
    public BuyHotelItem getHotelItem() {
        return hotelItem;
    }

    /**
     * @param hotelItem the hotelItem to set
     */
    public void setHotelItem(BuyHotelItem hotelItem) {
        this.hotelItem = hotelItem;
    }

    /**
     * @return the selectHotelItem
     */
    public BuyHotelItem getSelectHotelItem() {
        return selectHotelItem;
    }

    /**
     * @param selectHotelItem the selectHotelItem to set
     */
    public void setSelectHotelItem(BuyHotelItem selectHotelItem) {
        this.selectHotelItem = selectHotelItem;
    }

    /**
     * @return the hoteItems
     */
    public List<BuyHotelItem> getHoteItems() {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if ((hoteItems == null) || hoteItems.isEmpty()) {
            hoteItems = bimbl.getAllHotelItems(getMemberAccount());
        }
        return hoteItems;
    }

    /**
     * @param hoteItems the hoteItems to set
     */
    public void setHoteItems(List<BuyHotelItem> hoteItems) {
        this.hoteItems = hoteItems;
    }

    /**
     * @return the reservationmanager
     */
    public ExReservationManagedBean getReservationmanager() {
        return reservationmanager;
    }

    /**
     * @param reservationmanager the reservationmanager to set
     */
    public void setReservationmanager(ExReservationManagedBean reservationmanager) {
        this.reservationmanager = reservationmanager;
    }

    /**
     * @return the pkgItems
     */
    public List<BuyPkgItem> getPkgItems() {
        setMemberAccount((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if ((pkgItems == null) || pkgItems.isEmpty()) {
            pkgItems = bimbl.getAllPkgItems(getMemberAccount());
        }
        return pkgItems;
    }

    /**
     * @param pkgItems the pkgItems to set
     */
    public void setPkgItems(List<BuyPkgItem> pkgItems) {
        this.pkgItems = pkgItems;
    }



    /**
     * @return the attractionItem
     */
    public BuyAttractionItem getAttractionItem() {
        return attractionItem;
    }

    /**
     * @param attractionItem the attractionItem to set
     */
    public void setAttractionItem(BuyAttractionItem attractionItem) {
        this.attractionItem = attractionItem;
    }
}
