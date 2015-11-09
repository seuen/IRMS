/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall.entity;

import CRM.entity.MemberAccount;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Cindylulu
 */
@Entity
public class ShopOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date purchaseDate;
    private float grossTotalAmount;
    private float discountAmount;
    private float taxAmount;
    private float netTotalAmount;
    private int loyaltyPoint;
//    private TenderType tenderType;  
    private String status; // Paid or unpaid
    
    @ManyToOne
    private MemberAccount memberAccount;
    @OneToMany (mappedBy="shoppingOrder")
    private Collection<DetailShopOrder> detailShoppintOrder;
    
    public ShopOrder() {
        
    }
    
    public ShopOrder(List<DetailShopOrder> dso, MemberAccount mem) {
        this.purchaseDate = Calendar.getInstance().getTime();
        this.grossTotalAmount = 0;
        for (DetailShopOrder d: dso) {
            this.grossTotalAmount += d.getTotalPrice();
        }
        this.discountAmount = 0; // Subject to change according to mem
        this.taxAmount = (float) ((this.grossTotalAmount - this.discountAmount) * 0.07);
        this.netTotalAmount = this.grossTotalAmount - this.discountAmount - this.taxAmount;
        this.loyaltyPoint = (int) (this.netTotalAmount/100);
        this.status = "Unpaid";
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
        if (!(object instanceof ShopOrder)) {
            return false;
        }
        ShopOrder other = (ShopOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ShoppingMall.entity.ShoppingOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    /**
     * @return the detailShoppintOrder
     */
    public Collection<DetailShopOrder> getDetailShoppintOrder() {
        return detailShoppintOrder;
    }

    /**
     * @param detailShoppintOrder the detailShoppintOrder to set
     */
    public void setDetailShoppintOrder(Collection<DetailShopOrder> detailShoppintOrder) {
        this.detailShoppintOrder = detailShoppintOrder;
    }

    /**
     * @return the grossTotalAmount
     */
    public float getGrossTotalAmount() {
        return grossTotalAmount;
    }

    /**
     * @param grossTotalAmount the grossTotalAmount to set
     */
    public void setGrossTotalAmount(float grossTotalAmount) {
        this.grossTotalAmount = grossTotalAmount;
    }

    /**
     * @return the discountAmount
     */
    public float getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the discountAmount to set
     */
    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return the taxAmount
     */
    public float getTaxAmount() {
        return taxAmount;
    }

    /**
     * @param taxAmount the taxAmount to set
     */
    public void setTaxAmount(float taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @return the nettTotalAmount
     */
    public float getNetTotalAmount() {
        return netTotalAmount;
    }

    /**
     * @param nettTotalAmount the nettTotalAmount to set
     */
    public void setNetTotalAmount(float netTotalAmount) {
        this.netTotalAmount = netTotalAmount;
    }

    /**
     * @return the loyaltyPoint
     */
    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    /**
     * @param loyaltyPoint the loyaltyPoint to set
     */
    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
