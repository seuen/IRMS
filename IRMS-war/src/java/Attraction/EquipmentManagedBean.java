/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.AttraSection;
import Attraction.entity.Attraction;
import Attraction.entity.Equipment;
import Attraction.entity.Outlet;
import Attraction.entity.RetailOutlet;
import Attraction.session.EquipmentManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class EquipmentManagedBean {

    private Attraction attraction = new Attraction();
    private Attraction a = new Attraction();
    private Equipment equipment = new Equipment();
    private AttraSection attraSection = new AttraSection();
    private Outlet outlet = new Outlet();
    private RetailOutlet retailOutlet = new RetailOutlet();
    //attributes
    private String detail;
    private String address;
    private String openTime1;
    private String openTime2;
    private String closeTime1;
    private String closeTime2;
    private Date nextDate;
    //all list
    private List<Attraction> attractions = new ArrayList();
    private List<Equipment> equipments = new ArrayList();
    private List<AttraSection> attraSections = new ArrayList();
    private List<AttraSection> allAttraSections = new ArrayList();
    private List<Outlet> outlets = new ArrayList();
    private List<RetailOutlet> retailOutlets = new ArrayList();
    @EJB
    private EquipmentManagementSessionBeanLocal emsbl;
    private AttractionScheduleManagedBean asm = new AttractionScheduleManagedBean();
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of EquipmentManagedBean
     */
    public EquipmentManagedBean() {
    }

    public List<Attraction> viewAttractions() {
        setAttractions(getEmsbl().listAllAttractions());
        return getAttractions();
    }

    public void updateAttractionInfo(ActionEvent event) throws IOException {
        System.err.println("inside update managedbean");
        getA().setName(getAttraction().getName());
        getA().setAddress(getAddress());
        getA().setDetail(getDetail());
        getA().setOpenTime(getOpenTime1() + ":" + getOpenTime2());
        getA().setCloseTime(getCloseTime1() + ":" + getCloseTime2());

        if (getEmsbl().updateAttractionInfo(getA())) {
            System.err.println("success");
            System.out.println("update and redirect");
            getEc().redirect("ViewAllAttraction.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //view all equipments under one attraction
    public void viewEquipments() throws IOException {
        System.err.println("inside view equipments managedbean");
        equipments = emsbl.listAllEquipments(getAttraction().getName());

        if (getAttraction().getName() != null) {
            getEc().getFlash().put("equipments", getEquipments());
            getEc().getFlash().put("attraction", getAttraction());
            getEc().redirect("ViewAllEquipment.xhtml");
        }
    }

    //view all outlets under one attraction
    public void viewOutlets() throws IOException {
        System.err.println("inside view outlets managedbean");

        outlets = emsbl.listAllOutlets(attraction.getName());
        retailOutlets = emsbl.listAllRetailOutlets(attraction.getName());
        
        for(Outlet o: outlets){
            System.out.println(o);
        }
        
        for(RetailOutlet ro: retailOutlets){
            System.err.println(ro);
        }
        
        if (getAttraction().getName() != null) {
            getEc().getFlash().put("attraction", attraction);
            getEc().getFlash().put("outlets", outlets);
            getEc().getFlash().put("retailOutlets", retailOutlets);
            System.out.println("finish at managed bean");
            ec.redirect("ViewAllOutlet.xhtml");
        }
    }

    public List<Outlet> initViewOutlet() {
        System.err.println("inside intViewOutlet");
        attraction =(Attraction) ec.getFlash().get("attraction");
        outlets = (List<Outlet>) ec.getFlash().get("outlets");
        retailOutlets = (List<RetailOutlet>) getEc().getFlash().get("retailOutlets");
        getEc().getFlash().put("outlets", outlets);
        getEc().getFlash().put("retailOutlets", retailOutlets);
        getEc().getFlash().put("attraction", getAttraction());

        return outlets;
    }
    
    public List<RetailOutlet> initViewRetailOutlet() {
        attraction =(Attraction) getEc().getFlash().get("attraction");
        outlets = (List<Outlet>) getEc().getFlash().get("outlets");
        retailOutlets = (List<RetailOutlet>) getEc().getFlash().get("retailOutlets");
        getEc().getFlash().put("outlets", outlets);
        getEc().getFlash().put("retailOutlets", retailOutlets);
        getEc().getFlash().put("attraction", getAttraction());
        
        return retailOutlets;
    }

    //view all attraSections under one attraction
    public void viewAttraSections() throws IOException {
        System.err.println("inside view sections managedbean");
        System.out.println(getAttraction().getName());
        setAttraSections(getEmsbl().listAttraSections(getAttraction().getName()));

        if (getAttraction().getName() != null) {
            getEc().getFlash().put("attraSections", getAttraSections());
            getEc().getFlash().put("attraction", getAttraction());
            getEc().redirect("ViewAllAttraSection.xhtml");
        }
    }

    //view equipments under one attraSection
    public void ViewAttraSectionEquipments(AttraSection attraSection) throws IOException {
        System.err.println("inside view Sectionequipments managedbean");
        setEquipments(getEmsbl().listAllAttraSectionEquipments(attraSection));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        setAttraSections((List<AttraSection>) getEc().getFlash().get("attraSections"));

        if (getAttraction().getName() != null) {
            getEc().getFlash().put("equipments", getEquipments());
            getEc().getFlash().put("attraSections", getAttraSections());
            getEc().getFlash().put("attraction", getAttraction());
            getEc().redirect("ViewAllAttraSectionEquipment.xhtml");
        }
    }

    public void ViewAttraSectionEquipments1(AttraSection attraSection) throws IOException {
        setEquipments(getEmsbl().listAllAttraSectionEquipments(attraSection));
        setAllAttraSections((List<AttraSection>) getEc().getFlash().get("allAttraSections"));

        if (getAllAttraSections() != null) {
            getEc().getFlash().put("equipments", getEquipments());
        }
        getEc().getFlash().put("allAttraSections", getAllAttraSections());
        getEc().getFlash().put("attraSection", attraSection);
        getEc().redirect("ViewAllAttraSectionEquipments.xhtml");
    }

    public List<Equipment> initView1() {
        System.err.println("inside initeView1");
        setEquipments((List<Equipment>) getEc().getFlash().get("equipments"));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        getEc().getFlash().put("equipments", getEquipments());
        getEc().getFlash().put("attraction", getAttraction());
        return getEquipments();
    }

    public List<AttraSection> initView2() {
        setAttraSections((List<AttraSection>) getEc().getFlash().get("attraSections"));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        getEc().getFlash().put("attraSections", getAttraSections());
        getEc().getFlash().put("attraction", getAttraction());
        return getAttraSections();
    }

    public List<AttraSection> initViewSections() {
        setAllAttraSections((List<AttraSection>) getEc().getFlash().get("allAttraSections"));
        setAllAttraSections(getEmsbl().listAllAttraSections());
        getEc().getFlash().put("allAttraSections", getAllAttraSections());
        return getAllAttraSections();
    }

    public List<Equipment> initView3() {
        setEquipments((List<Equipment>) getEc().getFlash().get("equipments"));
        setAttraSection((AttraSection) getEc().getFlash().get("attraSection"));
        setAttraSections((List<AttraSection>) getEc().getFlash().get("attraSections"));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        getEc().getFlash().put("attraSections", getAttraSections());
        getEc().getFlash().put("equipments", getEquipments());
        getEc().getFlash().put("attraction", getAttraction());
        return getEquipments();
    }

    public void updateEquipmentDetail() throws IOException {
        System.err.println("inside updateDetail managedbean");
        System.out.println(getEquipment() + "'s detail is " + getDetail());
        getEquipment().setDetail(getDetail());

        if (getEmsbl().updateEquipment(getEquipment())) {
            getEc().redirect("ViewAllEquipment.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void updateEquipmentDate() throws IOException {
        getEquipment().setNextDate(nextDate);
        Date d = new Date();
        if (getEmsbl().updateEquipment(getEquipment()) && nextDate.after(d)) {
            getEc().redirect("ViewAllEquipment.xhtml");
        } else {
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void updateOutlet(Outlet outlet) throws IOException{
        System.err.println("inside update outlet and address is "+ outlet.getAddress());
        
        if(emsbl.updateOutlet(outlet)){
            ec.redirect("ViewAllOutlet.xhtml");
        }
        else{
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void updateRetailOutlet(RetailOutlet retailOutlet) throws IOException{
        
        if(emsbl.updateRetailOutlet(retailOutlet)){
            ec.redirect("ViewAllOutlet.xhtml");
        }
        else{
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void refresh1() throws IOException {
        setEquipments((List<Equipment>) getEc().getFlash().get("equipments"));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        getEc().getFlash().put("equipments", getEquipments());
        getEc().getFlash().put("attraction", getAttraction());
        getEc().redirect("ViewAllEquipment.xhtml");
    }

    public void refresh2() throws IOException {
        setAttraSections((List<AttraSection>) getEc().getFlash().get("attraSections"));
        setAllAttraSections((List<AttraSection>) getEc().getFlash().get("allAttraSections"));
        setAttraction((Attraction) getEc().getFlash().get("attraction"));
        getEc().getFlash().put("attraSections", getAttraSections());
        getEc().getFlash().put("allAttraSections", getAllAttraSections());
        getEc().getFlash().put("attraction", getAttraction());
        getEc().redirect("ViewAllAttraSection.xhtml");
    }

    public void maintainEquipment(Equipment equipment) throws IOException {
        System.err.println("inside maintain equipment managedbean");
        System.out.println(equipment);
        if (equipment != null) {
            if (getEmsbl().maintainEquipment(equipment)) {
                setEquipments((List<Equipment>) getEc().getFlash().get("equipments"));
                setAttraction((Attraction) getEc().getFlash().get("attraction"));
                getEc().getFlash().put("equipments", getEquipments());
                getEc().getFlash().put("attraction", getAttraction());
                getEc().redirect("ViewAllEquipment.xhtml");
            } else {
                FacesMessage msg = new FacesMessage("Fail to change status, please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void maintainSectionEquipment(AttraSection attraSection) throws IOException {
        System.err.println("inside maintain section equipment managedbean");
        System.out.println(attraSection);
        if (attraSection != null) {
            if (getEmsbl().maintainSectionEquipment(attraSection)) {
                getEc().getFlash().put("attraSections", getAttraSections());
                getEc().getFlash().put("attraction", getAttraction());
                getEc().redirect("ViewAllAttraSection.xhtml");
            } else {
                FacesMessage msg = new FacesMessage("Fail to change status, please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void activateEquipment(Equipment equipment) throws IOException {
        System.err.println("inside activate equipment managedbean");
        System.out.println(equipment);
        if (equipment != null) {
            if (getEmsbl().activateEquipment(equipment)) {
                setEquipments((List<Equipment>) getEc().getFlash().get("equipments"));
                setAttraction((Attraction) getEc().getFlash().get("attraction"));
                getEc().getFlash().put("equipments", getEquipments());
                getEc().getFlash().put("attraction", getAttraction());
                getEc().redirect("ViewAllEquipment.xhtml");
            } else {
                FacesMessage msg = new FacesMessage("Fail to change status, please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    /**
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the attractions
     */
    public List<Attraction> getAttractions() {
        return attractions;
    }

    /**
     * @param attractions the attractions to set
     */
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    /**
     * @return the openTime1
     */
    public String getOpenTime1() {
        return openTime1;
    }

    /**
     * @param openTime1 the openTime1 to set
     */
    public void setOpenTime1(String openTime1) {
        this.openTime1 = openTime1;
    }

    /**
     * @return the openTime2
     */
    public String getOpenTime2() {
        return openTime2;
    }

    /**
     * @param openTime2 the openTime2 to set
     */
    public void setOpenTime2(String openTime2) {
        this.openTime2 = openTime2;
    }

    /**
     * @return the closeTime1
     */
    public String getCloseTime1() {
        return closeTime1;
    }

    /**
     * @param closeTime1 the closeTime1 to set
     */
    public void setCloseTime1(String closeTime1) {
        this.closeTime1 = closeTime1;
    }

    /**
     * @return the closeTime2
     */
    public String getCloseTime2() {
        return closeTime2;
    }

    /**
     * @param closeTime2 the closeTime2 to set
     */
    public void setCloseTime2(String closeTime2) {
        this.closeTime2 = closeTime2;
    }

    /**
     * @return the a
     */
    public Attraction getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Attraction a) {
        this.a = a;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the emsbl
     */
    public EquipmentManagementSessionBeanLocal getEmsbl() {
        return emsbl;
    }

    /**
     * @param emsbl the emsbl to set
     */
    public void setEmsbl(EquipmentManagementSessionBeanLocal emsbl) {
        this.emsbl = emsbl;
    }

    /**
     * @return the equipments
     */
    public List<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * @param equipments the equipments to set
     */
    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    /**
     * @return the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * @return the attraSections
     */
    public List<AttraSection> getAttraSections() {
        return attraSections;
    }

    /**
     * @param attraSections the attraSections to set
     */
    public void setAttraSections(List<AttraSection> attraSections) {
        this.attraSections = attraSections;
    }

    /**
     * @return the attraSection
     */
    public AttraSection getAttraSection() {
        return attraSection;
    }

    /**
     * @param attraSection the attraSection to set
     */
    public void setAttraSection(AttraSection attraSection) {
        this.attraSection = attraSection;
    }

    /**
     * @return the nextDate
     */
    public Date getNextDate() {
        return nextDate;
    }

    /**
     * @param nextDate the nextDate to set
     */
    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    /**
     * @return the allAttraSections
     */
    public List<AttraSection> getAllAttraSections() {
        return allAttraSections;
    }

    /**
     * @param allAttraSections the allAttraSections to set
     */
    public void setAllAttraSections(List<AttraSection> allAttraSections) {
        this.allAttraSections = allAttraSections;
    }

    /**
     * @return the asm
     */
    public AttractionScheduleManagedBean getAsm() {
        return asm;
    }

    /**
     * @param asm the asm to set
     */
    public void setAsm(AttractionScheduleManagedBean asm) {
        this.asm = asm;
    }

    /**
     * @return the fc
     */
    public FacesContext getFc() {
        return fc;
    }

    /**
     * @param fc the fc to set
     */
    public void setFc(FacesContext fc) {
        this.fc = fc;
    }

    /**
     * @return the ec
     */
    public ExternalContext getEc() {
        return ec;
    }

    /**
     * @param ec the ec to set
     */
    public void setEc(ExternalContext ec) {
        this.ec = ec;
    }

    /**
     * @return the retailOutlet
     */
    public RetailOutlet getRetailOutlet() {
        return retailOutlet;
    }

    /**
     * @param retailOutlet the retailOutlet to set
     */
    public void setRetailOutlet(RetailOutlet retailOutlet) {
        this.retailOutlet = retailOutlet;
    }

    /**
     * @return the outlet
     */
    public Outlet getOutlet() {
        return outlet;
    }

    /**
     * @param outlet the outlet to set
     */
    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }
}
