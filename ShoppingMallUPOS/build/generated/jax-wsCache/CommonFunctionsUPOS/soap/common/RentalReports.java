
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for rentalReports complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rentalReports">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adhocCharges" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionFee" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionRate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="contract" type="{http://common.soap/}contract" minOccurs="0"/>
 *         &lt;element name="deposit" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="generationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="monthlyRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tenantBill" type="{http://common.soap/}tenantBill" minOccurs="0"/>
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
@XmlType(name = "rentalReports", propOrder = {
    "adhocCharges",
    "commissionFee",
    "commissionRate",
    "contract",
    "deposit",
    "generationDate",
    "id",
    "monthlyRental",
    "name",
    "tenantBill",
    "totalCharges",
    "totalSales"
})
public class RentalReports {

    protected float adhocCharges;
    protected float commissionFee;
    protected float commissionRate;
    protected Contract contract;
    protected float deposit;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generationDate;
    protected Long id;
    protected float monthlyRental;
    protected Long name;
    protected TenantBill tenantBill;
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
     * Gets the value of the commissionFee property.
     * 
     */
    public float getCommissionFee() {
        return commissionFee;
    }

    /**
     * Sets the value of the commissionFee property.
     * 
     */
    public void setCommissionFee(float value) {
        this.commissionFee = value;
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
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
    }

    /**
     * Gets the value of the deposit property.
     * 
     */
    public float getDeposit() {
        return deposit;
    }

    /**
     * Sets the value of the deposit property.
     * 
     */
    public void setDeposit(float value) {
        this.deposit = value;
    }

    /**
     * Gets the value of the generationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGenerationDate() {
        return generationDate;
    }

    /**
     * Sets the value of the generationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGenerationDate(XMLGregorianCalendar value) {
        this.generationDate = value;
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
     * Gets the value of the monthlyRental property.
     * 
     */
    public float getMonthlyRental() {
        return monthlyRental;
    }

    /**
     * Sets the value of the monthlyRental property.
     * 
     */
    public void setMonthlyRental(float value) {
        this.monthlyRental = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setName(Long value) {
        this.name = value;
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
