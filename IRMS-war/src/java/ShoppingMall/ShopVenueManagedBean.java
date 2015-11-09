/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall;

import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import ShoppingMall.session.ShopVenueManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author Cindylulu
 */
@ManagedBean
@ViewScoped
public class ShopVenueManagedBean {

    @EJB
    private ShopVenueManagementSessionBeanLocal svmsbl;
    private List<TenantVenue> tenantVenues = new ArrayList();
    private List<TenantVenue> basement = new ArrayList();
    private List<TenantVenue> levelOne = new ArrayList();
    private List<TenantVenue> levelTwo = new ArrayList();
    private List<TenantVenue> levelThree = new ArrayList();
    private List<TenantVenue> levelFour = new ArrayList();
    private List<TenantVenue> levelFive = new ArrayList();
    private TenantVenue viewVenue;
    private Shop viewShop;
    private TenantVenue negoVenue;
    private TenantVenue newVenue = new TenantVenue();
    private Negotiator newNego = new Negotiator();
    private String statusMessage;
    private List<Negotiator> myNegotiators = new ArrayList();
    private List<Negotiator> allNegotiators = new ArrayList();
    private List<Negotiator> selectedNegotiators = new ArrayList();
    private List<Shop> allShops = new ArrayList();
    private List<Shop> allHistoryShops = new ArrayList();
    private List<Shop> allActiveShops = new ArrayList();

    /**
     * Creates a new instance of ShopVenueManagedBean
     */
    public ShopVenueManagedBean() {
    }

    /**
     * @return the tenantVenues
     */
    public List<TenantVenue> getTenantVenues() {
        tenantVenues = svmsbl.getAllVenues();
        return tenantVenues;
    }

