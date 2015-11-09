/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingMall;

import Common.entity.Notification;
import Common.entity.NotificationRecord;
import Common.session.StaffNotificationSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@ViewScoped
public class NotificationManagedBean {
    
    @EJB
    private StaffNotificationSessionBeanLocal snsbl;
    private List<NotificationRecord> allRecords= new ArrayList<NotificationRecord>();
    private FacesContext fc = FacesContext.getCurrentInstance();
    private ExternalContext ec = fc.getExternalContext();

    /**
     * Creates a new instance of NotificationManagedBean
     */
    public NotificationManagedBean() {
    }
    
    public List<NotificationRecord> getAllRecords(){
        System.err.println("prepare to get all records.");
        
        String Id=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        Long receiver=Long.valueOf(Id);
        System.err.println("Get staffAccount Id is "+receiver);
        
        if((allRecords==null)||allRecords.isEmpty()){
            setAllRecords(snsbl.getNotficationRecords(receiver));
        }
        return allRecords;
    }
    
    public void removeNotification(NotificationRecord notification){
        System.out.println("test delete!!!");
        getAllRecords().remove(notification);
        System.out.println("Notification ID = "+notification.getId()+" will be deleted.");
        snsbl.deleteNotificationRecord(notification.getId());
        
    }


    public void setAllRecords(List<NotificationRecord> allRecords) {
        this.allRecords = allRecords;
    }
    
}
