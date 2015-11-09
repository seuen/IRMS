/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.BuyPkgItem;
import CRM.entity.MemberAccount;
import CRM.entity.ResortPackage;
import CRM.session.BuyItemSessionBeanLocal;
import CRM.session.PackageManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class ExPackageManagedBean {

    private MemberAccount member = new MemberAccount();
    private ResortPackage pkg = new ResortPackage();
    private ResortPackage selectPkg = new ResortPackage();
    private List<ResortPackage> listPkg = new ArrayList();
    @EJB
    private PackageManagementSessionBeanLocal pmsbl;
    @EJB
    private BuyItemSessionBeanLocal bimbl;

    /**
     * Creates a new instance of ExPackageManagedBean
     */
    public ExPackageManagedBean() {
    }

    public void createPkgItem(ResortPackage selectPkg) throws IOException {
        System.err.println(selectPkg);
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
       String name2=pkg.getPackageName();
        String name = selectPkg.getPackageName();
        System.err.println("name1 "+name+ "  name2: "+name2);  
        double price = selectPkg.getPrice();
        BuyPkgItem pkgItem = new BuyPkgItem();
        pkgItem = bimbl.addBuyPkgItem(member, name, price);
        FacesMessage msg = new FacesMessage("Your ticket order is succesfully added to Shopping Cart!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("../Payment/cart.xhtml");
    }

    /**
     * @return the pkg
     */
    public ResortPackage getPkg() {
        return pkg;
    }

    /**
     * @param pkg the pkg to set
     */
    public void setPkg(ResortPackage pkg) {
        this.pkg = pkg;
    }

    /**
     * @return the selectPkg
     */
    public ResortPackage getSelectPkg() {
        return selectPkg;
    }

    /**
     * @param selectPkg the selectPkg to set
     */
    public void setSelectPkg(ResortPackage selectPkg) {
        this.selectPkg = selectPkg;
    }

    /**
     * @return the listPkg
     */
    public List<ResortPackage> getListPkg() {
        if ((listPkg == null) || listPkg.isEmpty()) {
            listPkg = pmsbl.listUptoWebPkg();
        }
        return listPkg;
    }

    /**
     * @param listPkg the listPkg to set
     */
    public void setListPkg(List<ResortPackage> listPkg) {
        this.listPkg = listPkg;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
        return ((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }
}
