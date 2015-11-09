
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for room complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="room">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="floor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HK_status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="roomId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="roomNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomtype" type="{http://common.soap/}roomType" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stay_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stays" type="{http://common.soap/}stay" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "room", propOrder = {
    "floor",
    "hkStatus",
    "roomId",
    "roomNum",
    "roomtype",
    "status",
    "stayStatus",
    "stays"
})
public class Room {

    protected int floor;
    @XmlElement(name = "HK_status")
    protected boolean hkStatus;
    protected Long roomId;
    protected String roomNum;
    protected RoomType roomtype;
    protected String status;
    @XmlElement(name = "stay_status")
    protected String stayStatus;
    @XmlElement(nillable = true)
    protected List<Stay> stays;

    /**
     * Gets the value of the floor property.
     * 
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     */
    public void setFloor(int value) {
        this.floor = value;
    }

    /**
     * Gets the value of the hkStatus property.
     * 
     */
    public boolean isHKStatus() {
        return hkStatus;
    }

    /**
     * Sets the value of the hkStatus property.
     * 
     */
    public void setHKStatus(boolean value) {
        this.hkStatus = value;
    }

    /**
     * Gets the value of the roomId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * Sets the value of the roomId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRoomId(Long value) {
        this.roomId = value;
    }

    /**
     * Gets the value of the roomNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * Sets the value of the roomNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNum(String value) {
        this.roomNum = value;
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
     * Gets the value of the stayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStayStatus() {
        return stayStatus;
    }

    /**
     * Sets the value of the stayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStayStatus(String value) {
        this.stayStatus = value;
    }

    /**
     * Gets the value of the stays property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stays property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStays().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Stay }
     * 
     * 
     */
    public List<Stay> getStays() {
        if (stays == null) {
            stays = new ArrayList<Stay>();
        }
        return this.stays;
    }

}
