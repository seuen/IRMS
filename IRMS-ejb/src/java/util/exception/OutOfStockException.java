/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author Cindylulu
 */
@ApplicationException(rollback=true)
public class OutOfStockException extends Exception{
    private Long itemId;
    private String itemName;
    private float price;
    private int quantity;
    private Long barcode;
    private String category;
    
    public OutOfStockException() {
        
    }
    
    public OutOfStockException(Long itemId, String itemName, float price, int quantity, Long barcode,String category) {
        this.itemId=itemId;
        this.itemName=itemName;
        this.price=price;
        this.quantity=quantity;
        this.barcode=barcode;
        this.category=category;
    } 
    
    @Override
    public String toString(){
        return "Item ["+getItemId()+"]"+" "+getItemName()+" ("+getPrice()+" SGD) is out of stock.\n   Current stock is"+getQuantity()+"\n  Please look for other items under category: "+getCategory();
    }

    /**
     * @return the itemId
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the barcode
     */
    public Long getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
    
