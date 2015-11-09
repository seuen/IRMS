
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for reservationState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reservationState">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="availableNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="booknum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="confirmbook" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="rdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reservationStateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="roomtype" type="{http://common.soap/}roomType" minOccurs="0"/>
 *         &lt;element name="spareNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="todayconfirm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservationState", propOrder = {
    "availableNum",
    "booknum",
    "confirmbook",
    "rdate",
    "reservationStateId",
    "roomtype",
    "spareNum",
    "todayconfirm"
})
public class ReservationState {

    protected int availableNum;
    protected int booknum;
    protected int confirmbook;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rdate;
    protected Long reservationStateId;
    protected RoomType roomtype;
    protected int spareNum;
    protected int todayconfirm;

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
     * Gets the value of the booknum property.
     * 
     */
    public int getBooknum() {
        return booknum;
    }

    /**
     * Sets the value of the booknum property.
     * 
     */
    public void setBooknum(int value) {
        this.booknum = value;
    }

    /**
     * Gets the value of the confirmbook property.
     * 
     */
    public int getConfirmbook() {
        return confirmbook;
    }

    /**
     * Sets the value of the confirmbook property.
     * 
     */
    public void setConfirmbook(int value) {
        this.confirmbook = value;
    }

    /**
     * Gets the value of the rdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRdate() {
        return rdate;
    }

    /**
     * Sets the value of the rdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRdate(XMLGregorianCalendar value) {
        this.rdate = value;
    }

    /**
     * Gets the value of the reservationStateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReservationStateId() {
        return reservationStateId;
    }

    /**
     * Sets the value of the reservationStateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReservationStateId(Long value) {
        this.reservationStateId = value;
    }

    /**
     * Gets the value of the roomtype property.
     * 
     * @return
     *     possible object is
     *     {@link RoomType }
     *     
     */
    public RoomType getRoomtype() {
        return roomtype;
    }

    /**
     * Sets the value of the roomtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoomType }
     *     
     */
    public void setRoomtype(RoomType value) {
        this.roomtype = value;
    }

    /**
     * Gets the value of the spareNum property.
     * 
     */
    public int getSpareNum() {
        return spareNum;
    }

    /**
     * Sets the value of the spareNum property.
     * 
     */
    public void setSpareNum(int value) {
        this.spareNum = value;
    }

    /**
     * Gets the value of the todayconfirm property.
     * 
     */
    public int getTodayconfirm() {
        return todayconfirm;
    }

    /**
     * Sets the value of the todayconfirm property.
     * 
     */
    public void setTodayconfirm(int value) {
        this.todayconfirm = value;
    }

}
