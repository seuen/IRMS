
package soap.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.common package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NoSuchAlgorithmException_QNAME = new QName("http://common.soap/", "NoSuchAlgorithmException");
    private final static QName _LoginUPOSResponse_QNAME = new QName("http://common.soap/", "loginUPOSResponse");
    private final static QName _LoginUPOS_QNAME = new QName("http://common.soap/", "loginUPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginUPOS }
     * 
     */
    public LoginUPOS createLoginUPOS() {
        return new LoginUPOS();
    }

    /**
     * Create an instance of {@link NoSuchAlgorithmException }
     * 
     */
    public NoSuchAlgorithmException createNoSuchAlgorithmException() {
        return new NoSuchAlgorithmException();
    }

    /**
     * Create an instance of {@link LoginUPOSResponse }
     * 
     */
    public LoginUPOSResponse createLoginUPOSResponse() {
        return new LoginUPOSResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoSuchAlgorithmException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "NoSuchAlgorithmException")
    public JAXBElement<NoSuchAlgorithmException> createNoSuchAlgorithmException(NoSuchAlgorithmException value) {
        return new JAXBElement<NoSuchAlgorithmException>(_NoSuchAlgorithmException_QNAME, NoSuchAlgorithmException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUPOSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "loginUPOSResponse")
    public JAXBElement<LoginUPOSResponse> createLoginUPOSResponse(LoginUPOSResponse value) {
        return new JAXBElement<LoginUPOSResponse>(_LoginUPOSResponse_QNAME, LoginUPOSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "loginUPOS")
    public JAXBElement<LoginUPOS> createLoginUPOS(LoginUPOS value) {
        return new JAXBElement<LoginUPOS>(_LoginUPOS_QNAME, LoginUPOS.class, null, value);
    }

}
