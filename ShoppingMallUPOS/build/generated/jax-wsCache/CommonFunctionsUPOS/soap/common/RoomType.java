
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roomType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roomType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bedNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hotel" type="{http://common.soap/}hotel" minOccurs="0"/>
 *         &lt;element name="maxGuest" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ORatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pictures" type="{http://common.soap/}picture" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="price_h" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="price_l" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="reservationStates" type="{http://common.soap/}reservationState" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reservations" type="{http://common.soap/}rtReservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rooms" type="{http://common.soap/}room" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roomtypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="totalNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roomType", propOrder = {
    "bedNum",
    "detail",
    "hotel",
    "maxGuest",
    "oRatio",
    "pictures",
    "priceH",
    "priceL",
    "reservationStates",
    "reservations",
    "rooms",
    "roomtypeId",
    "totalNum",
    "type"
})
public class RoomType {

    protected int bedNum;
    protected String detail;
    protected Hotel hotel;
    protected int maxGuest;
    @XmlElement(name = "ORatio")
    protected float oRatio;
    @XmlElement(nillable = true)
    protected List<Picture> pictures;
    @XmlElement(name = "price_h")
    protected float priceH;
    @XmlElement(name = "price_l")
    protected float priceL;
    @XmlElement(nillable = true)
    protected List<ReservationState> reservationStates;
    @XmlElement(nillable = true)
    protected List<RtReservation> reservations;
    @XmlElement(nillable = true)
    protected List<Room> rooms;
    protected Long roomtypeId;
    protected int totalNum;
    protected String type;

    /**
     * Gets the value of the bedNum property.
     * 
     */
    public int getBedNum() {
        return bedNum;
    }

    /**
     * Sets the value of the bedNum property.
     * 
     */
    public void setBedNum(int value) {
        this.bedNum = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetail(String value) {
        this.detail = value;
    }

    /**
     * Gets the value of the hotel property.
     * 
     * @return
     *     possible object is
     *     {@link Hotel }
     *     
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Sets the value of the hotel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hotel }
     *     
     */
    public void setHotel(Hotel value) {
        this.hotel = value;
    }

    /**
     * Gets the value of the maxGuest property.
     * 
     */
    public int getMaxGuest() {
        return maxGuest;
    }

    /**
     * Sets the value of the maxGuest property.
     * 
     */
    public void setMaxGuest(int value) {
        this.maxGuest = value;
    }

    /**
     * Gets the value of the oRatio property.
     * 
     */
    public float getORatio() {
        return oRatio;
    }

    /**
     * Sets the value of the oRatio property.
     * 
     */
    public void setORatio(float value) {
        this.oRatio = value;
    }

    /**
     * Gets the value of the pictures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pictures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPictures().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Picture }
     * 
     * 
     */
    public List<Picture> getPictures() {
        if (pictures == null) {
            pictures = new ArrayList<Picture>();
        }
        return this.pictures;
    }

    /**
     * Gets the value of the priceH property.
     * 
     */
    public float getPriceH() {
        return priceH;
    }

    /**
     * Sets the value of the priceH property.
     * 
     */
    public void setPriceH(float value) {
        this.priceH = value;
    }

    /**
     * Gets the value of the priceL property.
     * 
     */
    public float getPriceL() {
        return priceL;
    }

    /**
     * Sets the value of the priceL property.
     * 
     */
    public void setPriceL(float value) {
        this.priceL = value;
    }

    /**
     * Gets the value of the reservationStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservationStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservationStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReservationState }
     * 
     * 
     */
    public List<ReservationState> getReservationStates() {
        if (reservationStates == null) {
            reservationStates = new ArrayList<ReservationState>();
        }
        return this.reservationStates;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RtReservation }
     * 
     * 
     */
    public List<RtReservation> getReservations() {
        if (reservations == null) {
            reservations = new ArrayList<RtReservation>();
        }
        return this.reservations;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rooms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRooms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */
    public List<Room> getRooms() {
        if (rooms == null) {
            rooms = new ArrayList<Room>();
        }
        return this.rooms;
    }

    /**
     * Gets the value of the roomtypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoomtypeId() {
        return roomtypeId;
    }

    /**
     * Sets the value of the roomtypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoomtypeId(Long value) {
        this.roomtypeId = value;
    }

    /**
     * Gets the value of the totalNum property.
     * 
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     * Sets the value of the totalNum property.
     * 
     */
    public void setTotalNum(int value) {
        this.totalNum = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
