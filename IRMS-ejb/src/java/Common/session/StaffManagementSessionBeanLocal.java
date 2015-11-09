/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.CIRInternalLog;
import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Task;
import Common.entity.Title;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Local;

/**
 * 
 * @author yifeng 
 */
@Local
public interface StaffManagementSessionBeanLocal {

    public List<String> getWorkLocations(String department,boolean normal);
    public List<String> getPositionsForSpeLocation(String workLocation, boolean normal);        
    public boolean replaceStaff(Staff staff);        
    public Staff getStaff(String username);
    public boolean checkStaffPassword(String username, String password) throws NoSuchAlgorithmException;    
    public List<String> getDepartments(String subsystem);
    public boolean frozeStaffAccount(Staff staff);
    public boolean resetStaffAccountStatus(StaffAccount staffAccount);
    public String resetStaffAccountPassword(StaffAccount staffAccount) throws NoSuchAlgorithmException;
    public boolean checkAccountFrozen(String username);
    public Staff getStaffByStaffId(Long staffId);
    public List<Title> getCurStaffTitles(Staff curStaff);
    public int getPassLength();
    public String resetPasswordForStaff(Staff resetStaff, String oldPass, String newPass) throws NoSuchAlgorithmException;
    public boolean logCIRInternalLog(CIRInternalLog log);
    public List<Task> addNewTaskToStaff(Long staffId, Task newStaffTask);
    public List<Task> deleteTaskToStaff(Long staffId, Task remTask);
    public List<Task> updateTaskStatus(Long staffId, Task curTask, String newStatus);
    public boolean createSuper() throws NoSuchAlgorithmException;
    public List<Title> getAllTitles();
    
    //D.1.1 Staff Account Management
    public String addStaff(Staff staff, boolean createAccount, String title) throws NoSuchAlgorithmException;
    public List<Staff> remove(Long remStaffId);
    public List<Staff> fetchStaffWithTitle(Title criteria);    
    public List<Title> addExtraTitle(String extraTitle, Long tempStaffId);
    public List<Title> deleteExtraTitle(Staff tempStaff,Title extraTitle);
    public List<StaffAccount> fethStaffAccountWithTitle(Title criteria);
}
