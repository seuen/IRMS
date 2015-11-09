/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class ResortPackage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String packageName;
    private String picName;
    private double price;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expireDate;
    private boolean postToWeb;
    private String packageType;
    private String imageType;
    
    @OneToMany(mappedBy = "pkg")
    private List<PackageItem> packageItems;
    
    public ResortPackage(){
        
    }

    public void create(String packageName, double price, Date expireDate, boolean postToWeb, String packageType) {
        this.setPackageName(packageName);
        this.setPicName("default");
        this.setPrice(price);
        this.setExpireDate(expireDate);
        this.setPostToWeb(postToWeb);
        this.setPackageType(packageType);
        this.setImageType("jpg");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isPostToWeb() {
        return postToWeb;
    }

    public void setPostToWeb(boolean postToWeb) {
        this.postToWeb = postToWeb;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public List<PackageItem> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(List<PackageItem> packageItems) {
        this.packageItems = packageItems;
    }

    /**
     * @return the imageType
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * @param imageType the imageType to set
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
