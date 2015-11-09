
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addShoppingMallItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addShoppingMallItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="curShop" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newItem" type="{http://common.soap/}shopItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addShoppingMallItem", propOrder = {
    "curShop",
    "newItem"
})
public class AddShoppingMallItem {

    protected String curShop;
    protected ShopItem newItem;

    /**
     * Gets the value of the curShop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurShop() {
        return curShop;
    }

    /**
     * Sets the value of the curShop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurShop(String value) {
        this.curShop = value;
    }

    /**
     * Gets the value of the newItem property.
     * 
     * @return
     *     possible object is
     *     {@link ShopItem }
     *     
     */
    public ShopItem getNewItem() {
        return newItem;
    }

    /**
     * Sets the value of the newItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShopItem }
     *     
     */
    public void setNewItem(ShopItem value) {
        this.newItem = value;
    }

}
