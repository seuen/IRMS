/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import CRM.entity.MemberAccount;
import CRM.session.StaffManageMemberSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class MemberManagedBean {

    @EJB
    private StaffManageMemberSessionBeanLocal smmsbl;
    private MemberAccount member;
    private MemberAccount selectedMember;
    private List<MemberAccount> allMember = new ArrayList<>();
    
    public MemberManagedBean() {
    }

    public MemberAccount getMember() {
        return member;
    }

    public void setMember(MemberAccount member) {
        this.member = member;
    }

    public List<MemberAccount> getAllMember() {
        if( (allMember==null) ||allMember.isEmpty()){
            allMember=smmsbl.getAllMembers();
        }  
        return allMember;
    }

    public void setAllMember(List<MemberAccount> allMember) {
        this.allMember = allMember;
    }

    public MemberAccount getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberAccount selectedMember) {
        this.selectedMember = selectedMember;
    }
}
