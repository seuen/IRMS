
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rsOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rsOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="itemOrders" type="{http://common.soap/}itemOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderNum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="payStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stay" type="{http://common.soap/}stay" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rsOrder", propOrder = {
    "dateo",
    "itemOrders",
    "orderNum",
    "payStatus",
    "stay",
    "totalPrice"
})
public class RsOrder {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateo;
    @XmlElement(nillable = true)
    protected List<ItemOrder> itemOrders;
    protected Long orderNum;
    protected String payStatus;
    protected Stay stay;
    protected Float totalPrice;

    /**
     * Gets the value of the dateo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateo() {
        return dateo;
    }

    /**
     * Sets the value of the dateo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateo(XMLGregorianCalendar value) {
        this.dateo = value;
    }

    /**
     * Gets the value of the itemOrders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemOrders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemOrders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemOrder }
     * 
     * 
     */
    public List<ItemOrder> getItemOrders() {
        if (itemOrders == null) {
            itemOrders = new ArrayList<ItemOrder>();
        }
        return this.itemOrders;
    }

    /**
     * Gets the value of the orderNum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrderNum() {
        return orderNum;
    }

    /**
     * Sets the value of the orderNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrderNum(Long value) {
        this.orderNum = value;
    }

    /**
     * Gets the value of the payStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * Sets the value of the payStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayStatus(String value) {
        this.payStatus = value;
    }

    /**
     * Gets the value of the stay property.
     * 
     * @return
     *     possible object is
     *     {@link Stay }
     *     
     */
    public Stay getStay() {
        return stay;
    }

    /**
     * Sets the value of the stay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Stay }
     *     
     */
    public void setStay(Stay value) {
        this.stay = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotalPrice(Float value) {
        this.totalPrice = value;
    }

}
