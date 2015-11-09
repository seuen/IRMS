/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.BuyAttractionItem;
import CRM.entity.BuyCardValue;
import CRM.entity.BuyHotelItem;
import CRM.entity.BuyPkgItem;
import CRM.entity.BuyShowTicketItem;
import CRM.entity.MemberAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface BuyItemSessionBeanLocal {

    public BuyCardValue addCardBuyItem(MemberAccount member, String cardType, double amount);

    public void removeCardValueItem(BuyCardValue cardItem);
 
    public List<BuyCardValue> getAllCardItems(MemberAccount member);

    public double calculateTotalCardValue(MemberAccount member);

    public BuyShowTicketItem addBuyShowItem(MemberAccount member, String ticketName, int quantity, double unitPrice);

    public List<BuyShowTicketItem> getAllShowItems(MemberAccount member);

    public void removeBuyShowItem(BuyShowTicketItem showItem);

    public void cleanShoppingCart();

    public BuyHotelItem addHotelItem(MemberAccount member, String name, int quantity, double price, String description);

    public List<BuyHotelItem> getAllHotelItems(MemberAccount member);

    public void removeBuyHotelItem(BuyHotelItem hotelItem);

    public List<BuyAttractionItem> getAllAttractionItems(MemberAccount member);

    public BuyAttractionItem addBuyAttractionItem(MemberAccount memebr, String ticketName, int q, double price);

    public void removeBuyAttracionItem(BuyAttractionItem attractionItem);

    public BuyPkgItem addBuyPkgItem(MemberAccount member, String name, double price);

    public List<BuyPkgItem> getAllPkgItems(MemberAccount member);

    public void removeBuyPkgItem(BuyPkgItem pkgItem);


    
}
