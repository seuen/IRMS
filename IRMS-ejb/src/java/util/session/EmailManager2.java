/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.session;

import CRM.entity.MemberAccount;
import Common.entity.Staff;
import EntertainmentShow.entity.ShowOrder;
import EntertainmentShow.entity.ShowTicket;
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
public class EmailManager2 {

    public EmailManager2() {
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


        Session session = Session.getInstance(props, new SMTPAuthenticator2());
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

    public void sendBill(TenantBill bill) {
        if (bill == null) {
            System.out.println("inside send bill null");
        } else {
            String subject = "[Coral Island Resort Shopping Center] Monthly Bill for " + bill.getPayer() + "(" + bill.getBillDateString() + ")";
            String text = "Dear Manager " + bill.getRentalReports().getContract().getShop().getShopOwnerName() + ",\n\n";

            text += "This is a reminder for the payment of monthly rental, commission, adhoc charges and deposit. Please kindly make your payments either online or by approaching our tenant collection center. The deadline for payment is " + bill.getDeadLine() + "\n\n";

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

    public void sendLapseReminders(Contract c) {
        if (c == null) {
            System.out.println("inside send reminder null");
        } else {
            String subject = "[Coral Island Resort Shopping Center] Reminder for Contract Lapse";
            String text = "Dear Manager " + c.getShop().getShopOwnerName() + ",\n\n";

            text += "This is a reminder that your contract is going to lapse in 30 days.\n\n";

            text += "Contract ID: " + c.getId() + ".\n\n";

            text += "***************************************************************\n";

            text += "* Lease Starting Date:                    " + c.getDateFrom() + "\n";
            text += "* Lease Ending Date:                   " + c.getDateTo() + "\n";
            text += "* Lease Term:                " + c.getLeaseterm() + " years \n";
            text += "--------------------------------------------------------------------------------\n";
            text += "* Venue:                     " + c.getVenue() + "\n";
            text += "* Shop:                     " + c.getShop().getShopName() + "\n";
            text += "***************************************************************\n\n\n";
            text += "Thank you very much!\n\n";
            text += "Best regards,\n";
            text += "Coral Island Resort Shopping Center\n";
            this.sendEmail(c.getShop().getEmail(), subject, text);
        }
    }

    public void sendRenewalReminders(Contract c) {
        if (c == null) {
            System.out.println("inside send reminder null");
        } else {
            String subject = "[Coral Island Resort Shopping Center] Reminder for Contract Renew Period";
            String text = "Dear Manager " + c.getShop().getShopOwnerName() + ",\n\n";

            text += "This is a reminder that the renewing period for your contract is going to end in 30 days. Please kindly proceed to CIR manager to if you wish to renew your contract. After 30 days, your contract will not be renewable, and your venue will be open for the market to rend.\n\n";

            text += "Contract ID: " + c.getId() + ".\n\n";

            text += "***************************************************************\n";

            text += "* Lease Starting Date:                    " + c.getDateFrom() + "\n";
            text += "* Lease Ending Date:                   " + c.getDateTo() + "\n";
            text += "* Lease Term:                " + c.getLeaseterm() + " years \n";
            text += "--------------------------------------------------------------------------------\n";
            text += "* Venue:                     " + c.getVenue() + "\n";
            text += "* Shop:                     " + c.getShop().getShopName() + "\n";
            text += "***************************************************************\n\n\n";
            text += "Thank you very much!\n\n";
            text += "Best regards,\n";
            text += "Coral Island Resort Shopping Center\n";
            this.sendEmail(c.getShop().getEmail(), subject, text);
        }


    }

//    public void sendShowTickets(ShowOrder showOrder) {
//        if (showOrder == null) {
//            System.out.println("inside send entertainmentShow order null");
//        } else {
//            String subject = "[Coral Island Resort Shopping Center] Confirmation for Entertainment Show Order";
//            String text = "Dear Customer Siying,\n\n";
//
//            text += "Confirmation Number: " + showOrder.getId() + ".\n\n";
//
//            text += "***************************************************************\n";
//
//            for (ShowTicket st : showOrder.getShowTickets()) {
//                text += "* Lease Starting Date:                    " + c.getDateFrom() + "\n";
//                text += "* Lease Ending Date:                   " + c.getDateTo() + "\n";
//                text += "* Lease Term:                " + c.getLeaseterm() + " years \n";
//                text += "--------------------------------------------------------------------------------\n";
//                text += "* Venue:                     " + c.getVenue() + "\n";
//                text += "* Shop:                     " + c.getShop().getShopName() + "\n";
//                text += "***************************************************************\n\n\n";
//                text += "Thank you very much!\n\n";
//                text += "Best regards,\n";
//                text += "Coral Island Resort Entertainment Show\n";
//            }
//
//            this.sendEmail(c.getShop().getEmail(), subject, text);
//        }
//    }
}
