/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.Address;
import CRM.entity.MemberAccount;
import CRM.entity.Membership;
import CRM.session.MemberAccountManagementSessionBeanLocal;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.ExternalEmailManager;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class AccountManagedBean {

    @EJB
    private MemberAccountManagementSessionBeanLocal mmsbl;
    private MemberAccount member = new MemberAccount();
    private MemberAccount saveMember = new MemberAccount();
    private Address address = new Address();
    private Membership membership = new Membership();
    private ExternalEmailManager emailManager = new ExternalEmailManager();
    private String oldPsw;
    private String newPsw1;
    private String newPsw2;

    /**
     * Creates a new instance of AccountManagedBean
     */
    public AccountManagedBean() {
    }

//    @PostConstruct
//    public void initialiseSession() {
//        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        System.err.println("    session test");
//    }
    public void signUp(ActionEvent action) throws NoSuchAlgorithmException {

        System.err.println("New member sign up");
        String psw = mmsbl.addMember(getMember(), getAddress(), getMembership());
        System.err.println("Member password: " + psw);
        System.err.println("Member password2: " + getMember().getMembership().getPassword());
        getEmailManager().sendMemberPassword(getMember(), psw);

        FacesMessage msg = new FacesMessage("Successfully added new member " + getMember().getFirstName() + " " + getMember().getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        member = new MemberAccount();
        address = new Address();
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
    }

    public void login(ActionEvent action) throws IOException, NoSuchAlgorithmException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        System.err.println("Existed member sign in  " + getMember().getId());

        if (mmsbl.verifyLogin(getMember().getId(), getMembership().getPassword())) {
            this.setMember(mmsbl.searchMember(getMember().getId()));
            System.err.println("Existed member sign in  " + getMember().getId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentMember", getMember());
            ec.redirect("AccountMgt/afterLogin.xhtml");

            FacesMessage msg = new FacesMessage("Login successfully! Welcome " + getMember().getFirstName() + " " + getMember().getLastName());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Wrong member ID or password. Please try again :) ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentMember", getMember());
    }

    public void save() {
        mmsbl.updateMember(getSaveMember());
    }
    
    public void resetMemberPassword() throws NoSuchAlgorithmException{
        System.err.println(newPsw1);
        System.err.println(newPsw2);
        if(!newPsw1.equals(newPsw2)){ 
              FacesMessage msg = new FacesMessage("The new passwords are not same, please enter again! ");
              FacesContext.getCurrentInstance().addMessage(null, msg);          
        }else{
            String response=mmsbl.resetMemberPassword((MemberAccount)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"), oldPsw, newPsw2);
            FacesMessage msg2=new FacesMessage(response);
               FacesContext.getCurrentInstance().addMessage(null, msg2);  
        }
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
//        member=(MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {

        this.member = member;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the membership
     */
    public Membership getMembership() {
        return membership;
    }

    /**
     * @param membership the membership to set
     */
    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    /**
     * @return the emailManager
     */
    public ExternalEmailManager getEmailManager() {
        return emailManager;
    }

    /**
     * @param emailManager the emailManager to set
     */
    public void setEmailManager(ExternalEmailManager emailManager) {
        this.emailManager = emailManager;
    }

    /**
     * @return the saveMember
     */
    public MemberAccount getSaveMember() {
        saveMember = (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
        return saveMember;
    }

    /**
     * @param saveMember the saveMember to set
     */
    public void setSaveMember(MemberAccount saveMember) {
        this.saveMember = saveMember;
    }

    /**
     * @return the oldPsw
     */
    public String getOldPsw() {
        return oldPsw;
    }

    /**
     * @param oldPsw the oldPsw to set
     */
    public void setOldPsw(String oldPsw) {
        this.oldPsw = oldPsw;
    }

    /**
     * @return the newPsw1
     */
    public String getNewPsw1() {
        return newPsw1;
    }

    /**
     * @param newPsw1 the newPsw1 to set
     */
    public void setNewPsw1(String newPsw1) {
        this.newPsw1 = newPsw1;
    }

    /**
     * @return the newPsw2
     */
    public String getNewPsw2() {
        return newPsw2;
    }

    /**
     * @param newPsw2 the newPsw2 to set
     */
    public void setNewPsw2(String newPsw2) {
        this.newPsw2 = newPsw2;
    }
}
