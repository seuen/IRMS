/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attraction;

import Attraction.entity.Attraction;
import Attraction.session.AttractionManagementSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author zsy
 */
@ManagedBean
@RequestScoped
public class AttractionManagedBean {
    private Attraction attraction;
    private Attraction a;
    private List<Attraction> attractions=new ArrayList();
    
    @EJB
    private AttractionManagementSessionBeanLocal amsbl;
    
    /**
     * Creates a new instance of AttractionManagedBean
     */
    public AttractionManagedBean() {
    }
    
    public List<Attraction> ListAllAttraction(){
       // System.err.println("inside managedbean");
        attractions = amsbl.getAllAttraction();
        return getAttractions();
    }
    
    public void updateAttractionInfo(){
        System.err.println("inside update managedbean");
        if(amsbl.updateAttractionInfo(attraction)){
            FacesMessage msg = new FacesMessage("Successfully updated!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{
            FacesMessage msg = new FacesMessage("Fail to update, please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the attractions
     */
    public List<Attraction> getAttractions() {
        return attractions;
    }

    /**
     * @param attractions the attractions to set
     */
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    /**
     * @return the amsbl
     */
    public AttractionManagementSessionBeanLocal getAmsbl() {
        return amsbl;
    }

    /**
     * @param amsbl the amsbl to set
     */
    public void setAmsbl(AttractionManagementSessionBeanLocal amsbl) {
        this.amsbl = amsbl;
    }

    /**
     * @return the a
     */
    public Attraction getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Attraction a) {
        this.a = a;
    }
}
