
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tenantBill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tenantBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adhocCharges" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="billDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="billDateString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commissionCharges" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionRate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="deadLine" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="depost" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="payer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentalCharges" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="rentalReports" type="{http://common.soap/}rentalReports" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tenantReceipt" type="{http://common.soap/}tenantReceipt" minOccurs="0"/>
 *         &lt;element name="totalCharges" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalSales" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tenantBill", propOrder = {
    "adhocCharges",
    "billDate",
    "billDateString",
    "commissionCharges",
    "commissionRate",
    "deadLine",
    "depost",
    "id",
    "payer",
    "receiver",
    "rentalCharges",
    "rentalReports",
    "status",
    "tenantReceipt",
    "totalCharges",
    "totalSales"
})
public class TenantBill {

    protected float adhocCharges;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billDate;
    protected String billDateString;
    protected float commissionCharges;
    protected float commissionRate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deadLine;
    protected float depost;
    protected Long id;
    protected String payer;
    protected String receiver;
    protected float rentalCharges;
    protected RentalReports rentalReports;
    protected String status;
    protected TenantReceipt tenantReceipt;
    protected float totalCharges;
    protected float totalSales;

    /**
     * Gets the value of the adhocCharges property.
     * 
     */
    public float getAdhocCharges() {
        return adhocCharges;
    }

    /**
     * Sets the value of the adhocCharges property.
     * 
     */
    public void setAdhocCharges(float value) {
        this.adhocCharges = value;
    }

    /**
     * Gets the value of the billDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillDate() {
        return billDate;
    }

    /**
     * Sets the value of the billDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillDate(XMLGregorianCalendar value) {
        this.billDate = value;
    }

    /**
     * Gets the value of the billDateString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillDateString() {
        return billDateString;
    }

    /**
     * Sets the value of the billDateString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillDateString(String value) {
        this.billDateString = value;
    }

    /**
     * Gets the value of the commissionCharges property.
     * 
     */
    public float getCommissionCharges() {
        return commissionCharges;
    }

    /**
     * Sets the value of the commissionCharges property.
     * 
     */
    public void setCommissionCharges(float value) {
        this.commissionCharges = value;
    }

    /**
     * Gets the value of the commissionRate property.
     * 
     */
    public float getCommissionRate() {
        return commissionRate;
    }

    /**
     * Sets the value of the commissionRate property.
     * 
     */
    public void setCommissionRate(float value) {
        this.commissionRate = value;
    }

    /**
     * Gets the value of the deadLine property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeadLine() {
        return deadLine;
    }

    /**
     * Sets the value of the deadLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeadLine(XMLGregorianCalendar value) {
        this.deadLine = value;
    }

    /**
     * Gets the value of the depost property.
     * 
     */
    public float getDepost() {
        return depost;
    }

    /**
     * Sets the value of the depost property.
     * 
     */
    public void setDepost(float value) {
        this.depost = value;
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
     * Gets the value of the rentalCharges property.
     * 
     */
    public float getRentalCharges() {
        return rentalCharges;
    }

    /**
     * Sets the value of the rentalCharges property.
     * 
     */
    public void setRentalCharges(float value) {
        this.rentalCharges = value;
    }

    /**
     * Gets the value of the rentalReports property.
     * 
     * @return
     *     possible object is
     *     {@link RentalReports }
     *     
     */
    public RentalReports getRentalReports() {
        return rentalReports;
    }

    /**
     * Sets the value of the rentalReports property.
     * 
     * @param value
     *     allowed object is
     *     {@link RentalReports }
     *     
     */
    public void setRentalReports(RentalReports value) {
        this.rentalReports = value;
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
     * Gets the value of the tenantReceipt property.
     * 
     * @return
     *     possible object is
     *     {@link TenantReceipt }
     *     
     */
    public TenantReceipt getTenantReceipt() {
        return tenantReceipt;
    }

    /**
     * Sets the value of the tenantReceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TenantReceipt }
     *     
     */
    public void setTenantReceipt(TenantReceipt value) {
        this.tenantReceipt = value;
    }

    /**
     * Gets the value of the totalCharges property.
     * 
     */
    public float getTotalCharges() {
        return totalCharges;
    }

    /**
     * Sets the value of the totalCharges property.
     * 
     */
    public void setTotalCharges(float value) {
        this.totalCharges = value;
    }

    /**
     * Gets the value of the totalSales property.
     * 
     */
    public float getTotalSales() {
        return totalSales;
    }

    /**
     * Sets the value of the totalSales property.
     * 
     */
    public void setTotalSales(float value) {
        this.totalSales = value;
    }

}
