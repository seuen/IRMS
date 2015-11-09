/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class CreditCard implements Serializable {

    @Id
    private String creditCardNum;
    private String cardHolder;
    private String issueBank;
    private String dateMonth;
    private String dateYear;     
    private String cvv;
    @ManyToOne
    private MemberAccount member;
    
    public CreditCard(){
        
    }
    
    public void create(String creditCardNum, String cardHolder, String issueBank, String dateMonth,String dateYear, String cvv){
        this.setCreditCardNum(creditCardNum);
        this.setCardHolder(cardHolder);
        this.setIssueBank(issueBank);
        this.setDateMonth(dateMonth);
        this.setDateYear(dateYear);
        this.setCvv(cvv);
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getIssueBank() {
        return issueBank;
    }

    public void setIssueBank(String issueBank) {
        this.issueBank = issueBank;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateYear() {
        return dateYear;
    }

    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }
    
    
    
}
