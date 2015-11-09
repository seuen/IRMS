/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.session.EventRevenueManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class CeRevenueManagedBean {

    @EJB
    EventRevenueManagementSessionBeanLocal ermsbl;
    private List<EventOrder> eo = new ArrayList();
    private Date start;
    private Date end;

    /**
     * Creates a new instance of CeRevenueManagedBean
     */
    public CeRevenueManagedBean() {
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("start") != null) {
            start = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("start");
            System.out.println("start:" + start);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("end") != null) {
            end = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("end");
            System.out.println("end:" + end);
        }
    }

     
    public float calculateEventRevenues() {
        return ermsbl.calculateTotalRevenue(eo);
    }
    
       public void navigatePeriodEventRevenue(ActionEvent event) throws IOException {
        if (start == null || end == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please specify start date and end date for reporting.", ""));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
            FacesContext.getCurrentInstance().getExternalContext().redirect("ceEvents.xhtml");
        }
    }

    public void navigateCurMonthER(ActionEvent event) throws IOException {
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ceEvents.xhtml");
    }

    public void navigateCurYearER(ActionEvent event) throws IOException {
        Calendar c = Calendar.getInstance();
        end = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("end", end);
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        start = c.getTime();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("start", start);
        FacesContext.getCurrentInstance().getExternalContext().redirect("ceEvents.xhtml");
    }

   
    
    /**
     * @return the eo
     */
    public List<EventOrder> getEo() {
        eo = ermsbl.getCEevents(start, end);
        return eo;
    }

    /**
     * @param eo the eo to set
     */
    public void setEo(List<EventOrder> eo) {
        this.eo = eo;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }
}
