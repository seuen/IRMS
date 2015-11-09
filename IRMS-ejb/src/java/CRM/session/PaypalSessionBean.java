/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import com.paypal.exception.SSLConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import urn.ebay.api.PayPalAPI.DoDirectPaymentReq;
import urn.ebay.api.PayPalAPI.DoDirectPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoDirectPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CreditCardDetailsType;
import urn.ebay.apis.eBLBaseComponents.CreditCardTypeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoDirectPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PayerInfoType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless
public class PaypalSessionBean implements PaypalSessionBeanLocal {

    public PaypalSessionBean() {
    }
//
//    @Override
//    public String doDirectPayment(String paymentAction, String amount, String cardType, String cardNum, String expireDate,
//            String cvv2, String firstName, String lastName, String street, String city, String state, String zip, String countryCode) {
//        System.err.println("tet!!!!");
//        String response = "";
//
//        return response;
//    }

    @Override
    public String doDirectPayment(double amount, String cardType, String cardNum, String cvv, String MM, String YYYY, String email) throws SSLConfigurationException {

        System.err.println("Session Bean test");
        System.err.println(cardNum);
        String c1 = (String) cardNum.substring(0, 4);
        String c2 = (String) cardNum.substring(5, 9);
        String c3 = (String) cardNum.substring(10, 14);
        String c4 = (String) cardNum.substring(15, 19);
        System.err.println(c1);
        System.err.println(c2);
        System.err.println(c3);
        System.err.println(c4);
        String num = c1 + c2 + c3 + c4;

        System.err.println(num);
        Logger logger = Logger.getLogger(this.getClass().toString());

        DoDirectPaymentReq doDirectPaymentReq = new DoDirectPaymentReq();
        DoDirectPaymentRequestDetailsType doDirectPaymentRequestDetails = new DoDirectPaymentRequestDetailsType();
        CreditCardDetailsType creditCard = new CreditCardDetailsType();

        if (cardType.equals("Visa")) {
            creditCard.setCreditCardType(CreditCardTypeType.VISA);
        }
        if (cardType.equals("MasterCard")) {
            creditCard.setCreditCardType(CreditCardTypeType.MASTERCARD);
        }
        creditCard.setCreditCardNumber(num);
        creditCard.setExpMonth(Integer.valueOf(MM));
        creditCard.setExpYear(Integer.valueOf(YYYY));
        creditCard.setCVV2(cvv);

        PayerInfoType cardOwner = new PayerInfoType();
        cardOwner.setPayer(email);
        creditCard.setCardOwner(cardOwner);

        doDirectPaymentRequestDetails.setCreditCard(creditCard);
        doDirectPaymentRequestDetails.setPaymentAction(PaymentActionCodeType.SALE);

        PaymentDetailsType paymentDetails = new PaymentDetailsType();
        BasicAmountType orderTotal = new BasicAmountType(CurrencyCodeType.USD, String.valueOf(amount));
        paymentDetails.setOrderTotal(orderTotal);

        paymentDetails.setNotifyURL("paymentDone.xhtml");
        doDirectPaymentRequestDetails.setPaymentDetails(paymentDetails);

        doDirectPaymentRequestDetails.setIPAddress("137.132.250.14");

        DoDirectPaymentRequestType doDirectPaymentRequest =
                new DoDirectPaymentRequestType(doDirectPaymentRequestDetails);
        doDirectPaymentReq.setDoDirectPaymentRequest(doDirectPaymentRequest);

        PayPalAPIInterfaceServiceService service = null;
        try {
            service = new PayPalAPIInterfaceServiceService("//Users//zsy//Desktop//IRMS//IRMSExternal//web//WEB-INF//sdk_config.properties");
        } catch (IOException e) {
            logger.severe("Error Message : " + e.getMessage());
        }

        DoDirectPaymentResponseType doDirectPaymentResponse = null;

        try {
            doDirectPaymentResponse = service.doDirectPayment(doDirectPaymentReq);
        } catch (Exception e) {
            logger.severe("Error Message : " + e.getMessage());
            return "Credit Card Info is incorrect, please try again!";
        }

        if (doDirectPaymentResponse.getAck().getValue().equalsIgnoreCase("success")) {
            logger.info("Transaction ID : "
                    + doDirectPaymentResponse.getTransactionID());
            return "Transaction is successful! Your paypal transaction ID is " + doDirectPaymentResponse.getTransactionID();
        } else {
            List<urn.ebay.apis.eBLBaseComponents.ErrorType> errorList = doDirectPaymentResponse.getErrors();
            logger.severe("API Error Message : "
                    + errorList.get(0).getLongMessage());
            return "Payment is failed, please try again！";
        }

    }
}
