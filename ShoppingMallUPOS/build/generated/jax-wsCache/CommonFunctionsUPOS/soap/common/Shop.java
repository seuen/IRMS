
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adhocEvents" type="{http://common.soap/}adhocEvent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contracts" type="{http://common.soap/}contract" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="currentVenues" type="{http://common.soap/}tenantVenue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="historyVenues" type="{http://common.soap/}tenantVenue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="shopId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="shopName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shopOwnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shopType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shoppingItems" type="{http://common.soap/}shopItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shop", propOrder = {
    "adhocEvents",
    "area",
    "category",
    "contractStatus",
    "contracts",
    "currentVenues",
    "email",
    "historyVenues",
    "shopId",
    "shopName",
    "shopOwnerName",
    "shopType",
    "shoppingItems",
    "status",
    "telNum"
})
@XmlSeeAlso({
    Restaurant.class
})
public class Shop {

    @XmlElement(nillable = true)
    protected List<AdhocEvent> adhocEvents;
    protected int area;
    protected String category;
    protected String contractStatus;
    @XmlElement(nillable = true)
    protected List<Contract> contracts;
    @XmlElement(nillable = true)
    protected List<TenantVenue> currentVenues;
    protected String email;
    @XmlElement(nillable = true)
    protected List<TenantVenue> historyVenues;
    protected Long shopId;
    protected String shopName;
    protected String shopOwnerName;
    protected String shopType;
    @XmlElement(nillable = true)
    protected List<ShopItem> shoppingItems;
    protected String status;
    protected String telNum;

    /**
     * Gets the value of the adhocEvents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adhocEvents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdhocEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdhocEvent }
     * 
     * 
     */
    public List<AdhocEvent> getAdhocEvents() {
        if (adhocEvents == null) {
            adhocEvents = new ArrayList<AdhocEvent>();
        }
        return this.adhocEvents;
    }

    /**
     * Gets the value of the area property.
     * 
     */
    public int getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     */
    public void setArea(int value) {
        this.area = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the contractStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractStatus() {
        return contractStatus;
    }

    /**
     * Sets the value of the contractStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractStatus(String value) {
        this.contractStatus = value;
    }

    /**
     * Gets the value of the contracts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contracts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContracts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contract }
     * 
     * 
     */
    public List<Contract> getContracts() {
        if (contracts == null) {
            contracts = new ArrayList<Contract>();
        }
        return this.contracts;
    }

    /**
     * Gets the value of the currentVenues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the currentVenues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurrentVenues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TenantVenue }
     * 
     * 
     */
    public List<TenantVenue> getCurrentVenues() {
        if (currentVenues == null) {
            currentVenues = new ArrayList<TenantVenue>();
        }
        return this.currentVenues;
    }

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
     * Gets the value of the historyVenues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the historyVenues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHistoryVenues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TenantVenue }
     * 
     * 
     */
    public List<TenantVenue> getHistoryVenues() {
        if (historyVenues == null) {
            historyVenues = new ArrayList<TenantVenue>();
        }
        return this.historyVenues;
    }

    /**
     * Gets the value of the shopId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * Sets the value of the shopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setShopId(Long value) {
        this.shopId = value;
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
     * Gets the value of the shopOwnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShopOwnerName() {
        return shopOwnerName;
    }

    /**
     * Sets the value of the shopOwnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShopOwnerName(String value) {
        this.shopOwnerName = value;
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
     * Gets the value of the shoppingItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shoppingItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShoppingItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShopItem }
     * 
     * 
     */
    public List<ShopItem> getShoppingItems() {
        if (shoppingItems == null) {
            shoppingItems = new ArrayList<ShopItem>();
        }
        return this.shoppingItems;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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

}
