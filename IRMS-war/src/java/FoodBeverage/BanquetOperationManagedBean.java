/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage;

import ConventionExhibition.entity.EmployeeNeed;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.Facility;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.FacilityType;
import ConventionExhibition.session.EventManagementSessionBeanLocal;
import ConventionExhibition.session.FacilityManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Acer
 */
@ManagedBean
@RequestScoped
public class BanquetOperationManagedBean {

     @EJB 
   private EventManagementSessionBeanLocal emsbl;
    @EJB 
    private FacilityManagementSessionBeanLocal fmsbl;
    
    
     private List<EventOrder> alleventorders=new ArrayList();
    private List<EventOrder> todayeventorders=new ArrayList();
    private EventOrder selecteventorder=new EventOrder();
    private EmployeeNeed selectemployee=new EmployeeNeed();
    private FacilityNeed selectfacility=new FacilityNeed();
    private List<Facility> onetypefacilities=new ArrayList();
    private List<Facility> selectedfacilities=new ArrayList();
    
    private List<FacilityNeed> updatedfacilities=new ArrayList();
    private List<String> selectfacilityTypenames=new ArrayList();
    private FacilityNeed updatefacility=new FacilityNeed();
    private float facilityprice=0;
    
     private EmployeeNeed updateemployee=new EmployeeNeed();
    private List<EmployeeNeed> updaeemployees=new ArrayList();
    private float employeeprice=0;
    public BanquetOperationManagedBean() {
    }
    
    
    
    public void finishAddEmployeeNeed() throws IOException{
         int i=0;
        setEmployeeprice(getEmployeeprice() + getUpdateemployee().getPrice());
        for(EmployeeNeed en: getUpdaeemployees()){
            if(en.getType().equals(getUpdateemployee().getType())){
                i++;
                en.setAmount(en.getAmount()+getUpdateemployee().getAmount());
                en.setPrice(en.getPrice()+getUpdateemployee().getPrice());
            }
        }
        if(i==0){
            getUpdaeemployees().add(getUpdateemployee());
        }
        setUpdateemployee(new EmployeeNeed());
        
        getEmsbl().updateeventemployee(getUpdaeemployees(), getSelecteventorder(), getEmployeeprice());
        
      
        setUpdaeemployees((List<EmployeeNeed>) new ArrayList());
        setEmployeeprice(0);
        FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetEventDetails.xhtml");
    }
    
     public void addoneemployeeneed() throws IOException{
        int i=0;
        setEmployeeprice(getEmployeeprice() + getUpdateemployee().getPrice());
        for(EmployeeNeed en: getUpdaeemployees()){
            if(en.getType().equals(getUpdateemployee().getType())){
                i++;
                en.setAmount(en.getAmount()+getUpdateemployee().getAmount());
                en.setPrice(en.getPrice()+getUpdateemployee().getPrice());
            }
        }
        if(i==0){
            getUpdaeemployees().add(getUpdateemployee());
        }
        setUpdateemployee(new EmployeeNeed());
        FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetUpdateEmployee.xhtml");
    }
    
     public void setfacilitneedPrice(){
        String type=getUpdatefacility().getType();
        getUpdatefacility().setPrice(getFmsbl().findfacilityType(type).getPrice());
    }

