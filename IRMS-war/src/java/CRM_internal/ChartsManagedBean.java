/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import CRM.session.CRManalysisSessionBeanLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class ChartsManagedBean{

    @EJB
    private CRManalysisSessionBeanLocal crmSBL;
    private PieChartModel pieModel;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;


    public ChartsManagedBean() {
    }
    
    @PostConstruct
    public void init(){
        createPieModel();
        createPieModel2();
        createPieModel3();
    }

    public PieChartModel getPieModel() {
        return pieModel;
        
    }
    
    public PieChartModel getPieModel2(){
        return pieModel2;
    }
    
    public PieChartModel getPieModel3(){
        return pieModel3;
    }

    public void createPieModel() {
        pieModel = new PieChartModel();
        int count1=crmSBL.ListAllMale().size();
        int count2=crmSBL.ListAllFemale().size();
        pieModel.set("Female", count2);
        pieModel.set("Male", count1);
    }
    
    public void createPieModel2() {
        pieModel2=new PieChartModel();
        int count1=crmSBL.ListAllClassic().size();
        int count2=crmSBL.ListAllSilver().size();
        int count3=crmSBL.ListAllGold().size();
        int count4=crmSBL.ListAllDiamond().size();
        
        pieModel2.set("Classic", count1);
        pieModel2.set("Silver", count2);
        pieModel2.set("Gold", count3);
        pieModel2.set("Diamond", count4);    
    }
    
   public void createPieModel3(){
       pieModel3=new PieChartModel();
       int count1=crmSBL.ListAgeGroup1().size();
       int count2=crmSBL.ListAgeGroup2().size();
       int count3=crmSBL.ListAgeGroup3().size();
       int count4=crmSBL.ListAgeGroup4().size();
       int count5=crmSBL.ListAgeGroup5().size();
       System.out.println("count1-5 :"+count1+count2+count3+count4+count5);
       
       pieModel3.set("Below 30", count1);
       pieModel3.set("30 - 40", count2);
       pieModel3.set("40 - 50", count3);
       pieModel3.set("50 - 60", count4);
       pieModel3.set("Above 60", count5);
   }
    
}
