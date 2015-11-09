/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@RequestScoped
public class ImageManagedBean {
    
    private List<String> images1;
    private Boolean showButton;
    private int attemps;

    /**
     * Creates a new instance of ImageManagedBean
     */
    public ImageManagedBean() {
        showButton=false;
        attemps =0;
        images1 = new ArrayList();
        images1.add("signUp1.jpg");
        images1.add("signUp2.jpg");
        images1.add("signUp3.jpg");
        images1.add("signUp4.jpg");       
    }

    public List<String> getImages1() {
        return images1;
    }

    public void setImages1(List<String> images1) {
        this.images1 = images1;
    }

    public Boolean getShowButton() {
        return showButton;
    }

    public void setShowButton(Boolean showButton) {
        this.showButton = showButton;
    }
}
