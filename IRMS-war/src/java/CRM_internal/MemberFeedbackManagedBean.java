/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import CRM.entity.Feedback;
import CRM.session.FeedbackManagedSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@ViewScoped
public class MemberFeedbackManagedBean {
    @EJB
    private FeedbackManagedSessionBeanLocal fmsbl;
    private Feedback feedback;
    private Feedback seletedFb;
    private List<Feedback> allFeedbacks;
    private CartesianChartModel categoryModel;
    private CartesianChartModel ratingModel;

    /**
     * Creates a new instance of MemberFeedbackManagedBean
     */
    public MemberFeedbackManagedBean() {
    }
    
    @PostConstruct
    public void init(){
       createCategoryModel(); 
       createRatingModel();
    }
    
    private void createRatingModel(){
        ratingModel=new CartesianChartModel();
        ChartSeries num=new ChartSeries();
        num.setLabel("Average Rating Score");
        
        List<Double> rate=new ArrayList();
        rate=fmsbl.feedbackRating();
        num.set("Accommodation", rate.get(0));
        num.set("F&B", rate.get(1));
        num.set("Attractions", rate.get(2));
        num.set("Shopping Mall", rate.get(3));
        num.set("Entertainment Show", rate.get(4));
        num.set("MICE", rate.get(5));
        num.set("CRM", rate.get(6));
        num.set("Others", rate.get(7));
        
        this.getRatingModel().addSeries(num);
        
        
    }
    private void createCategoryModel(){
        setCategoryModel(new CartesianChartModel());
        ChartSeries num=new ChartSeries();
        num.setLabel("Feedback Quantity");
        
        List<Integer> count=new ArrayList();
        count=fmsbl.countFeedback();
        
        num.set("Accommodation", count.get(0));
        num.set("F&B", count.get(1));
        num.set("Attractions", count.get(2));
        num.set("Shopping Mall", count.get(3));
        num.set("Entertainment Show", count.get(4));
        num.set("MICE", count.get(5));
        num.set("CRM", count.get(6));
        num.set("Others", count.get(7));
        
        getCategoryModel().addSeries(num);
             
        
    }

    /**
     * @return the feedback
     */
    public Feedback getFeedback() {
        return feedback;
    }

    /**
     * @param feedback the feedback to set
     */
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    /**
     * @return the allFeedbacks
     */
    public List<Feedback> getAllFeedbacks() {
        if((allFeedbacks==null)||allFeedbacks.isEmpty())
            allFeedbacks=fmsbl.listAllFeedbacks();
        return allFeedbacks;
    }

    /**
     * @param allFeedbacks the allFeedbacks to set
     */
    public void setAllFeedbacks(List<Feedback> allFeedbacks) {
        this.allFeedbacks = allFeedbacks;
    }


    /**
     * @return the seletedFb
     */
    public Feedback getSeletedFb() {
        return seletedFb;
    }

    /**
     * @param seletedFb the seletedFb to set
     */
    public void setSeletedFb(Feedback seletedFb) {
        this.seletedFb = seletedFb;
    }

    /**
     * @return the categoryModel
     */
    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    /**
     * @param categoryModel the categoryModel to set
     */
    public void setCategoryModel(CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    /**
     * @return the ratingModel
     */
    public CartesianChartModel getRatingModel() {
        return ratingModel;
    }

    /**
     * @param ratingModel the ratingModel to set
     */
    public void setRatingModel(CartesianChartModel ratingModel) {
        this.ratingModel = ratingModel;
    }
}
