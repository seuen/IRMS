
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addRestaurantTable complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addRestaurantTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="restId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newTable" type="{http://common.soap/}tableType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addRestaurantTable", propOrder = {
    "restId",
    "newTable"
})
public class AddRestaurantTable {

    protected String restId;
    protected TableType newTable;

    /**
     * Gets the value of the restId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestId() {
        return restId;
    }

    /**
     * Sets the value of the restId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestId(String value) {
        this.restId = value;
    }

    /**
     * Gets the value of the newTable property.
     * 
     * @return
     *     possible object is
     *     {@link TableType }
     *     
     */
    public TableType getNewTable() {
        return newTable;
    }

    /**
     * Sets the value of the newTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableType }
     *     
     */
    public void setNewTable(TableType value) {
        this.newTable = value;
    }

}
