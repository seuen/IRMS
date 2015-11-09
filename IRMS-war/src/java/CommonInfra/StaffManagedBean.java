/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Shift;
import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Task;
import Common.entity.Title;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import Common.session.StaffManagementSessionBeanLocal;
import Common.session.StaffScheduleSessionBeanLocal;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import util.EmailManager;

/**
 *
 * @author yifeng
 */
@ManagedBean
@SessionScoped
public class StaffManagedBean {

    @EJB
    StaffManagementSessionBeanLocal smsbl;
    @EJB
    StaffScheduleSessionBeanLocal sssbl;
    //selected value
    //current staff    
    private Staff curStaff;
    private List<String> curStaffTitles = new ArrayList();
    private List<Title> currentStaffTitles = new ArrayList();
    private List<Task> staffTasks = new ArrayList();
    private Task newTask = new Task();
    private String titleSelected;
    private String newTaskStatus;
    private String[] staffTaskStatus = {"unTouched", "done", "doing"};
    private SelectItem[] staffTaskOptions = new SelectItem[staffTaskStatus.length + 1];
    private String workSpaceURL;
    private String subsystem;
    //managed staff
    //private List<Staff> allStaff = new ArrayList();
    private Staff staff = new Staff();
    private Title title = new Title();
    private Title extraTitle = new Title();
    private List<Title> titles = new ArrayList();
    private List<StaffAccount> staffAccounts = new ArrayList();
    private List<StaffAccount> staffAccountsWithTitle = new ArrayList();
    private Long tempStaffId;
    private boolean createAccount = false;
    private String welcome = "Hello, Have a good day at work. :)";
    private EmailManager emailManager = new EmailManager();
    private String department;
    private List<String> workLocations = new ArrayList();
    private List<String> positions = new ArrayList();
    private List<String> departments = new ArrayList();
    private ScheduleModel eventModel;
    private ScheduleEvent event;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    //newly added in
    private List<Staff> staffWithTitle = new ArrayList();
    //to be removed later
    private List<Title> allPrimaryTitles = new ArrayList<Title>();

    /**
     * Creates a new instance of StaffManagedBean
     */
    public StaffManagedBean() {
        eventModel = new DefaultScheduleModel();
        event = new DefaultScheduleEvent();
    }

    @PostConstruct
    public void init() {
        //allStaff = smsbl.getAllStaff();
        staffTaskOptions[0] = new SelectItem("", "Select One");
        for (int i = 0; i < staffTaskStatus.length; i++) {
            getStaffTaskOptions()[i + 1] = new SelectItem(staffTaskStatus[i], staffTaskStatus[i]);
        }

//        for (int i = 0; i < allStaff.size(); i++) {
//            allPrimaryTitles.add(allStaff.get(i).getTitles().get(0));
//        }

        System.err.println(allPrimaryTitles.size());

        curStaff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaff");
        if (curStaff != null) {
            System.out.println("current staff is " + curStaff.getFirstName() + curStaff.getLastName());
            welcome = "Hello, " + curStaff.getFirstName() + " " + curStaff.getLastName() + " Have a good day at work. : )";
            currentStaffTitles = smsbl.getCurStaffTitles(curStaff);
            staffTasks = curStaff.getStaffAccount().getTasks();
            subsystem = curStaff.getTitles().get(0).getDepartment().getSubSystem();
            System.out.println("subsystem is " + subsystem);
        }

        setDepartments(smsbl.getDepartments(subsystem));
        workLocations = smsbl.getWorkLocations(departments.get(0), true);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), true);
        //staffAccounts = smsbl.getAllStaffAccount();
    }

    public void addNewTask() {
        staffTasks = smsbl.addNewTaskToStaff(curStaff.getStaffAccount().getStaffId(), newTask);
        if (staffTasks == null) {
            this.displayFaceMessage("add new task failed");
        }
        
        newTask = new Task();
    }

    public void deleteTask(Task remTask) {
        staffTasks = smsbl.deleteTaskToStaff(curStaff.getStaffAccount().getStaffId(), remTask);
        if (staffTasks == null) {
            this.displayFaceMessage("task is not removed");
        }
    }

    public void updateTaskStatus(Task curTask) {
        System.out.println("change to " + newTaskStatus);
        staffTasks = smsbl.updateTaskStatus(curStaff.getStaffAccount().getStaffId(), curTask, newTaskStatus);
        this.displayFaceMessage("Task updated!");
    }

    public void backToLogin() throws IOException {
        System.out.println("direct back to login");
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String requestUrl = origRequest.getRequestURL().toString();
        String[] requestUrlBreak = requestUrl.split("/");

        if (requestUrlBreak.length == 7) {
            ec.redirect("../../index.xhtml");
        } else {
            ec.redirect("./../index.xhtml");
        }
    }

    public void deleteExtraTitle(Title extraTitle) {
        if (titles.size() == 1) {// if staff only has one title
            this.displayFaceMessage("a staff must at least has one title, deletion failed");
        } else {
            List<Title> response = smsbl.deleteExtraTitle(staff, extraTitle);
            if (response == null) {
                this.displayFaceMessage("staff relating to this title" + extraTitle.getPosition() + "is currently on duty, thus cannot be deleted yet");
            } else {
                titles = response;
            }
        }
    }

    public void resetStaffPassword(StaffAccount staffAccount) throws NoSuchAlgorithmException {
        String response = smsbl.resetStaffAccountPassword(staffAccount);
        String[] res = response.split("_");
        if (res[0].equals("Password")) {
            System.out.println("new username and password " + res[1] + res[2]);
            emailManager.sendStaffResetPassword(staffAccount.getStaff().getEmail(), staffAccount.getStaff(), res[1], res[2]);
            this.displayFaceMessage("new username and password has be sent to staff's email address " + staffAccount.getStaff().getEmail());
            System.out.println("done display message");
        } else {
            this.displayFaceMessage("invalid response from server");
        }

    }

    public void changeStaffAccountStatus(StaffAccount staffAccount) {
        smsbl.resetStaffAccountStatus(staffAccount);
        //this.retrieveAllStaffAccountFromDB();
        this.fetchStaffAccountGivenTitle();
    }

