/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.Attraction;
import Attraction.entity.Bundle;
import Attraction.entity.PkgTicket;
import Attraction.entity.AttractionTicket;
import Attraction.entity.AttractionTicketOrder;
import Attraction.entity.BundleOrder;
import Attraction.entity.TicketType;
import Attraction.session.AttraTicketManagementSessionBeanLocal;
import CRM.entity.MemberAccount;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author zsy
 */
@ManagedBean
@ViewScoped
public class ExTicketManagedBean implements Serializable{

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
    private List<ExAttractionTicketSale> attractionTicketSales;
    private String stringDate;
    @EJB
    private AttraTicketManagementSessionBeanLocal atmsb;

    /**
     * Creates a new instance of ExTicketManagedBean
     */
    public ExTicketManagedBean() {
    }

    public void addTicketType() {
        System.err.println("inside AttractionTicketManagedBean addTicketType()");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            //Attraction attraction = em.find(Attraction.class, attractionName);
            getAtmsb().addTicketType(getTicketType(), getAttractionName());
            String statusMessage = "New Ticket Type Created Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
            this.resetTicketType(getTicketType());

        } catch (Exception ex) {
        }
    }

    public void test(ActionEvent event) {
        System.err.println("managedBean: only testing!");
    }

    public void resetTicketType(TicketType ticketType) {
        System.err.println("inside AttractionTicketManagedBean: resetTicketType()");
        ticketType.setName(null);
        ticketType.setPrice(null);
        ticketType.setMaxNumber(null);
        setAttractionName("");

    }

    public void getTheTicketType(ActionEvent event) {
        System.err.println("inside managedBean getTheTicketType()");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Long ticketTypeId = Long.parseLong(event.getComponent().getAttributes().get("ticketTypeId").toString());
        System.err.println("ticketTypeId: " + ticketTypeId);
        setTheTicketType(getAtmsb().findTicketType(ticketTypeId));
        System.err.println("theTicketType： " + getTheTicketType().getId());
        System.err.println("name: " + getTheTicketType().getName());

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("TicketTypeDetail.xhtml");
        } catch (Exception ex) {
        }
    }

    public void updateTicketType() {
        System.err.println("ManagedBean: updateTIcketType()");
        boolean result = getAtmsb().updateTicketType(getTheTicketType());
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
        boolean result = getAtmsb().updateBundle(getTheBundle());
        if (result == true) {
            String statusMessage = "Information Updated Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Information Update Failed";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }
    }

    public void deleteTicketType(ActionEvent event) {
        System.err.println("inside attractionTicketManagedBean: deleteTicketType() ");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Long ticketTypeId = Long.parseLong(event.getComponent().getAttributes().get("ticketTypeId").toString());
        if (getAtmsb().deleteTicketType(ticketTypeId)) {
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
        Long bundleId = getTheBundle().getId();
        if (getAtmsb().deleteBundle(bundleId)) {
            String statusMessage = "Bundle deleted from database Successfully";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        } else {
            String statusMessage = "Error: cannot delete bundle";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        }

    }

    public void createBundle() throws IOException {
        System.err.println("ManagedBean: inside createBundle()");
        System.err.println("selected ticketTypes list: " + getPkgTickets().size());
        if (getAtmsb().createBundle(getPkgTickets(), getBundleName(), getBundlePrice())) {

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
            Logger.getLogger(ExTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reloadBundle(ActionEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ViewAllBundle.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ExTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addPkgTicket(TicketType ticketType) {
        System.err.println("inside managedBean addTicketType()");
        //System.err.println("pkgTicket,ticketType number" + pkgTicket.getTicketNumber());
        int tquantity = Integer.parseInt(getQuantity());
        PkgTicket pt = new PkgTicket();
        pt.setTicketId(ticketType.getId());
        pt.setPrice(ticketType.getPrice());
        pt.setTicketNumber(tquantity);
        pt.setTicketType(ticketType);
        getPkgTickets().add(pt);

    }
    
       public float bookTickets1(ExAttractionTicketSale attractionTicketSale){
        List <BundleOrder> bundleOrders = new ArrayList();
        float totalPrice = 0;
        bundleOrders.addAll(bundleOrders1); bundleOrders.addAll(bundleOrders2);
          List<AttractionTicketOrder> ticketOrders = new ArrayList<AttractionTicketOrder>();
        for(ExAttractionTicketSaleEntry ats:attractionTicketSale.getExAttractionTicketSaleEntries()){
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
    public void bookTickets(ExAttractionTicketSale attractionTicketSale) throws ParseException{
        System.err.println("managedBean: bookTickets");
        System.err.println("managedBean: bookTickets " + attractionTicketSale.getAttraction().getName());
        System.err.println("1 BundleOrders: "+ bundleOrders1.size());
        System.err.println("2 BundleOrders: "+ bundleOrders2.size());
     
        System.err.println("339 ticket date: "+ticketDate);
        
        List <BundleOrder> bundleOrders = new ArrayList();
        bundleOrders.addAll(bundleOrders1); bundleOrders.addAll(bundleOrders2);
        System.err.println("ADD: BundleOrders: "+ bundleOrders.size());
        List<AttractionTicketOrder> ticketOrders = new ArrayList<AttractionTicketOrder>();
        for(ExAttractionTicketSaleEntry ats:attractionTicketSale.getExAttractionTicketSaleEntries()){
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


    public void createNewPkgTickets() {
        System.err.println("inside managedBean: creatNewPkgTickets");
        System.err.println("selectedTIcketTypes: " + getSelectedTicketTypes().size());
        for (TicketType tp : getSelectedTicketTypes()) {
            PkgTicket pt = new PkgTicket();
            pt.setTicketType(tp);
            pt.setTicketId(tp.getId());
            pt.setPrice(tp.getPrice());
            //all attr set except quantity
            getPkgTickets().add(pt); //pkgTickets-->bundle, only if quantity is modified for each

        }

        System.err.println("pkgTickets.size()" + getPkgTickets().size());
    }

    public void setListQuantities(TicketType ticketType, int quantity) {
        String ticketName = ticketType.getName();
        Long ticketId = ticketType.getId();
        for (PkgTicket pt : getPkgTickets()) {
            if (pt.getId() == ticketId) {
                pt.setTicketNumber(quantity);
            }

        }
    }

    public void computeOriTotal() {
        System.err.println("inside ManagedBean: computePriTotal");
        int total = 0;
        for (PkgTicket pt : getPkgTickets()) {
            int num = pt.getTicketNumber();
            int price = Integer.parseInt(pt.getPrice());
            total += num * price;

        }
        setOriginalTotalPrice(Integer.toString(total));

    }

    public void assignQuantity(AttractionTicketOrder to) {
        System.err.println("managedBean: haha");
        to.setQuantity(Integer.parseInt(getQuantityString()));
        setQuantityString("0");
        System.err.println("selected quantity: " + to.getQuantity());
        //this.sellTickets();
    }

    public void assignNBundleQuantity(BundleOrder bundleOrder) {
        System.err.println("assign N BundleQuantity");
        bundleOrder.setQuantity(Integer.parseInt(getQuantityString()));
        getBundleOrders1().add(bundleOrder);
        setQuantityString("0");
        System.err.println("selected N bundle quantity: " + bundleOrder.getQuantity() + " " + getBundleOrders1().size());
    }

    public void assignEBundleQuantity(BundleOrder bundleOrder) {
        System.err.println("assign E BundleQuantity");
        bundleOrder.setQuantity(Integer.parseInt(getQuantityString()));
        getBundleOrders2().add(bundleOrder);
        setQuantityString("0");
        System.err.println("selected E bundle quantity: " + bundleOrder.getQuantity() + " " + getBundleOrders2().size());
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
            System.err.println("Stringdate " + getStringDate());
            String[] str = getStringDate().split("/");
            System.err.println("1" + str[1]);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            setTicketDate(sdf.parse(getStringDate()));
            System.err.println("ticketDate " + getTicketDate());
        } catch (ParseException ex) {
            Logger.getLogger(ExTicketManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void bookTickets(ExAttractionTicketSale attractionTicketSale) throws ParseException {
//        System.err.println("managedBean: bookTickets");
//        System.err.println("managedBean: bookTickets " + attractionTicketSale.getAttraction().getName());
//        System.err.println("1 BundleOrders: " + getBundleOrders1().size());
//        System.err.println("2 BundleOrders: " + getBundleOrders2().size());
//
//        System.err.println("339 ticket date: " + getTicketDate());
//
//        List<BundleOrder> bundleOrders = new ArrayList();
//        bundleOrders.addAll(getBundleOrders1());
//        bundleOrders.addAll(getBundleOrders2());
//        System.err.println("ADD: BundleOrders: " + bundleOrders.size());
//        List<AttractionTicketOrder> ticketOrders = new ArrayList<AttractionTicketOrder>();
//        for (ExAttractionTicketSaleEntry ats : attractionTicketSale.getExAttractionTicketSaleEntries()) {
//            AttractionTicketOrder ato = ats.getAttractionTicketOrder();
//            if (!(ato.getQuantity() == 0)) {
//                ticketOrders.add(ato);
//            }
//        }
//        System.err.println("ticketOrders.size() " + ticketOrders.size());
//        if (getAtmsb().sellTickets(ticketOrders, bundleOrders, getTicketDate())) {
//            System.err.println("success, managedBean");
//            String statusMessage = "Ticket Sold Successfully !";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//
//        } else {
//            String statusMessage = "Error: not enough tickets left !";
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
//        }
//        bundleOrders = new ArrayList();
//        setBundleOrders1((List<BundleOrder>) new ArrayList());
//        setBundleOrders2((List<BundleOrder>) new ArrayList());
//        ticketOrders = new ArrayList();
//    }


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


    public int retrieveActiveIndex(Attraction attraction) {
        setActiveIndex(2);
        System.err.println("managedBean retrieveTicketOrders " + attraction.getName());
        //  System.err.println("curName "+curName);
        // allTheTicketOrders =  atmsb.getAllTheTicketOrders(attraction);

        if ((getCurName() == null) || (!(curName.equals(attraction.getName())))) {
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
            setCurName(attraction.getName());
        }

        return getActiveIndex();
    }

    public List<AttractionTicketOrder> retrieveTicketOrders(Attraction attraction) {
        System.err.println("managedBean retrieveTicketOrders " + attraction.getName());
        //  System.err.println("curName "+curName);
        // allTheTicketOrders =  atmsb.getAllTheTicketOrders(attraction);
        if ((getCurName() == null) || (!(curName.equals(attraction.getName())))) {
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
            setCurName(attraction.getName());
        }

        // System.err.println("521 "+allTheTicketOrders.size());
        return getAllTheTicketOrders();
    }

    /**
     * @return the disneyTicketOrders
     */
    public List<AttractionTicketOrder> retrieveDisneyTicketOrders() {
        System.err.println("managedBean retrieveDisneyTicketOrders ");
        setDisneyTicketOrders(getAtmsb().getDisneyTicketOrders());


        return getDisneyTicketOrders();
    }

    public List<AttractionTicketOrder> getDisneyTicketOrders() {
        System.err.println("managedBean getDisneyTicketOrders ");
        disneyTicketOrders = getAtmsb().getDisneyTicketOrders();
        return disneyTicketOrders;
    }

    public List<ExAttractionTicketSale> getAttractionTicketSales() {

        if (attractionTicketSales == null) {
            attractionTicketSales = new ArrayList<ExAttractionTicketSale>();
            List<Attraction> attractions = getAtmsb().getAllAttractions();
            System.err.println("attractions " + attractions.size());
            for (Attraction attraction : attractions) {
                System.err.println("attraction: " + attraction.getName());
                ExAttractionTicketSale attractionTicketSale = new ExAttractionTicketSale(attraction);

                for (TicketType ticketType: attraction.getTicketTypes()) {
                    System.err.println("ticketType: " + ticketType.getName());
                    AttractionTicketOrder ato = new AttractionTicketOrder();
                    ato.setAttraction(attraction);
                    attraction.getAttractionTicketOrder().add(ato);
                    ato.setType(ticketType.getName());
                    float price = Float.parseFloat(ticketType.getPrice());
                    ato.setPrice(price); System.err.println("468 "+ ato.getAttraction().getName());
                    attractionTicketSale.getExAttractionTicketSaleEntries(); System.err.println("469"+ attractionTicketSale.getExAttractionTicketSaleEntries());
                    ExAttractionTicketSaleEntry ats = new ExAttractionTicketSaleEntry(ticketType, ato); System.err.println("470 "+ats);
                    attractionTicketSale.getExAttractionTicketSaleEntries().add(new ExAttractionTicketSaleEntry(ticketType, ato));
                }
                  {
                    System.err.println("ticketType: " + ticketType.getName());
                    attractionTicketSale.getExAttractionTicketSaleEntries().add(new ExAttractionTicketSaleEntry(ticketType, new AttractionTicketOrder()));
                }

                attractionTicketSales.add(attractionTicketSale);
            }
        }

        return attractionTicketSales;
    }

    public List<BundleOrder> retrieveBundleOrders() {
        //  System.err.println("getBundleOrders");
        setBundleOrders((List<BundleOrder>) new ArrayList());
        setAllBundles(getAtmsb().getAllBundles()); //System.err.println("allBundles.size: "+allBundles.size());
        for (Bundle bundle : getAllBundles()) {
            BundleOrder bo = new BundleOrder();
            bo.setBunlde(bundle);
            float price = Float.parseFloat(bundle.getPrice());
            bo.setPrice(price);
            bo.setQuantity(0);
            getBundleOrders().add(bo);
        }
        //  System.err.println("bundleOrders "+bundleOrders.size());
        return getBundleOrders();
    }

    public List<BundleOrder> retrievenNBundleOrders() {
        //    System.err.println("retrievenNBundleOrders");
        List<BundleOrder> bos = this.retrieveBundleOrders();
        List<BundleOrder> nBundleOrders = new ArrayList();
        for (BundleOrder bo : bos) {
            String name = bo.getBunlde().getName();
            String[] splited = name.split(" ");
            String type = splited[splited.length - 1];
            if (type.equals("Bundle")) {
                nBundleOrders.add(bo);
            }
        }
        return nBundleOrders;
    }

    public List<BundleOrder> retrievenEBundleOrders(Attraction attraction) {
        //    System.err.println("retrievenEBundleOrders");
        // System.err.println("attraction "+attraction);
        List<BundleOrder> bos = this.retrieveBundleOrders();
        List<BundleOrder> eBundleOrders = new ArrayList();
        for (BundleOrder bo : bos) {
            boolean sameA = true;
            boolean express = false;
            Collection<PkgTicket> pts = bo.getBunlde().getPkgTickets();

            for (PkgTicket pt : pts) {
                //   System.err.println("attraction "+pt.getTicketType().getAttraction());
                String nowName = pt.getTicketType().getAttraction().getName();
                if (!(nowName.equals(attraction.getName()))) {
                    sameA = false;
                }
            }   //System.err.println("sameA "+sameA );
            for (PkgTicket pt : pts) {
                // System.err.println("name： "+pt.getTicketType().getName());
                if (pt.getTicketType().getName().equals("Express Pass")) {
                    express = true;
                }
            }     // System.err.println("express "+express );
            if ((sameA == true) && (express == true)) {
                eBundleOrders.add(bo);
            }
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
     * @return the ticketType
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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
        this.theTicketType = theTicketType;
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
        return allTicketTypes;
    }

    /**
     * @param allTicketTypes the allTicketTypes to set
     */
    public void setAllTicketTypes(List<TicketType> allTicketTypes) {
        this.allTicketTypes = allTicketTypes;
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
     * @return the allAttractions
     */
    public List<Attraction> getAllAttractions() {
        return allAttractions;
    }

    /**
     * @param allAttractions the allAttractions to set
     */
    public void setAllAttractions(List<Attraction> allAttractions) {
        this.allAttractions = allAttractions;
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
     * @param originalTotalPrice the originalTotalPrice to set
     */
    public void setOriginalTotalPrice(String originalTotalPrice) {
        this.originalTotalPrice = originalTotalPrice;
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
     * @return the allBundles
     */
    public List<Bundle> getAllBundles() {
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
     * @param disneyTicketOrders the disneyTicketOrders to set
     */
    public void setDisneyTicketOrders(List<AttractionTicketOrder> disneyTicketOrders) {
        this.disneyTicketOrders = disneyTicketOrders;
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
     * @return the bundleOrders2
     */
    public List<BundleOrder> getBundleOrders2() {
        return bundleOrders2;
    }

    /**
     * @param bundleOrders2 the bundleOrders2 to set
     */
    public void setBundleOrders2(List<BundleOrder> bundleOrders2) {
        this.bundleOrders2 = bundleOrders2;
    }

    /**
     * @return the ticketDate
     */
    public Date getTicketDate() {
        return ticketDate;
    }

    /**
     * @param ticketDate the ticketDate to set
     */
    public void setTicketDate(Date ticketDate) {
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
     * @param attractionTicketSales the attractionTicketSales to set
     */
    public void setAttractionTicketSales(List<ExAttractionTicketSale> attractionTicketSales) {
        this.attractionTicketSales = attractionTicketSales;
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
     * @return the atmsb
     */
    public AttraTicketManagementSessionBeanLocal getAtmsb() {
        return atmsb;
    }

    /**
     * @param atmsb the atmsb to set
     */
    public void setAtmsb(AttraTicketManagementSessionBeanLocal atmsb) {
        this.atmsb = atmsb;
    }
    
}
