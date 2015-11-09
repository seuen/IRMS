
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createDetailOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createDetailOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shopItem" type="{http://common.soap/}shopItem" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createDetailOrder", propOrder = {
    "shopItem",
    "quantity"
})
public class CreateDetailOrder {

    protected ShopItem shopItem;
    protected int quantity;

    /**
     * Gets the value of the shopItem property.
     * 
     * @return
     *     possible object is
     *     {@link ShopItem }
     *     
     */
    public ShopItem getShopItem() {
        return shopItem;
    }

    /**
     * Sets the value of the shopItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShopItem }
     *     
     */
    public void setShopItem(ShopItem value) {
        this.shopItem = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

}
