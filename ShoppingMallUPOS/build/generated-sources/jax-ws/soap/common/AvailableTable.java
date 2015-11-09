
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for availableTable complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="availableTable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="availableNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="countDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tabletype" type="{http://common.soap/}tableType" minOccurs="0"/>
 *         &lt;element name="timeslot" type="{http://common.soap/}timeslot" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "availableTable", propOrder = {
    "availableNum",
    "countDate",
    "id",
    "tabletype",
    "timeslot"
})
public class AvailableTable {

    protected int availableNum;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar countDate;
    protected Long id;
    protected TableType tabletype;
    protected Timeslot timeslot;

    /**
     * Gets the value of the availableNum property.
     * 
     */
    public int getAvailableNum() {
        return availableNum;
    }

    /**
     * Sets the value of the availableNum property.
     * 
     */
    public void setAvailableNum(int value) {
        this.availableNum = value;
    }

    /**
     * Gets the value of the countDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCountDate() {
        return countDate;
    }

    /**
     * Sets the value of the countDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCountDate(XMLGregorianCalendar value) {
        this.countDate = value;
    }

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
     * Gets the value of the tabletype property.
     * 
     * @return
     *     possible object is
     *     {@link TableType }
     *     
     */
    public TableType getTabletype() {
        return tabletype;
    }

    /**
     * Sets the value of the tabletype property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableType }
     *     
     */
    public void setTabletype(TableType value) {
        this.tabletype = value;
    }

    /**
     * Gets the value of the timeslot property.
     * 
     * @return
     *     possible object is
     *     {@link Timeslot }
     *     
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Sets the value of the timeslot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timeslot }
     *     
     */
    public void setTimeslot(Timeslot value) {
        this.timeslot = value;
    }

}
