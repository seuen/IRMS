
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createShopOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createShopOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shopOrder" type="{http://common.soap/}shopOrder" minOccurs="0"/>
 *         &lt;element name="detailOrders" type="{http://common.soap/}detailShopOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createShopOrder", propOrder = {
    "shopOrder",
    "detailOrders",
    "memberId"
})
public class CreateShopOrder {

    protected ShopOrder shopOrder;
    protected List<DetailShopOrder> detailOrders;
    protected Long memberId;

    /**
     * Gets the value of the shopOrder property.
     * 
     * @return
     *     possible object is
     *     {@link ShopOrder }
     *     
     */
    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    /**
     * Sets the value of the shopOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShopOrder }
     *     
     */
    public void setShopOrder(ShopOrder value) {
        this.shopOrder = value;
    }

    /**
     * Gets the value of the detailOrders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detailOrders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetailOrders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetailShopOrder }
     * 
     * 
     */
    public List<DetailShopOrder> getDetailOrders() {
        if (detailOrders == null) {
            detailOrders = new ArrayList<DetailShopOrder>();
        }
        return this.detailOrders;
    }

    /**
     * Gets the value of the memberId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * Sets the value of the memberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMemberId(Long value) {
        this.memberId = value;
    }

}
