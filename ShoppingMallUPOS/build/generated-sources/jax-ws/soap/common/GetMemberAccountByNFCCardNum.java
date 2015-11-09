
package soap.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getMemberAccountByNFCCardNum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getMemberAccountByNFCCardNum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nfcCardNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMemberAccountByNFCCardNum", propOrder = {
    "nfcCardNum"
})
public class GetMemberAccountByNFCCardNum {

    protected String nfcCardNum;

    /**
     * Gets the value of the nfcCardNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNfcCardNum() {
        return nfcCardNum;
    }

    /**
     * Sets the value of the nfcCardNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNfcCardNum(String value) {
        this.nfcCardNum = value;
    }

}
