/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.CreditCard;
import CRM.entity.MemberAccount;
import CRM.entity.TransactionRecord;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface MemberPaymentSessionBeanLocal {


    public CreditCard addCreditCard(MemberAccount memebr, String cardNum, String cardHolder, String dateMonth, String dateYear, String issueBank, String cvv);

    public String checkMembershipType(int points);

    public double rewardCardValue(MemberAccount member, double amount);

    public MemberAccount updateMembership(MemberAccount member, double amount);

    public void createTransactionRecord(MemberAccount member);


    public List<TransactionRecord> ListTransactionRecords(MemberAccount member);

    public List<CreditCard> ListCreditCards(MemberAccount member);

    public void DeleteCreditCard(CreditCard c);

    public void payByCardValue(MemberAccount member, double amount);


}
