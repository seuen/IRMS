
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tenantReceipt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tenantReceipt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adhocPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="depositPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="payer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiptDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="receiptDateString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentalPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tenantBill" type="{http://common.soap/}tenantBill" minOccurs="0"/>
 *         &lt;element name="totalPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tenantReceipt", propOrder = {
    "adhocPayment",
    "commissionPayment",
    "depositPayment",
    "id",
    "payer",
    "receiptDate",
    "receiptDateString",
    "receiver",
    "rentalPayment",
    "tenantBill",
    "totalPayment"
})
public class TenantReceipt {

    protected float adhocPayment;
    protected float commissionPayment;
    protected float depositPayment;
    protected Long id;
    protected String payer;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar receiptDate;
    protected String receiptDateString;
    protected String receiver;
    protected float rentalPayment;
    protected TenantBill tenantBill;
    protected float totalPayment;

    /**
     * Gets the value of the adhocPayment property.
     * 
     */
    public float getAdhocPayment() {
        return adhocPayment;
    }

    /**
     * Sets the value of the adhocPayment property.
     * 
     */
    public void setAdhocPayment(float value) {
        this.adhocPayment = value;
    }

    /**
     * Gets the value of the commissionPayment property.
     * 
     */
    public float getCommissionPayment() {
        return commissionPayment;
    }

    /**
     * Sets the value of the commissionPayment property.
     * 
     */
    public void setCommissionPayment(float value) {
        this.commissionPayment = value;
    }

    /**
     * Gets the value of the depositPayment property.
     * 
     */
    public float getDepositPayment() {
        return depositPayment;
    }

    /**
     * Sets the value of the depositPayment property.
     * 
     */
    public void setDepositPayment(float value) {
        this.depositPayment = value;
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
     * Gets the value of the payer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayer() {
        return payer;
    }

    /**
     * Sets the value of the payer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayer(String value) {
        this.payer = value;
    }

    /**
     * Gets the value of the receiptDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReceiptDate() {
        return receiptDate;
    }

    /**
     * Sets the value of the receiptDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReceiptDate(XMLGregorianCalendar value) {
        this.receiptDate = value;
    }

    /**
     * Gets the value of the receiptDateString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptDateString() {
        return receiptDateString;
    }

    /**
     * Sets the value of the receiptDateString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptDateString(String value) {
        this.receiptDateString = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the rentalPayment property.
     * 
     */
    public float getRentalPayment() {
        return rentalPayment;
    }

    /**
     * Sets the value of the rentalPayment property.
     * 
     */
    public void setRentalPayment(float value) {
        this.rentalPayment = value;
    }

    /**
     * Gets the value of the tenantBill property.
     * 
     * @return
     *     possible object is
     *     {@link TenantBill }
     *     
     */
    public TenantBill getTenantBill() {
        return tenantBill;
    }

    /**
     * Sets the value of the tenantBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link TenantBill }
     *     
     */
    public void setTenantBill(TenantBill value) {
        this.tenantBill = value;
    }

    /**
     * Gets the value of the totalPayment property.
     * 
     */
    public float getTotalPayment() {
        return totalPayment;
    }

    /**
     * Sets the value of the totalPayment property.
     * 
     */
    public void setTotalPayment(float value) {
        this.totalPayment = value;
    }

}
