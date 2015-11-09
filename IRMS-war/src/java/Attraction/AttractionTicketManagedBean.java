/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.Attraction;
import Attraction.entity.AttractionOrder;
import Attraction.entity.Bundle;
import Attraction.entity.PkgTicket;
import Attraction.entity.AttractionTicket;
import Attraction.entity.AttractionTicketOrder;
import Attraction.entity.AvailableTicket;
import Attraction.entity.BundleOrder;
import Attraction.entity.TicketType;
import Attraction.session.AttraTicketManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.EmailManager;

/**
 *
 * @author zsy
 */
@ManagedBean
@SessionScoped
public class AttractionTicketManagedBean implements Serializable {

    private TicketType ticketType = new TicketType();
    private TicketType theTicketType;
    private AttractionTicket ticket;
    private String attractionName;
    private List<TicketType> ticketTypes = new ArrayList();
    private List<TicketType> allTicketTypes = new ArrayList();
    private List<TicketType> selectedTicketTypes = new ArrayList();
    private List<Attraction> allAttractions = new ArrayList();
    private String bundleName;
    private String bundlePrice;
    private String originalTotalPrice;
    private String quantityString;
    private List<Bundle> allBundles = new ArrayList();
    private Bundle theBundle;
    private PkgTicket pkgTicket;
    private List<PkgTicket> pkgTickets = new ArrayList();
    private String quantity;
    private List<String> quantities = new ArrayList();
    private List<AttractionTicketOrder> allTheTicketOrders = new ArrayList();
    private List<AttractionTicketOrder> disneyTicketOrders = new ArrayList();
    private String curName = null; 
    private int activeIndex;
    private List<BundleOrder> bundleOrders = new ArrayList();
    private List<BundleOrder> nbundleOrders = new ArrayList();
    private List<BundleOrder> ebundleOrders = new ArrayList();
     private List<BundleOrder> bundleOrders1 = new ArrayList();
      private List<BundleOrder> bundleOrders2 = new ArrayList();
      private Date ticketDate;
      private Date date1;
    private List<AttractionTicketSale> attractionTicketSales;
    private String stringDate;
    private String emailAddress;
    private int purchasedNum =0;
    @EJB
    AttraTicketManagementSessionBeanLocal atmsb;

    /**
     * Creates a new instance of AttractionTicketManagedBean
     */
    public AttractionTicketManagedBean() {
    }
    
   

