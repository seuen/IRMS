
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for negotiator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="negotiator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="negotiatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shopCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shopName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shopType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="venues" type="{http://common.soap/}tenantVenue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "negotiator", propOrder = {
    "email",
    "negotiatorName",
    "shopCategory",
    "shopName",
    "shopType",
    "telNum",
    "venues"
})
public class Negotiator {

    protected String email;
    protected String negotiatorName;
    protected String shopCategory;
    protected String shopName;
    protected String shopType;
    protected String telNum;
    @XmlElement(nillable = true)
    protected List<TenantVenue> venues;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the negotiatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegotiatorName() {
        return negotiatorName;
    }

    /**
     * Sets the value of the negotiatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegotiatorName(String value) {
        this.negotiatorName = value;
    }

    /**
     * Gets the value of the shopCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopCategory() {
        return shopCategory;
    }

    /**
     * Sets the value of the shopCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopCategory(String value) {
        this.shopCategory = value;
    }

    /**
     * Gets the value of the shopName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * Sets the value of the shopName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopName(String value) {
        this.shopName = value;
    }

    /**
     * Gets the value of the shopType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopType() {
        return shopType;
    }

    /**
     * Sets the value of the shopType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopType(String value) {
        this.shopType = value;
    }

    /**
     * Gets the value of the telNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * Sets the value of the telNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelNum(String value) {
        this.telNum = value;
    }

    /**
     * Gets the value of the venues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the venues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVenues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TenantVenue }
     * 
     * 
     */
    public List<TenantVenue> getVenues() {
        if (venues == null) {
            venues = new ArrayList<TenantVenue>();
        }
        return this.venues;
    }

}
