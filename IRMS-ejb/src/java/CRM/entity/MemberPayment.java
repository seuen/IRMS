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
public class MemberPayment implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date currentDate;
    private double totalAmount;
    private double payByCreditCard;
    @ManyToOne
    private MemberAccount memberAccount;
    
    public MemberPayment(){
        
    }
    
    public void create(Date currentDate, double totalAmount, double paybycredit){
        this.setCurrentDate(currentDate);
        this.setPayByCreditCard(payByCreditCard);
        this.setTotalAmount(totalAmount);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberPayment)) {
            return false;
        }
        MemberPayment other = (MemberPayment) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.payment[ id=" + getId() + " ]";
    }

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return the payByCreditCard
     */
    public double getPayByCreditCard() {
        return payByCreditCard;
    }

    /**
     * @param payByCreditCard the payByCreditCard to set
     */
    public void setPayByCreditCard(double payByCreditCard) {
        this.payByCreditCard = payByCreditCard;
    }

    /**
     * @return the memberAccount
     */
    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    /**
     * @param memberAccount the memberAccount to set
     */
    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }
    
}
