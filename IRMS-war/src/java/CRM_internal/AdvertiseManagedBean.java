/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import CRM.entity.MemberAccount;
import CRM.session.CRManalysisSessionBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.EmailManager;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class AdvertiseManagedBean {

    private String object;
    private String title;
    private String content;
    private List<MemberAccount> targetMembers;
    @EJB
    private CRManalysisSessionBeanLocal crm;

    /**
     * Creates a new instance of AdvertiseManagedBean
     */
    public AdvertiseManagedBean() {
    }

    public void sendTargeteAd() {
        EmailManager emailM = new EmailManager();
        if ("female".equals(getObject())) {
            this.setTargetMembers(crm.ListAllFemale());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("male".equals(object)) {
            this.setTargetMembers(crm.ListAllMale());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("classic".equals(object)) {
            this.setTargetMembers(crm.ListAllClassic());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("silver".equals(object)) {
            this.setTargetMembers(crm.ListAllSilver());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("gold".equals(object)) {
            this.setTargetMembers(crm.ListAllGold());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("diamond".equals(object)) {
            this.setTargetMembers(crm.ListAllDiamond());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("age1".equals(object)) {
            this.setTargetMembers(crm.ListAgeGroup1());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("age2".equals(object)) {
            this.setTargetMembers(crm.ListAgeGroup2());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("age3".equals(object)) {
            this.setTargetMembers(crm.ListAgeGroup3());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("age4".equals(object)) {
            this.setTargetMembers(crm.ListAgeGroup4());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
        if ("age5".equals(object)) {
            this.setTargetMembers(crm.ListAgeGroup5());
            emailM.sendTargetAdvertising(title, content, targetMembers);
        }
    }

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the targetMembers
     */
    public List<MemberAccount> getTargetMembers() {
        return targetMembers;
    }

    /**
     * @param targetMembers the targetMembers to set
     */
    public void setTargetMembers(List<MemberAccount> targetMembers) {
        this.targetMembers = targetMembers;
    }
}
