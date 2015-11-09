/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Accommodation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
/**
 *
 * @author zsy
 */
@Entity
public class RSOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNum;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateo;
    private float totalPrice;
    private String payStatus;
    
    @ManyToOne
    private Stay stay;
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="rsorder")
    private Collection<ItemOrder> itemOrders;
    
    public RSOrder(){
        this.itemOrders=new ArrayList();
    }
    
    public void create(Date dateo, Float totalPrice){
        this.setDateo(dateo);
        this.setTotalPrice(totalPrice);
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getOrderNum() != null ? getOrderNum().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RSOrder)) {
            return false;
        }
        RSOrder other = (RSOrder) object;
        if ((this.getOrderNum() == null && other.getOrderNum() != null) || (this.getOrderNum() != null && !this.orderNum.equals(other.orderNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.RSOrder[ orderNum=" + getOrderNum() + " ]";
    }

    /**
     * @return the orderNum
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum the orderNum to set
     */
    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return the date
     */
    public Date getDateo() {
        return dateo;
    }

    /**
     * @param date the date to set
     */
    public void setDateo(Date dateo) {
        this.dateo = dateo;
    }

    /**
     * @return the totalPrice
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the privateStatus
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * @param privateStatus the privateStatus to set
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return the stay
     */
    public Stay getStay() {
        return stay;
    }

    /**
     * @param stay the stay to set
     */
    public void setStay(Stay stay) {
        this.stay = stay;
    }

    /**
     * @return the itemOrders
     */
    public Collection<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    /**
     * @param itemOrders the itemOrders to set
     */
    public void setItemOrders(Collection<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }
    
}