    public void displayMessage(String response){
        FacesMessage msg=new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    
    public void finishAddFacility() throws IOException{
     int i=0;
        float total=getUpdatefacility().getAmount()*getUpdatefacility().getPrice();
        getUpdatefacility().setTotalprice(total);
        setFacilityprice(getFacilityprice() + total);
        for(FacilityNeed fn: getUpdatedfacilities()){
            if(fn.getType().equals(getUpdatefacility().getType())){
                i++;
                fn.setAmount(fn.getAmount()+getUpdatefacility().getAmount());
                fn.setTotalprice(fn.getTotalprice()+getUpdatefacility().getTotalprice());
            }
        }
        if(i==0){
            getUpdatedfacilities().add(getUpdatefacility());
        }
        setUpdatefacility(new FacilityNeed());
        getEmsbl().updateevent(getUpdatedfacilities(),getSelecteventorder(), getFacilityprice());
        setUpdatedfacilities((List<FacilityNeed>) new ArrayList());
        setFacilityprice(0);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetEventDetails.xhtml");
}
    
    public void addonefacilityneed() throws IOException{
        int i=0;
        float total=getUpdatefacility().getAmount()*getUpdatefacility().getPrice();
        getUpdatefacility().setTotalprice(total);
        setFacilityprice(getFacilityprice() + total);
        for(FacilityNeed fn: getUpdatedfacilities()){
            if(fn.getType().equals(getUpdatefacility().getType())){
                i++;
                fn.setAmount(fn.getAmount()+getUpdatefacility().getAmount());
                fn.setTotalprice(fn.getTotalprice()+getUpdatefacility().getTotalprice());
            }
        }
        if(i==0){
            getUpdatedfacilities().add(getUpdatefacility());
        }
        setUpdatefacility(new FacilityNeed());
        FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetUpdateFacility.xhtml");
    }
    
    public void addeventfacility(){
        String status=getSelecteventorder().getVenuetype()+" "+getSelecteventorder().getRoomnum();
        int choosenum=getSelectedfacilities().size();
        int neednum=getSelectfacility().getAmount();
        if(choosenum<neednum){
            this.displayMessage("Need "+neednum+" facilities, Please choose enough");
        }else if(choosenum>neednum){
            this.displayMessage("Only Need "+neednum+" facilities.");
        }else{
            int i=0;
            for(Facility temp: getSelectedfacilities()){
                if(!temp.getStatus().equals("Free"))
                    i++;
            }
            if(i!=0){
                this.displayMessage("Chosen facilities are not available, please choose free facilities");
            }else{
                getFmsbl().updateeventfacility(getSelectfacility(), getSelectedfacilities(), status);
            this.displayMessage("Request Facilities Successfully");
            }
        }
    }
   
    
    public void cancelevent(){
        getEmsbl().cancelEvent(getSelecteventorder());
    }

    /**
     * @return the emsbl
     */
    public EventManagementSessionBeanLocal getEmsbl() {
        return emsbl;
    }

    /**
     * @param emsbl the emsbl to set
     */
    public void setEmsbl(EventManagementSessionBeanLocal emsbl) {
        this.emsbl = emsbl;
    }

    /**
     * @return the fmsbl
     */
    public FacilityManagementSessionBeanLocal getFmsbl() {
        return fmsbl;
    }

    /**
     * @param fmsbl the fmsbl to set
     */
    public void setFmsbl(FacilityManagementSessionBeanLocal fmsbl) {
        this.fmsbl = fmsbl;
    }

    /**
     * @return the alleventorders
     */
    public List<EventOrder> getAlleventorders() {
        alleventorders=getEmsbl().listBanqeutevents();
        return alleventorders;
    }

    /**
     * @param alleventorders the alleventorders to set
     */
    public void setAlleventorders(List<EventOrder> alleventorders) {
        this.alleventorders = alleventorders;
    }

    /**
     * @return the todayeventorders
     */
    public List<EventOrder> getTodayeventorders() {
         todayeventorders=getEmsbl().listBanquettodayevents();
        return todayeventorders;
    }

    /**
     * @param todayeventorders the todayeventorders to set
     */
    public void setTodayeventorders(List<EventOrder> todayeventorders) {
        this.todayeventorders = todayeventorders;
    }

    /**
     * @return the selecteventorder
     */
    public EventOrder getSelecteventorder() {
        return selecteventorder;
    }

    /**
     * @param selecteventorder the selecteventorder to set
     */
    public void setSelecteventorder(EventOrder selecteventorder) {
        this.selecteventorder = selecteventorder;
    }

    /**
     * @return the selectemployee
     */
    public EmployeeNeed getSelectemployee() {
        return selectemployee;
    }

    /**
     * @param selectemployee the selectemployee to set
     */
    public void setSelectemployee(EmployeeNeed selectemployee) {
        this.selectemployee = selectemployee;
    }

    /**
     * @return the selectfacility
     */
    public FacilityNeed getSelectfacility() {
        return selectfacility;
    }

    /**
     * @param selectfacility the selectfacility to set
     */
    public void setSelectfacility(FacilityNeed selectfacility) {
        this.selectfacility = selectfacility;
    }

    /**
     * @return the onetypefacilities
     */
    public List<Facility> getOnetypefacilities() {
        onetypefacilities=getFmsbl().getonetypefacility(getSelectfacility().getType());
        return onetypefacilities;
        
    }

    /**
     * @param onetypefacilities the onetypefacilities to set
     */
    public void setOnetypefacilities(List<Facility> onetypefacilities) {
        this.onetypefacilities = onetypefacilities;
    }

    /**
     * @return the selectedfacilities
     */
    public List<Facility> getSelectedfacilities() {
        return selectedfacilities;
    }

    /**
     * @param selectedfacilities the selectedfacilities to set
     */
    public void setSelectedfacilities(List<Facility> selectedfacilities) {
        this.selectedfacilities = selectedfacilities;
    }

    /**
     * @return the updatedfacilities
     */
    public List<FacilityNeed> getUpdatedfacilities() {
        return updatedfacilities;
    }

    /**
     * @param updatedfacilities the updatedfacilities to set
     */
    public void setUpdatedfacilities(List<FacilityNeed> updatedfacilities) {
        this.updatedfacilities = updatedfacilities;
    }

    /**
     * @return the selectfacilityTypenames
     */
    public List<String> getSelectfacilityTypenames() {
         selectfacilityTypenames=new ArrayList();
        List<FacilityType> fts=getFmsbl().getAllFacilityType();
        for(FacilityType currentft: fts){
            selectfacilityTypenames.add(currentft.getType());
        }
        return selectfacilityTypenames;
    }

    /**
     * @param selectfacilityTypenames the selectfacilityTypenames to set
     */
    public void setSelectfacilityTypenames(List<String> selectfacilityTypenames) {
        this.selectfacilityTypenames = selectfacilityTypenames;
    }

    /**
     * @return the updatefacility
     */
    public FacilityNeed getUpdatefacility() {
        return updatefacility;
    }

    /**
     * @param updatefacility the updatefacility to set
     */
    public void setUpdatefacility(FacilityNeed updatefacility) {
        this.updatefacility = updatefacility;
    }

    /**
     * @return the facilityprice
     */
    public float getFacilityprice() {
        return facilityprice;
    }

    /**
     * @param facilityprice the facilityprice to set
     */
    public void setFacilityprice(float facilityprice) {
        this.facilityprice = facilityprice;
    }

    /**
     * @return the updateemployee
     */
    public EmployeeNeed getUpdateemployee() {
        return updateemployee;
    }

    /**
     * @param updateemployee the updateemployee to set
     */
    public void setUpdateemployee(EmployeeNeed updateemployee) {
        this.updateemployee = updateemployee;
    }

    /**
     * @return the updaeemployees
     */
    public List<EmployeeNeed> getUpdaeemployees() {
        return updaeemployees;
    }

    /**
     * @param updaeemployees the updaeemployees to set
     */
    public void setUpdaeemployees(List<EmployeeNeed> updaeemployees) {
        this.updaeemployees = updaeemployees;
    }

    /**
     * @return the employeeprice
     */
    public float getEmployeeprice() {
        return employeeprice;
    }

    /**
     * @param employeeprice the employeeprice to set
     */
    public void setEmployeeprice(float employeeprice) {
        this.employeeprice = employeeprice;
    }
}
