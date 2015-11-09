/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Staff;
import Common.entity.Title;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import Common.session.StaffManagementSessionBeanLocal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author yifeng
 */
@ManagedBean
@SessionScoped
public class LoginManagedBean {

    private Boolean showButton;
    private String username;
    private String password;
    private int attemps;
    private final int maxAttemps = 3;
    private List<String> images;
    private Date dateToday;
    private Map<String, Boolean> adminRight = new HashMap<String, Boolean>();
    private boolean testRender = true;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    @EJB
    StaffManagementSessionBeanLocal smsbl;

    /**
     * Creates a new instance of loginManagedBean
     */
    public LoginManagedBean() {
        showButton = false;
        attemps = 0;
        images = new ArrayList();
        images.add("hp1.jpg");
        images.add("hp2.jpg");
        images.add("hp3.jpg");
        images.add("hp4.jpg");
        dateToday = new Date();
    }

    @PostConstruct
    public void createSuperStaff() throws NoSuchAlgorithmException {
        System.out.println("create super staff account");
        smsbl.createSuper();
        List<Title> titles = smsbl.getAllTitles();
        for (Title title : titles) {
            adminRight.put(title.getLocationPosition(), false);
        }
    }

    public boolean checkAdminRight(String titleId) {
        try {
            return this.adminRight.get(titleId);
        } catch (Exception ex) {
            return false;
        }
    }

    public void login() {

        System.err.println("inside login");

        //set the session attribute
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customerId");

        try {
            if (username != null) {
                Staff staff = smsbl.getStaff(username);
                if (staff != null) {

                    if (smsbl.checkStaffPassword(username, password) && smsbl.checkAccountFrozen(username)) {
                        attemps = 0;
                        System.out.println("staff is " + staff.getFirstName() + staff.getLastName());
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStaff", staff);
                        String nextpage = staff.getTitles().get(0).getWorkspaceUrl();
                        adminRight.put("Manager", false);
                        for (Title title : staff.getTitles()) {
                            adminRight.put(title.getLocationPosition(), true);
                            if (title.getPosition().contains("Manager") || title.getPosition().contains("manager")) {
                                adminRight.put("Manager", true);
                                System.out.println("manager " + adminRight.get("Manager"));
                            }
                            if((title.getWorkLocation().contains("Hotel"))||(title.getWorkLocation().contains("hotel"))){
                                adminRight.put("Hotel", true);
                                System.out.println("hotel " + adminRight.get("Hotel"));
                            }
                            if((title.getWorkLocation().contains("Attraction"))){
                                adminRight.put("Attraction", true);
                            }
                            if((title.getWorkLocation().contains("Convention"))){
                                adminRight.put("Convention", true);
                            }
                            if((title.getWorkLocation().contains("Entertainment"))){
                                adminRight.put("Entertainment", true);
                            }
                            if((title.getWorkLocation().contains("CRM"))){
                                adminRight.put("CRM", true);
                            }
                            if((title.getWorkLocation().contains("FoodBeverage"))){
                                adminRight.put("FoodBeverage", true);
                            }
                        }
                        if (nextpage.equals("UPOS")) {
                            this.displayFaceMessage("Dear staff, your account can only access UPOS portal");
                            username = null;
                            password = null;
                        } else {
                            ec.redirect(nextpage);
                        }
                    } else if (smsbl.checkAccountFrozen(username)) {
                        attemps++;
                        if ((maxAttemps - attemps) > 0) {
                            username = null;
                            password = null;
                            this.displayFaceMessage("You have " + (maxAttemps - attemps) + " try before your account is frozen!");
                        } else {
                            smsbl.frozeStaffAccount(staff);
                            attemps = 0;
                            this.displayFaceMessage("Your account is frozen, please go to staff manager to reactivate your account");
                        }
                    } else {
                        this.displayFaceMessage("your account is fronzen, please go to staff manager to reactivate your account");
                    }
                } else {
                    this.displayFaceMessage("no such staff in database");
                    username = null;
                    password = null;
//                    ec.redirect("MainWorkSpace.xhtml");
                }
            } else {
                this.displayFaceMessage("must input username and password");
            }

        } catch (Exception ex) {
        }

    }

    public void onStaffIdle() {
        System.out.println("set currentStaff to null in session");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStaff", null);
    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getShowButton() {
        return showButton;
    }

    public void setShowButton(Boolean showButton) {
        this.showButton = showButton;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }

    public Map<String, Boolean> getAdminRight() {
        return adminRight;
    }

    public void setAdminRight(Map<String, Boolean> adminRight) {
        this.adminRight = adminRight;
    }

    public boolean isTestRender() {
        return testRender;
    }

    public void setTestRender(boolean testRender) {
        this.testRender = testRender;
    }
}
