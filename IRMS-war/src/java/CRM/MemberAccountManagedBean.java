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
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@SessionScoped
public class MemberAccountManagedBean {

    @EJB
    private MemberAccountManagementSessionBeanLocal mmsbl;
    private MemberAccount member = new MemberAccount();
    private Address address = new Address();
    private Membership membership = new Membership();

    /**
     * Creates a new instance of MemberAccountManagedBean
     */
    public MemberAccountManagedBean() {
    }

    public void signUp(ActionEvent action) throws  NoSuchAlgorithmException {

        System.err.println("New member sign up");
        String psw=mmsbl.addMember(member, address, membership);
        System.err.println("Member password: "+psw);
        
        FacesMessage msg = new FacesMessage("Successfully added new member " + member.getFirstName() + " " + member.getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
    }

    public void login(ActionEvent action) throws IOException, NoSuchAlgorithmException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        System.err.println("Existed member sign in  " + member.getId());

        if (mmsbl.verifyLogin(member.getId(), membership.getPassword())) {
            this.member = mmsbl.searchMember(member.getId());
            System.err.println("Existed member sign in  " + member.getId());
            ec.redirect("AccountManagement/afterLogin.xhtml");
//            FacesMessage msg = new FacesMessage("Login successfully! Welcome " + member.getFirstName() + " " + member.getLastName());
//            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Wrong member ID or password. Please try again :) ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentMember", member);
    }

    public void save() {
        mmsbl.updateMember(member);
    }

    public MemberAccount getMember() {

        return member;
    }

    public Address getAddress() {
        return address;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
