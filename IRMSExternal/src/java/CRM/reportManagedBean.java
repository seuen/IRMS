/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import CRM.entity.MemberAccount;
import CRM.entity.TransactionRecord;
import CRM.session.CRManalysisSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class reportManagedBean {
    
    private int month;
    private int year;
    private TransactionRecord tRecord;
    private CartesianChartModel transactionModel;
    private MemberAccount member;
    @EJB
    private CRManalysisSessionBeanLocal crmsbl;


    /**
     * Creates a new instance of reportManagedBean
     */
    public reportManagedBean() {
    }
    
    @PostConstruct
    public void init(){
     createTransactionModel();   
    }
    
    public void createTransactionModel(){
        setTransactionModel(new CartesianChartModel());
        ChartSeries num=new ChartSeries();
       num.setLabel("Monthly Transaction Amount");
        setMember((MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember"));
        
       List<Double> amount=new ArrayList();
        System.err.println("year: "+year);
        System.err.println("month: "+month);
       amount=crmsbl.transactionReport(member, 10, 113);
       
       num.set("CRM", amount.get(0));
       num.set("Accommodation", amount.get(1));
       num.set("Entertainment Show", amount.get(2));
       num.set("Attraction", amount.get(3));
       num.set("Others", amount.get(4));
       
       this.getTransactionModel().addSeries(num);
       
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the tRecord
     */
    public TransactionRecord gettRecord() {
        return tRecord;
    }

    /**
     * @param tRecord the tRecord to set
     */
    public void settRecord(TransactionRecord tRecord) {
        this.tRecord = tRecord;
    }

    /**
     * @return the transactionModel
     */
    public CartesianChartModel getTransactionModel() {
        return transactionModel;
    }

    /**
     * @param transactionModel the transactionModel to set
     */
    public void setTransactionModel(CartesianChartModel transactionModel) {
        this.transactionModel = transactionModel;
    }

    /**
     * @return the member
     */
    public MemberAccount getMember() {
         return (MemberAccount) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentMember");
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberAccount member) {
        this.member = member;
    }
    
    
}
