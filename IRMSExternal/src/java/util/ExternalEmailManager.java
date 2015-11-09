/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import CRM.entity.MemberAccount;
import Common.entity.Staff;
import Common.entity.Title;
import ConventionExhibition.entity.ClientBill;
import ConventionExhibition.entity.ClientReceipt;
import ConventionExhibition.entity.EventOrder;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.TenantBill;
import ShoppingMall.entity.TenantReceipt;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author yifeng
 */
public class ExternalEmailManager {

    public ExternalEmailManager() {
    }

    public void sendEmail(String desEAdress, String subject, String text) {
        System.out.println("enter sendEmail");
        Properties props = new Properties();
        props.put("mail.transport.protocal", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");


        Session session = Session.getInstance(props, new SMTPAuthenticator());
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(InternetAddress.parse("it05aaa@gmail.com", false)[0]);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(desEAdress, false));
            message.setSubject(subject);
            message.setText(text);
            message.setHeader("X-Mailer", "Hou Yifeng");
            message.setSentDate(new Date());


            Transport t = session.getTransport("smtps");
            t.connect("smtp.gmail.com", "it05aaa", "WeAreOne");
            t.sendMessage(message, message.getAllRecipients());
            t.close();

            System.out.println("Done sending email");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTargetAdvertising(String title, String content, List<MemberAccount> members) {
        for (MemberAccount m : members) {
            String subject = title;
            String text = "";
            text = text + "Dear " + m.getTitle() + " " + m.getLastName() + ", \n\n";
            text = text + "Here is our recommended advertisement for you! \n\n\n";
            text = text + content;
            text = text + "\n\nBest Regards\n";
            text = text + "CIR Marketing Department";

            this.sendEmail(m.getEmailAddr(), subject, text);
        }
    }

    

    public void sendMemberPassword(MemberAccount member, String psw) {
        String subject = "Thank you for joining Coral Island Resort";
        String text = "";
        text = text + "Dear " + member.getTitle() + " " + member.getLastName() + ",";
        text = text + "\n\n\t Thank you taking the time to register as a Coral Island Resort member.";
        text = text + "\n\n\t Your new member account detail is :";
        text = text + "\n\n\t Your Login ID : " + member.getId();
        text = text + "\n\t Your Login Passord : " + psw;
        text = text + "\n\t *Please use your Unique Login ID for further enquires about your application *";
        text = text + "\n\n\t Please feel free to contact the Customer Relationship Management Team should you have any queries.";
        text = text + "\n\n\nBest Regards,\n";
        text = text + "CIR Customer Relationship Management Department";

        this.sendEmail(member.getEmailAddr(), subject, text);

    }


//    public void sendStaffResetPassword(String desEAdress, Staff staff, String staffAccountId, String password) {
//        String subject = "New Staff Account Information";
//        String text = "";
//
//        text = text + "Dear " + staff.getFirstName() + " " + staff.getLastName();
//        text = text + "\n\t You are working at " + title.getWorkLocation();
//        text = text + "\n\t as " + title.getPosition();
//        text = text + "\n\t Your Staff Account Id is " + staffAccountId;
//        text = text + "\n\t Your Password is " + password;
//
//        this.sendEmail(desEAdress, subject, text);
//    }

    public void sendStaffResetPassword(String desEAdress, Staff staff, String staffAccountId, String password) {
        String subject = "New Staff Account Information";
        String text = "";

        text = text + "Dear " + staff.getFirstName() + " " + staff.getLastName();
        text = text + "\n\t Your Staff Account Id is " + staffAccountId;
        text = text + "\n\t Your new Password is " + password;

        this.sendEmail(desEAdress, subject, text);
    }

    public void sendContract(Contract contract) {
        String subject = "Contract information for " + contract.getShop().getShopName();
        String text = "";
        text = text + "Dear Manager " + contract.getShop().getShopOwnerName() + ",\n";
        text = text + "Your new contract has been created, details are as follows: \n\n";
        text = text + "Contract ID; " + contract.getId() + "\n\n";
        text = text + "\t\t LEASE AGREEMENT" + "\n\n";
        text = text + "\t\t CIR Shopping Center Space\n\n\n";
        text = text + "This Lease Agreement, is entered into this " + contract.getcDate() + " by and between " + contract.getLessor() + " refferred to herin as lessor, and " + contract.getLessee() + ", referred to herin as Lessee.\n\n";
        text = text + "WITNESSETH:\n\n\n";
        text = text + "1. Description of Leased Premises.Lessor hereby leases to the lessee and the Lessee takes as lessee the premises described as follows:\n";
        text = text + "Unit number(s) " + contract.getVenue() + ". (Total area: " + contract.getShop().getArea() + ") of the CIR Shopping Center, Singapore Coral Island Central, containing air-conditioned space of air-conditioned space,more or less.\n\n";
        text = text + "2. Term.\n";
        text = text + "The term of this lease shall be " + contract.getLeaseterm() + " years(s), from " + contract.getDateFrom() + " to " + contract.getDateTo() + ", commencing upon Lessee's written acceptance of the premises. In the event construction the premises has not been completed and ready for occupancy within one year of execution of this lease agreement, Lessee shall have the option of terminating this lease by mailing or delivering written notice to the Lessor.\n\n";
        text = text + "3. Rental.\n";
        text = text + "The rental for the entire term shall be " + contract.getTotalRental() + ". payable at the rate of " + "contract.getMonthlyRental()+ per month in advance on the 1st day of each month durin gthe term of this lease. Receipt of the sum of the lease for the first month is acknowledged by the Lessor. Until further notice all rent payments shall be mailed or delieved o Lessor's address at: Coral Island Central Park, Singapore 119224.The rent above includes the following facilities and services: Electricity, Heat, Water, Hot water, Janitorial, Parking. Any additional charges or services will be counter towards ad-hoc charges (refer to section 4). ";
        text = text + "\n\n4. Adhoc Charges.\n";
        text = text + "Additional services, charges or compensation for damages will be charges as ad-hoc spendings. Payment should be done on the 1st day of subsequent month with the advance rental payment.";
        text = text + "\n\n5. Security Deposit.\n";
        text = text + "In addition to the rental shown herin, Lessee has deposited with Lessor the sum of " + contract.getDeposit() + " on which deposit Lessor is not required to pay interest. Said deposit shall be held as a guaranty that Lessee will perform all convenants of this lease, including prompt payment of rental when due.";
        text = text + "\n\n6. Monthly Commision Fee.\n";
        text = text + "Each month's sales revenue will be subjected to a monthly conmission fee,which equals to monthly sales revenue times the monthly commission rate: " + contract.getCommissionRate() * 100 + ".\n\n";
        text = text + "7. Termination policy.\n";
        text = text + "Tenants who wish to terminate their tenancy must give proper one-month notice. Tenants are liable for the rent to the end of their tenancy legal notice period, and other costs that may have incurred, according to the Residential Tenancy Act. However, Lessor has an obligation to mitigate its losses. Lessor reserves the right to terminate tenancy fot those who behaves against Tenancy Laws.\n\n\n";
        text = text + "Summary:\n";
        text = text + "Shop Name:" + contract.getShop().getShopName() + "\n" + "Venue:" + contract.getVenue() + "\n";
        text = text + "Lease Date From:" + contract.getDateFrom() + "\n" + "Lease Term: " + contract.getLeaseterm() + "\nMonthly Rental:" + contract.getMonthlyRental() + "\nMonthly Commision Rate: " + contract.getCommissionRate() + "\n" + "Deposit: " + contract.getDeposit() + "\n\n\n";
        text = text + "Best regards,\n";
        text = text + "CIR Management Team.\n\n";

        this.sendEmail(contract.getShop().getEmail(), subject, text);
    }

    public void sendReceipt(TenantReceipt receipt) {
        String subject = "[Coral Island Resort Shopping Center] Receipt for " + receipt.getPayer() + "Monthly Payments (" + receipt.getReceiptDateString() + ")";
        String text = "Dear Manager " + receipt.getTenantBill().getRentalReports().getContract().getShop().getShopOwnerName() + ",\n\n";

        text += "This is to confirm the payment for tenant monthly bill (No. " + receipt.getTenantBill().getRentalReports().getName() + ").\n\n";
        text += "The receipt No. is " + receipt.getId() + ".\n";
        text += "***************************************************************\n";
        text += "* Payment time:                      " + receipt.getReceiptDateString() + "\n";
        text += "* Rental Payment:                    " + receipt.getRentalPayment() + "SGD\n";
        text += "* Deposit Payment:                   " + receipt.getDepositPayment() + "SGD\n";
        text += "* Commission Payment:                " + receipt.getCommissionPayment() + "SGD\n";
        text += "* Adhoc Charge Payment:              " + receipt.getAdhocPayment() + "SGD\n";
        text += "--------------------------------------------------------------------------------\n";
        text += "* Total Payment:                     " + receipt.getTotalPayment() + "SGD\n";
        text += "***************************************************************\n\n\n";
        text += "Thank you very much.\n\n";
        text += "Best regards,\n";
        text += "Coral Island Resort Shopping Center\n";


        this.sendEmail(receipt.getTenantBill().getRentalReports().getContract().getShop().getEmail(), subject, text);
    }

    public void sendBill(TenantBill bill) {
        if (bill == null) {
            System.out.println("inside send bill null");
        } else {
            String subject = "[Coral Island Resort Shopping Center] Monthly Bill for " + bill.getPayer() + "(" + bill.getBillDateString() + ")";
            String text = "Dear Manager " + bill.getRentalReports().getContract().getShop().getShopOwnerName() + ",\n\n";

            text += "This is a reminder for the payment of monthly rental, commission, adhoc charges and deposit. Please kindly make your payments either online or by approaching our tenancy collection center. The deadline for payment is " + bill.getDeadLine() + "\n\n";

            text += "Bill Number: " + bill.getRentalReports().getName() + ".\n\n";

            text += "***************************************************************\n";

            text += "* Rental Charges:                    " + bill.getRentalCharges() + "SGD\n";
            text += "* Deposit Charges:                   " + bill.getDepost() + "SGD\n";
            text += "* Commission Charges:                " + bill.getCommissionCharges() + "SGD\n";
            text += "* Adhoc Charges:                     " + bill.getAdhocCharges() + "SGD\n";
            text += "--------------------------------------------------------------------------------\n";
            text += "* Total Payment:                     " + bill.getTotalCharges() + "SGD\n";
            text += "***************************************************************\n\n\n";
            text += "Thank you very much for your cooporation!\n\n";
            text += "Best regards,\n";
            text += "Coral Island Resort Shopping Center\n";
            this.sendEmail(bill.getRentalReports().getContract().getShop().getEmail(), subject, text);
        }
    }

    public void sendClientBill(ClientBill bill) {
        if (bill == null) {
            System.out.println("inside send client bill null");
        } else {
            String subject = "[Coral Island Resort] Client Bill for " + bill.getPayer() + "(" + bill.getBillDateString() + ")";
            String text = "Dear " + bill.getClient().getTitle() + " " + bill.getClient().getLastname() + ",\n\n";

            text += "This is a reminder for the payment of your event bill in Coral Island Resort. Please kindly make your payments either online or by approaching our tenant collection center. The deadline for payment is \n\n";

            text += "Bill Number: " + bill.getId() + ".\n\n";

            text += "***************************************************************\n";
            List<EventOrder> oes = bill.getEvents();
            for (EventOrder oe : oes) {
                text += "EventID:" + oe.getId() + " " + oe.getType() + "-" + oe.getCreateDate() + " \n[***Price: " + oe.getTotalprice() + ";  OutstandingCharge: " + oe.getTotalcharge() + "***]";
                text += "\n-----------------------------------------------------------------\n";
            }
            text += "***************************************************************\n\n\n";


            text += "Total Price: " + bill.getTotalprice() + ".\n\n";

            text += "Outstanding Charges Needs to be Paid: " + bill.getTotalcharges() + ".\n\n";

            text += "Thank you very much for your cooporation!\n\n";
            text += "Best regards,\n";
            text += "Coral Island Resort\n";
            this.sendEmail(bill.getClient().getEmailAddr(), subject, text);
        }
    }

    public void sendClientReceipt(ClientReceipt cr) {
        if (cr == null) {
            System.out.println("inside send client receipt null");
        } else {
            String subject = "[Coral Island Resort] Client Receipt for " + cr.getPayer() + "(" + cr.getReceiptDateString() + ")";
            String text = "Dear " + cr.getClientBill().getClient().getTitle() + " " + cr.getClientBill().getClient().getLastname() + ",\n\n";

            text += "This is a receipt for the payment of your event bill in Coral Island Resort. \n\n";

            text += "Receipt Number: " + cr.getId() + ".\n\n";

            text += "***************************************************************\n";
            List<EventOrder> oes = cr.getClientBill().getEvents();
            for (EventOrder oe : oes) {
                text += "EventID:" + oe.getId() + " " + oe.getType() + "-" + oe.getCreateDate() + " [***Price: " + oe.getTotalprice() + "***]";
                text += "\n-----------------------------------------------------------------\n";
            }
            text += "***************************************************************\n\n\n";


            text += "Total Payment: " + cr.getTotalPayment() + ".\n\n";


            text += "Thank you very much for your partnering with CIRMS!\n\n";
            text += "Best regards,\n";
            text += "Coral Island Resort\n";
            this.sendEmail(cr.getClientBill().getClient().getEmailAddr(), subject, text);
        }
    }
    
    public void sendBirthdayEmail(List<MemberAccount> members) {
        for (MemberAccount m : members) {
            String subject = "Coral Island Resort wishes you happy birthday";
            String text = "";
            text = text + "Dear " + m.getTitle() + " " + m.getLastName() + ", \n\n";
            text = text + "CELEBRATEING YOUR BIRTHDAY WITH CIR\n\n";
            text = text + "We would like to wish you Happy Birthday and take this opportunity to thank you for endorsing Coral Island Resort.\n\n";
            text = text + "As a token of our appreciation, we would like to celebrate your birthday with a special $25 birthday reward which can be used to "
                    + "spend anywhere inside Coral Island Resort.\n\n";
            text = text + "The $25 reward will be automatically added into your member card value and you may check your account information on our website. "
                    + "For more information our latest hot sale or servies of CIR, please visit http://localhost:8080/IRMSExternal/";
            text = text + "Once again, may you have a very Happy Birthday and blessed with good health.\n\n\n";
            text = text + "Best Regards\n";
            text = text + "Cheng Ran\n";
            text = text + "Customer Relationship Management Director";

            this.sendEmail(m.getEmailAddr(), subject, text);

        }
    }
}
