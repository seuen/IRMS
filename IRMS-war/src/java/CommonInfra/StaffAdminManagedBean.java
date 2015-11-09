/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Staff;
import Common.entity.Title;
import Common.session.StaffManagementSessionBeanLocal;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.EmailManager;

/**
 *
 * @author yifeng
 */
@ManagedBean
@ViewScoped
public class StaffAdminManagedBean {

    /**
     * Creates a new instance of StaffAdminManagedBean
     */
    public StaffAdminManagedBean() {
        this.managedAreas = new String[]{"All Staff General", "All Staff Titles", "All Staff Accounts"};
    }
    @EJB
    StaffManagementSessionBeanLocal smsbl;
    //for create purpose
    private Staff staff = new Staff();
    private Title title = new Title();
    private String department;
    private boolean createAccount = false;
    private EmailManager emailManager = new EmailManager();
    //for display purpose, current staff
    private Staff curStaff;
    //for display purpose, all staff
    //private List<Staff> allStaff = new ArrayList();
    private List<String> workLocations = new ArrayList();
    private List<String> positions = new ArrayList();
    private List<String> departments = new ArrayList();
    private String[] managedAreas;
    private String subsystem;
    //utils
    private String managedAreaSelected;
    private boolean allStaffGeneral = true;
    private boolean allStaffTitles = false;
    private boolean allStaffAccount = false;
    private String displayMessage;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();

    @PostConstruct
    public void init() {
        curStaff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaff");
        if(curStaff != null){
            subsystem = curStaff.getTitles().get(0).getDepartment().getSubSystem();
        }
        //setAllStaff(smsbl.getAllStaff());
        setDepartments(smsbl.getDepartments(subsystem));
        workLocations = smsbl.getWorkLocations(departments.get(0), true);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), true);
    }

    public void saveNewStaff(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        System.err.print(this);
        String response = smsbl.addStaff(staff, isCreateAccount(), title.getWorkLocation() + title.getPosition());
        System.err.println(response);
        if (response != null) {
            String[] res = response.split("_");
            switch (res[0]) {
                case "Password":
                    displayMessage = "Email is being sent to the staff, please wait...";
                    System.out.println("account and password are " + res[1] + " " + res[2]);
                    emailManager.sendStaffPassword(staff.getEmail(), staff, title, res[1], res[2]);
                    this.displayFaceMessage("Staff account for " + staff.getFirstName() + " " + staff.getLastName() + "has been created successfully!\n Staff Account Id and password has been sent by email: " + staff.getEmail());
                    break;
                case "NoAccount":
                    displayMessage = "New staff is being created, please wait...";
                    this.displayFaceMessage("Staff account for " + staff.getFirstName() + " " + staff.getLastName() + "has been created successfully!");
                    break;
                default:
                    this.displayFaceMessage("invalid response from server!!!");
                    break;
            }

        } else {
            this.displayFaceMessage("no response from server!!!");
        }
        this.resetAll();
    }

    //utils
    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void retrieveDynamicPositionsForCIR(boolean normal) {
        System.err.println("inside getPosition for cir");

        System.out.println(title.getWorkLocation());
        setPositions(smsbl.getPositionsForSpeLocation(title.getWorkLocation(), normal));
    }

    public void retrieveDynamicWorkLocaitonsForCIR(boolean normal) {
        System.err.println("inside getworklocations");
        System.out.println("Departments get " + department);
        workLocations = smsbl.getWorkLocations(department, normal);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), normal);
    }

    private void resetAll() {
        staff = new Staff();
        createAccount = false;
        title = new Title();
    }

    public void updateSelectedAreas() throws IOException {

        //"All Staff General", "All Staff Titles", "All Staff Accounts"};
//        private boolean allStaffGeneral = true;
//    private boolean allStaffTitles = false;
//    private boolean allStaffAccount = false;
        System.out.println("Enter updateSelectedAreas, managedAreaSelected is " + managedAreaSelected);
        switch (managedAreaSelected) {
            case "All Staff General":
                allStaffGeneral = true;
                allStaffTitles = false;
                allStaffAccount = false;
                break;
            case "All Staff Titles":
                allStaffGeneral = false;
                allStaffTitles = true;
                allStaffAccount = false;
                break;
            case "All Staff Accounts":
                allStaffGeneral = false;
                allStaffTitles = false;
                allStaffAccount = true;
                break;
            default:
                break;
        }
        
        //ec.redirect("manageAllRoles.xhtml");

    }

    //Getter Setter fields
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(boolean createAccount) {
        this.createAccount = createAccount;
    }

//    public List<Staff> getAllStaff() {
//        return allStaff;
//    }
//
//    public void setAllStaff(List<Staff> allStaff) {
//        this.allStaff = allStaff;
//    }

    public List<String> getWorkLocations() {
        return workLocations;
    }

    public void setWorkLocations(List<String> workLocations) {
        this.workLocations = workLocations;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String[] getManagedAreas() {
        return managedAreas;
    }

    public void setManagedAreas(String[] managedAreas) {
        this.managedAreas = managedAreas;
    }

    public boolean isAllStaffGeneral() {
        return allStaffGeneral;
    }

    public void setAllStaffGeneral(boolean allStaffGeneral) {
        this.allStaffGeneral = allStaffGeneral;
    }

    public boolean isAllStaffTitles() {
        return allStaffTitles;
    }

    public void setAllStaffTitles(boolean allStaffTitles) {
        this.allStaffTitles = allStaffTitles;
    }

    public boolean isAllStaffAccount() {
        return allStaffAccount;
    }

    public void setAllStaffAccount(boolean allStaffAccount) {
        this.allStaffAccount = allStaffAccount;
    }

    public String getManagedAreaSelected() {
        return managedAreaSelected;
    }

    public void setManagedAreaSelected(String managedAreaSelected) {
        this.managedAreaSelected = managedAreaSelected;
    }
}
