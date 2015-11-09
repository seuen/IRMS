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
public class BuyItemSessionBean implements BuyItemSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    MemberAccount member;
    BuyCardValue buyCardValue;
    BuyHotelItem buyHotelItem;
    BuyShowTicketItem buyShowTicketItem;
    BuyAttractionItem buyAttractionItem;
    BuyPkgItem buyPkgItem;
    
    
    public BuyItemSessionBean(){
        
    }
    
    @Override
    public BuyCardValue addCardBuyItem(MemberAccount member, String cardType, double amount){
        buyCardValue=new BuyCardValue();   
        Date thisTime=new Date(); 
        System.err.println(thisTime);
        buyCardValue.create(amount, cardType);
        
        buyCardValue.setBuyTime(thisTime);
        System.out.println(buyCardValue.getCardValue());
        buyCardValue.setMemberAccount(member);
        System.out.println(buyCardValue);
        
        em.persist(buyCardValue);       
        return buyCardValue;
    }
    
    @Override
    public BuyAttractionItem addBuyAttractionItem(MemberAccount memebr, String ticketName,int q, double price){
        buyAttractionItem=new BuyAttractionItem();
        Date current=new Date();
        buyAttractionItem.create(ticketName, q, current, price);
        buyAttractionItem.setMember(member);
        em.persist(buyAttractionItem);
        return buyAttractionItem;
    }
    
    @Override
    public BuyPkgItem addBuyPkgItem(MemberAccount member, String name, double price){
        buyPkgItem=new BuyPkgItem();
        Date current=new Date();
        buyPkgItem.create(name, price, current);
        buyPkgItem.setMember(member);
        em.persist(buyPkgItem);
        return buyPkgItem;
    }
    
    @Override
    public BuyShowTicketItem addBuyShowItem(MemberAccount member, String ticketName, int quantity, double unitPrice){
        buyShowTicketItem=new BuyShowTicketItem();
        Date currentTime=new Date();
        buyShowTicketItem.create(currentTime, ticketName, quantity, unitPrice);
        buyShowTicketItem.setMemberAccount(member);
        
        em.persist(buyShowTicketItem);
        return buyShowTicketItem;        
    }
    
    @Override
    public BuyHotelItem addHotelItem(MemberAccount member, String name,int quantity, double price, String description){
        buyHotelItem=new BuyHotelItem();
        Date currentTime=new Date();
        System.err.println(name);
        System.err.println("quantity: "+quantity);
        System.err.println("price : "+price);
        buyHotelItem.create(name, currentTime, quantity, price, description);
        buyHotelItem.setMember(member);
        em.persist(buyHotelItem);
        return buyHotelItem;
    }
    
    @Override
    public List<BuyHotelItem> getAllHotelItems(MemberAccount member){
        Query q=em.createQuery("Select h From BuyHotelItem h Where h.member=:member");
        q.setParameter("member", member);
        return q.getResultList();
    }
    
    @Override
    public List<BuyPkgItem> getAllPkgItems(MemberAccount member){
        Query q=em.createQuery("Select p From BuyPkgItem p Where p.member=:member");
        q.setParameter("member", member);
        return q.getResultList();
    }
    
    @Override
    public List<BuyShowTicketItem> getAllShowItems(MemberAccount member){
        Query q=em.createQuery("SELECT s FROM BuyShowTicketItem s WHERE s.memberAccount=:member");
        q.setParameter("member", member);
        return q.getResultList();
    }
    
    @Override
    public List<BuyAttractionItem> getAllAttractionItems(MemberAccount member){
        Query q=em.createQuery("SELECT a FROM BuyAttractionItem a WHERE a.member=:member ");
        q.setParameter("member", member);
        return q.getResultList();
    }
   
    
    @Override
    public void removeBuyShowItem(BuyShowTicketItem showItem){
        BuyShowTicketItem temp=em.merge(showItem);
        em.remove(temp);
        em.flush();
    }
    
    @Override
    public void removeBuyPkgItem(BuyPkgItem pkgItem){
        BuyPkgItem temp=em.merge(pkgItem);
        em.remove(temp);
        em.flush();
    }
    
    @Override
    public void removeBuyHotelItem(BuyHotelItem hotelItem){
        BuyHotelItem temp=em.merge(hotelItem);
        em.remove(temp);
        em.flush();
    }
    
    @Override
    public void removeBuyAttracionItem(BuyAttractionItem attractionItem){
        BuyAttractionItem temp=em.merge(attractionItem);
        em.remove(temp);
        em.flush();
    }
    
    @Override
    public List<BuyCardValue> getAllCardItems(MemberAccount member){
        
        Query q= em.createQuery("SELECT c FROM BuyCardValue c WHERE c.memberAccount=:member");
        q.setParameter("member", member);
        System.out.println("Session Bean: List All card values in the shopping cart!");
        return q.getResultList();
    }
    
    @Override
    public void removeCardValueItem(BuyCardValue cardItem){
        System.out.println("Remove card item "+cardItem);
        BuyCardValue toberemoved=em.merge(cardItem);
        em.remove(toberemoved);
        System.out.println("Card Item is successfully deleted! ");
        em.flush();
    }
    
    @Override
    public double calculateTotalCardValue(MemberAccount member){
        double total = 0.0;
        double total2 =0.0;
        double total3 = 0.0;
        double total4=0.0;
        double total5=0.0;
      
        Query q=em.createQuery("SELECT c FROM BuyCardValue c WHERE c.memberAccount=:member");
        q.setParameter("member", member);
        List<BuyCardValue> temp=q.getResultList();
        for(BuyCardValue c:temp){
            total=total+c.getCardValue();
        }
        
        Query q1=em.createQuery("Select s from BuyShowTicketItem s Where s.memberAccount=:member");
        q1.setParameter("member", member);
        List<BuyShowTicketItem> temp1=q1.getResultList();
        for(BuyShowTicketItem s :temp1){
            total2=total2+s.getQuantity()*s.getUnitPrice();
        }
        
        Query q2=em.createQuery("Select h from BuyHotelItem h Where h.member=:member");
        q2.setParameter("member", member);
        List<BuyHotelItem> temp2=q2.getResultList();
        for(BuyHotelItem h : temp2){
            total3=total3+h.getQuantity()*h.getUnitPrice();
        }
        
        Query q3=em.createQuery("Select a from BuyAttractionItem a Where a.member=:member");
        q3.setParameter("member", member);
        List<BuyAttractionItem> temp3=q3.getResultList();
        for(BuyAttractionItem a:temp3){
            total4=total4+a.getUnitPrice()*a.getQuantity();
        }
        
        Query q4=em.createQuery("Select p from BuyPkgItem p Where p.member=:member");
        q4.setParameter("member", member);
        List<BuyPkgItem> temp4=q4.getResultList();
        for(BuyPkgItem p:temp4){
            total5=total5+p.getPrice();
        }
        return total+total2+total3+total4+total5;
    }
     
   
    @Override
    public void cleanShoppingCart(){
        Query q=em.createQuery("SELECT c From BuyCardValue c");
        List<BuyCardValue> temp=q.getResultList();
        for(BuyCardValue c:temp ){
            this.removeCardValueItem(c);
        }
        em.flush();
        
        Query q2=em.createQuery("SELECT s From BuyShowTicketItem");
        List<BuyShowTicketItem> temp2=q.getResultList();
        for(BuyShowTicketItem s:temp2){
            this.removeBuyShowItem(s);
        }
        em.flush();
        
        Query q3=em.createQuery("SELECT h From BuyHotelItem");
        List<BuyHotelItem> temp3=q.getResultList();
        for(BuyHotelItem h:temp3){
            this.removeBuyHotelItem(h);
        }
        
        Query q4=em.createQuery("Select a From BuyAttractionItem");
        List<BuyAttractionItem> temp4=q.getResultList();
        for(BuyAttractionItem a:temp4){
            this.removeBuyAttracionItem(a);
        }
        
        Query q5=em.createQuery("Select p From BuyPkgItem");
        List<BuyPkgItem> temp5=q.getResultList();
        for(BuyPkgItem p:temp5){
            this.removeBuyPkgItem(p);
        }
    }
    

}
