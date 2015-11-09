/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.entity;

import Accommodation.entity.RTReservation;
import Accommodation.entity.Stay;
import Common.entity.MemberTransaction;
import ShoppingMall.entity.ShopOrder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ARIEL CHENG
 */
@Entity
public class MemberAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String emailAddr;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    private String title;
    private String nationality;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Address address;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Membership membership;
    @OneToMany(mappedBy = "member")
    private Collection<RTReservation> reservations;
//    @OneToMany(mappedBy = "member")
//    private Collection<DelayedPayment> memberTransaction;
    @OneToMany(mappedBy = "memberAccount")
    private Collection<ShopOrder> shoppingOrder;
    @OneToMany(mappedBy = "memberAccount")
    private List<BuyCardValue> buyCardValues;
    @OneToMany(mappedBy = "member")
    private List<CreditCard> creditCards;
    @OneToMany(mappedBy = "member")
    private List<TransactionRecord> transactionRecords;
    @OneToMany(mappedBy = "memberAccount")
    private List<Feedback> feedbacks;
    @OneToMany(mappedBy = "memberAccount")
    private List<BuyShowTicketItem> buyShowTicketItems;
    @OneToMany(mappedBy = "member")
    private List<BuyHotelItem> buyHotelItems;
    @OneToMany(mappedBy = "memberAccount")
    private List<MemberPayment> memberPayments;
    @OneToMany(mappedBy = "member")
    private List<MonthlyCustomerReport> monthlyCustomerReports;
    @OneToMany(mappedBy = "member")
    private List<BuyAttractionItem> buyAtIicketItems;

     @OneToMany(mappedBy="memberAccount")
    private List<Stay> stays;
    @OneToMany(mappedBy = "member")
    private List<BuyPkgItem> buyPkgItems;

    public MemberAccount() {
        stays=new ArrayList();
    }

    public void create(String firstName, String lastName, String phoneNum, String emailAddr,
            Date dateOfBirth, String title, String nationality) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNum(phoneNum);
        this.setEmailAddr(emailAddr);
        this.setDateOfBirth(dateOfBirth);
        this.setTitle(title);
        this.setNationality(nationality);
    }

    public Address getAddress() {
        return address;
    }

    public Membership getMembership() {
        return membership;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return the emailAddr
     */
    public String getEmailAddr() {
        return emailAddr;
    }

    /**
     * @param emailAddr the emailAddr to set
     */
    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the gender
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param gender the gender to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @param membership the membership to set
     */
    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    /**
     * @return the reservations
     */
    public Collection<RTReservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(Collection<RTReservation> reservations) {
        this.reservations = reservations;
    }
//
//    /**
//     * @return the memberTransaction
//     */
//    public Collection<MemberTransaction> getMemberTransaction() {
//        return memberTransaction;
//    }
//
//    /**
//     * @param memberTransaction the memberTransaction to set
//     */
//    public void setMemberTransaction(Collection<MemberTransaction> memberTransaction) {
//        this.memberTransaction = memberTransaction;
//    }

    /**
     * @return the shoppingOrder
     */
    public Collection<ShopOrder> getShoppingOrder() {
        return shoppingOrder;
    }

    /**
     * @param shoppingOrder the shoppingOrder to set
     */
    public void setShoppingOrder(Collection<ShopOrder> shoppingOrder) {
        this.shoppingOrder = shoppingOrder;
    }

    /**
     * @return the buyCardValues
     */
    public List<BuyCardValue> getBuyCardValues() {
        return buyCardValues;
    }

    /**
     * @param buyCardValues the buyCardValues to set
     */
    public void setBuyCardValues(List<BuyCardValue> buyCardValues) {
        this.buyCardValues = buyCardValues;
    }

    /**
     * @return the creditCards
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * @param creditCards the creditCards to set
     */
    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    /**
     * @return the transactionRecords
     */
    public List<TransactionRecord> getTransactionRecords() {
        return transactionRecords;
    }

    /**
     * @param transactionRecords the transactionRecords to set
     */
    public void setTransactionRecords(List<TransactionRecord> transactionRecords) {
        this.transactionRecords = transactionRecords;
    }

    /**
     * @return the feedbacks
     */
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    /**
     * @param feedbacks the feedbacks to set
     */
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    /**
     * @return the buyShowTicketItems
     */
    public List<BuyShowTicketItem> getBuyShowTicketItems() {
        return buyShowTicketItems;
    }

    /**
     * @param buyShowTicketItems the buyShowTicketItems to set
     */
    public void setBuyShowTicketItems(List<BuyShowTicketItem> buyShowTicketItems) {
        this.buyShowTicketItems = buyShowTicketItems;
    }

    /**
     * @return the buyHotelItems
     */
    public List<BuyHotelItem> getBuyHotelItems() {
        return buyHotelItems;
    }

    /**
     * @param buyHotelItems the buyHotelItems to set
     */
    public void setBuyHotelItems(List<BuyHotelItem> buyHotelItems) {
        this.buyHotelItems = buyHotelItems;
    }

    /**
     * @return the memberPayments
     */
    public List<MemberPayment> getMemberPayments() {
        return memberPayments;
    }

    /**
     * @param memberPayments the memberPayments to set
     */
    public void setMemberPayments(List<MemberPayment> memberPayments) {
        this.memberPayments = memberPayments;
    }

    /**
     * @return the monthlyCustomerReports
     */
    public List<MonthlyCustomerReport> getMonthlyCustomerReports() {
        return monthlyCustomerReports;
    }

    /**
     * @param monthlyCustomerReports the monthlyCustomerReports to set
     */
    public void setMonthlyCustomerReports(List<MonthlyCustomerReport> monthlyCustomerReports) {
        this.monthlyCustomerReports = monthlyCustomerReports;
    }

    /**
     * @return the stays
     */
    public List<Stay> getStays() {
        return stays;
    }

    /**
     * @param stays the stays to set
     */
    public void setStays(List<Stay> stays) {
        this.stays = stays;
    }
    
        /**
     * @return the buyAtIicketItems
     */
    public List<BuyAttractionItem> getBuyAtIicketItems() {
        return buyAtIicketItems;
    }

    /**
     * @param buyAtIicketItems the buyAtIicketItems to set
     */
    public void setBuyAtIicketItems(List<BuyAttractionItem> buyAtIicketItems) {
        this.buyAtIicketItems = buyAtIicketItems;
    }

    /**
     * @return the buyPkgItems
     */
    public List<BuyPkgItem> getBuyPkgItems() {
        return buyPkgItems;
    }

    /**
     * @param buyPkgItems the buyPkgItems to set
     */
    public void setBuyPkgItems(List<BuyPkgItem> buyPkgItems) {
        this.buyPkgItems = buyPkgItems;
    }
}
