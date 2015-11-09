
package soap.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for memberAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="memberAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://common.soap/}address" minOccurs="0"/>
 *         &lt;element name="buyCardValues" type="{http://common.soap/}buyCardValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="buyHotelItems" type="{http://common.soap/}buyHotelItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="buyShowTicketItems" type="{http://common.soap/}buyShowTicketItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="creditCards" type="{http://common.soap/}creditCard" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="emailAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedbacks" type="{http://common.soap/}feedback" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberPayments" type="{http://common.soap/}memberPayment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="membership" type="{http://common.soap/}membership" minOccurs="0"/>
 *         &lt;element name="monthlyCustomerReports" type="{http://common.soap/}monthlyCustomerReport" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservations" type="{http://common.soap/}rtReservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="shoppingOrder" type="{http://common.soap/}shopOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionRecords" type="{http://common.soap/}transactionRecord" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "memberAccount", propOrder = {
    "address",
    "buyCardValues",
    "buyHotelItems",
    "buyShowTicketItems",
    "creditCards",
    "dateOfBirth",
    "emailAddr",
    "feedbacks",
    "firstName",
    "id",
    "lastName",
    "memberPayments",
    "membership",
    "monthlyCustomerReports",
    "nationality",
    "phoneNum",
    "reservations",
    "shoppingOrder",
    "title",
    "transactionRecords"
})
public class MemberAccount {

    protected Address address;
    @XmlElement(nillable = true)
    protected List<BuyCardValue> buyCardValues;
    @XmlElement(nillable = true)
    protected List<BuyHotelItem> buyHotelItems;
    @XmlElement(nillable = true)
    protected List<BuyShowTicketItem> buyShowTicketItems;
    @XmlElement(nillable = true)
    protected List<CreditCard> creditCards;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOfBirth;
    protected String emailAddr;
    @XmlElement(nillable = true)
    protected List<Feedback> feedbacks;
    protected String firstName;
    protected Long id;
    protected String lastName;
    @XmlElement(nillable = true)
    protected List<MemberPayment> memberPayments;
    protected Membership membership;
    @XmlElement(nillable = true)
    protected List<MonthlyCustomerReport> monthlyCustomerReports;
    protected String nationality;
    protected String phoneNum;
    @XmlElement(nillable = true)
    protected List<RtReservation> reservations;
    @XmlElement(nillable = true)
    protected List<ShopOrder> shoppingOrder;
    protected String title;
    @XmlElement(nillable = true)
    protected List<TransactionRecord> transactionRecords;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the buyCardValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buyCardValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuyCardValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuyCardValue }
     * 
     * 
     */
    public List<BuyCardValue> getBuyCardValues() {
        if (buyCardValues == null) {
            buyCardValues = new ArrayList<BuyCardValue>();
        }
        return this.buyCardValues;
    }

    /**
     * Gets the value of the buyHotelItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buyHotelItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuyHotelItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuyHotelItem }
     * 
     * 
     */
    public List<BuyHotelItem> getBuyHotelItems() {
        if (buyHotelItems == null) {
            buyHotelItems = new ArrayList<BuyHotelItem>();
        }
        return this.buyHotelItems;
    }

    /**
     * Gets the value of the buyShowTicketItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buyShowTicketItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBuyShowTicketItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BuyShowTicketItem }
     * 
     * 
     */
    public List<BuyShowTicketItem> getBuyShowTicketItems() {
        if (buyShowTicketItems == null) {
            buyShowTicketItems = new ArrayList<BuyShowTicketItem>();
        }
        return this.buyShowTicketItems;
    }

    /**
     * Gets the value of the creditCards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditCards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditCards().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditCard }
     * 
     * 
     */
    public List<CreditCard> getCreditCards() {
        if (creditCards == null) {
            creditCards = new ArrayList<CreditCard>();
        }
        return this.creditCards;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the emailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * Sets the value of the emailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddr(String value) {
        this.emailAddr = value;
    }

    /**
     * Gets the value of the feedbacks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the feedbacks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeedbacks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Feedback }
     * 
     * 
     */
    public List<Feedback> getFeedbacks() {
        if (feedbacks == null) {
            feedbacks = new ArrayList<Feedback>();
        }
        return this.feedbacks;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
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
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the memberPayments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the memberPayments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMemberPayments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MemberPayment }
     * 
     * 
     */
    public List<MemberPayment> getMemberPayments() {
        if (memberPayments == null) {
            memberPayments = new ArrayList<MemberPayment>();
        }
        return this.memberPayments;
    }

    /**
     * Gets the value of the membership property.
     * 
     * @return
     *     possible object is
     *     {@link Membership }
     *     
     */
    public Membership getMembership() {
        return membership;
    }

    /**
     * Sets the value of the membership property.
     * 
     * @param value
     *     allowed object is
     *     {@link Membership }
     *     
     */
    public void setMembership(Membership value) {
        this.membership = value;
    }

    /**
     * Gets the value of the monthlyCustomerReports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monthlyCustomerReports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonthlyCustomerReports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonthlyCustomerReport }
     * 
     * 
     */
    public List<MonthlyCustomerReport> getMonthlyCustomerReports() {
        if (monthlyCustomerReports == null) {
            monthlyCustomerReports = new ArrayList<MonthlyCustomerReport>();
        }
        return this.monthlyCustomerReports;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the phoneNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets the value of the phoneNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNum(String value) {
        this.phoneNum = value;
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
     * Gets the value of the shoppingOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shoppingOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShoppingOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShopOrder }
     * 
     * 
     */
    public List<ShopOrder> getShoppingOrder() {
        if (shoppingOrder == null) {
            shoppingOrder = new ArrayList<ShopOrder>();
        }
        return this.shoppingOrder;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the transactionRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionRecord }
     * 
     * 
     */
    public List<TransactionRecord> getTransactionRecords() {
        if (transactionRecords == null) {
            transactionRecords = new ArrayList<TransactionRecord>();
        }
        return this.transactionRecords;
    }

}
