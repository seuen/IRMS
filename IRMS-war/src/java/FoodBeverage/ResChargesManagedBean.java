/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import ShoppingMall.entity.AdhocEvent;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.DetailShopOrder;
import ShoppingMall.entity.RentalReports;
import ShoppingMall.entity.Shop;
import ShoppingMall.session.ChargesManagementSessionBeanLocal;
import ShoppingMall.session.ContractManagementSessionBeanLocal;
import ShoppingMall.session.ShopRevenueManagementSessionBeanLocal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Cindylulu
 */
@ManagedBean
@SessionScoped
public class ResChargesManagedBean {

    @EJB
    private ChargesManagementSessionBeanLocal cmsb;
    @EJB
    private ContractManagementSessionBeanLocal conmsb;
    @EJB
    private ShopRevenueManagementSessionBeanLocal srmsbl;
    private String eventType;
    private String charge;
    private String description;
    private Date eventDate;
    private String descriptionLength;
    private String shopId;
    private String shopName;
    private Long newAdhocEventId;
    private String statusMessage;
    private String currentDate;
    private String shopVenue;
    private List<AdhocEvent> allAdhoc = new ArrayList();
    private List<AdhocEvent> myAdhoc = new ArrayList();
    private List<AdhocEvent> historyAdhoc = new ArrayList();
    private List<AdhocEvent> historyMonthlyAdhoc = new ArrayList();
    private float myCharges;
    private RentalReports rp;
    private float calculateRental;
    private float calculateDeposit;
    private float calculateCommission;
    private float calculateAdhoc;
    private float calculateTotal;
    private Contract contract;

