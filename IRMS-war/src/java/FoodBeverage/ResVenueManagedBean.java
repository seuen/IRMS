/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import FoodBeverage.entity.Restaurant;
import ShoppingMall.entity.Negotiator;
import ShoppingMall.entity.TenantVenue;
import ShoppingMall.session.ShopVenueManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ResVenueManagedBean {

    @EJB
    private ShopVenueManagementSessionBeanLocal svmsbl;
    private List<TenantVenue> tenantVenues = new ArrayList();
    private List<Negotiator> myNegotiators = new ArrayList();
    private List<Negotiator> allNegotiators = new ArrayList();
    private TenantVenue viewVenue;
    private TenantVenue negoVenue;
    private TenantVenue newVenue = new TenantVenue();
    private Map<String, String> buildings = new HashMap();
    private Map<String, Map<String, String>> floorData = new HashMap();
    private Map<String, String> floors = new HashMap();
    private String statusMessage;
    private Negotiator newNego = new Negotiator();
    private List<Negotiator> selectedNegotiators = new ArrayList();
    private List<Restaurant> allRes = new ArrayList();
    private List<Restaurant> allHistoryRes = new ArrayList();
    private List<Restaurant> allActiveRes = new ArrayList();
    private Restaurant viewShop;

    /**
     * Creates a new instance of ResVenueManagedBean
     */
    public ResVenueManagedBean() {
        buildings.put("Central Fountain (luxury)", "Central Fountain(luxury)");
        buildings.put("Beach View Cresent (luxury)", "Beach View Cresent(luxury)");
        buildings.put("Food Republic(food court)", "Food Republic (food court)");
        buildings.put("Harry Potter World Central", "Harry Potter World Central");
        buildings.put("Botanic World Central", "Botanic World Central");
        buildings.put("Disney World East", "Disney World East");
        buildings.put("Disney World West", "Disney World West");

        Map<String, String> floorsCF = new HashMap();
        floorsCF.put("01", "01");
        floorsCF.put("02", "02");
        Map<String, String> floorsBV = new HashMap();
        floorsBV.put("01", "01");
        floorsBV.put("02", "02");
        floorsBV.put("03", "03");
        Map<String, String> floorsFR = new HashMap();
        floorsFR.put("01", "01");
        floorsFR.put("02", "02");
        floorsFR.put("03", "03");
        floorsFR.put("04", "04");
        floorsFR.put("05", "05");
        Map<String, String> floorsHP = new HashMap();
        floorsHP.put("01", "01");
        floorsHP.put("02", "02");
        floorsHP.put("03", "03");
        floorsHP.put("04", "04");
        Map<String, String> floorsBW = new HashMap();
        floorsBW.put("01", "01");
        floorsBW.put("02", "02");
        floorsBW.put("03", "03");
        Map<String, String> floorsDWE = new HashMap();
        floorsDWE.put("01", "01");
        floorsDWE.put("02", "02");
        Map<String, String> floorsDWW = new HashMap();
        floorsDWW.put("01", "01");
        floorsDWW.put("02", "02");

        floorData.put("Central Fountain(luxury)", floorsCF);
        floorData.put("Beach View Cresent(luxury)", floorsBV);
        floorData.put("Food Republic (food court)", floorsFR);
        floorData.put("Harry Potter World Central", floorsHP);
        floorData.put("Botanic World Central", floorsBW);
        floorData.put("Disney World East", floorsDWE);
        floorData.put("Disney World West", floorsDWW);
    }

    /**
     * @return the tenantVenues
     */
    public List<TenantVenue> getTenantVenues() {
        tenantVenues = svmsbl.getAllResVenues();
        return tenantVenues;
    }

    public void navigateSpaceInfo(ActionEvent event) throws IOException {
        TenantVenue view = (TenantVenue) event.getComponent().getAttributes().get("viewVenue");
        viewVenue = svmsbl.getVenue(view.getRealID());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewVenue", viewVenue);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewResVenueInfo.xhtml");
    }

    public void navigateNewVenue(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addEmptyResVenue.xhtml");
    }

    public void navigateShopInfo(ActionEvent event) throws IOException {
        Restaurant view = (Restaurant) event.getComponent().getAttributes().get("viewShop");
        viewShop = svmsbl.getRestaurant(view.getShopId());
        System.out.println(viewShop);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewShop", viewShop);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewResInfo.xhtml");
        System.out.println("inside view info of restaurant " + viewShop.getShopName());
    }

    public void updateShop() throws IOException {
        svmsbl.updateRestaurant(viewShop);
        statusMessage = "Information Updated Successfully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewShop", viewShop);
    }

    public void saveSpaceInfo(ActionEvent event) throws IOException {
        System.err.println("inside Food and Beverage restaurant space creation");
        try {
            if (Integer.valueOf(newVenue.getArea()) != 0 && !(newVenue.getBuilding().equalsIgnoreCase("")) && !(newVenue.getFloor().equalsIgnoreCase("")) && !(newVenue.getSector().equalsIgnoreCase(""))) {
//                System.out.println(Integer.valueOf(newVenue.getArea()));
                newVenue.setNegoAvailability("Available");
                newVenue.setStatus("Not Occupied");
                svmsbl.createVenue(newVenue);
                setStatusMessage("New Venue Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage() + " (New Venue is " + newVenue.getBuilding() + newVenue.getId() + ")", ""));
                System.out.println(newVenue.getBuilding() + "-" + newVenue.getArea() + "m*m-" + newVenue.getFloor() + "-" + newVenue.getSector());
                System.err.println("out of Food and Beverage restaurant space creation");
            } else {
                setStatusMessage("Sorry. Please validate your inputs again.");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            }
        } catch (Exception e) {
            setStatusMessage("Sorry. Please validate your inputs again.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            e.printStackTrace();
            System.err.println("out of Food and Beverage restaurant space creation");
        }
    }

    public void navigateMyNegotiations(ActionEvent event) throws IOException {
        setNegoVenue((TenantVenue) event.getComponent().getAttributes().get("negoVenue"));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("negoVenue", negoVenue);
        //System.err.println("NegoID:" + negoVenue.getId());
        try {
            if (negoVenue.getNegoAvailability().equalsIgnoreCase("N.A.")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("resNegotiationNA.xhtml");
            } else {
                setMyNegotiators(svmsbl.getNegotiators(negoVenue.getRealID()));
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewResVenueNegotiators.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateAddNegotiation(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addResNegotiation.xhtml");
    }

    public void createNewNegotiator(ActionEvent event) throws IOException {
        System.out.println("inside new negotiator created in repository");
        try {
            newNego.setShopType("Restaurant");
            svmsbl.createNegotiator(getNewNego());
            setStatusMessage("Negotiator " + getNewNego().getNegotiatorName() + " create successfully!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            System.out.println("out of create new negotiator in repository");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("out of create new negotiator in repository");
        }
    }

    public void createNewNegotiations(ActionEvent event) throws IOException {
        System.err.println("inside create new negotiations");
        try {
            for (Negotiator n : getSelectedNegotiators()) {
                if (!n.getVenues().contains(negoVenue)) {
                    svmsbl.createNegotiation(n, negoVenue);
                    System.out.println(n.getVenues());
                    setStatusMessage("Negotiation recorded successfully between negotiator " + n.getNegotiatorName() + " and venue " + negoVenue.getBuilding() + ":" + negoVenue.getId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
                } else {
                    setStatusMessage("Negotiation already exists between negotiator " + n.getNegotiatorName() + " and venue " + negoVenue.getBuilding() + ":" + negoVenue.getId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));

                }
            }
            System.err.println("out of create new negotiations");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of create new negotiations");
        }
    }

    public void updateVenueInfo(ActionEvent event) throws IOException {
        svmsbl.updateVenue(viewVenue);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewVenue", viewVenue);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewResVenueInfo.xhtml");
    }

    public void navigateSpaceVenues(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllResVenues.xhtml");
    }

    @PostConstruct
    public void init() {
        //System.err.println("Init svmb");
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("negoVenue") != null) {
            negoVenue = (TenantVenue) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("negoVenue");
            //System.err.println("Init NegoID:" + negoVenue.getId());
            setMyNegotiators(svmsbl.getNegotiators(negoVenue.getRealID()));
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewShop") != null) {
            viewShop = (Restaurant) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewShop");
//            System.err.println("Init viewShop:" + viewShop.getShopId());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewVenue") != null) {
            viewVenue = (TenantVenue) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewVenue");
            //System.err.println("Init viewID:" + viewVenue.getId());
        }
    }

    public void deleteVenueInTable(ActionEvent event) throws IOException {

        try {
            Long delID = (Long) event.getComponent().getAttributes().get("venueId");
            System.err.println("inside delete venue " + delID + " in food and beverage space management");
            if (svmsbl.deleteVenue(delID)) {
                setStatusMessage("Venue (" + delID + ") Deleted Successfully!");
            } else {
                setStatusMessage("Venue (" + delID + ") cannot be deleted because it is occupied or under negotiation!");
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            System.err.println(statusMessage);
            System.err.println("out of delete venue in food and beverage space management");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of delete venue in food and beverage space management");
        }
    }

    public void deleteNegotiationInTable(ActionEvent event) throws IOException {

        try {
            String delID = (String) event.getComponent().getAttributes().get("negoID");
            System.err.println("inside delete negotiation " + delID + " of food and beverage");
            Negotiator nego = svmsbl.getNegotiator(delID);
            svmsbl.deleteNegotiation(nego, negoVenue);
            negoVenue.getNegotiators().remove(nego);
            setStatusMessage("Negotiation between " + nego.getNegotiatorName() + " and " + negoVenue.getBuilding() + ":" + negoVenue.getId() + " Deleted Successfully!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getStatusMessage(), ""));
            System.err.println(statusMessage);
            System.err.println("out of delete negotation for food and baverage");
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewResVenueNegotiators.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("out of delete venue for food and beverage");
        }
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
     * @return the buildings
     */
    public Map<String, String> getBuildings() {
        return buildings;
    }

    /**
     * @param buildings the buildings to set
     */
    public void setBuildings(Map<String, String> buildings) {
        this.buildings = buildings;
    }

    /**
     * @return the floorData
     */
    public Map<String, Map<String, String>> getFloorData() {
        return floorData;
    }

    /**
     * @param floorData the floorData to set
     */
    public void setFloorData(Map<String, Map<String, String>> floorData) {
        this.floorData = floorData;
    }

    /**
     * @return the floors
     */
    public Map<String, String> getFloors() {
        return floors;
    }

    /**
     * @param floors the floors to set
     */
    public void setFloors(Map<String, String> floors) {
        this.floors = floors;
    }

    public void handleBuildingChange() {
        if (newVenue.getBuilding() != null && !newVenue.getBuilding().equals("")) {
            floors = floorData.get(newVenue.getBuilding());
        } else {
            floors = new HashMap();
        }
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
     * @return the negeVenue
     */
    public TenantVenue getNegoVenue() {
        return negoVenue;
    }

    /**
     * @param negeVenue the negeVenue to set
     */
    public void setNegoVenue(TenantVenue negoVenue) {
        this.negoVenue = negoVenue;
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
     * @return the allNegotiators
     */
    public List<Negotiator> getAllNegotiators() {
        allNegotiators = svmsbl.getResNegotiators();
        return allNegotiators;
    }

    /**
     * @param allNegotiators the allNegotiators to set
     */
    public void setAllNegotiators(List<Negotiator> allNegotiators) {
        this.allNegotiators = allNegotiators;
    }

    /**
     * @return the allRes
     */
    public List<Restaurant> getAllRes() {
        allRes = svmsbl.getAllRestaurants();
        return allRes;
    }

    /**
     * @param allRes the allRes to set
     */
    public void setAllRes(List<Restaurant> allRes) {
        this.allRes = allRes;
    }

    /**
     * @return the allHistoryRes
     */
    public List<Restaurant> getAllHistoryRes() {
        allHistoryRes = svmsbl.getAllHistoryRestaurants();
        return allHistoryRes;
    }

    /**
     * @param allHistoryRes the allHistoryRes to set
     */
    public void setAllHistoryRes(List<Restaurant> allHistoryRes) {
        this.allHistoryRes = allHistoryRes;
    }

    /**
     * @return the allActiveRes
     */
    public List<Restaurant> getAllActiveRes() {
        allActiveRes = svmsbl.getAllActiveRestaurants();
        return allActiveRes;
    }

    /**
     * @param allActiveRes the allActiveRes to set
     */
    public void setAllActiveRes(List<Restaurant> allActiveRes) {
        this.allActiveRes = allActiveRes;
    }

    /**
     * @return the viewShop
     */
    public Restaurant getViewShop() {
        return viewShop;
    }

    /**
     * @param viewShop the viewShop to set
     */
    public void setViewShop(Restaurant viewShop) {
        this.viewShop = viewShop;
    }
}