//    public void retrieveAllStaffAccountFromDB() {
//        staffAccounts = smsbl.getAllStaffAccount();
//    }

    public void retrieveDynamicPositionsForCIR(boolean normal) {
        System.err.println("inside getPosition for cir");

        System.out.println(title.getWorkLocation());
        setPositions(smsbl.getPositionsForSpeLocation(title.getWorkLocation(), normal));
        System.out.println("positions " + positions);
    }

    public void retrieveDynamicWorkLocaitonsForCIR(boolean normal) {
        System.err.println("inside getworklocations");
        System.out.println("Departments get " + department);
        workLocations = smsbl.getWorkLocations(department, normal);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), normal);
        System.out.println("get " + workLocations + positions);
    }

    //moved to staffadminManagedBean
    public void save(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        System.err.print(this);
        String response = smsbl.addStaff(staff, createAccount, title.getWorkLocation() + title.getPosition());
        System.err.println(response);
        if (response != null) {
            String[] res = response.split("_");
            if (res[0].equals("Password")) {
                System.out.println("account and password are " + res[1] + " " + res[2]);
                emailManager.sendStaffPassword(staff.getEmail(), staff, title, res[1], res[2]);
                this.displayFaceMessage("Staff account for " + staff.getFirstName() + " " + staff.getLastName() + "has been created successfully!\n Staff Account Id and password has been sent by email: " + staff.getEmail());
            } else if (res[0].equals("NoAccount")) {
                this.displayFaceMessage("Staff account for " + staff.getFirstName() + " " + staff.getLastName() + "has been created successfully!");
            } else {
                this.displayFaceMessage("invalid response from server!!!");
            }

        } else {
            this.displayFaceMessage("no response from server!!!");
        }

        //this.retrieveAllStaffTitleFromDB();
        //this.retrieveAllStaffAccountFromDB();
        //emailManager.sendStaffPassword("hyf94244280@gmail.com", "subject: test4", "text: this is to test final before first push");        
        this.resetAll();
    }

//    public void retrieveAllStaffFromDB() {
//        allStaff = smsbl.getAllStaff();
//    }
//
//    public void retrieveAllStaffTitleFromDB() {
//        allStaff = smsbl.getAllStaff();
//        allPrimaryTitles = new ArrayList();
//        for (int i = 0; i < allStaff.size(); i++) {
//            allPrimaryTitles.add(allStaff.get(i).getTitles().get(0));
//        }
//    }

