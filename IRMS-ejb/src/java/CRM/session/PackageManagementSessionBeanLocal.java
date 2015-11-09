/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import Accommodation.entity.RoomType;
import Attraction.entity.TicketType;
import CRM.entity.PackageItem;
import CRM.entity.ResortPackage;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface PackageManagementSessionBeanLocal {

    public PackageItem createAccomItem(RoomType roomtype, int quantity);

    public PackageItem createFBItem(double price,String name);

    public ResortPackage createPackage(String name, double price, Date expireDate, boolean toWeb, String packgeType, List<PackageItem> packageItems);

    public List<ResortPackage> ListAllPackage();

    public boolean checkPackageExpired(ResortPackage pkg);

    public void deletePackage(ResortPackage pkg);

    public void deletePackageItem(PackageItem pkgItem);

    public void updatePackage(ResortPackage pkg);

    public List<PackageItem> listItems(ResortPackage pkg);

    public void updatePackagePicture(String picName);

    public PackageItem createTicketItem(TicketType ticketType, int quantity);

    public void setImageType(Long id, String type);

    public List<ResortPackage> listUptoWebPkg();


    
}
