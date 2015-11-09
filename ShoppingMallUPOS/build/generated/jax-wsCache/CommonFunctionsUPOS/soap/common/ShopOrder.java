
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
 * <p>Java class for shopOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shopOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detailShoppintOrder" type="{http://common.soap/}detailShopOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="discountAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="grossTotalAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="loyaltyPoint" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="memberAccount" type="{http://common.soap/}memberAccount" minOccurs="0"/>
 *         &lt;element name="netTotalAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="purchaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shopOrder", propOrder = {
    "detailShoppintOrder",
    "discountAmount",
    "grossTotalAmount",
    "id",
    "loyaltyPoint",
    "memberAccount",
    "netTotalAmount",
    "purchaseDate",
    "status",
    "taxAmount"
})
public class ShopOrder {

    @XmlElement(nillable = true)
    protected List<DetailShopOrder> detailShoppintOrder;
    protected float discountAmount;
    protected float grossTotalAmount;
    protected Long id;
    protected int loyaltyPoint;
    protected MemberAccount memberAccount;
    protected float netTotalAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar purchaseDate;
    protected String status;
    protected float taxAmount;

    /**
     * Gets the value of the detailShoppintOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detailShoppintOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetailShoppintOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetailShopOrder }
     * 
     * 
     */
    public List<DetailShopOrder> getDetailShoppintOrder() {
        if (detailShoppintOrder == null) {
            detailShoppintOrder = new ArrayList<DetailShopOrder>();
        }
        return this.detailShoppintOrder;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     */
    public float getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     */
    public void setDiscountAmount(float value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the grossTotalAmount property.
     * 
     */
    public float getGrossTotalAmount() {
        return grossTotalAmount;
    }

    /**
     * Sets the value of the grossTotalAmount property.
     * 
     */
    public void setGrossTotalAmount(float value) {
        this.grossTotalAmount = value;
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
     * Gets the value of the loyaltyPoint property.
     * 
     */
    public int getLoyaltyPoint() {
        return loyaltyPoint;
    }

    /**
     * Sets the value of the loyaltyPoint property.
     * 
     */
    public void setLoyaltyPoint(int value) {
        this.loyaltyPoint = value;
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
     * Gets the value of the netTotalAmount property.
     * 
     */
    public float getNetTotalAmount() {
        return netTotalAmount;
    }

    /**
     * Sets the value of the netTotalAmount property.
     * 
     */
    public void setNetTotalAmount(float value) {
        this.netTotalAmount = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchaseDate(XMLGregorianCalendar value) {
        this.purchaseDate = value;
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
     * Gets the value of the taxAmount property.
     * 
     */
    public float getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     */
    public void setTaxAmount(float value) {
        this.taxAmount = value;
    }

}
