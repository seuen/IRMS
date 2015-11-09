/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Common.session.StaffManagementSessionBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author yifeng
 */
@ManagedBean
@RequestScoped
public class ValidationManagedBean {
    
    @EJB
    StaffManagementSessionBeanLocal smsbl;

    /**
     * Creates a new instance of ValidationManagedBean
     */
    public ValidationManagedBean() {
    }

    public void checkLongID(FacesContext context, UIComponent toValidate, Object value) {
        try {
            System.out.println("check whether input is long ");
            System.out.println(Long.valueOf((String) value));
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage("please input a number for ID");
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
    
    public void checkPasswordLength(FacesContext context, UIComponent toValidate, Object value){
        int passwordLength = smsbl.getPassLength();
        String password = (String) value;
        try{
        if(password.length() != passwordLength)
            throw new IOException();            
        }catch(IOException e){
            FacesMessage message = new FacesMessage("password must be of length " + passwordLength);
            context.addMessage(toValidate.getClientId(context), message);
        }
    }
}
