/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import FoodBeverage.entity.MenuItem;
import FoodBeverage.session.MenuManagementSessionBeanLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Acer
 */
@ManagedBean
@SessionScoped
public class MenuItemManagedBean {
@EJB
private MenuManagementSessionBeanLocal mmsbl;
     
    private MenuItem menuitem=new MenuItem();
    public MenuItemManagedBean() {
    }
    
    public void displayMessage(String response){
        FacesMessage msg=new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void createmenuitem(){
        getMmsbl().addMenuItem(getMenuitem());
        menuitem=new MenuItem();
        this.displayMessage("Menu Itm has been created successfully!");
        
    }

    /**
     * @return the mmsbl
     */
    public MenuManagementSessionBeanLocal getMmsbl() {
        return mmsbl;
    }

    /**
     * @param mmsbl the mmsbl to set
     */
    public void setMmsbl(MenuManagementSessionBeanLocal mmsbl) {
        this.mmsbl = mmsbl;
    }

    /**
     * @return the menuitem
     */
    public MenuItem getMenuitem() {
        return menuitem;
    }

    /**
     * @param menuitem the menuitem to set
     */
    public void setMenuitem(MenuItem menuitem) {
        this.menuitem = menuitem;
    }
}
