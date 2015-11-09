/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ARIEL CHENG
 */

@Entity
public class PackageItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private String department;
    private int quantity;
    private double price;
    @ManyToOne
    private ResortPackage pkg;
    
    public PackageItem (){
        
    }
    
    public void create(String itemName, String department, int quantity, double price){
        this.setItemName(itemName);
        this.setDepartment(department);
        this.setPrice(price);
        this.setQuantity(quantity);
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ResortPackage getPkg() {
        return pkg;
    }

    public void setPkg(ResortPackage pkg) {
        this.pkg = pkg;
    }

}