    public ResChargesManagedBean() {
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the charge
     */
    public String getCharge() {
        return charge;
    }

    /**
     * @param charge the charge to set
     */
    public void setCharge(String charge) {
        this.charge = charge;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the descriptionLength
     */
    public String getDescriptionLength() {
        return descriptionLength;
    }

    /**
     * @param descriptionLength the descriptionLength to set
     */
    public void setDescriptionLength(String descriptionLength) {
        this.descriptionLength = descriptionLength;
    }

    /**
     * @return the shopId
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * @param shopId the shopId to set
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void saveNewAdhocEvent(ActionEvent event) {

        Long shopID = Long.valueOf(shopId);
        float charges = Float.valueOf(charge);
        try {
            System.err.println("inside saveNewAdhocEvent");
            newAdhocEventId = cmsb.addAdhocEvents(eventType, charges, description, eventDate, shopID);
            statusMessage = "Successfully!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add New Adhoc Event " + statusMessage + " (New Adhoc Event ID is " + newAdhocEventId + ")", ""));
            this.resetAll();
        } catch (Exception ex) {
        }
    }

    public void resetAll() {
        System.err.println("inside resetAll (of adhoc event form)");
        this.eventType = "";
        this.charge = null;
        this.description = "";
        this.eventDate = null;
        this.shopId = "";
    }
    // passDown ShopId  

    public void passDown(ActionEvent event) {
        String passDown = event.getComponent().getAttributes().get("passDown").toString();
        System.err.println("passDown: " + passDown);
        shopId = passDown;
        Long shopID = Long.valueOf(passDown);
        Shop s = cmsb.getShop(shopID);
        shopName = s.getShopName();
        cmsb.mergeShop(s);
    }

    public void navigateRentalReports(ActionEvent event) throws IOException {
        Long cID = Long.valueOf(event.getComponent().getAttributes().get("ContractID").toString());
        System.err.println("inside navigateRentalReports");

        if (!(cID == null)) {
            this.setContract(conmsb.getContract(cID));
            this.setShopId(this.getContract().getShop().getShopId().toString());
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("shopId", getShopId());
            this.calculateRentalDepositAdhocCommissionTotal(event);
            FacesContext.getCurrentInstance().getExternalContext().redirect("rentalResReports.xhtml");
        } else {
            System.out.println("ContractID does not get correctly");
        }
    }

    public void navigateHistoryReports(ActionEvent event) throws IOException {
        Long cID = Long.valueOf(event.getComponent().getAttributes().get("ContractID").toString());
        System.err.println("inside navigateHistoryReports");

        if (!(cID == null)) {
            this.setContract(conmsb.getContract(cID));
            this.setShopId(this.getContract().getShop().getShopId().toString());
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("shopId", getShopId());

            FacesContext.getCurrentInstance().getExternalContext().redirect("viewHistoryResReports.xhtml");
        } else {
            System.out.println("ContractID does not get correctly");
        }
    }

    public void navigateHistoryReport(ActionEvent event) throws IOException {
        Long rID = Long.valueOf(event.getComponent().getAttributes().get("reportID").toString());
        System.err.println("inside navigateHistoryReport");

        if (!(rID == null)) {
            this.setRp(cmsb.getRentalReports(rID));
            this.setShopId(this.getRp().getContract().getShop().getShopId().toString());
            this.setHistoryMonthlyAdhoc(cmsb.getMyMonthlyAdhocEvents(rp.getContract().getShop().getShopId(), rp.getGenerationDate()));
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("shopId", getShopId());

            FacesContext.getCurrentInstance().getExternalContext().redirect("viewHistoryResReport.xhtml");
        } else {
            System.out.println("ContractID does not get correctly");
        }
    }

    public void calculateRentalDepositAdhocCommissionTotal(ActionEvent event) {
        Long cID = Long.valueOf(event.getComponent().getAttributes().get("ContractID").toString());
        Contract C = conmsb.getContract(cID);
        Calendar start = Calendar.getInstance();
        int year = start.get(Calendar.YEAR);
        int month = start.get(Calendar.MONTH);
        start.setTime(C.getLeaseDateFrom());
        int startYear = start.get(Calendar.YEAR);
        int startMonth = start.get(Calendar.MONTH);
        Calendar end = Calendar.getInstance();
        end.setTime(C.getLeaseDateTo());
        int endYear = end.get(Calendar.YEAR);
        int endMonth = end.get(Calendar.MONTH);
        if (year == startYear && month == startMonth) {
            this.setCalculateDeposit(C.getDeposit());
            this.setCalculateRental(C.getFirstMonthRental());
        } else if (year == endYear && month == endMonth) {
            this.setCalculateDeposit(0);
            this.setCalculateRental(C.getLastMonthRental());
        } else {
            this.setCalculateDeposit(0);
            this.setCalculateRental(C.getMonthlyRental());
        }
       Calendar cal = Calendar.getInstance();
        Date endD = cal.getTime();
        cal.set(Calendar.DATE, 1);
        Date startD = cal.getTime();
        List<DetailShopOrder> list = srmsbl.getDetailShopOrders(C.getShop().getShopId(), startD, endD);
        this.setCalculateCommission(srmsbl.calculateTotalRevenue(list));
        this.setCalculateAdhoc(this.getMyCharges());
        this.setCalculateTotal(this.getCalculateAdhoc() + this.getCalculateCommission() + this.getCalculateDeposit() + this.getCalculateRental());
    }

//     
//    @PostConstruct
//    public void init(){
//        if(FacesContext.getCurrentInstance().getExternalContext().getFlash().get("shopId") != null)
//            shopId = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("shopId").toString();
//    }
    public void doAjaxCountEventNameLength() {
        if (description != null) {
            descriptionLength = Integer.toString(description.length());
        } else {
            descriptionLength = "0";
        }
        descriptionLength = "Current event name length is " + descriptionLength;
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        this.setShopName(shopId);
        return shopName;
    }

    /**
     * @param shopName the shopName to set
     */
    public void setShopName(String shopId) {
        Long shopID = Long.valueOf(shopId);
        Shop s = cmsb.getShop(shopID);
        shopName = s.getShopName();
        cmsb.mergeShop(s);
    }

    /**
     * @return the currentDate
     */
    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM");
        currentDate = sdf.format(new Date());
        return currentDate;
    }

    /**
     * @return the shopVenue
     */
    public String getShopVenue() {
        this.setShopVenue(shopId);
        return shopVenue;
    }

    /**
     * @param shopVenue the shopVenue to set
     */
    public void setShopVenue(String shopId) {
        Long shopID = Long.valueOf(shopId);
        shopVenue = cmsb.getShop(shopID).getCurrentVenues().toString();
    }

    /**
     * @return the myAdhoc
     */
    public List<AdhocEvent> getMyAdhoc() {
        System.err.println("inside getMyMonthlyAdhoc");
        Long shopID = Long.valueOf(shopId);
        myAdhoc = cmsb.getMyMonthlyAdhocEvents(shopID, Calendar.getInstance().getTime());
        return myAdhoc;
    }

    /**
     * @return the myCharges
     */
    public float getMyCharges() {
        System.err.println("inside getMyCharges");
        Long shopID = Long.valueOf(shopId);
        myCharges = cmsb.computeAdhocCharges(cmsb.getMyMonthlyAdhocEvents(shopID, Calendar.getInstance().getTime()));
        return myCharges;
    }

    /**
     * @return the rp
     */
    public RentalReports getRp() {
        return rp;
    }

    /**
     * @param rp the rp to set
     */
    public void setRp(RentalReports rp) {
        this.rp = rp;
    }

    /**
     * @return the calculateRental
     */
    public float getCalculateRental() {
        return calculateRental;
    }

    /**
     * @param calculateRental the calculateRental to set
     */
    public void setCalculateRental(float calculateRental) {
        this.calculateRental = calculateRental;
    }

    /**
     * @return the calculateDeposit
     */
    public float getCalculateDeposit() {
        return calculateDeposit;
    }

    /**
     * @param calculateDeposit the calculateDeposit to set
     */
    public void setCalculateDeposit(float calculateDeposit) {
        this.calculateDeposit = calculateDeposit;
    }

    /**
     * @return the calculateCommission
     */
    public float getCalculateCommission() {
        return calculateCommission;
    }

    /**
     * @param calculateCommission the calculateCommission to set
     */
    public void setCalculateCommission(float calculateCommission) {
        this.calculateCommission = calculateCommission;
    }

    /**
     * @return the calculateAdhoc
     */
    public float getCalculateAdhoc() {
        return calculateAdhoc;
    }

    /**
     * @param calculateAdhoc the calculateAdhoc to set
     */
    public void setCalculateAdhoc(float calculateAdhoc) {
        this.calculateAdhoc = calculateAdhoc;
    }

    /**
     * @return the calculateTotal
     */
    public float getCalculateTotal() {
        return calculateTotal;
    }

    /**
     * @param calculateTotal the calculateTotal to set
     */
    public void setCalculateTotal(float calculateTotal) {
        this.calculateTotal = calculateTotal;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    /**
     * @return the historyAdhoc
     */
    public List<AdhocEvent> getHistoryAdhoc() {
        return historyAdhoc;
    }

    /**
     * @param historyAdhoc the historyAdhoc to set
     */
    public void setHistoryAdhoc(List<AdhocEvent> historyAdhoc) {
        this.historyAdhoc = historyAdhoc;
    }

    /**
     * @return the historyMonthlyAdhoc
     */
    public List<AdhocEvent> getHistoryMonthlyAdhoc() {
        return historyMonthlyAdhoc;
    }

    /**
     * @param historyMonthlyAdhoc the historyMonthlyAdhoc to set
     */
    public void setHistoryMonthlyAdhoc(List<AdhocEvent> historyMonthlyAdhoc) {
        this.historyMonthlyAdhoc = historyMonthlyAdhoc;
    }

    /**
     * @return the allAdhoc
     */
    public List<AdhocEvent> getAllAdhoc() {
        allAdhoc = cmsb.getAllResAdhocEvents();
        return allAdhoc;
    }

    /**
     * @param allAdhoc the allAdhoc to set
     */
    public void setAllAdhoc(List<AdhocEvent> allAdhoc) {
        this.allAdhoc = allAdhoc;
    }
}
