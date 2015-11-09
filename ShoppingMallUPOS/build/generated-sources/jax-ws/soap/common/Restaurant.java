
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for restaurant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="restaurant">
 *   &lt;complexContent>
 *     &lt;extension base="{http://common.soap/}shop">
 *       &lt;sequence>
 *         &lt;element name="priceLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tabletypes" type="{http://common.soap/}tableType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timeOfClosing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timeOfOpening" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalSeats" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "restaurant", propOrder = {
    "priceLevel",
    "resDescription",
    "tabletypes",
    "timeOfClosing",
    "timeOfOpening",
    "totalSeats"
})
public class Restaurant
    extends Shop
{

    protected String priceLevel;
    protected String resDescription;
    @XmlElement(nillable = true)
    protected List<TableType> tabletypes;
    protected String timeOfClosing;
    protected String timeOfOpening;
    protected int totalSeats;

    /**
     * Gets the value of the priceLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceLevel() {
        return priceLevel;
    }

    /**
     * Sets the value of the priceLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceLevel(String value) {
        this.priceLevel = value;
    }

    /**
     * Gets the value of the resDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResDescription() {
        return resDescription;
    }

    /**
     * Sets the value of the resDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResDescription(String value) {
        this.resDescription = value;
    }

    /**
     * Gets the value of the tabletypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tabletypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTabletypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableType }
     * 
     * 
     */
    public List<TableType> getTabletypes() {
        if (tabletypes == null) {
            tabletypes = new ArrayList<TableType>();
        }
        return this.tabletypes;
    }

    /**
     * Gets the value of the timeOfClosing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOfClosing() {
        return timeOfClosing;
    }

    /**
     * Sets the value of the timeOfClosing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOfClosing(String value) {
        this.timeOfClosing = value;
    }

    /**
     * Gets the value of the timeOfOpening property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeOfOpening() {
        return timeOfOpening;
    }

    /**
     * Sets the value of the timeOfOpening property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeOfOpening(String value) {
        this.timeOfOpening = value;
    }

    /**
     * Gets the value of the totalSeats property.
     * 
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * Sets the value of the totalSeats property.
     * 
     */
    public void setTotalSeats(int value) {
        this.totalSeats = value;
    }

}
