/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.CreditCard;
import CRM.entity.MemberAccount;
import CRM.entity.TransactionRecord;
import CRM.session.BuyItemSessionBeanLocal;
import CRM.session.MemberPaymentSessionBeanLocal;
import CRM.session.PaypalSessionBeanLocal;
import com.paypal.exception.SSLConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class ExternalPaymentManagedBean {

    @EJB
    private MemberPaymentSessionBeanLocal mpsbl;
    @EJB
    private PaypalSessionBeanLocal psbl;
    @EJB
    private BuyItemSessionBeanLocal bisbl;
    private CreditCard creditCard = new CreditCard();
    private MemberAccount member = new MemberAccount();
    private TransactionRecord tRecord = new TransactionRecord();
    private List<TransactionRecord> allRecords = new ArrayList<>();
    private List<CreditCard> allCreditCards = new ArrayList<>();
    private double totalAmount;
    private double payByCard;
   
    private String cardType;
    private String cardNum;
    private String firstName;
    private String lastName;
    private String cvv;
    private String expireDateMM;
    private String expireDateYYYY;
    private Boolean success;
    private String email;
    private DoDirectPaymentResponseType response;
    private ShoppingCartManagedBean shoppingcart;

    public ExternalPaymentManagedBean() {
    }

    public void checkPayByCard() throws IOException {
        System.err.println("test check pay by card");
        totalAmount = (double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("amount");
        System.err.println("Total Amount: " + totalAmount);
        System.err.println(payByCard);
        member = ((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (payByCard > totalAmount) {
            System.err.println("test");
            FacesMessage msg = new FacesMessage("Please provide valid card value.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (payByCard > member.getMembership().getCardValue()) {
                FacesMessage msg = new FacesMessage("Your card value is not enough, please try again.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                if (payByCard == totalAmount) {
                    mpsbl.payByCardValue(member, totalAmount);
                    System.err.println("member card value 1 :" + member.getMembership().getCardValue());
                    mpsbl.createTransactionRecord(member);
                    mpsbl.rewardCardValue(member, totalAmount);

                    ec.redirect("paymentDone.xhtml");
                } else {
                    mpsbl.payByCardValue(member, payByCard);
                    double temp = totalAmount - payByCard;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("amount");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("amount", temp);
                    System.err.println("current total amount: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("amount"));

                    ec.redirect("creditCard.xhtml");
                }
            }
        }
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public MemberAccount getMember() {
        member = ((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public double getTotalAmount() {
        totalAmount = (double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("amount");
        this.setTotalAmount(totalAmount);
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TransactionRecord gettRecord() {
        return tRecord;
    }

    public void settRecord(TransactionRecord tRecord) {
        this.tRecord = tRecord;
    }

    public List<TransactionRecord> getAllRecords() {
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if ((allRecords == null) || allRecords.isEmpty()) {
            allRecords = mpsbl.ListTransactionRecords(getMember());
        }
        return allRecords;
    }

    public void setAllRecords(List<TransactionRecord> allRecords) {
        this.allRecords = allRecords;
    }

    public void removeCreditCard(CreditCard creditCard) {
        getAllCreditCards().remove(creditCard);
        mpsbl.DeleteCreditCard(creditCard);
        FacesMessage msg = new FacesMessage("This credit card record is deleted ");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public List<CreditCard> getAllCreditCards() {
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        if ((allCreditCards == null) || allCreditCards.isEmpty()) {
            allCreditCards = mpsbl.ListCreditCards(getMember());
        }
        return allCreditCards;
    }

    public void setAllCreditCards(List<CreditCard> allCreditCards) {
        this.allCreditCards = allCreditCards;
    }

    public void doPaypalPayment() throws SSLConfigurationException, IOException {
        System.err.println("paypal start....");
        String response;
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        totalAmount = (double) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("amount");
        response = psbl.doDirectPayment(totalAmount, getCardType(), getCardNum(), getCvv(), getExpireDateMM(), getExpireDateYYYY(), getEmail());
        if (response.contains("Transaction is successful!")) {
            setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
            if (member != null) {
                mpsbl.createTransactionRecord(member);
                mpsbl.updateMembership(member, totalAmount);
                ec.redirect("paymentDone.xhtml");
            }

        }

        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the cardNum
     */
    public String getCardNum() {
        return cardNum;
    }

    /**
     * @param cardNum the cardNum to set
     */
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the expireDateMM
     */
    public String getExpireDateMM() {
        return expireDateMM;
    }

    /**
     * @param expireDateMM the expireDateMM to set
     */
    public void setExpireDateMM(String expireDateMM) {
        this.expireDateMM = expireDateMM;
    }

    /**
     * @return the expireDateYYYY
     */
    public String getExpireDateYYYY() {
        return expireDateYYYY;
    }

    /**
     * @param expireDateYYYY the expireDateYYYY to set
     */
    public void setExpireDateYYYY(String expireDateYYYY) {
        this.expireDateYYYY = expireDateYYYY;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the response
     */
    public DoDirectPaymentResponseType getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(DoDirectPaymentResponseType response) {
        this.response = response;
    }

    /**
     * @return the payByCard
     */
    public double getPayByCard() {
        return payByCard;
    }

    /**
     * @param payByCard the payByCard to set
     */
    public void setPayByCard(double payByCard) {
        this.payByCard = payByCard;
    }

    /**
     * @return the shoppingcart
     */
    public ShoppingCartManagedBean getShoppingcart() {
        return shoppingcart;
    }

    /**
     * @param shoppingcart the shoppingcart to set
     */
    public void setShoppingcart(ShoppingCartManagedBean shoppingcart) {
        this.shoppingcart = shoppingcart;
    }
}