//    public void checkSelfSchedule() throws IOException {
//        if (curStaff != null) {
//            System.out.println(titleSelected);
//            String[] para = titleSelected.split("_");
//            List<Shift> tempShifts = sssbl.getAllShiftsOfStaff(curStaff, para[0], para[1]);
//            eventModel = new DefaultScheduleModel();
//            System.out.println("number of shifts " + tempShifts.size());
//            for (Shift tempShift : tempShifts) {
//                event = new DefaultScheduleEvent("Event", tempShift.getStartDate(), tempShift.getEndDate());
//                eventModel.addEvent(event);
//            }
//            ec.redirect("selfScheduleCal.xhtml");
//        } else {
//            this.displayFaceMessage("staff has not login");
//        }
//    }

    private void resetAll() {
        staff = new Staff();
        createAccount = false;
        title = new Title();
    }

    public void initiateManageStaffPage() throws IOException {
        System.out.println("Enter initiateManageStaffPage");
        setDepartments(smsbl.getDepartments(subsystem));
        workLocations = smsbl.getWorkLocations(departments.get(0), true);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), true);
        department = null;
        ec.redirect("../../CommonInfraWeb/Staff/manageAllRoles.xhtml");
    }

    public void initiateCreateSchedulePage() throws IOException {
        System.out.println("Enter initiateManageStaffPage");
        setDepartments(smsbl.getDepartments(subsystem));
        workLocations = smsbl.getWorkLocations(departments.get(0), false);
        positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), false);
        department = null;
        ec.redirect("../../CommonInfraWeb/Schedule/createSchedule.xhtml");
    }
    //v2
    public void saveExtraTitle() {

        if (workLocations.get(0).equals("No Title Available as Extra Title")) {
            this.displayFaceMessage("No Title Available as Extra Title");
        } else {
            String extraTitleKey = title.getWorkLocation() + title.getPosition();
            List<Title> tempTitles = smsbl.addExtraTitle(extraTitleKey, staff.getId());

            if (tempTitles == null) {
                this.displayFaceMessage("operation error!");
            } else if (tempTitles.size() == 1) {
                this.displayFaceMessage("staff has already got the title! please verify");
            } else {
                this.displayFaceMessage("extraTitle Added to staff");
                titles = tempTitles;
            }
            title = new Title();
        }
    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void remove(Staff remStaff) {
        smsbl.remove(remStaff.getId());
        this.displayFaceMessage("staff" + remStaff.getFirstName() + " " + remStaff.getLastName() + "is removed");
        this.fetchStaffGivenTitle();
        this.fetchStaffAccountGivenTitle();
    }

    /**
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

//    public List<Staff> getAllStaff() {
//
//        return allStaff;
//    }
//
//    public void setAllStaff(List<Staff> allStaff) {
//        this.allStaff = allStaff;
//    }

    public void onEditStaff(RowEditEvent event) {
        System.err.println("inside onEditStaff");
        Staff temp = (Staff) event.getObject();
        smsbl.replaceStaff(temp);
        FacesMessage msg = new FacesMessage("Staff Edited", temp.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelStaff(RowEditEvent event) {
        this.displayFaceMessage("cancel edit staff");
    }

    public void onEditRole(RowEditEvent event) {
        System.err.println("inside onEditRole");
        title = (Title) event.getObject();
        System.out.println("the location in title is " + title.getWorkLocation());
        //smsbl.replaceTitle(title);
        FacesMessage msg = new FacesMessage("Saff Role successfully edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelRole(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Staff Role Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void saveStaff(Staff repStaff) throws IOException {

        if (repStaff != null) {
            System.out.println("the staff received is " + repStaff.getId());
            staff = repStaff;
            titles = smsbl.getCurStaffTitles(repStaff);
            staffWithTitle = new ArrayList();
            department = null;
            setDepartments(smsbl.getDepartments(subsystem));
            workLocations = smsbl.getWorkLocations(departments.get(0), false);
            positions = smsbl.getPositionsForSpeLocation(workLocations.get(0), false);
            ec.redirect("updateIndividual.xhtml");
        } else {
            System.out.println("null staff received");
        }
    }

    //v2
    public void fetchStaffGivenTitle() {
        staffWithTitle = smsbl.fetchStaffWithTitle(title);
        if ((staffWithTitle != null) && (!staffWithTitle.isEmpty())) {
            System.out.println("received " + staffWithTitle.size());
            title.setShifts(!staffWithTitle.get(0).getTitles().get(0).isShifts());
            System.out.println("boolean value for title " + title.isShifts());
        } else {
            this.displayFaceMessage("there is not staff under this searching criteria");
            System.out.println("null received");
        }
    }

    //v2 
    public void fetchStaffAccountGivenTitle() {
        staffAccountsWithTitle = smsbl.fethStaffAccountWithTitle(title);
        if (staffAccountsWithTitle != null) {
            System.out.println("received " + staffAccountsWithTitle.size());
        } else {
            System.out.println("null received");
        }
    }

    /**
     * @return the title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return the createAccount
     */
    public boolean isCreateAccount() {
        return createAccount;
    }

    /**
     * @param createAccount the createAccount to set
     */
    public void setCreateAccount(boolean createAccount) {
        this.createAccount = createAccount;
    }

    /**
     * @return the tempStaffId
     */
    public Long getTempStaffId() {
        return tempStaffId;
    }

    /**
     * @param tempStaffId the tempStaffId to set
     */
    public void setTempStaffId(Long tempStaffId) {
        this.tempStaffId = tempStaffId;
    }

    /**
     * @return the allPrimaryTitles
     */
    public List<Title> getAllPrimaryTitles() {

        return allPrimaryTitles;
    }

    /**
     * @param allPrimaryTitles the allPrimaryTitles to set
     */
    public void setAllPrimaryTitles(List<Title> allPrimaryTitles) {
        this.allPrimaryTitles = allPrimaryTitles;
    }

    /**
     * @return the extraTitle
     */
    public Title getExtraTitle() {
        return extraTitle;
    }

    /**
     * @param extraTitle the extraTitle to set
     */
    public void setExtraTitle(Title extraTitle) {
        this.extraTitle = extraTitle;
    }

    /**
     * @return the curStaff
     */
    public Staff getCurStaff() {
        return curStaff;
    }

    /**
     * @param curStaff the curStaff to set
     */
    public void setCurStaff(Staff curStaff) {
        this.curStaff = curStaff;
    }

    /**
     * @return the workLocations
     */
    public List<String> getWorkLocations() {
        return workLocations;
    }

    /**
     * @param workLocations the workLocations to set
     */
    public void setWorkLocations(List<String> workLocations) {
        this.workLocations = workLocations;
    }

    /**
     * @return the positions
     */
    public List<String> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    /**
     * @return the departments
     */
    public List<String> getDepartments() {
        return departments;
    }

    /**
     * @param departments the departments to set
     */
    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the staffAccounts
     */
    public List<StaffAccount> getStaffAccounts() {
        return staffAccounts;
    }

    /**
     * @param staffAccounts the staffAccounts to set
     */
    public void setStaffAccounts(List<StaffAccount> staffAccounts) {
        this.staffAccounts = staffAccounts;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Title> getCurrentStaffTitles() {
        return currentStaffTitles;
    }

    public void setCurrentStaffTitles(List<Title> currentStaffTitles) {
        this.currentStaffTitles = currentStaffTitles;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String getTitleSelected() {
        return titleSelected;
    }

    public void setTitleSelected(String titleSelected) {
        this.titleSelected = titleSelected;
    }

    public List<String> getCurStaffTitles() {
        return curStaffTitles;
    }

    public void setCurStaffTitles(List<String> curStaffTitles) {
        this.curStaffTitles = curStaffTitles;
    }

    public List<Task> getStaffTasks() {
        return staffTasks;
    }

    public void setStaffTasks(List<Task> staffTasks) {
        this.staffTasks = staffTasks;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }

    public String[] getStaffTaskStatus() {
        return staffTaskStatus;
    }

    public void setStaffTaskStatus(String[] staffTaskStatus) {
        this.staffTaskStatus = staffTaskStatus;
    }

    public SelectItem[] getStaffTaskOptions() {
        return staffTaskOptions;
    }

    public void setStaffTaskOptions(SelectItem[] staffTaskOptions) {
        this.staffTaskOptions = staffTaskOptions;
    }

    public String getNewTaskStatus() {
        return newTaskStatus;
    }

    public void setNewTaskStatus(String newTaskStatus) {
        this.newTaskStatus = newTaskStatus;
    }

    public String getWorkSpaceURL() {
        return workSpaceURL;
    }

    public void setWorkSpaceURL(String workSpaceURL) {
        this.workSpaceURL = workSpaceURL;
    }

    public List<Staff> getStaffWithTitle() {
        return staffWithTitle;
    }

    public void setStaffWithTitle(List<Staff> staffWithTitle) {
        this.staffWithTitle = staffWithTitle;
    }

    public List<StaffAccount> getStaffAccountsWithTitle() {
        return staffAccountsWithTitle;
    }

    public void setStaffAccountsWithTitle(List<StaffAccount> staffAccountsWithTitle) {
        this.staffAccountsWithTitle = staffAccountsWithTitle;
    }
}
