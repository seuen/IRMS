/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.ConventionSchedule;
import ConventionExhibition.entity.EmployeeNeed;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.entity.Facility;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.FacilityType;
import ConventionExhibition.entity.HourTime;
import ConventionExhibition.session.EventManagementSessionBeanLocal;
import ConventionExhibition.session.FacilityManagementSessionBeanLocal;
import FoodBeverage.entity.BanquetItemOrder;
import FoodBeverage.entity.MenuItem;
import FoodBeverage.session.MenuManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class EventOperationManagedBean {
 
    @EJB 
   private EventManagementSessionBeanLocal emsbl;
    @EJB 
    private FacilityManagementSessionBeanLocal fmsbl;
    @EJB
    private MenuManagementSessionBeanLocal mmsbl;
    
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
    
    private List<MenuItem> items=new ArrayList();
    private List<MenuItem> selectitems=new ArrayList();
    private EventOrder banquet =new EventOrder();
    private BanquetItemOrder banquetitem=new BanquetItemOrder();
    private List<String> menuitem=new ArrayList();
    private List<Integer> menuid=new ArrayList();
    private HourTime hour=new HourTime();
    private Date eventDate;
    private Date startTime;
    private Date endTime;
    private int creatclick=0;
    
    
    private List<EventOrder> banquets=new ArrayList();
    private EventOrder selectbanquet=new EventOrder();
    private List<String> menu=new ArrayList();
    
    
    private EmployeeNeed updateemployee=new EmployeeNeed();
    private List<EmployeeNeed> updaeemployees=new ArrayList();
    private float employeeprice=0;
    
    public EventOperationManagedBean() {
    }
    
    public void eventend(){
        selecteventorder.setStatus("After");
        emsbl.eventend(selecteventorder);
        this.displayMessage("The Event has Ended");
    }
    
    public void getbanquetmenu() throws IOException{
        menu=selectbanquet.getItemorder().getMenunames();
        FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetMenuInfo.xhtml");
    }
    
    
    public void geteventbanquets() throws IOException{
        Long evenid=selecteventorder.getId();
        banquets=emsbl.geteventbanquets(evenid);
        FacesContext.getCurrentInstance().getExternalContext().redirect("EventBanquetInfo.xhtml");
    }
    
    public void sethourdaytime() {
        Calendar ca = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        ca.setTime(getEventDate());
        cal.setTime(getStartTime());
        cal.set(Calendar.YEAR, ca.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, ca.get(Calendar.MONTH));
        cal.set(Calendar.DATE, ca.get(Calendar.DATE));
        setStartTime(cal.getTime());
        System.err.println("the startitme is " + getStartTime());
        cal.setTime(getEndTime());
        cal.set(Calendar.YEAR, ca.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, ca.get(Calendar.MONTH));
        cal.set(Calendar.DATE, ca.get(Calendar.DATE));
        setEndTime(cal.getTime());
        System.err.println("the endtime is " + getEndTime());
    }
    
    public void setBanquetInfo() throws IOException{
         creatclick=0;
         setBanquet(new EventOrder());
         getBanquet().setEventorderid(getSelecteventorder().getId());
         getBanquet().setVenuetype(getSelecteventorder().getVenuetype());
         getBanquet().setRoomnum(getSelecteventorder().getRoomnum());
         getBanquet().setClient(getSelecteventorder().getClient());
         FacesContext.getCurrentInstance().getExternalContext().redirect("EventSelectItem.xhtml");

    }
    
     public void setBanquetMenu() throws IOException{
        
         
         getBanquet().setAuditorium(null);
         getBanquet().setExhibitionsections(null);
         getBanquet().setOpenspace(null);
         getBanquet().setOthervenue(null);
         getBanquet().setBanquethall(null);
         getBanquet().setTheater(null);
         getBanquet().setCreateDate(new Date());
         getBanquet().setDaytime(null);
         getBanquet().setHalfdaytime(null);
         getBanquet().setEmployeeprice(0);
         getBanquet().setEmployeesNeed(null);
         getBanquet().setFacilitiesNeed(null);
         getBanquet().setFacilityprice(0);
         banquet.setVenueprice(0);
         float totalprice=0;
         int amount=getBanquetitem().getQuantity();
         System.err.println("the menu amount is "+amount);
         List<String> itemnames=new ArrayList();
         List<Long> itemid=new ArrayList();
         for(MenuItem item:getSelectitems()){
             itemnames.add(item.getName());
             itemid.add(item.getId());
             totalprice+=item.getPrice();
             System.err.println("the totalprice is "+totalprice);
         }
         getBanquetitem().setMenuids(itemid);
         getBanquetitem().setMenunames(itemnames);
         getBanquetitem().setOrderDate(new Date());
         getBanquetitem().setTotalprice(totalprice*amount);
         getBanquet().setItemorder(getBanquetitem());
         getBanquet().setFoodprice(totalprice*amount);
         getBanquet().setTotalprice(totalprice*amount);
         getBanquet().setTotalcharge(totalprice*amount);
         FacesContext.getCurrentInstance().getExternalContext().redirect("BanquetSelectTime.xhtml");
     }
     
     public void createHourTime() {
        this.sethourdaytime();
        hour = new HourTime();
        hour.setEventDate(eventDate);
        hour.setStartTime(startTime);
        hour.setEndTime(endTime);
    }
     
     public void createbanquet(){
         if(creatclick==0){
         this.createHourTime();
         banquet.setHourtime(hour);
         banquet.setType("Banquet");
         banquet.setStatus("Before");
         emsbl.createEventBanquet(banquet);
         creatclick++;
         banquet=new EventOrder();
         this.displayMessage("Banquet has been created successfully");
         }else{
             this.displayMessage("Banquet has beed added");
         }
        
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
        FacesContext.getCurrentInstance().getExternalContext().redirect("EventDetails.xhtml");
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
        FacesContext.getCurrentInstance().getExternalContext().redirect("UpdateEmployee.xhtml");
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
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("EventDetails.xhtml");
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
        FacesContext.getCurrentInstance().getExternalContext().redirect("UpdateFacility.xhtml");
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
     * @return the alleventorders
     */
    public List<EventOrder> getAlleventorders() {
        alleventorders=getEmsbl().listCEevents();
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
        todayeventorders=getEmsbl().listCEtodayevents();
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

    /**
     * @return the banquet
     */
    public EventOrder getBanquet() {
        return banquet;
    }

    /**
     * @param banquet the banquet to set
     */
    public void setBanquet(EventOrder banquet) {
        this.banquet = banquet;
    }

    /**
     * @return the banquetitem
     */
    public BanquetItemOrder getBanquetitem() {
        return banquetitem;
    }

    /**
     * @param banquetitem the banquetitem to set
     */
    public void setBanquetitem(BanquetItemOrder banquetitem) {
        this.banquetitem = banquetitem;
    }

    /**
     * @return the menuitem
     */
    public List<String> getMenuitem() {
        return menuitem;
    }

    /**
     * @param menuitem the menuitem to set
     */
    public void setMenuitem(List<String> menuitem) {
        this.menuitem = menuitem;
    }

    /**
     * @return the menuid
     */
    public List<Integer> getMenuid() {
        return menuid;
    }

    /**
     * @param menuid the menuid to set
     */
    public void setMenuid(List<Integer> menuid) {
        this.menuid = menuid;
    }

    /**
     * @return the items
     */
    public List<MenuItem> getItems() {
        items=getMmsbl().listAllMenuItem();
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    /**
     * @return the selectitems
     */
    public List<MenuItem> getSelectitems() {
        return selectitems;
    }

    /**
     * @param selectitems the selectitems to set
     */
    public void setSelectitems(List<MenuItem> selectitems) {
        this.selectitems = selectitems;
    }

    /**
     * @return the hour
     */
    public HourTime getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(HourTime hour) {
        this.hour = hour;
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
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the creatclick
     */
    public int getCreatclick() {
        return creatclick;
    }

    /**
     * @param creatclick the creatclick to set
     */
    public void setCreatclick(int creatclick) {
        this.creatclick = creatclick;
    }

    /**
     * @return the banquets
     */
    public List<EventOrder> getBanquets() {
        return banquets;
    }

    /**
     * @param banquets the banquets to set
     */
    public void setBanquets(List<EventOrder> banquets) {
        this.banquets = banquets;
    }

    /**
     * @return the selectbanquet
     */
    public EventOrder getSelectbanquet() {
        return selectbanquet;
    }

    /**
     * @param selectbanquet the selectbanquet to set
     */
    public void setSelectbanquet(EventOrder selectbanquet) {
        this.selectbanquet = selectbanquet;
    }

    /**
     * @return the menu
     */
    public List<String> getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(List<String> menu) {
        this.menu = menu;
    }


   
    
}
