/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.CreditCard;
import CRM.entity.MemberAccount;
import CRM.entity.TransactionRecord;
import CRM.session.MemberPaymentSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class PaymentManagedBean {

    @EJB
    private MemberPaymentSessionBeanLocal mpsbl;
    
    private CreditCard creditCard = new CreditCard();
    private MemberAccount member = new MemberAccount();
    private TransactionRecord tRecord = new TransactionRecord();
    private List<TransactionRecord> allRecords = new ArrayList<>();
    private List<CreditCard> allCreditCards = new ArrayList<>();
    private double totalAmount;

    public PaymentManagedBean() {
    }

    public void addCreditCard() throws IOException {
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        String cardHolder = getCreditCard().getCardHolder();
        System.err.println("CardHoder is " + cardHolder);
        String cardNum = getCreditCard().getCreditCardNum();
        System.err.println("CardNumber is " + cardNum);
        String bank = getCreditCard().getIssueBank();
        String cvv = getCreditCard().getCvv();
        String dateMonth = getCreditCard().getDateMonth();
        String dateYear = getCreditCard().getDateYear();
        System.err.println("Card Expire year " + dateYear);
        this.setCreditCard(mpsbl.addCreditCard(getMember(), cardNum, cardHolder, dateMonth, dateYear, bank, cvv));
        setTotalAmount(this.getTotalAmount());
        this.setMember(mpsbl.updateMembership(getMember(), getTotalAmount()));
        mpsbl.createTransactionRecord(getMember());

        FacesMessage msg = new FacesMessage("Your credit card is completed! ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.redirect("payment.xhtml");

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
            allCreditCards = mpsbl.ListCreditCards(member);
        }
        return allCreditCards;
    }

    public void setAllCreditCards(List<CreditCard> allCreditCards) {
        this.allCreditCards = allCreditCards;
    }
}
