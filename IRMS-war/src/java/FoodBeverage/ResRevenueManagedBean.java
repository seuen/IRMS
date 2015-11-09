/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.session.EventRevenueManagementSessionBeanLocal;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.Shop;
import ShoppingMall.session.ShopRevenueManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class ResRevenueManagedBean {

    @EJB
    ShopRevenueManagementSessionBeanLocal srmsbl;
    @EJB
    EventRevenueManagementSessionBeanLocal ermsbl;
    private List<Contract> activeContracts = new ArrayList();
    private List<DetailShopOrder> dso = new ArrayList();
    private List<EventOrder> eo = new ArrayList();
    private Contract viewContract;
    private Date start;
    private Date end;

    /**
     * Creates a new instance of RevenueManagedBean
     */
    public ResRevenueManagedBean() {
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContract") != null) {
            viewContract = (Contract) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContract");
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("start") != null) {
            start = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("start");
            System.out.println("start:"+start);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("end") != null) {
            end = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("end");
            System.out.println("end:"+end);
        }
    }

    public void navigateActiveShops(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("activeContracts.xhtml");
    }

    public void navigateDetailOrderRevenue(ActionEvent event) throws IOException {
        if (start == null || end == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please specify start date and end date for reporting.", ""));
        } else {
            viewContract = (Contract) event.getComponent().getAttributes().get("viewContract");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContract", viewContract);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
            FacesContext.getCurrentInstance().getExternalContext().redirect("resDetailOrderRevenue.xhtml");
        }
    }

    public void navigateCurMonthDOR(ActionEvent event) throws IOException {
        viewContract = (Contract) event.getComponent().getAttributes().get("viewContract");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContract", viewContract);
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        System.out.println(end);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        System.out.println(start);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("resDetailOrderRevenue.xhtml");
    }

    public void navigateCurYearDOR(ActionEvent event) throws IOException {
        viewContract = (Contract) event.getComponent().getAttributes().get("viewContract");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContract", viewContract);
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        System.out.println(end);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        System.out.println(start);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("resDetailOrderRevenue.xhtml");
    }

    public float calculateMonthlyRevenue(Contract contract) {
        Shop shop = contract.getShop();
//        System.out.println("shopid calculateMonthlyRevenue: "+shop.getShopId());
        Calendar c = Calendar.getInstance();
        Date endDate = c.getTime();
//        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        Date startDate = c.getTime();
        List<DetailShopOrder> list = srmsbl.getDetailShopOrders(shop.getShopId(), startDate, endDate);
        float revenue = srmsbl.calculateTotalRevenue(list);
        return revenue;
    }

    public float calculateListRevenues() {
        return srmsbl.calculateTotalRevenue(dso);
    }
    
    public float calculateEventRevenues() {
        return ermsbl.calculateTotalRevenue(eo);
    }
    
       public void navigatePeriodEventRevenue(ActionEvent event) throws IOException {
        if (start == null || end == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please specify start date and end date for reporting.", ""));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
            FacesContext.getCurrentInstance().getExternalContext().redirect("banquetEvents.xhtml");
        }
    }

    public void navigateCurMonthER(ActionEvent event) throws IOException {
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("banquetEvents.xhtml");
    }

    public void navigateCurYearER(ActionEvent event) throws IOException {
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("banquetEvents.xhtml");
    }


    /**
     * @return the activeShops
     */
    public List<Contract> getActiveContracts() {
        activeContracts = srmsbl.getAllActiveResContracts();
        return activeContracts;
    }

    /**
     * @param activeShops the activeShops to set
     */
    public void setActiveContracts(List<Contract> activeShops) {
        this.activeContracts = activeShops;
    }

    /**
     * @return the viewShop
     */
    public Contract getViewContract() {
        return viewContract;
    }

    /**
     * @param viewShop the viewShop to set
     */
    public void setViewContract(Contract viewContract) {
        this.viewContract = viewContract;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @return the dso
     */
    public List<DetailShopOrder> getDso() {
        dso = srmsbl.getDetailShopOrders(viewContract.getShop().getShopId(), start, end);
        return dso;
    }

    /**
     * @param dso the dso to set
     */
    public void setDso(List<DetailShopOrder> dso) {
        this.dso = dso;
    }

    /**
     * @return the eo
     */
    public List<EventOrder> getEo() {
        eo = ermsbl.getBanquets(start, end);
        return eo;
    }

    /**
     * @param eo the eo to set
     */
    public void setEo(List<EventOrder> eo) {
        this.eo = eo;
    }
}
