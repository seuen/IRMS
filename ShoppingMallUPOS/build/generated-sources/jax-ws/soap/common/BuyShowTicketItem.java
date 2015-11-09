
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for buyShowTicketItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buyShowTicketItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="memberAccount" type="{http://common.soap/}memberAccount" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ticketName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buyShowTicketItem", propOrder = {
    "buyTime",
    "id",
    "memberAccount",
    "quantity",
    "ticketName",
    "unitPrice"
})
public class BuyShowTicketItem {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar buyTime;
    protected Long id;
    protected MemberAccount memberAccount;
    protected int quantity;
    protected String ticketName;
    protected double unitPrice;

    /**
     * Gets the value of the buyTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBuyTime() {
        return buyTime;
    }

    /**
     * Sets the value of the buyTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBuyTime(XMLGregorianCalendar value) {
        this.buyTime = value;
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
     * Gets the value of the memberAccount property.
     * 
     * @return
     *     possible object is
     *     {@link MemberAccount }
     *     
     */
    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    /**
     * Sets the value of the memberAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberAccount }
     *     
     */
    public void setMemberAccount(MemberAccount value) {
        this.memberAccount = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the ticketName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketName() {
        return ticketName;
    }

    /**
     * Sets the value of the ticketName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketName(String value) {
        this.ticketName = value;
    }

    /**
     * Gets the value of the unitPrice property.
     * 
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the value of the unitPrice property.
     * 
     */
    public void setUnitPrice(double value) {
        this.unitPrice = value;
    }

}
