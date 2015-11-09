
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for combine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="combine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="test" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "combine", propOrder = {
    "id",
    "test",
    "venues"
})
public class Combine {

    protected Long id;
    protected int test;
    @XmlElement(nillable = true)
    protected List<TenantVenue> venues;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the test property.
     * 
     */
    public int getTest() {
        return test;
    }

    /**
     * Sets the value of the test property.
     * 
     */
    public void setTest(int value) {
        this.test = value;
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
