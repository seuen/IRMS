/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import Accommodation.entity.RoomType;
import Accommodation.session.CustomerReservationSessionBeanLocal;
import Attraction.entity.TicketType;
import CRM.entity.PackageItem;
import CRM.entity.ResortPackage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ARIEL CHENG
 */
@Stateless
public class PackageManagementSessionBean implements PackageManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    ResortPackage pkg;
    PackageItem packageItem;
    @EJB
    CustomerReservationSessionBeanLocal crsbl;

    public PackageManagementSessionBean() {
    }
    
    @Override
    public PackageItem createTicketItem(TicketType ticketType, int quantity){
        packageItem=new PackageItem();
        String name=ticketType.getAttraction().getName()+": "+ticketType.getName();
        String department="Attraction";
        double price= Double.parseDouble(ticketType.getPrice());
        packageItem.create(name, department, quantity, price);
        System.out.println(packageItem);
        em.persist(packageItem);
        return packageItem;
    }
    
    @Override
    public void setImageType(Long id, String type){
        ResortPackage pkgg= new ResortPackage();
        pkgg=em.find(ResortPackage.class,id);
        pkgg.setImageType(type);
        em.merge(pkgg);      
    }

    @Override
    public PackageItem createAccomItem(RoomType roomtype, int quantity) {
        System.err.println(roomtype);
        packageItem = new PackageItem();
        String name = roomtype.getHotel().getName() + ": " + roomtype.getType();
        String department = "Accommodation";
        double price;
        Date thisdate = new Date();
        if (crsbl.isPeak(thisdate)) {
            price = (double) roomtype.getPrice_h();
            System.err.println("Check pick time true");
        } else {
            System.err.println("Check pick time false");
            price = (double) roomtype.getPrice_l();
        }
        packageItem.create(name, department, quantity, price);
        System.out.println(packageItem);
        em.persist(packageItem);
        return packageItem;
    }

    /**
     *
     * @param price
     * @return
     */
    @Override
    public PackageItem createFBItem(double price, String name) {
        packageItem = new PackageItem();
        String department = "Food and Beverage";
        packageItem.create(name, department, 1, price);
        System.out.println(packageItem);

        em.persist(packageItem);
        return packageItem;
    }

    /**
     *
     * @param name
     * @param price
     * @param expireDate
     * @param toWeb
     * @param packgeType
     * @param packageItems
     * @return
     */
    @Override
    public ResortPackage createPackage(String name, double price, Date expireDate, boolean toWeb,
            String packgeType, List<PackageItem> packageItems) {
        pkg = new ResortPackage();
        pkg.create(name, price, expireDate, toWeb, packgeType);
        em.persist(pkg);
        System.out.println("New package ID : "+pkg.getId());
        for (PackageItem pi : packageItems) {
            pi.setPkg(pkg);
            em.merge(pi);
        }
        em.merge(pkg);
        return pkg;
    }
    
    @Override
    public List<ResortPackage> ListAllPackage(){
        Query q =em.createQuery("SELECT p FROM ResortPackage p");
        return q.getResultList();
    }
    
    @Override
    public List<PackageItem> listItems(ResortPackage pkg){
        Long pkgID=pkg.getId();
        Query q=em.createQuery("SELECT i FROM PackageItem i WHERE i.pkg.id=:pkgID");
        q.setParameter("pkgID", pkgID);
        return q.getResultList();
    }
   
    @Override
    public boolean checkPackageExpired(ResortPackage pkg){
        Date today = new Date();
        if(pkg.getExpireDate().before(today))
            return true;
        else
            return false;
    }
   
    @Override
    public void deletePackage(ResortPackage pkg){
        List<PackageItem> plist = new ArrayList();
        Long pkgID=pkg.getId();
        System.err.println("Session Bean Delete Package "+pkg.getId());
        Query q=em.createQuery("SELECT i FROM PackageItem i WHERE i.pkg.id=:pkgID");
        q.setParameter("pkgID", pkgID);
        System.err.println("wowowowo"); 
        plist=q.getResultList();
      
        for(PackageItem p:plist){     
            pkg.getPackageItems().remove(p);
            p.setPkg(null);
            this.deletePackageItem(p);
            em.flush();
            System.err.println("liiiiii");          
        }   
        pkg.setPackageItems(null);
        em.merge(pkg);
        em.remove(pkg);
        System.err.println("hahahah2");
        em.flush();
        System.err.println("wkkkkk");
                
//         List<PackageItem> plist = pkg.getPackageItems();
//        for(PackageItem p:plist){
//           pkg.getPackageItems().remove(p);
//            p.setPkg(null);
//            this.deletePackageItem(p);       
//             em.flush();
//        }
//        pkg.setPackageItems(null);
//         em.flush(); System.err.println("hehe");
//        em.remove(pkg); System.err.println("gg");
//        em.flush();
    }
    
    @Override
    public void deletePackageItem(PackageItem pkgItem){
        em.remove(pkgItem);
        em.flush();
    }
    
    @Override
    public void updatePackage(ResortPackage pkg){
        em.merge(pkg);
    }
    
    @Override
    public void updatePackagePicture(String picName){
        Long pkgID=Long.valueOf(picName);
        pkg=new ResortPackage();
        pkg=em.find(ResortPackage.class, pkgID);
        pkg.setPicName(picName);
        em.merge(pkg);     
    }
    
    @Override
    public List<ResortPackage> listUptoWebPkg(){
        Query q =em.createQuery("SELECT p FROM ResortPackage p WHERE p.postToWeb=true");
        return q.getResultList();
    }
}
