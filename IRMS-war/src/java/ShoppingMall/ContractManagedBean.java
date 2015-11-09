/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall;

import Common.session.StaffNotificationSessionBeanLocal;
import ShoppingMall.entity.Contract;
import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import ShoppingMall.session.ContractManagementSessionBeanLocal;
import ShoppingMall.session.ShopVenueManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import util.EmailManager;

/**
 *
 * @author Cindylulu
 */
@ManagedBean
@ViewScoped
public class ContractManagedBean implements Serializable {

    @EJB
    ContractManagementSessionBeanLocal cmsb;
    @EJB
    ShopVenueManagementSessionBeanLocal svmsbl;
    @EJB
    StaffNotificationSessionBeanLocal snsb;
    private List<Contract> allContracts = new ArrayList();
    private List<Contract> activeContracts = new ArrayList();
    private List<Contract> inactiveContracts = new ArrayList();
    private List<Contract> myContracts = new ArrayList();
    private String venueString = new String();
    private String statusMessage;
    private String lessee;
    private Date leaseDateFrom;
    private Integer leaseTerm;
    private Float monthlyRental;
    private Float commissionRate;
    private Float deposit;
    private Float baselineRental;
    private List<Negotiator> allNegotiators = new ArrayList();
    private Negotiator selectedNego;
    private List<TenantVenue> selectedVenues = new ArrayList();
    private List<Shop> allShops = new ArrayList();
    private Contract selectedContract;
    private Contract contract;
    private Shop viewContractShop;
    private Date tomorrow;
    private Integer totalArea = new Integer(0);

    /* Creates a new instance of ContractManagedBean
     */
    public ContractManagedBean() {
    }

    /**
     * @return the allContracts
     */
    public List<Contract> getAllContracts() {
        allContracts = cmsb.getAllContracts();
        return allContracts;
    }

    /**
     * @return the activeContracts
     */
    public List<Contract> getActiveContracts() {
        activeContracts = cmsb.getAllActiveContracts();
        return activeContracts;
    }

    public void navigateShopContracts(ActionEvent event) throws IOException {
        viewContractShop = (Shop) event.getComponent().getAttributes().get("viewContractShop");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContractShop", viewContractShop);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewMyContracts.xhtml");
    }

    public void navigateContractInfo(ActionEvent event) throws IOException {

        setContract((Contract) event.getComponent().getAttributes().get("viewContract"));

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContract", getContract());
        System.err.println("inside view ContractInfo of ID " + getContract().getId());
        System.out.println("contract ID " + getContract().getId() + " " + getContract().getActivated());
        if (getContract().getActivated().compareToIgnoreCase("Inactivated") == 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewInactiveContract.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewContractInfo.xhtml");
        }
    }

    public void update() {
        if (contract.getMonthlyRental() < contract.getBaselineRental()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Sorry, the monthly rental entered should not be lower than baseline rental " + contract.getBaselineRental() + "for contract " + contract.getId(), ""));
        } else {
            System.err.println("inside update contract info");
            cmsb.updateContract(getContract());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update successfully for contract with ID " + getContract().getId(), ""));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewContract", getContract());
            System.err.println("outof update contract info");
        }
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedNego") != null) {
            selectedNego = (Negotiator) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedNego");
//            System.out.println("Initilized selectedNego: " + selectedNego.getNegotiatorName());
        }
        Calendar calt = Calendar.getInstance();
        calt.add(Calendar.DATE, 1);
        tomorrow = calt.getTime();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selecteContract") != null) {
            selectedContract = (Contract) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedContract");
//            System.out.println("Initilized selectedContract: " + selectedContract.getId());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContractShop") != null) {
            viewContractShop = (Shop) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContractShop");
