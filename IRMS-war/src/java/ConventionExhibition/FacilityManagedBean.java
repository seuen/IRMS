/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.Facility;
import ConventionExhibition.session.FacilityManagementSessionBeanLocal;
import Accommodation.session.HotelManagementSessionBeanLocal;
import ConventionExhibition.entity.FacilityType;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class FacilityManagedBean implements Serializable {

    @EJB
    private FacilityManagementSessionBeanLocal fmsbl;
    @EJB
    private HotelManagementSessionBeanLocal hmsbl;
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();
    private FacilityType facilityType=new FacilityType();
    private String location;
    private Facility facility = new Facility();
    private int quantity;
    private List<FacilityType> AllfacilityTypes=new ArrayList();
    private List<Facility> Allfacilities = new ArrayList();
    private List<Facility> selectfacilities=new ArrayList();

    private FacilityType selectfacilityType=new FacilityType();
    
    
    private String hotelId = "Singland Hotel";
    private Long facilityId;
    private String detail;
    private String status="Available";

    /**
     * Creates a new instance of FacilityManagedBean
     */
    public FacilityManagedBean() {
    }

    public void displayMessage(String message){
        FacesMessage msg=new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void addFacilityType(ActionEvent event) {
        System.err.println("inside add facility");
        if (getFacilityType().getQuantity() == 0) {
           this.displayMessage("Quantity cannot be zero!");
        } else {
            getFmsbl().addFacilityType(getFacilityType(), getLocation());
            this.displayMessage("Successfully add "+getFacilityType().getQuantity()+" "+getFacilityType().getType());
            setFacilityType(new FacilityType());
            setLocation(null);
        }
    }
    
    public void addFacility(){
        if(getQuantity()==0){
            this.displayMessage("quantity cannot be zero!");
        }else{
            getFmsbl().addFacility(getFacility(), getQuantity());
            this.displayMessage(getQuantity()+" "+getFacility().getFacilityType().getType()+" have been added successfully!");
            setFacility(new Facility());
            setQuantity(0);
        }
    }
//
//    public void searchFacility() {
//        setFacility(getFmsbl().searchFacility(getFacilityId()));
//        if (getFacility() != null) {
//            FacesMessage msg = new FacesMessage("Facility is " + getFacility());
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
//            FacesMessage msg = new FacesMessage("Facility is " + getFacility());
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//        setFacilityId(null);
//        setFacility(new Facility());
//    }

    public void deleteFacility() throws IOException {
        int i=0;
        for(Facility temp: selectfacilities){
            if(!temp.getStatus().equals("Free")){
                i++;
                this.displayMessage("One or more facility is in use, Cannot Delete!");
            }
        }
        if(i==0){
        getFmsbl().deleteFacility(selectfacilities);
        selectfacilities=new ArrayList();
        }
    }
    
    public void returnFacility(){
        int i=0;
        for(Facility temp: selectfacilities){
            if(temp.getStatus().equals("Free")){
                i++;
                this.displayMessage("One or more facility is free. Do not need to return!");
            }
        }
        if(i==0){
            fmsbl.returnFacility(selectfacilities);
            selectfacilities=new ArrayList();
        }
    }

    
   
//    public void updateFacilityDetail() throws IOException {
//        if (getDetail() == null || getDetail().equals("")) {
//            FacesMessage msg = new FacesMessage("Detail requires input!");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        } else {
//            if (getFmsbl().updateFacilityDetail(getFacility(), getDetail())) {
//                System.err.println("Facility detail is updated");
//                setDetail(null);
//                setFacility(new Facility());
//                getEc().redirect("viewAllFacility.xhtml");
//            } else {
//                FacesMessage msg = new FacesMessage("Detail is not updated, please try again!");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//                setFacility(new Facility());
//            }
//        }
//    }
//
//    public void updateFacilityStatus() throws IOException{
//        if(getStatus()==null||getStatus().equals("")){
//            FacesMessage msg = new FacesMessage("Incorrect input!");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            setFacility(new Facility());
//        }
//        else{
//            if(getFmsbl().updateFacilityStatus(getFacility(), getStatus())){
//                setStatus("Available");
//                setFacility(new Facility());
//                getEc().redirect("viewAllFacility.xhtml");
//            }
//            else{
//                FacesMessage msg = new FacesMessage("Status is not updated, please try again!");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//                setFacility(new Facility());
//            }
//        }
//    }
//    
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
     * @return the facility
     */
    public Facility getFacility() {
        return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(Facility facility) {
        this.facility = facility;
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
     * @return the hotelId
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the facilityId
     */
    public Long getFacilityId() {
        return facilityId;
    }

    /**
     * @param facilityId the facilityId to set
     */
    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the facilityType
     */
    public FacilityType getFacilityType() {
        return facilityType;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    

    /**
     * @return the selectfacilityType
     */
    public FacilityType getSelectfacilityType() {
        return selectfacilityType;
    }

    /**
     * @param selectfacilityType the selectfacilityType to set
     */
    public void setSelectfacilityType(FacilityType selectfacilityType) {
        this.selectfacilityType = selectfacilityType;
    }

    /**
     * @return the AllfacilityTypes
     */
    public List<FacilityType> getAllfacilityTypes() {
        AllfacilityTypes=getFmsbl().getAllFacilityType();
        return AllfacilityTypes;
    }

    /**
     * @param AllfacilityTypes the AllfacilityTypes to set
     */
    public void setAllfacilityTypes(List<FacilityType> AllfacilityTypes) {
        this.AllfacilityTypes = AllfacilityTypes;
    }

    /**
     * @return the Allfacilities
     */
    public List<Facility> getAllfacilities() {
        Allfacilities=getFmsbl().getAllFacility();
        return Allfacilities;
    }

    /**
     * @param Allfacilities the Allfacilities to set
     */
    public void setAllfacilities(List<Facility> Allfacilities) {
        this.Allfacilities = Allfacilities;
    }

    /**
     * @return the selectfacilities
     */
    public List<Facility> getSelectfacilities() {
        return selectfacilities;
    }

    /**
     * @param selectfacilities the selectfacilities to set
     */
    public void setSelectfacilities(List<Facility> selectfacilities) {
        this.selectfacilities = selectfacilities;
    }
}
