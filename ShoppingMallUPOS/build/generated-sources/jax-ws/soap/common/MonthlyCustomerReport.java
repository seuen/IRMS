
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for monthlyCustomerReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="monthlyCustomerReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accommodationCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="foodCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="generateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="member" type="{http://common.soap/}memberAccount" minOccurs="0"/>
 *         &lt;element name="monthlyExpense" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="shoppingCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ticketCost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "monthlyCustomerReport", propOrder = {
    "accommodationCost",
    "foodCost",
    "generateDate",
    "id",
    "member",
    "monthlyExpense",
    "name",
    "otherCost",
    "shoppingCost",
    "ticketCost"
})
public class MonthlyCustomerReport {

    protected double accommodationCost;
    protected double foodCost;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generateDate;
    protected Long id;
    protected MemberAccount member;
    protected double monthlyExpense;
    protected String name;
    protected double otherCost;
    protected double shoppingCost;
    protected double ticketCost;

    /**
     * Gets the value of the accommodationCost property.
     * 
     */
    public double getAccommodationCost() {
        return accommodationCost;
    }

    /**
     * Sets the value of the accommodationCost property.
     * 
     */
    public void setAccommodationCost(double value) {
        this.accommodationCost = value;
    }

    /**
     * Gets the value of the foodCost property.
     * 
     */
    public double getFoodCost() {
        return foodCost;
    }

    /**
     * Sets the value of the foodCost property.
     * 
     */
    public void setFoodCost(double value) {
        this.foodCost = value;
    }

    /**
     * Gets the value of the generateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGenerateDate() {
        return generateDate;
    }

    /**
     * Sets the value of the generateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGenerateDate(XMLGregorianCalendar value) {
        this.generateDate = value;
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
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link MemberAccount }
     *     
     */
    public MemberAccount getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberAccount }
     *     
     */
    public void setMember(MemberAccount value) {
        this.member = value;
    }

    /**
     * Gets the value of the monthlyExpense property.
     * 
     */
    public double getMonthlyExpense() {
        return monthlyExpense;
    }

    /**
     * Sets the value of the monthlyExpense property.
     * 
     */
    public void setMonthlyExpense(double value) {
        this.monthlyExpense = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the otherCost property.
     * 
     */
    public double getOtherCost() {
        return otherCost;
    }

    /**
     * Sets the value of the otherCost property.
     * 
     */
    public void setOtherCost(double value) {
        this.otherCost = value;
    }

    /**
     * Gets the value of the shoppingCost property.
     * 
     */
    public double getShoppingCost() {
        return shoppingCost;
    }

    /**
     * Sets the value of the shoppingCost property.
     * 
     */
    public void setShoppingCost(double value) {
        this.shoppingCost = value;
    }

    /**
     * Gets the value of the ticketCost property.
     * 
     */
    public double getTicketCost() {
        return ticketCost;
    }

    /**
     * Sets the value of the ticketCost property.
     * 
     */
    public void setTicketCost(double value) {
        this.ticketCost = value;
    }

}
