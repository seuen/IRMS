/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.common;

import Common.entity.Staff;
import Common.entity.Title;
import Common.session.StaffManagementSessionBeanLocal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author yifeng
 */
@WebService(serviceName = "CommonUtilsUPOS")
@Stateless()
public class CommonUtilsUPOS {

    @EJB
    private StaffManagementSessionBeanLocal smsbl;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "loginUPOS")
    public String LoginUPOS(@WebParam(name = "staffId") String staffId, @WebParam(name = "password") String password) throws NoSuchAlgorithmException{
        //TODO write your implementation code here:
        Staff staff = smsbl.getStaff(staffId.toString());
        if (staff != null) {
            if (smsbl.checkStaffPassword(staffId.toString(), password)) {
                //PassCheck_Position_ShopID
                List<Title> titles = staff.getTitles();
                for(Title title: titles){
                    if(title.getWorkspaceUrl().equals("UPOS")){
                        return "PassCheck_" + title.getPosition() + "_" + staff.getShopId();
                    }
                }
                return "Staff is not authorized to work on UPOS portal";
            } else {
                return "password is not correct";
            }
        } else {
            return "staff does not exist";
        }
    }
}
