
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tenantVenue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tenantVenue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="building" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="combine" type="{http://common.soap/}combine" minOccurs="0"/>
 *         &lt;element name="combineStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="counter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="floor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="negoAvailability" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="negotiators" type="{http://common.soap/}negotiator" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="occupiedShopId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="realID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shop" type="{http://common.soap/}shop" minOccurs="0"/>
 *         &lt;element name="shops" type="{http://common.soap/}shop" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tenantVenue", propOrder = {
    "area",
    "building",
    "combine",
    "combineStatus",
    "counter",
    "floor",
    "id",
    "negoAvailability",
    "negotiators",
    "occupiedShopId",
    "realID",
    "sector",
    "shop",
    "shops",
    "status"
})
public class TenantVenue {

    protected int area;
    protected String building;
    protected Combine combine;
    protected String combineStatus;
    protected int counter;
    protected String floor;
    protected String id;
    protected String negoAvailability;
    @XmlElement(nillable = true)
    protected List<Negotiator> negotiators;
    protected Long occupiedShopId;
    protected Long realID;
    protected String sector;
    protected Shop shop;
    @XmlElement(nillable = true)
    protected List<Shop> shops;
    protected String status;

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
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilding(String value) {
        this.building = value;
    }

    /**
     * Gets the value of the combine property.
     * 
     * @return
     *     possible object is
     *     {@link Combine }
     *     
     */
    public Combine getCombine() {
        return combine;
    }

    /**
     * Sets the value of the combine property.
     * 
     * @param value
     *     allowed object is
     *     {@link Combine }
     *     
     */
    public void setCombine(Combine value) {
        this.combine = value;
    }

    /**
     * Gets the value of the combineStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombineStatus() {
        return combineStatus;
    }

    /**
     * Sets the value of the combineStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombineStatus(String value) {
        this.combineStatus = value;
    }

    /**
     * Gets the value of the counter property.
     * 
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets the value of the counter property.
     * 
     */
    public void setCounter(int value) {
        this.counter = value;
    }

    /**
     * Gets the value of the floor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the negoAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegoAvailability() {
        return negoAvailability;
    }

    /**
     * Sets the value of the negoAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegoAvailability(String value) {
        this.negoAvailability = value;
    }

    /**
     * Gets the value of the negotiators property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the negotiators property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNegotiators().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Negotiator }
     * 
     * 
     */
    public List<Negotiator> getNegotiators() {
        if (negotiators == null) {
            negotiators = new ArrayList<Negotiator>();
        }
        return this.negotiators;
    }

    /**
     * Gets the value of the occupiedShopId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOccupiedShopId() {
        return occupiedShopId;
    }

    /**
     * Sets the value of the occupiedShopId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOccupiedShopId(Long value) {
        this.occupiedShopId = value;
    }

    /**
     * Gets the value of the realID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRealID() {
        return realID;
    }

    /**
     * Sets the value of the realID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRealID(Long value) {
        this.realID = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSector(String value) {
        this.sector = value;
    }

    /**
     * Gets the value of the shop property.
     * 
     * @return
     *     possible object is
     *     {@link Shop }
     *     
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets the value of the shop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shop }
     *     
     */
    public void setShop(Shop value) {
        this.shop = value;
    }

    /**
     * Gets the value of the shops property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shops property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShops().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Shop }
     * 
     * 
     */
    public List<Shop> getShops() {
        if (shops == null) {
            shops = new ArrayList<Shop>();
        }
        return this.shops;
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

}
