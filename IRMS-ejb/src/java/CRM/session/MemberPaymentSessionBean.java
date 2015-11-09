/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.BuyAttractionItem;
import CRM.entity.BuyCardValue;
import CRM.entity.BuyHotelItem;
import CRM.entity.BuyShowTicketItem;
import CRM.entity.CreditCard;
import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import CRM.entity.TransactionRecord;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless
public class MemberPaymentSessionBean implements MemberPaymentSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;
    CreditCard creditCard;
    TransactionRecord transactionRecord;

    public MemberPaymentSessionBean() {
    }

    @Override
    public CreditCard addCreditCard(MemberAccount member, String cardNum, String cardHolder,
            String dateMonth, String dateYear, String issueBank, String cvv) {
        creditCard = new CreditCard();
        System.err.println(member);
        System.out.println("Year " + dateYear);
        creditCard.create(cardNum, cardHolder, issueBank, dateMonth, dateYear, cvv);
        System.out.println(creditCard);

        creditCard.setMember(member);
        System.err.println("New credit card " + creditCard.getCreditCardNum()
                + " is added to member ID " + member.getId());
        em.persist(creditCard);
        return creditCard;
    }

    @Override
    public MemberAccount updateMembership(MemberAccount member, double amount) {
        int points;
        int pointNow;
        double cardValue;
        String memberType;
        Membership membership;
        membership = member.getMembership();
        points = (int) Math.round(amount);
        pointNow = member.getMembership().getLoyaltyPoints();
        pointNow = pointNow + points;
        membership.setLoyaltyPoints(pointNow);
        memberType = checkMembershipType(pointNow);
        membership.setMemberType(memberType);
        cardValue = rewardCardValue(member, amount);
        membership.setCardValue(cardValue);
        member.setMembership(membership);

        em.merge(member);
        em.merge(member.getMembership());

        System.err.println("Membership card value " + member.getMembership().getCardValue());
        System.err.println("Membership type " + member.getMembership().getMemberType());
        System.err.println("Membership loyalty point " + member.getMembership().getLoyaltyPoints());

        return member;
    }

    @Override
    public void createTransactionRecord(MemberAccount member) {
        List<BuyCardValue> cardvalues;
        Query q = em.createQuery("SELECT c FROM BuyCardValue c WHERE c.memberAccount=:member");
        q.setParameter("member", member);
        cardvalues = q.getResultList();
        for (BuyCardValue c : cardvalues) {
            String itemName = "Card Value Topup " + c.getTopupType();
            Date thisTime = new Date();
            double TotalPrice = c.getCardValue();
            double payMoney = c.getCardValue();
            member.getMembership().setCardValue(member.getMembership().getCardValue()+(double)Double.valueOf(c.getTopupType()));
            transactionRecord = new TransactionRecord();
            transactionRecord.create(itemName, 1, "CRM", thisTime, TotalPrice, payMoney);
            transactionRecord.setMember(member);
            em.persist(transactionRecord);
            System.err.println("New transaction record is created. Transaction record ID is " + transactionRecord.getId());
            em.remove(c);
            em.flush();
        }

        Query q2 = em.createQuery("Select s From BuyShowTicketItem s Where s.memberAccount=:member");
        q2.setParameter("member", member);
        List<BuyShowTicketItem> temp;
        temp = q.getResultList();
        for (BuyShowTicketItem s : temp) {
            String itemName = s.getTicketName();
            Date thisTime = s.getBuyTime();
            double price = s.getQuantity() * s.getUnitPrice();
            transactionRecord = new TransactionRecord();
            transactionRecord.create(itemName, s.getQuantity(), "Entertainment Show", thisTime, price, price);
            transactionRecord.setMember(member);
            em.persist(transactionRecord);
            System.err.println("New transaction record is created. Transaction record ID is " + transactionRecord.getId());
            em.remove(s);
            em.flush();
        }

        Query q3 = em.createQuery("SELECT h FROM BuyHotelItem h WHERE h.member=:member");
        q3.setParameter("member", member);
        List<BuyHotelItem> temp2;
        temp2 = q3.getResultList();
        for (BuyHotelItem h : temp2) {
            String itemName = h.getItemName();
            Date thisTime = h.getCurrentTime();
            double price = h.getUnitPrice() * h.getQuantity();
            transactionRecord = new TransactionRecord();
            transactionRecord.create(itemName, h.getQuantity(), "Accommodation", thisTime, price, price);
            transactionRecord.setMember(member);
            em.persist(transactionRecord);
            System.err.println("New transaction record is created. Transaction record ID is " + transactionRecord.getId());
            em.remove(h);
            em.flush();
        }
        
        Query q4=em.createQuery("SELECT a FROM BuyAttractionItem a WHERE a.member=:member");
        q4.setParameter("member", member);
        List<BuyAttractionItem> temp3;
        temp3=q4.getResultList();
        for(BuyAttractionItem a : temp3){
            String itemName=a.getTicketName();
            Date t=a.getCurrentTime();
            double p=a.getUnitPrice()*a.getQuantity();
            transactionRecord=new TransactionRecord();
            transactionRecord.create(itemName, a.getQuantity(),"Attraction", t, p, p);
            transactionRecord.setMember(member);
            em.persist(transactionRecord);
            em.remove(t);
            em.flush();         
        }
        
    }

    @Override
    public List<TransactionRecord> ListTransactionRecords(MemberAccount member) {
        Query q = em.createQuery("SELECT t FROM TransactionRecord t WHERE t.member=:member");
        q.setParameter("member", member);
        System.out.println("Session Bean: List All Transaction Records! ");
        return q.getResultList();
    }

    @Override
    public List<CreditCard> ListCreditCards(MemberAccount member) {
        Query q = em.createQuery("SELECT c FROM CreditCard c WHERE c.member=:member");
        q.setParameter("member", member);
        System.out.println("Session Bean: List All Credit Cards! ");
        return q.getResultList();
    }

    @Override
    public void DeleteCreditCard(CreditCard c) {
        em.remove(c);
        em.flush();
    }

//    public MemberAccount topupCardValue(MemberAccount member, double amount){
//        
//        return member;
//    }
    @Override
    public double rewardCardValue(MemberAccount member, double amount) {
        double cardValue = member.getMembership().getCardValue();
        cardValue = cardValue + amount * 0.02;
        return cardValue;
    }

    @Override
    public String checkMembershipType(int points) {
        if (points < 3000) {
            return "Classic";
        } else if ((points >= 3000) && (points < 6000)) {
            return "Silver";
        } else if ((points >= 6000) && (points < 10000)) {
            return "Gold";
        } else if (points >= 10000) {
            return "Diamond";
        }
        return "Cannot Find Type";
    }

    @Override
    public void payByCardValue(MemberAccount member, double amount) {
        double oldCardValue = member.getMembership().getCardValue();
        double currentCardValue = oldCardValue - amount;
        member.getMembership().setCardValue(currentCardValue);
        em.merge(member);
        em.merge(member.getMembership());

    }
}
