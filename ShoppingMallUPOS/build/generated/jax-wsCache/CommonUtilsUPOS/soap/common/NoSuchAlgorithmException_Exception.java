
package soap.common;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "NoSuchAlgorithmException", targetNamespace = "http://common.soap/")
public class NoSuchAlgorithmException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private NoSuchAlgorithmException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public NoSuchAlgorithmException_Exception(String message, NoSuchAlgorithmException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public NoSuchAlgorithmException_Exception(String message, NoSuchAlgorithmException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: soap.common.NoSuchAlgorithmException
     */
    public NoSuchAlgorithmException getFaultInfo() {
        return faultInfo;
    }

}