    public void addTicketType() {
        System.err.println("inside AttractionTicketManagedBean addTicketType()");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            //Attraction attraction = em.find(Attraction.class, attractionName);
            atmsb.addTicketType(ticketType, attractionName);
            String statusMessage = "New Ticket Type Created Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
            this.resetTicketType(ticketType);

        } catch (Exception ex) {
        }
    }
    
    public int computePurchasedNumber(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          Date now = sdf.parse(sdf.format(new Date()));
           Collection<AvailableTicket> ats = theTicketType.getAvailableTickets();
           AvailableTicket availableTicket = new AvailableTicket();
           for (AvailableTicket at : ats) {
                        if (at.gettDate().equals(now)) {
                            availableTicket = at;
                             return availableTicket.getPurchasedNum();
                        }
                    }
          
        } catch (ParseException ex) {
            Logger.getLogger(AttractionTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
}
    public void test(ActionEvent event){
        System.err.println("managedBean: only testing!");
    }

    public void resetTicketType(TicketType ticketType) {
        System.err.println("inside AttractionTicketManagedBean: resetTicketType()");
        ticketType.setName(null);
        ticketType.setPrice(null);
        ticketType.setMaxNumber(null);
        attractionName = "";

    }

  
    public void getTheTicketType(ActionEvent event) {
        System.err.println("inside managedBean getTheTicketType()");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Long ticketTypeId = Long.parseLong(event.getComponent().getAttributes().get("ticketTypeId").toString());
        System.err.println("ticketTypeId: " + ticketTypeId);
        setTheTicketType(atmsb.findTicketType(ticketTypeId));
        System.err.println("theTicketType： " + theTicketType.getId());
        System.err.println("name: " + theTicketType.getName());

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("TicketTypeDetail.xhtml");
        } catch (Exception ex) {
        }
    }

    public void updateTicketType() {
        System.err.println("ManagedBean: updateTIcketType()");
        boolean result = atmsb.updateTicketType(theTicketType);
        if (result == true) {
            String statusMessage = "Information Updated Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Information Update Failed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }

    public void updateBundle() {
        System.err.println("ManagedBean: updateTIcketType()");
        boolean result = atmsb.updateBundle(theBundle);
        if (result == true) {
            String statusMessage = "Information Updated Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Information Update Failed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }

//    public void deleteTicketType(ActionEvent event) {
//        System.err.println("inside attractionTicketManagedBean: deleteTicketType() ");
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext ec = fc.getExternalContext();
//        Long ticketTypeId = Long.parseLong(event.getComponent().getAttributes().get("ticketTypeId").toString());
//        System.err.println("id: "+ticketTypeId);
//        if (atmsb.deleteTicketType(ticketTypeId)) {
//            String statusMessage = "Ticket Type deleted from database Successfully";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//        } else {
//            String statusMessage = "Error: cannot delete ticket type. Make sure there is no bundle connect to it !";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//        }
//
//    }

     public void deleteTicketType() {
        System.err.println("inside attractionTicketManagedBean: deleteTicketType() ");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
      //  Long ticketTypeId = Long.parseLong(event.getComponent().getAttributes().get("ticketTypeId").toString());
       Long ticketTypeId = theTicketType.getId();
        System.err.println("id: "+ticketTypeId);
        if (atmsb.deleteTicketType(ticketTypeId)) {
            String statusMessage = "Ticket Type deleted from database Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Error: cannot delete ticket type. Make sure there is no bundle connect to it !";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }

    }
     
    public void deleteBundle() {
        System.err.println("inside attractionTicketManagedBean: deleteBundle() ");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Long bundleId = theBundle.getId();
        if (atmsb.deleteBundle(bundleId)) {
            String statusMessage = "Bundle deleted from database Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Error: cannot delete bundle";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }

    }

    public void createBundle() throws IOException {
        System.err.println("ManagedBean: inside createBundle()");
        System.err.println("selected ticketTypes list: " + pkgTickets.size());
        if (atmsb.createBundle(pkgTickets, bundleName, bundlePrice)) {

            String statusMessage = "Bundle Created Successfully !";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
            //FacesContext.getCurrentInstance().getExternalContext().redirect("CreateBundle.xhtml");

        } else {
            String statusMessage = "Error: Cannot Create Bundle";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }

        //FacesContext.getCurrentInstance().getExternalContext().redirect("CreateBundle.xhtml");
    }

    public void reloadTicketType(ActionEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewAllTicketType.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AttractionTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reloadBundle(ActionEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewAllBundle.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AttractionTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addPkgTicket(TicketType ticketType) {
        System.err.println("inside managedBean addTicketType()");
        //System.err.println("pkgTicket,ticketType number" + pkgTicket.getTicketNumber());
        int tquantity = Integer.parseInt(quantity);
        PkgTicket pt = new PkgTicket();
        pt.setTicketId(ticketType.getId());
        pt.setPrice(ticketType.getPrice());
        pt.setTicketNumber(tquantity);
        pt.setTicketType(ticketType);
        pkgTickets.add(pt);

    }

    public void createNewPkgTickets() {
        System.err.println("inside managedBean: creatNewPkgTickets");
        System.err.println("selectedTIcketTypes: " + selectedTicketTypes.size());
        for (TicketType tp : selectedTicketTypes) {
            PkgTicket pt = new PkgTicket();
            pt.setTicketType(tp);
            pt.setTicketId(tp.getId());
            pt.setPrice(tp.getPrice());
            //all attr set except quantity
            pkgTickets.add(pt); //pkgTickets-->bundle, only if quantity is modified for each

        }

        System.err.println("pkgTickets.size()" + pkgTickets.size());
    }

    public void setListQuantities(TicketType ticketType, int quantity) {
        String ticketName = ticketType.getName();
        Long ticketId = ticketType.getId();
        for (PkgTicket pt : pkgTickets) {
            if (pt.getId() == ticketId) {
                pt.setTicketNumber(quantity);
            }

        }
    }

    public void computeOriTotal() {
        System.err.println("inside ManagedBean: computePriTotal");
        int total = 0;
        for (PkgTicket pt : pkgTickets) {
            int num = pt.getTicketNumber();
            int price = Integer.parseInt(pt.getPrice());
            total += num * price;

        }
        originalTotalPrice = Integer.toString(total);

    }

    public void assignQuantity(AttractionTicketOrder to) {
        System.err.println("managedBean: haha");
        to.setQuantity(Integer.parseInt(quantityString));
        quantityString = "0";
        System.err.println("selected quantity: "+ to.getQuantity());
        //this.sellTickets();
    }
    
    public void assignNBundleQuantity(BundleOrder bundleOrder){
         System.err.println("assign N BundleQuantity");
        bundleOrder.setQuantity(Integer.parseInt(quantityString));
        bundleOrders1.add(bundleOrder);
        quantityString = "0";
        System.err.println("selected N bundle quantity: "+ bundleOrder.getQuantity()+" " +bundleOrders1.size());
    }

    public void assignEBundleQuantity(BundleOrder bundleOrder){
         System.err.println("assign E BundleQuantity");
        bundleOrder.setQuantity(Integer.parseInt(quantityString));
         bundleOrders2.add(bundleOrder);
        quantityString = "0";
        System.err.println("selected E bundle quantity: "+ bundleOrder.getQuantity()+" "+bundleOrders2.size());
    }
    
    public void sellTickets() {
        System.err.println("managedBean: sellTickets()" + getAllTheTicketOrders().size());
        List<AttractionTicketOrder> atos = new ArrayList();
        for (AttractionTicketOrder ato : getAllTheTicketOrders()) {
            System.err.println("quantity: " + ato.getQuantity());
        }
        for (AttractionTicketOrder ato : getAllTheTicketOrders()) {
            if (!(ato.getQuantity() == 0)) {
                atos.add(ato);
            }
        }
        System.err.println("aros.size() " + atos.size());
//        if (atmsb.sellTickets(atos)) {
//            String statusMessage = "Bundle Created Successfully !";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//
//        } else {
//            String statusMessage = "Erro: not enough tickets left !";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//        }

    }
    
      public void assignDate() {
        try {
            System.err.println("Stringdate " +stringDate);
            String[] str = stringDate.split("/");
            System.err.println("1"+str[1]);
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
              ticketDate = sdf.parse(stringDate);
              System.err.println("ticketDate "+ticketDate);
        } catch (ParseException ex) {
            Logger.getLogger(AttractionTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   public float bookTickets1(AttractionTicketSale attractionTicketSale){
        List <BundleOrder> bundleOrders = new ArrayList();
        float totalPrice = 0;
        bundleOrders.addAll(bundleOrders1); bundleOrders.addAll(bundleOrders2);
          List<AttractionTicketOrder> ticketOrders = new ArrayList<AttractionTicketOrder>();
        for(AttractionTicketSaleEntry ats:attractionTicketSale.getAttractionTicketSaleEntries()){
            AttractionTicketOrder ato = ats.getAttractionTicketOrder();
            if(!(ato.getQuantity()==0)){
                ticketOrders.add(ato);
            }
        }
        //check availabilty
         if(atmsb.checkTicketAvailability( ticketOrders, bundleOrders, ticketDate)){
             for(AttractionTicketOrder ato:ticketOrders){
                 totalPrice +=ato.getQuantity()*ato.getPrice();
             }
             for(BundleOrder bo:bundleOrders){
                 totalPrice += bo.getQuantity()*bo.getPrice();
             }
             
         }
         return totalPrice;
           //need to call managedBean,bookTickets(attractionTicketSale)
   }
    public void bookTickets(AttractionTicketSale attractionTicketSale) throws ParseException{
        System.err.println("managedBean: bookTickets");
        System.err.println("managedBean: bookTickets " + attractionTicketSale.getAttraction().getName());
        System.err.println("1 BundleOrders: "+ bundleOrders1.size());
        System.err.println("2 BundleOrders: "+ bundleOrders2.size());
     
        System.err.println("339 ticket date: "+ticketDate);
        
        List <BundleOrder> bundleOrders = new ArrayList();
        bundleOrders.addAll(bundleOrders1); bundleOrders.addAll(bundleOrders2);
        System.err.println("ADD: BundleOrders: "+ bundleOrders.size());
        List<AttractionTicketOrder> ticketOrders = new ArrayList<AttractionTicketOrder>();
        for(AttractionTicketSaleEntry ats:attractionTicketSale.getAttractionTicketSaleEntries()){
            AttractionTicketOrder ato = ats.getAttractionTicketOrder();
            if(!(ato.getQuantity()==0)){
                ticketOrders.add(ato);
            }
        }
         System.err.println("ticketOrders.size() " + ticketOrders.size());
        if (atmsb.sellTickets(ticketOrders,bundleOrders,ticketDate)) {
            System.err.println("success, managedBean");
            String statusMessage = "Ticket Sold Successfully !";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));

        } else {
            String statusMessage = "Error: not enough tickets left !";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
        bundleOrders = new ArrayList();
        bundleOrders1 = new ArrayList();
        bundleOrders2 = new ArrayList();
        ticketOrders = new ArrayList();
    }

    public void sendETicket(){
//    List<AttractionTicketOrder> attractionTicketOrders = new ArrayList();
//    List<BundleOrder> bundleOrders = new ArrayList();
    AttractionOrder attractionOrder = new AttractionOrder();
    EmailManager emailManager = new EmailManager();
    emailManager.sendAttractionTicket(attractionOrder,emailAddress);
}
    /**
     * @return the ticketType
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void TicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return the ticket
     */
    public AttractionTicket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(AttractionTicket ticket) {
        this.ticket = ticket;
    }

    /**
     * @return the attractionName
     */
    public String getAttractionName() {
        return attractionName;
    }

    /**
     * @param attractionName the attractionName to set
     */
    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    /**
     * @return the ticketTypes
     */
    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    /**
     * @param ticketTypes the ticketTypes to set
     */
    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    /**
     * @return the allTicketTypes
     */
    public List<TicketType> getAllTicketTypes() {
        System.err.print("inside managedBean: getAllTicketTypes()");
        allTicketTypes = atmsb.getAllTicketTypes();
        return allTicketTypes;
    }

    /**
     * @param allTicketTypes the allTicketTypes to set
     */
    public void setAllTicketTypes(List<TicketType> allTicketTypes) {
        this.allTicketTypes = allTicketTypes;
    }

    /**
     * @return the theTicketType
     */
    public TicketType getTheTicketType() {
        return theTicketType;
    }

    /**
     * @param theTicketType the theTicketType to set
     */
    public void setTheTicketType(TicketType theTicketType) {
        System.err.println("ManagedBean setTheTicketType: " + theTicketType.getId());
        this.theTicketType = theTicketType;
    }

    /**
     * @return the selectedTicketTypes
     */
    public List<TicketType> getSelectedTicketTypes() {
        return selectedTicketTypes;
    }

    /**
     * @param selectedTicketTypes the selectedTicketTypes to set
     */
    public void setSelectedTicketTypes(List<TicketType> selectedTicketTypes) {
        this.selectedTicketTypes = selectedTicketTypes;
    }

    /**
     * @return the bundleName
     */
    public String getBundleName() {
        return bundleName;
    }

    /**
     * @param bundleName the bundleName to set
     */
    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    /**
     * @return the bundlePrice
     */
    public String getBundlePrice() {
        return bundlePrice;
    }

    /**
     * @param bundlePrice the bundlePrice to set
     */
    public void setBundlePrice(String bundlePrice) {
        this.bundlePrice = bundlePrice;
    }

    /**
     * @return the originalTotalPrice
     */
    public String getOriginalTotalPrice() {
//        float totalPrice = 0;
//        System.err.println("managedBean: selectedTicketTypesList: "+selectedTicketTypes.size());
//        for(TicketType t:selectedTicketTypes){
//            String price = t.getPrice();
//            float ticketPrice = Float.parseFloat(price);
//            totalPrice += ticketPrice;
//            System.err.println("totalPrice "+totalPrice);
//        }
//        originalTotalPrice = Float.toString(totalPrice);
        System.err.println("managedBean: originalTotalPrice" + originalTotalPrice);
        return originalTotalPrice;
    }

    /**
     * @param originalTotalPrice the originalTotalPrice to set
     */
    public void setOriginalTotalPrice(String originalTotalPrice) {
        this.originalTotalPrice = originalTotalPrice;
    }

    /**
     * @return the allBundles
     */
    public List<Bundle> getAllBundles() {
        System.err.print("inside managedBean: getAllBundles()");
        allBundles = atmsb.getAllBundles();
        return allBundles;
    }

    /**
     * @param allBundles the allBundles to set
     */
    public void setAllBundles(List<Bundle> allBundles) {
        this.allBundles = allBundles;
    }

    /**
     * @return the theBundle
     */
    public Bundle getTheBundle() {
        return theBundle;
    }

    /**
     * @param theBundle the theBundle to set
     */
    public void setTheBundle(Bundle theBundle) {
        this.theBundle = theBundle;
    }

    /**
     * @return the pkgTicket
     */
    public PkgTicket getPkgTicket() {
        return pkgTicket;
    }

    /**
     * @param pkgTicket the pkgTicket to set
     */
    public void setPkgTicket(PkgTicket pkgTicket) {
        this.pkgTicket = pkgTicket;
    }

    /**
     * @return the pkgTickets
     */
    public List<PkgTicket> getPkgTickets() {
        return pkgTickets;
    }

    /**
     * @param pkgTickets the pkgTickets to set
     */
    public void setPkgTickets(List<PkgTicket> pkgTickets) {
        this.pkgTickets = pkgTickets;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the quantities
     */
    public List<String> getQuantities() {
        return quantities;
    }

    /**
     * @param quantities the quantities to set
     */
    public void setQuantities(List<String> quantities) {
        this.quantities = quantities;
    }

    /**
     * @return the attractions
     */
    public List<Attraction> getAllAttractions() {
        System.err.print("inside managedBean: getAllAttractions()");
        allAttractions = atmsb.getAllAttractions();
        return allAttractions;
    }

    /**
     * @param attractions the attractions to set
     */
    public void setAttractions(List<Attraction> allAttractions) {

        this.allAttractions = allAttractions;
    }


    public int retrieveActiveIndex(Attraction attraction){
        setActiveIndex(2);
         System.err.println("managedBean retrieveTicketOrders " + attraction.getName());
      //  System.err.println("curName "+curName);
        // allTheTicketOrders =  atmsb.getAllTheTicketOrders(attraction);
         
        if((curName==null)||(!(curName.equals(attraction.getName())))){
        if (getAllTheTicketOrders().isEmpty()) {
            System.out.println("size of " + getAllTheTicketOrders().size());
            Collection<TicketType> ts = attraction.getTicketTypes();
            for (TicketType tt : ts) {
                AttractionTicketOrder ato = new AttractionTicketOrder();
                ato.setAttraction(attraction);
                attraction.getAttractionTicketOrder().add(ato);
                ato.setType(tt.getName());
                float price = Float.parseFloat(tt.getPrice());
                ato.setPrice(price);
                // ato.setQuantity(0);    
                    getAllTheTicketOrders().add(ato);  //System.err.println("519 "+ allTheTicketOrders.size());
            }
        }
         curName = attraction.getName();
        }
        
        return getActiveIndex();
    }

    public List<AttractionTicketOrder> retrieveTicketOrders(Attraction attraction) {
        System.err.println("managedBean retrieveTicketOrders " + attraction.getName());
      //  System.err.println("curName "+curName);
        // allTheTicketOrders =  atmsb.getAllTheTicketOrders(attraction);
        if((curName==null)||(!(curName.equals(attraction.getName())))){
        if (getAllTheTicketOrders().isEmpty()) {
            System.out.println("size of " + getAllTheTicketOrders().size());
            Collection<TicketType> ts = attraction.getTicketTypes();
            for (TicketType tt : ts) {
                AttractionTicketOrder ato = new AttractionTicketOrder();
                ato.setAttraction(attraction);
                attraction.getAttractionTicketOrder().add(ato);
                ato.setType(tt.getName());
                float price = Float.parseFloat(tt.getPrice());
                ato.setPrice(price);
                // ato.setQuantity(0);    
                    getAllTheTicketOrders().add(ato);  //System.err.println("519 "+ allTheTicketOrders.size());
            }
        }
         curName = attraction.getName();
        }
       
        // System.err.println("521 "+allTheTicketOrders.size());
        return getAllTheTicketOrders();
    }

    /**
     * @return the disneyTicketOrders
     */
    public List<AttractionTicketOrder> retrieveDisneyTicketOrders() {
        System.err.println("managedBean retrieveDisneyTicketOrders ");
        disneyTicketOrders = atmsb.getDisneyTicketOrders();


        return disneyTicketOrders;
    }

    public List<AttractionTicketOrder> getDisneyTicketOrders() {
        System.err.println("managedBean getDisneyTicketOrders ");
        disneyTicketOrders = atmsb.getDisneyTicketOrders();
        return disneyTicketOrders;
    }

    /**
     * @param disneyTicketOrders the disneyTicketOrders to set
     */
    public void setDisneyTicketOrders(List<AttractionTicketOrder> disneyTicketOrders) {
        this.disneyTicketOrders = disneyTicketOrders;
    }

    /**
     * @return the quantityString
     */
    public String getQuantityString() {
        return quantityString;
    }

    /**
     * @param quantityString the quantityString to set
     */
    public void setQuantityString(String quantityString) {
        this.quantityString = quantityString;
    }

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return the allTheTicketOrders
     */
    public List<AttractionTicketOrder> getAllTheTicketOrders() {
        return allTheTicketOrders;
    }

    /**
     * @param allTheTicketOrders the allTheTicketOrders to set
     */
    public void setAllTheTicketOrders(List<AttractionTicketOrder> allTheTicketOrders) {
        this.allTheTicketOrders = allTheTicketOrders;
    }

    /**
     * @return the activeIndex
     */
    public int getActiveIndex() {
        return activeIndex;
    }

    /**
     * @param activeIndex the activeIndex to set
     */
    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    /**
     * @return the attractionTicketSales
     */
    public List<AttractionTicketSale> getAttractionTicketSales() {
        
        if(attractionTicketSales == null)
        {
            attractionTicketSales = new ArrayList<AttractionTicketSale>();            
            List<Attraction> attractions = atmsb.getAllAttractions();
            System.err.println("attractions "+attractions.size());
            for(Attraction attraction:attractions)
            {
                System.err.println("attraction: " + attraction.getName());
                AttractionTicketSale attractionTicketSale = new AttractionTicketSale(attraction);
                
                for(TicketType ticketType:attraction.getTicketTypes())
                {
                    System.err.println("ticketType: " + ticketType.getName());
                      AttractionTicketOrder ato = new AttractionTicketOrder();
                     ato.setAttraction(attraction);
                      attraction.getAttractionTicketOrder().add(ato);
                     ato.setType(ticketType.getName());
                     float price = Float.parseFloat(ticketType.getPrice());
                     ato.setPrice(price);
                    attractionTicketSale.getAttractionTicketSaleEntries().add(new AttractionTicketSaleEntry(ticketType, ato));
                }
//                  {
//                    System.err.println("ticketType: " + ticketType.getName());
//                    attractionTicketSale.getAttractionTicketSaleEntries().add(new AttractionTicketSaleEntry(ticketType, new AttractionTicketOrder()));
//                }
                
                attractionTicketSales.add(attractionTicketSale);
            }
        }
        
        return attractionTicketSales;
    }

    /**
     * @param attractionTicketSales the attractionTicketSales to set
     */
    public void setAttractionTicketSales(List<AttractionTicketSale> attractionTicketSales) {
        this.attractionTicketSales = attractionTicketSales;
    }

    /**
     * @return the bundleOrders
     */
    public List<BundleOrder> retrieveBundleOrders() {
      //  System.err.println("getBundleOrders");
        bundleOrders = new ArrayList();
        allBundles = atmsb.getAllBundles(); //System.err.println("allBundles.size: "+allBundles.size());
        for(Bundle bundle:allBundles){
            BundleOrder bo = new BundleOrder(); 
           bo.setBunlde(bundle); 
           float price = Float.parseFloat(bundle.getPrice()); 
           bo.setPrice(price); 
           bo.setQuantity(0);         
           bundleOrders.add(bo);        
        }
      //  System.err.println("bundleOrders "+bundleOrders.size());
        return bundleOrders;
    }
    
    public List<BundleOrder> retrievenNBundleOrders(){
    //    System.err.println("retrievenNBundleOrders");
        List<BundleOrder> bos = this.retrieveBundleOrders();
        List<BundleOrder> nBundleOrders = new ArrayList();
        for(BundleOrder bo:bos){
            String name = bo.getBunlde().getName();
            String[] splited = name.split(" ");
            String type = splited[splited.length-1];
            if(type.equals("Bundle"))
                nBundleOrders.add(bo);
        }
        return nBundleOrders;
    }
    
    
    public List<BundleOrder> retrievenEBundleOrders(Attraction attraction){
    //    System.err.println("retrievenEBundleOrders");
       // System.err.println("attraction "+attraction);
        List<BundleOrder> bos = this.retrieveBundleOrders();
        List<BundleOrder> eBundleOrders = new ArrayList();
        for(BundleOrder bo:bos){
            boolean sameA = true;
            boolean express = false;
            Collection<PkgTicket> pts =bo.getBunlde().getPkgTickets(); 
           
            for(PkgTicket pt:pts){
             //   System.err.println("attraction "+pt.getTicketType().getAttraction());
                String nowName = pt.getTicketType().getAttraction().getName();
                if(!(nowName.equals(attraction.getName()))){
                    sameA=false;}
            }   //System.err.println("sameA "+sameA );
             for(PkgTicket pt:pts){
                // System.err.println("name： "+pt.getTicketType().getName());
                if(pt.getTicketType().getName().equals("Express Pass"))
                    express=true;
            }     // System.err.println("express "+express );
            if((sameA==true) && (express==true))
                eBundleOrders.add(bo);
        }
        return eBundleOrders;
    }
    public List<BundleOrder> getBundleOrders() {
//        System.err.println("getBundleOrders");
//        allBundles = atmsb.getAllBundles(); System.err.println("allBundles.size: "+allBundles.size());
//        for(Bundle bundle:allBundles){
//            BundleOrder bo = new BundleOrder();
//           bo.setBunlde(bundle);
//           float price = Float.parseFloat(bundle.getPrice());
//           bo.setPrice(price);
//           bo.setQuantity(0);
//           bundleOrders.add(bo);
//        }
//        System.err.println("bundleOrders "+bundleOrders.size());
        return bundleOrders;
    }

    /**
     * @param bundleOrders the bundleOrders to set
     */
    public void setBundleOrders(List<BundleOrder> bundleOrders) {
        this.bundleOrders = bundleOrders;
    }

    /**
     * @return the nbundleOrders
     */
    public List<BundleOrder> getNbundleOrders() {
        return nbundleOrders;
    }

    /**
     * @param nbundleOrders the nbundleOrders to set
     */
    public void setNbundleOrders(List<BundleOrder> nbundleOrders) {
        this.nbundleOrders = nbundleOrders;
    }

    /**
     * @return the ebundleOrders
     */
    public List<BundleOrder> getEbundleOrders() {
        return ebundleOrders;
    }

    /**
     * @param ebundleOrders the ebundleOrders to set
     */
    public void setEbundleOrders(List<BundleOrder> ebundleOrders) {
        this.ebundleOrders = ebundleOrders;
    }

    /**
     * @return the bundleOrders1
     */
    public List<BundleOrder> getBundleOrders1() {
        return bundleOrders1;
    }

    /**
     * @param bundleOrders1 the bundleOrders1 to set
     */
    public void setBundleOrders1(List<BundleOrder> bundleOrders1) {
        this.bundleOrders1 = bundleOrders1;
    }

    /**
     * @return the ebundleOrders2
     */
    public List<BundleOrder> getbundleOrders2() {
        return bundleOrders2;
    }

    /**
     * @param ebundleOrders2 the ebundleOrders2 to set
     */
    public void setEbundleOrders2(List<BundleOrder> bundleOrders2) {
        this.bundleOrders2 = bundleOrders2;
    }

    /**
     * @return the date
     */
    public Date getTicketDate() {
        return ticketDate;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    /**
     * @return the date1
     */
    public Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the stringDate
     */
    public String getStringDate() {
        return stringDate;
    }

    /**
     * @param stringDate the stringDate to set
     */
    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the purchasedNum
     */
    public int getPurchasedNum() {
          try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          Date now = sdf.parse(sdf.format(new Date()));
           Collection<AvailableTicket> ats = theTicketType.getAvailableTickets();
           AvailableTicket availableTicket = new AvailableTicket();
           for (AvailableTicket at : ats) {
                        if (at.gettDate().equals(now)) {
                            availableTicket = at;
                             return availableTicket.getPurchasedNum();
                        }
                    }
          
        } catch (ParseException ex) {
            Logger.getLogger(AttractionTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return purchasedNum;
    }

    /**
     * @param purchasedNum the purchasedNum to set
     */
    public void setPurchasedNum(int purchasedNum) {
        this.purchasedNum = purchasedNum;
    }


}
