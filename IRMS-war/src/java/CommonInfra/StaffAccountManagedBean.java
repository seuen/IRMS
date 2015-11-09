/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonInfra;

import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Title;
import Common.session.StaffAccountSessionBeanLocal;
import Common.session.StaffManagementSessionBeanLocal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class StaffAccountManagedBean {

    private String staffAccountId;
    private String emailAddr;
    private StaffAccount staffAccount;
    private Staff staff = new Staff();
    private List<Title> titles = new ArrayList();
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();
    private String oldPassword;
    private String newPassword;
    private String newPasswordReEnter;
    private boolean showResetPassword = false;
    @EJB
    private StaffAccountSessionBeanLocal sasbl;
    @EJB
    StaffManagementSessionBeanLocal smsbl;

    /**
     * Creates a new instance of StaffAccountManagedBean
     */
    public StaffAccountManagedBean() {
    }

    public void testtest(ActionEvent event) {
        System.out.println("enter testtest");
    }

    public void updateResetStatus() {
        this.showResetPassword = true;
    }

    public void resetStaffPassword() throws NoSuchAlgorithmException {
        System.out.println("enter reset staff password");
        if (!newPassword.equals(newPasswordReEnter)) {
            this.displayFaceMessage("the re-entered new password is not the same as the original new password, please enter again");
        } else {
            staff = (Staff) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentStaff");
            if (staff == null) {
                System.out.println("staff is null");
            } else {
                System.out.println("staff is " + staff.getFirstName() + staff.getLastName());
            }
            String response = smsbl.resetPasswordForStaff(staff, oldPassword, newPassword);
            this.displayFaceMessage(response);
        }
        oldPassword = null;
        newPassword = null;
        newPasswordReEnter = null;
    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Title> getTitles() {
        setStaffAccountId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
        Long ID = Long.valueOf(getStaffAccountId());
        titles = getSasbl().getAllTitles(ID);
        return titles;
    }

    public Staff findStaff() {
//        setStaff(new Staff());
        setStaffAccountId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
        Long ID = Long.valueOf(getStaffAccountId());
        System.err.println("inside find staff " + getSasbl().findStaff(ID));
//        setStaff(sasbl.findStaff(ID));
        setStaff(getSasbl().findStaff(ID));
        System.err.println("staff Email" + getStaff().getEmail());
        setEmailAddr(getStaff().getEmail());
        return getStaff();
    }

    public void updateEmail() {
        setStaffAccountId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
        Long ID = Long.valueOf(getStaffAccountId());
        setEmailAddr(getEmailAddr());
        getSasbl().updateEmail(ID, getEmailAddr());

    }

    public void save() {
        setStaffAccountId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
        Long ID = Long.valueOf(getStaffAccountId());
        System.err.println("inside find staff " + getSasbl().findStaff(ID));
        setStaff(getSasbl().findStaff(ID));
        getStaff().setEmail(getEmailAddr());
        getSasbl().updateStaff(getStaff());
    }

    public String getStaffAccountId() {
        return staffAccountId;
    }

    public void setStaffAccountId(String staffAccountId) {
        this.staffAccountId = staffAccountId;
    }

    public StaffAccount getStaffAccount() {
        return staffAccount;
    }

    public void setStaffAccount(StaffAccount staffAccount) {
        this.staffAccount = staffAccount;
    }

    public StaffAccountSessionBeanLocal getSasbl() {
        return sasbl;
    }

    public void setSasbl(StaffAccountSessionBeanLocal sasbl) {
        this.sasbl = sasbl;
    }

    public Staff getStaff() {
        setStaffAccountId((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
        Long ID = Long.valueOf(getStaffAccountId());
        System.err.println("inside find staff " + getSasbl().findStaff(ID));
//        setStaff(sasbl.findStaff(ID));
        staff = getSasbl().findStaff(ID);
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordReEnter() {
        return newPasswordReEnter;
    }

    public void setNewPasswordReEnter(String newPasswordReEnter) {
        this.newPasswordReEnter = newPasswordReEnter;
    }

    public boolean isShowResetPassword() {
        return showResetPassword;
    }

    public void setShowResetPassword(boolean showResetPassword) {
        this.showResetPassword = showResetPassword;
    }
}