//            System.out.println("Initilized viewContractShop: " + viewContractShop.getShopId());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContract") != null) {
            setContract((Contract) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewContract"));
//            System.out.println("Initilized viewContractShop: " + viewContractShop.getShopId());
        }
    }

    public void saveContract(ActionEvent event) throws IOException {
        if (selectedVenues.isEmpty()) {
            setStatusMessage("No Venue Selected!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
        } else {
            try {
                System.out.println("inside createContract");

                float totalBaseline;
                if (selectedNego.getShopType().equalsIgnoreCase("pushCart")) {
                    String minRanking = "G";
                    for (TenantVenue v : selectedVenues) {
                        if (v.getSector().compareTo(minRanking) < 0) {
                            minRanking = v.getSector();
                        }
                    }
                    if (minRanking.compareToIgnoreCase("A") == 0) {
                        baselineRental = new Float(800);
                    } else if (minRanking.compareToIgnoreCase("B") == 0) {
                        baselineRental = new Float(725);
                    } else if (minRanking.compareToIgnoreCase("C") == 0) {
                        baselineRental = new Float(650);
                    } else if (minRanking.compareToIgnoreCase("D") == 0) {
                        baselineRental = new Float(575);
                    } else if (minRanking.compareToIgnoreCase("E") == 0) {
                        baselineRental = new Float(500);
                    }
                } else if (selectedNego.getShopType().equalsIgnoreCase("Shop")) {
                    String minRanking = "G";
                    for (TenantVenue v : selectedVenues) {
                        if (v.getSector().compareTo(minRanking) < 0) {
                            minRanking = v.getSector();
                        }
                    }
                    if (minRanking.compareToIgnoreCase("A") == 0) {
                        baselineRental = new Float(420);
                    } else if (minRanking.compareToIgnoreCase("B") == 0) {
                        baselineRental = new Float(380);
                    } else if (minRanking.compareToIgnoreCase("C") == 0) {
                        baselineRental = new Float(340);
                    } else if (minRanking.compareToIgnoreCase("D") == 0) {
                        baselineRental = new Float(300);
                    } else if (minRanking.compareToIgnoreCase("E") == 0) {
                        baselineRental = new Float(260);
                    }
                }
                System.out.println(getTotalArea());
                totalBaseline = getTotalArea() * getBaselineRental().floatValue();
                baselineRental = totalBaseline;
                if (monthlyRental < baselineRental) {
                    setStatusMessage("Sorry, the monthly rental entered should not be lower than baseline rental " + baselineRental);
                    System.out.println(statusMessage);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
                } else if (commissionRate >= 100 || commissionRate <= 0) {
                    setStatusMessage("Sorry, the commission rate entered should be within range (0,100)");
                    System.out.println(statusMessage);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
                } else {
                    Contract nc = cmsb.createContract(selectedNego.getShopName() + "-Manager:" + selectedNego.getNegotiatorName(), leaseDateFrom, leaseTerm.intValue(), monthlyRental.floatValue(), commissionRate.floatValue() / 100, deposit.floatValue(), selectedVenues, selectedNego.getNegotiatorName(), baselineRental.floatValue());
                    Shop s = svmsbl.createShop(selectedNego);
                    System.out.println("new Shop created with ID: "+s.getShopId());
                    for (TenantVenue v : selectedVenues) {
                        svmsbl.attach(s, v);
                    }
                    cmsb.attachContractShop(nc, s);
                    svmsbl.registerShop(s);
                    setStatusMessage("New Contract Saved Successfully! Contract ID is " + nc.getId());
                    System.out.println(statusMessage);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage() + " (New Contract ID is " + nc.getId() + ")", ""));

                    String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
                    Long sender = Long.valueOf(Id);
                    String content = "A new shopping mall tenant contract is created. New Contract ID = " + nc.getId();
                    snsb.createNotification(sender, content);
                    System.err.println("out of createContract");
                }
            } catch (Exception vex) {
                setStatusMessage("Unknown Error occured during contract creation. Please try again.");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getStatusMessage(), ""));
                System.out.println(vex);
                System.out.println(statusMessage);
                System.err.println("out of createContract");
            }
        }
    }

    public void renewContract(ActionEvent event) throws IOException {
        try {
            Contract active = selectedContract;
            Shop shop = active.getShop();
            Calendar cal = Calendar.getInstance();
            cal.setTime(active.getLeaseDateTo());
            cal.add(Calendar.DATE, 1);
//            System.err.println("inside renewContract: before renewContract");
            if (active.getRenewed() != null && active.getRenewed().equalsIgnoreCase("Renewed")) {
                setStatusMessage("Sorry, contract " + active.getId() + " has already been renewed!");
                System.out.println(statusMessage);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            } else if (monthlyRental < active.getBaselineRental()) {
                setStatusMessage("Sorry, the monthly rental entered should not be lower than baseline rental " + active.getBaselineRental());
                System.out.println(statusMessage);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            } else if (commissionRate >= 100 || commissionRate <= 0) {
                setStatusMessage("Sorry, the commission rate entered should be within range (0,100)");
                System.out.println(statusMessage);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            } else {
                System.err.println("inside renewContract");
                monthlyRental = this.getMonthlyRental().floatValue();
                Calendar calt = Calendar.getInstance();
                calt.setTime(active.getLeaseDateTo());
                calt.add(Calendar.DATE, 1);
                Contract contractR;
                contractR = cmsb.renewContract(calt.getTime(), leaseTerm.intValue(), monthlyRental.floatValue(), commissionRate.floatValue() / 100, deposit.floatValue(), active.getBaselineRental(), shop);
                System.err.println("inside renewContract: after createContract");
                setStatusMessage("Contract Renewed Successfully");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage() + " (New Contract ID is " + contractR.getId() + ")", ""));

                String Id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
                Long sender = Long.valueOf(Id);
                String content = "A shopping mall tenant contract is renewed. New Contract ID = " + contractR.getId();
                snsb.createNotification(sender, content);
                active.setRenewed("Renewed");
                cmsb.updateContract(active);
                System.err.println("out of createContract");
            }
        } catch (Exception vex) {
            System.out.println("inside renewContract exception");
            setStatusMessage("Unknown Error occured during contract renewal. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, getStatusMessage(), ""));
            System.out.println(vex);
        }
    }

    public void sendContract(ActionEvent event) {
        Contract c = (Contract) event.getComponent().getAttributes().get("c");
        EmailManager emailManager = new EmailManager();
        emailManager.sendContract(c);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contract sent successfully!", ""));
    }

    public void removeInTable(ActionEvent event) {
        try {
            System.out.println("inside remove inactive contract");
            Contract c = (Contract) event.getComponent().getAttributes().get("contracte");
            System.out.println("inside remove4");
            cmsb.updateContract(c);
            String msg = cmsb.deleteContract(c.getId());
            System.out.println("inside remove5");
            if (msg.compareTo("Delete successfully") != 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllInactiveContracts.xhtml");
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Unknown Error Occurred", ""));
        }
    }

    public void navigateAllNegotiators(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllNegotiators.xhtml");
    }

    public void navigateSelectVenues(ActionEvent event) throws IOException {
        Negotiator nego = (Negotiator) event.getComponent().getAttributes().get("selectedNego");
        System.out.println("inside select negotiator to create contract with " + nego.getNegotiatorName());
        selectedNego = svmsbl.getNegotiator(nego.getEmail());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedNego", selectedNego);
        FacesContext.getCurrentInstance().getExternalContext().redirect("selectContractVenues.xhtml");
    }

    /**
     * @return the venueString
     */
    public String getVenueString() {
        try {
            venueString = new String();
            if (!selectedVenues.isEmpty()) {
                for (TenantVenue venue : selectedVenues) {
                    String concat = venueString.concat(venue.getBuilding() + " " + venue.getId() + "(Floor " + venue.getFloor().toUpperCase() + ", Sector " + venue.getSector().toUpperCase() + ")   ");
                    venueString = concat;
                }
            } else {
                System.out.println("selectedVenues not set");
            }
        } catch (Exception e) {
            System.out.println("VenueString got Exception");
            System.out.println(e.toString());
        }
        return venueString;
    }

    /**
     * @return the totalArea
     */
    public Integer getTotalArea() {
        totalArea = 0;
        try {
            if (!selectedVenues.isEmpty()) {
                for (TenantVenue venue : selectedVenues) {
                    totalArea += venue.getArea();
                }
            }
        } catch (Exception e) {
            System.out.println("TotalArea got exception");
            System.out.println(e.toString());
        }
        return totalArea;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return the lessee
     */
    public String getLessee() {
        return lessee;
    }

    /**
     * @return the leaseDateFrom
     */
    public Date getLeaseDateFrom() {
        return leaseDateFrom;
    }

    /**
     * @param shopId the shopId to set
     */
    /**
     * @param venueString the venueString to set
     */
    public void setVenueString(String venueString) {
        this.venueString = venueString;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @param lessee the lessee to set
     */
    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    /**
     * @param leaseDateFrom the leaseDateFrom to set
     */
    public void setLeaseDateFrom(Date leaseDateFrom) {
        this.leaseDateFrom = leaseDateFrom;
    }

    /**
     * @return the leaseTerm
     */
    public Integer getLeaseTerm() {
        return leaseTerm;
    }

    /**
     * @param leaseTerm the leaseTerm to set
     */
    public void setLeaseTerm(Integer leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    /**
     * @return the monthlyRental
     */
    public Float getMonthlyRental() {
        return monthlyRental;
    }

    /**
     * @param monthlyRental the monthlyRental to set
     */
    public void setMonthlyRental(Float monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    /**
     * @return the commissionRate
     */
    public Float getCommissionRate() {
        return commissionRate;
    }

    /**
     * @param commissionRate the commissionRate to set
     */
    public void setCommissionRate(Float commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * @return the deposit
     */
    public Float getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the selectedContract
     */
    public Contract getSelectedContract() {
        return selectedContract;
    }

    /**
     * @param selectedContract the selectedContract to set
     */
    public void setSelectedContract(Contract selectedContract) {
        this.selectedContract = selectedContract;
    }

    /**
     * @return the baselineRental
     */
    public Float getBaselineRental() {
        return baselineRental;
    }

    /**
     * @param baselineRental the baselineRental to set
     */
    public void setBaselineRental(Float baselineRental) {
        this.baselineRental = baselineRental;
    }

    /**
     * @return the allNegotiators
     */
    public List<Negotiator> getAllNegotiators() {
        allNegotiators = svmsbl.getShopNegotiators();
        return allNegotiators;
    }

    /**
     * @param allNegotiators the allNegotiators to set
     */
    public void setAllNegotiators(List<Negotiator> allNegotiators) {
        this.allNegotiators = allNegotiators;
    }

    /**
     * @return the selectedNego
     */
    public Negotiator getSelectedNego() {
        return selectedNego;
    }

    /**
     * @param selectedNego the selectedNego to set
     */
    public void setSelectedNego(Negotiator selectedNego) {
        this.selectedNego = selectedNego;
    }

    /**
     * @return the seletedVenues
     */
    public List<TenantVenue> getSelectedVenues() {
        return selectedVenues;
    }

    /**
     * @param seletedVenues the seletedVenues to set
     */
    public void setSelectedVenues(List<TenantVenue> seletedVenues) {
        this.selectedVenues = seletedVenues;
    }

    /**
     * @param totalArea the totalArea to set
     */
    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    /**
     * @return the tomorrow
     */
    public Date getTomorrow() {
        return tomorrow;
    }

    /**
     * @param tomorrow the tomorrow to set
     */
    public void setTomorrow(Date tomorrow) {
        this.tomorrow = tomorrow;
    }

    /**
     * @return the allShops
     */
    public List<Shop> getAllShops() {
        allShops = svmsbl.getAllShops();
        return allShops;
    }

    /**
     * @param allShops the allShops to set
     */
    public void setAllShops(List<Shop> allShops) {
        this.allShops = allShops;
    }

    /**
     * @return the viewContractShop
     */
    public Shop getViewContractShop() {
        return viewContractShop;
    }

    /**
     * @param viewContractShop the viewContractShop to set
     */
    public void setViewContractShop(Shop viewContractShop) {
        this.viewContractShop = viewContractShop;
    }

    /**
     * @return the inactiveContracts
     */
    public List<Contract> getInactiveContracts() {
        inactiveContracts = cmsb.getAllInActiveContracts();
        return inactiveContracts;
    }

    /**
     * @param inactiveContracts the inactiveContracts to set
     */
    public void setInactiveContracts(List<Contract> inactiveContracts) {
        this.inactiveContracts = inactiveContracts;
    }

    /**
     * @return the myContracts
     */
    public List<Contract> getMyContracts() {
        return myContracts;
    }

    /**
     * @param myContracts the myContracts to set
     */
    public void setMyContracts(List<Contract> myContracts) {
        this.myContracts = myContracts;
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
}
