/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import com.paypal.exception.SSLConfigurationException;
import javax.ejb.Local;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface PaypalSessionBeanLocal {

//    public String doDirectPayment(String paymentAction, String amount, String cardType, String cardNum, String expireDate, String cvv2, String firstName, String lastName, String street, String city, String state, String zip, String countryCode);

    public String doDirectPayment(double amount, String cardType, String cardNum, String cvv,String MM, String YYYY, String email) throws SSLConfigurationException;

    
}
