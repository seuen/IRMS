/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.BuyCardValue;
import CRM.entity.MemberAccount;
import CRM.session.BuyItemSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class BuyItemManagedBean {

    @EJB
    BuyItemSessionBeanLocal bimbl;
    private double totalAmount;
    private MemberAccount memberAccount;
    private BuyCardValue buyCardValue = new BuyCardValue();
    private List<BuyCardValue> cardItems = new ArrayList();
    private List<BuyCardValue> selectedCardItems = new ArrayList();

    /**
     * Creates a new instance of BuyItemManagedBean
     */
    public BuyItemManagedBean() {
    }

    public void createBuyCardItem(ActionEvent event) {
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

    public void removeBuyCardValue(BuyCardValue buyCardValue) {
        getCardItems().remove(buyCardValue);
        FacesMessage msg = new FacesMessage("Your item is deleted! ");
        bimbl.removeCardValueItem(buyCardValue);
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
    
    public void directTo() throws IOException{
       FacesContext fc = FacesContext.getCurrentInstance();
       ExternalContext ec = fc.getExternalContext();
       ec.redirect("paymentModes.xhtml");    
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
}
