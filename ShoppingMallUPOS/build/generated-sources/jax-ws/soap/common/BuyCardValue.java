
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for buyCardValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buyCardValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buyTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cardValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="memberAccount" type="{http://common.soap/}memberAccount" minOccurs="0"/>
 *         &lt;element name="topupType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buyCardValue", propOrder = {
    "buyTime",
    "cardValue",
    "id",
    "memberAccount",
    "topupType"
})
public class BuyCardValue {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar buyTime;
    protected double cardValue;
    protected Long id;
    protected MemberAccount memberAccount;
    protected String topupType;

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
     * Gets the value of the cardValue property.
     * 
     */
    public double getCardValue() {
        return cardValue;
    }

    /**
     * Sets the value of the cardValue property.
     * 
     */
    public void setCardValue(double value) {
        this.cardValue = value;
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
     * Gets the value of the topupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopupType() {
        return topupType;
    }

    /**
     * Sets the value of the topupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopupType(String value) {
        this.topupType = value;
    }

}