    public void navigateSpaceInfo(ActionEvent event) throws IOException {
        TenantVenue view = (TenantVenue) event.getComponent().getAttributes().get("viewVenue");
        viewVenue = svmsbl.getVenue(view.getRealID());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewVenue", viewVenue);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewVenueInfo.xhtml");
    }

    public void navigateShopInfo(ActionEvent event) throws IOException {
        Shop view = (Shop) event.getComponent().getAttributes().get("viewShop");
        viewShop = svmsbl.getShop(view.getShopId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewShop", viewShop);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewTenantInfo.xhtml");
        System.out.println("inside view info of shop " + viewShop.getShopName());
    }

    public void updateShop() throws IOException {
        svmsbl.updateShop(viewShop);
        statusMessage = "Information Updated Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewShop", viewShop);
    }

    public void saveSpaceInfo(ActionEvent event) throws IOException {
        System.err.println("inside shopping mall space creation");
        try {
            if (Integer.valueOf(newVenue.getArea()) != 0 && !(newVenue.getFloor().equalsIgnoreCase("")) && !(newVenue.getSector().equalsIgnoreCase(""))) {
                newVenue.setBuilding("Shopping Mall");
                newVenue.setNegoAvailability("Available");
                newVenue.setStatus("Not Occupied");
                svmsbl.createVenue(newVenue);
                setStatusMessage("New Venue Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage() + " (New Venue ID is " + newVenue.getId() + ")", ""));
                System.out.println(newVenue.getBuilding() + "-" + newVenue.getArea() + "m*m-" + newVenue.getFloor() + "-" + newVenue.getSector());
                System.err.println("outof shopping mall space creation");
            } else {
                setStatusMessage("Sorry. Please validate your inputs again.");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            }
        } catch (Exception e) {
            setStatusMessage("Sorry. Please validate your inputs again.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
//            e.printStackTrace();
            System.err.println("outof shopping mall space creation");
        }
    }

    public void navigateNewVenue(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addEmptyVenue.xhtml");
    }

    public void navigateMyNegotiations(ActionEvent event) throws IOException {
        setNegoVenue((TenantVenue) event.getComponent().getAttributes().get("negoVenue"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("negoVenue", negoVenue);
        //System.err.println("NegoID:" + negoVenue.getId());
        try {
            if (negoVenue.getNegoAvailability().equalsIgnoreCase("N.A.")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("negotiationNA.xhtml");
            } else {
                myNegotiators = svmsbl.getNegotiators(negoVenue.getRealID());
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewVenueNegotiators.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateAddNegotiation(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addNegotiation.xhtml");
    }

    public void createNewNegotiator(ActionEvent event) throws IOException {
        System.out.println("inside new negotiator created in repository");
        try {
            if (svmsbl.getNegotiator(newNego.getEmail()) != null) {
                setStatusMessage("Negotiator " + newNego.getNegotiatorName() + " already exists in system. Please select it from the list.");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            } else {
                svmsbl.createNegotiator(newNego);
                setStatusMessage("Negotiator " + newNego.getNegotiatorName() + " create successfully!");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
                System.out.println("out of create new negotiator in repository");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("out of create new negotiator in repository");
        }
    }

    public void createNewNegotiations(ActionEvent event) throws IOException {
        System.err.println("inside create new negotiations");
        try {
            if (selectedNegotiators.isEmpty()) {
                setStatusMessage("No negotiators selected. Please select at least one to form a negotiation");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));

            } else {
                for (Negotiator n : selectedNegotiators) {
                    if (!n.getVenues().contains(negoVenue)) {
                        svmsbl.createNegotiation(n, negoVenue);
                        setStatusMessage("Negotiation recorded successfully between negotiator " + n.getNegotiatorName() + " and venue " + "shopping mall " + negoVenue.getId());
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
                    } else {
                        setStatusMessage("Negotiation already exists between negotiator " + n.getNegotiatorName() + " and venue " + "shopping mall " + negoVenue.getId());
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));

                    }
                }
            }
            System.err.println("out of create new negotiations");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of create new negotiations");
        }
    }

    public void updateVenueInfo(ActionEvent event) throws IOException {
        System.err.println("inside update shopping mall venue info: " + viewVenue.getId());
        svmsbl.updateVenue(viewVenue);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewVenue", viewVenue);
        setStatusMessage("Venue information updated successfully!");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
        System.err.println("out of update shopping mall venue info: " + viewVenue.getId());
    }

    public void navigateSpaceVenues(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllVenues.xhtml");
    }

    @PostConstruct
    public void init() {
        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("negoVenue") != null) {
                negoVenue = (TenantVenue) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("negoVenue");
                //System.err.println("Init NegoID:" + negoVenue.getId());
                myNegotiators = svmsbl.getNegotiators(negoVenue.getRealID());
            }
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewVenue") != null) {
                viewVenue = (TenantVenue) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewVenue");
                //System.err.println("Init viewID:" + viewVenue.getId());
            }
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewShop") != null) {
                viewShop = (Shop) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewShop");
//            System.err.println("Init viewShop:" + viewShop.getShopId());
            }
        } catch (Exception ex) {
            System.out.println("Venue deleted or shop deleted so that session could not get them.");
        }
    }

    public void deleteVenueInTable(ActionEvent event) throws IOException {

        try {
            Long delID = (Long) event.getComponent().getAttributes().get("venueId");
            System.err.println("inside delete venue " + delID + " in shopping mall");
            if (svmsbl.deleteVenue(delID)) {
                setStatusMessage("Venue (Shopping Mall" + delID + ") Deleted Successfully!");
            } else {
                setStatusMessage("Venue (Shopping Mall" + delID + ") cannot be deleted because it is occupied or under negotiation!");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            System.err.println(statusMessage);
            System.err.println("out of delete venue in shopping mall");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of delete venue in shopping mall");
        }
    }

    public void deleteNegotiationInTable(ActionEvent event) throws IOException {

        try {
            String delID = (String) event.getComponent().getAttributes().get("negoID");
            System.err.println("inside delete negotiation " + delID + " in shopping mall");
            Negotiator nego = svmsbl.getNegotiator(delID);
            svmsbl.deleteNegotiation(nego, negoVenue);
            negoVenue.getNegotiators().remove(nego);
            setStatusMessage("Negotiation between " + nego.getNegotiatorName() + " and " + negoVenue.getBuilding() + ":" + negoVenue.getId() + " Deleted Successfully!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            System.err.println(statusMessage);
            System.err.println("out of delete negotation in shopping mall");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewVenueNegotiators.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of delete venue in shopping mall");
        }
    }

    /**
     * @return the newVenue
     */
    public TenantVenue getNewVenue() {
        return newVenue;
    }

    /**
     * @param newVenue the newVenue to set
     */
    public void setNewVenue(TenantVenue newVenue) {
        this.newVenue = newVenue;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the viewVenue
     */
    public TenantVenue getViewVenue() {
        return viewVenue;
    }

    /**
     * @param viewVenue the viewVenue to set
     */
    public void setViewVenue(TenantVenue viewVenue) {
        this.viewVenue = viewVenue;
    }

    /**
     * @return the myNegotiators
     */
    public List<Negotiator> getMyNegotiators() {
        return myNegotiators;
    }

    /**
     * @param myNegotiators the myNegotiators to set
     */
    public void setMyNegotiators(List<Negotiator> myNegotiators) {
        this.myNegotiators = myNegotiators;
    }

    /**
     * @return the negoVenue
     */
    public TenantVenue getNegoVenue() {
        return negoVenue;
    }

    /**
     * @param negoVenue the negoVenue to set
     */
    public void setNegoVenue(TenantVenue negoVenue) {
        this.negoVenue = negoVenue;
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
     * @return the newNego
     */
    public Negotiator getNewNego() {
        return newNego;
    }

    /**
     * @param newNego the newNego to set
     */
    public void setNewNego(Negotiator newNego) {
        this.newNego = newNego;
    }

    /**
     * @return the selectedNegotiators
     */
    public List<Negotiator> getSelectedNegotiators() {
        return selectedNegotiators;
    }

    /**
     * @param selectedNegotiators the selectedNegotiators to set
     */
    public void setSelectedNegotiators(List<Negotiator> selectedNegotiators) {
        this.selectedNegotiators = selectedNegotiators;
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
        svmsbl.getAllShops();
        this.allShops = allShops;
    }

    /**
     * @return the allHistoryShops
     */
    public List<Shop> getAllHistoryShops() {
        allHistoryShops = svmsbl.getAllHistoryShops();
        return allHistoryShops;
    }

    /**
     * @param allHistoryShops the allHistoryShops to set
     */
    public void setAllHistoryShops(List<Shop> allHistoryShops) {
        this.allHistoryShops = allHistoryShops;
    }

    /**
     * @return the viewShop
     */
    public Shop getViewShop() {
        return viewShop;
    }

    /**
     * @param viewShop the viewShop to set
     */
    public void setViewShop(Shop viewShop) {
        this.viewShop = viewShop;
    }

    /**
     * @return the allActiveShops
     */
    public List<Shop> getAllActiveShops() {
        allActiveShops = svmsbl.getAllActiveShops();
        return allActiveShops;
    }

    /**
     * @param allActiveShops the allActiveShops to set
     */
    public void setAllActiveShops(List<Shop> allActiveShops) {
        this.allActiveShops = allActiveShops;
    }

    /**
     * @return the levelOne
     */
    public List<TenantVenue> getLevelOne() {
        levelOne = svmsbl.getLevelVenues("01");
        return levelOne;
    }

    /**
     * @param levelOne the levelOne to set
     */
    public void setLevelOne(List<TenantVenue> levelOne) {
        this.levelOne = levelOne;
    }

    /**
     * @return the levelTwo
     */
    public List<TenantVenue> getLevelTwo() {
        levelTwo = svmsbl.getLevelVenues("02");
        return levelTwo;
    }

    /**
     * @param levelTwo the levelTwo to set
     */
    public void setLevelTwo(List<TenantVenue> levelTwo) {
        this.levelTwo = levelTwo;
    }

    /**
     * @return the levelThree
     */
    public List<TenantVenue> getLevelThree() {
        levelThree = svmsbl.getLevelVenues("03");
        return levelThree;
    }

    /**
     * @param levelThree the levelThree to set
     */
    public void setLevelThree(List<TenantVenue> levelThree) {
        this.levelThree = levelThree;
    }

    /**
     * @return the levelFour
     */
    public List<TenantVenue> getLevelFour() {
        levelFour = svmsbl.getLevelVenues("04");
        return levelFour;
    }

    /**
     * @param levelFour the levelFour to set
     */
    public void setLevelFour(List<TenantVenue> levelFour) {
        this.levelFour = levelFour;
    }

    /**
     * @return the levelFive
     */
    public List<TenantVenue> getLevelFive() {
        levelFive = svmsbl.getLevelVenues("05");
        return levelFive;
    }

    /**
     * @param levelFive the levelFive to set
     */
    public void setLevelFive(List<TenantVenue> levelFive) {
        this.levelFive = levelFive;
    }

    /**
     * @return the basement
     */
    public List<TenantVenue> getBasement() {
        basement = svmsbl.getLevelVenues("-1");
        return basement;
    }

    /**
     * @param basement the basement to set
     */
    public void setBasement(List<TenantVenue> basement) {
        this.basement = basement;
    }
//    
//    public int test(Shop shop) {
//        return shop.getArea()*3;
//    }
}
