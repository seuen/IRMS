/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class TransactionRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private int quantity;
    private String fromWhere;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date transactionTime;
    private double totalPrice;
    private double payByCreditCard;
    @ManyToOne
    private MemberAccount member;
    
    public TransactionRecord(){
        
    }
    
    public void create(String  itemName, int quantity, String fromWhere, Date transactionTime, double totalPrice, double paybyCreditCard){
        this.setItemName(itemName);
        this.setQuantity(quantity);
        this.setFromWhere(fromWhere);
        this.setTransactionTime(transactionTime);
        this.setTotalPrice(totalPrice);
        this.setPayByCreditCard(paybyCreditCard);
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getPayByCreditCard() {
        return payByCreditCard;
    }

    public void setPayByCreditCard(double payByCreditCard) {
        this.payByCreditCard = payByCreditCard;
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }


}
