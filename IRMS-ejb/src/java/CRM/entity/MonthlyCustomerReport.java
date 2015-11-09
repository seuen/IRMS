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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class MonthlyCustomerReport implements Serializable {

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
    private String name;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date generateDate;
    private double monthlyExpense;
    private double accommodationCost;
    private double foodCost;
    private double ticketCost;
    private double shoppingCost;
    private double otherCost;
    
    @ManyToOne
    private MemberAccount member;
    
    public MonthlyCustomerReport(){        
    }
    
    public void create(String name, Date generateDate, double monthlyExpense, double accommodationCost, double foodCost, double ticketCost,
            double shoppingCost, double otherCost){
        this.setName(name);
        this.setGenerateDate(generateDate);
        this.setMonthlyExpense(monthlyExpense);
        this.setAccommodationCost(accommodationCost);
        this.setFoodCost(foodCost);
        this.setTicketCost(ticketCost);
        this.setShoppingCost(shoppingCost);
        this.setOtherCost(otherCost);
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
        if (!(object instanceof MonthlyCustomerReport)) {
            return false;
        }
        MonthlyCustomerReport other = (MonthlyCustomerReport) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.MonthlyCustomerReport[ id=" + getId() + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the generateDate
     */
    public Date getGenerateDate() {
        return generateDate;
    }

    /**
     * @param generateDate the generateDate to set
     */
    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    /**
     * @return the monthlyExpense
     */
    public double getMonthlyExpense() {
        return monthlyExpense;
    }

    /**
     * @param monthlyExpense the monthlyExpense to set
     */
    public void setMonthlyExpense(double monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    /**
     * @return the accommodationCost
     */
    public double getAccommodationCost() {
        return accommodationCost;
    }

    /**
     * @param accommodationCost the accommodationCost to set
     */
    public void setAccommodationCost(double accommodationCost) {
        this.accommodationCost = accommodationCost;
    }

    /**
     * @return the foodCost
     */
    public double getFoodCost() {
        return foodCost;
    }

    /**
     * @param foodCost the foodCost to set
     */
    public void setFoodCost(double foodCost) {
        this.foodCost = foodCost;
    }

    /**
     * @return the ticketCost
     */
    public double getTicketCost() {
        return ticketCost;
    }

    /**
     * @param ticketCost the ticketCost to set
     */
    public void setTicketCost(double ticketCost) {
        this.ticketCost = ticketCost;
    }

    /**
     * @return the shoppingCost
     */
    public double getShoppingCost() {
        return shoppingCost;
    }

    /**
     * @param shoppingCost the shoppingCost to set
     */
    public void setShoppingCost(double shoppingCost) {
        this.shoppingCost = shoppingCost;
    }

    /**
     * @return the otherCost
     */
    public double getOtherCost() {
        return otherCost;
    }

    /**
     * @param otherCost the otherCost to set
     */
    public void setOtherCost(double otherCost) {
        this.otherCost = otherCost;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }
}
