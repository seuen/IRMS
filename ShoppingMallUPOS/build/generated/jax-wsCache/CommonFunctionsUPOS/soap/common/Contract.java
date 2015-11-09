
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
 * <p>Java class for contract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baselineRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="commissionRate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="contractDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deposit" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="firstMonthRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="lastMonthRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="leaseDateFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="leaseDateTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="leaseterm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lessee" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lessor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthlyRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="renewed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentalReports" type="{http://common.soap/}rentalReports" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="shop" type="{http://common.soap/}shop" minOccurs="0"/>
 *         &lt;element name="totalRental" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="venue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contract", propOrder = {
    "activated",
    "baselineRental",
    "commissionRate",
    "contractDate",
    "dateFrom",
    "dateTo",
    "deposit",
    "firstMonthRental",
    "id",
    "lastMonthRental",
    "leaseDateFrom",
    "leaseDateTo",
    "leaseterm",
    "lessee",
    "lessor",
    "monthlyRental",
    "renewed",
    "rentalReports",
    "shop",
    "totalRental",
    "venue",
    "cDate"
})
public class Contract {

    protected String activated;
    protected float baselineRental;
    protected float commissionRate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contractDate;
    protected String dateFrom;
    protected String dateTo;
    protected float deposit;
    protected float firstMonthRental;
    protected Long id;
    protected float lastMonthRental;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar leaseDateFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar leaseDateTo;
    protected int leaseterm;
    protected String lessee;
    protected String lessor;
    protected float monthlyRental;
    protected String renewed;
    @XmlElement(nillable = true)
    protected List<RentalReports> rentalReports;
    protected Shop shop;
    protected float totalRental;
    protected String venue;
    protected String cDate;

    /**
     * Gets the value of the activated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivated() {
        return activated;
    }

    /**
     * Sets the value of the activated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivated(String value) {
        this.activated = value;
    }

    /**
     * Gets the value of the baselineRental property.
     * 
     */
    public float getBaselineRental() {
        return baselineRental;
    }

    /**
     * Sets the value of the baselineRental property.
     * 
     */
    public void setBaselineRental(float value) {
        this.baselineRental = value;
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
     * Gets the value of the contractDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractDate() {
        return contractDate;
    }

    /**
     * Sets the value of the contractDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractDate(XMLGregorianCalendar value) {
        this.contractDate = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFrom(String value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTo(String value) {
        this.dateTo = value;
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
     * Gets the value of the firstMonthRental property.
     * 
     */
    public float getFirstMonthRental() {
        return firstMonthRental;
    }

    /**
     * Sets the value of the firstMonthRental property.
     * 
     */
    public void setFirstMonthRental(float value) {
        this.firstMonthRental = value;
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
     * Gets the value of the lastMonthRental property.
     * 
     */
    public float getLastMonthRental() {
        return lastMonthRental;
    }

    /**
     * Sets the value of the lastMonthRental property.
     * 
     */
    public void setLastMonthRental(float value) {
        this.lastMonthRental = value;
    }

    /**
     * Gets the value of the leaseDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLeaseDateFrom() {
        return leaseDateFrom;
    }

    /**
     * Sets the value of the leaseDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLeaseDateFrom(XMLGregorianCalendar value) {
        this.leaseDateFrom = value;
    }

    /**
     * Gets the value of the leaseDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLeaseDateTo() {
        return leaseDateTo;
    }

    /**
     * Sets the value of the leaseDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLeaseDateTo(XMLGregorianCalendar value) {
        this.leaseDateTo = value;
    }

    /**
     * Gets the value of the leaseterm property.
     * 
     */
    public int getLeaseterm() {
        return leaseterm;
    }

    /**
     * Sets the value of the leaseterm property.
     * 
     */
    public void setLeaseterm(int value) {
        this.leaseterm = value;
    }

    /**
     * Gets the value of the lessee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLessee() {
        return lessee;
    }

    /**
     * Sets the value of the lessee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLessee(String value) {
        this.lessee = value;
    }

    /**
     * Gets the value of the lessor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLessor() {
        return lessor;
    }

    /**
     * Sets the value of the lessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLessor(String value) {
        this.lessor = value;
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
     * Gets the value of the renewed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewed() {
        return renewed;
    }

    /**
     * Sets the value of the renewed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewed(String value) {
        this.renewed = value;
    }

    /**
     * Gets the value of the rentalReports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rentalReports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRentalReports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RentalReports }
     * 
     * 
     */
    public List<RentalReports> getRentalReports() {
        if (rentalReports == null) {
            rentalReports = new ArrayList<RentalReports>();
        }
        return this.rentalReports;
    }

    /**
     * Gets the value of the shop property.
     * 
     * @return
     *     possible object is
     *     {@link Shop }
     *     
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Sets the value of the shop property.
     * 
     * @param value
     *     allowed object is
     *     {@link Shop }
     *     
     */
    public void setShop(Shop value) {
        this.shop = value;
    }

    /**
     * Gets the value of the totalRental property.
     * 
     */
    public float getTotalRental() {
        return totalRental;
    }

    /**
     * Sets the value of the totalRental property.
     * 
     */
    public void setTotalRental(float value) {
        this.totalRental = value;
    }

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVenue(String value) {
        this.venue = value;
    }

    /**
     * Gets the value of the cDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDate() {
        return cDate;
    }

    /**
     * Sets the value of the cDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDate(String value) {
        this.cDate = value;
    }

}
