
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getShoppingMallItemsByShopId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getShoppingMallItemsByShopId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shopId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getShoppingMallItemsByShopId", propOrder = {
    "shopId"
})
public class GetShoppingMallItemsByShopId {

    protected long shopId;

    /**
     * Gets the value of the shopId property.
     * 
     */
    public long getShopId() {
        return shopId;
    }

    /**
     * Sets the value of the shopId property.
     * 
     */
    public void setShopId(long value) {
        this.shopId = value;
    }

}
