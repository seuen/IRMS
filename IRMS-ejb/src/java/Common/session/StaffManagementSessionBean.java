/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.session;

import Common.entity.CIRInternalLog;
import Common.entity.Department;
import Common.entity.Staff;
import Common.entity.StaffAccount;
import Common.entity.Task;
import Common.entity.Title;
import Common.entity.WorkGroup;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yifeng
 */
@Stateless(name = "StaffManagementSession")
public class StaffManagementSessionBean implements StaffManagementSessionBeanLocal, StaffManagementSessionBeanRemote {

    @PersistenceContext
    private EntityManager em;
    private int passLength = 8;

    public StaffManagementSessionBean() {
    }

    //utils get information      
    @Override
    public List<Staff> fetchStaffWithTitle(Title criteria) {
        try {
            String titleKey = criteria.getWorkLocation() + criteria.getPosition();
            System.out.println("title key is " + titleKey);
            Title temp = em.find(Title.class, titleKey);
            if (temp != null) {
                return temp.getStaff();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<StaffAccount> fethStaffAccountWithTitle(Title criteria) {
        try {
            List<StaffAccount> response = new ArrayList();
            String titleKey = criteria.getWorkLocation() + criteria.getPosition();
            System.out.println("title key is " + titleKey);
            Title temp = em.find(Title.class, titleKey);
            if (temp != null) {
                List<Staff> tempStaff = temp.getStaff();
                if (!tempStaff.isEmpty()) {
                    for (Staff staff : tempStaff) {
                        if (staff.getStaffAccount() != null) {
                            response.add(staff.getStaffAccount());
                        }
                    }
                    return response;
                } else {
                    return null;//title don't have staff
                }
            } else {
                return null;//title is null
            }
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public List<Title> getCurStaffTitles(Staff curStaff) {
        try {
            Staff temp = em.find(Staff.class, curStaff.getId());
            return temp.getTitles();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Title> getAllTitles() {
        try {
            Query query = em.createQuery("SELECT t FROM Title t");
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<String> getPositionsForSpeLocation(String workLocation, boolean normal) {
        //assume all positions in specific location are unique
        try {
            List<String> positions = new ArrayList();

            Query query = em.createQuery("SELECT t FROM Title t WHERE t.workLocation = :inWorkLocation");
            query.setParameter("inWorkLocation", workLocation);
            List<Title> titleCats = query.getResultList();
            System.out.println("get unique positions for " + workLocation);

            if (normal) {
                for (Title titleCat : titleCats) {
                    //System.out.println(titleCat.getPosition());
                    positions.add(titleCat.getPosition());
                }
            } else {
                for (Title titleCat : titleCats) {
                    if (titleCat.isShifts()) {
                        positions.add(titleCat.getPosition());
                    }
                }
            }

            if (positions.isEmpty()) {
                positions.add("No Title Available for Schedule or Extra Title");
            }
            return positions;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<String> getWorkLocations(String department, boolean normal) {
        try {

            String[] params = department.split("_");
            Query query = em.createQuery("SELECT d FROM Department d WHERE d.subSystem = :inSubSystem AND d.departmentName = :inDepartmentName");
            query.setParameter("inSubSystem", params[0]);
            query.setParameter("inDepartmentName", params[1]);
            Department dep = (Department) query.getSingleResult();
            System.out.println("department get " + dep.getDepartmentName());
            List<String> workLocations = new ArrayList();
            List<Title> tempTitleCats = dep.getTitles();

            if (normal) {
                for (Title tempTitleCat : tempTitleCats) {
                    workLocations.add(tempTitleCat.getWorkLocation());
                }
            } else {
                for (Title tempTitleCat : tempTitleCats) {
                    if (tempTitleCat.isShifts()) {
                        workLocations.add(tempTitleCat.getWorkLocation());
                    }
                }
            }

//        //get unique worklocations, may remove later
            HashSet<String> uniqueWorkLocations = new HashSet<>(workLocations);
            workLocations = new ArrayList();
            System.out.println("get unique worklocations");
            for (String tempWorkLocation : uniqueWorkLocations) {
                System.out.println(tempWorkLocation);
                workLocations.add(tempWorkLocation);
            }

            if (workLocations.isEmpty()) {
                workLocations.add("No Title Available for Schedule or Extra Title");
            }
            return workLocations;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<String> getDepartments(String subsystem) {
        try {
            List<String> departments = new ArrayList();

            Query query;
            if (!subsystem.equals("CIRGeneral")) {
                query = em.createQuery("SELECT d FROM Department d WHERE d.subSystem = :inSubSystem");
                query.setParameter("inSubSystem", subsystem);
            } else {
                query = em.createQuery("SELECT d FROM Department d");
            }
            List<Department> tempDepartments = query.getResultList();
            if (tempDepartments != null) {
                System.err.println("get departments ");
                for (Department tempDep : tempDepartments) {
                    System.out.println(tempDep.getDepartmentName());
                    departments.add(tempDep.getSubSystem() + "_" + tempDep.getDepartmentName());
                }
                return departments;
            } else {
                System.out.println("departments null");
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean checkAccountFrozen(String username) {
        try {
            StaffAccount staffAccount = em.find(StaffAccount.class, Long.valueOf(username));
            return !staffAccount.isFrozen();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Staff getStaffByStaffId(Long staffId) {
        try {
            return em.find(Staff.class, staffId);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Staff getStaff(String username) {

        try {
            Staff tempStaff;
            StaffAccount staffAccount = em.find(StaffAccount.class, Long.valueOf(username));
            if (staffAccount
                    == null) {
                return null;
            } else {
                tempStaff = staffAccount.getStaff();
            }
            return tempStaff;
        } catch (Exception ex) {
            return null;
        }
    }

    private List<Staff> getAllStaff() {
        try {
            Query query = em.createQuery("SELECT s FROM Staff s");
            List<Staff> staffs = query.getResultList();

            return staffs;
        } catch (Exception ex) {
            return null;
        }
    }
    //utils update information
    @Override
    public boolean logCIRInternalLog(CIRInternalLog log) {
        try {
            em.persist(log);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Task> updateTaskStatus(Long staffId, Task curTask, String newStatus) {
        try {
            StaffAccount tempAccount = em.find(StaffAccount.class, staffId);
            Task tempTask = em.find(Task.class, curTask.getId());
            if (tempAccount
                    != null) {
                tempAccount.getTasks().remove(tempTask);
                tempTask.setStatus(newStatus);
                tempAccount.getTasks().add(tempTask);
                em.merge(tempTask);
                em.merge(tempAccount);
                return tempAccount.getTasks();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String resetPasswordForStaff(Staff resetStaff, String oldPass, String newPass) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            Staff tempStaff = em.find(Staff.class, resetStaff.getId());
            StaffAccount resetAccount = tempStaff.getStaffAccount();
            oldPass = "" + resetAccount.getStaffId() + oldPass;

            System.out.println(
                    "account id is " + resetAccount.getStaffId());
            System.out.println(
                    "old password is " + oldPass);
            String oldPasswordHash = this.byteArrayToHexString(md.digest(oldPass.getBytes()));

            if (oldPasswordHash.equals(resetAccount.getStaffPassword())) {
                newPass = "" + resetAccount.getStaffId() + newPass;
                String newPasswordHash = this.byteArrayToHexString(md.digest(newPass.getBytes()));
                resetAccount.setStaffPassword(newPasswordHash);
                em.merge(resetAccount);
                em.flush();
                return "change to newPassword successfully";
            } else {
                return "old password does not match!";
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String resetStaffAccountPassword(StaffAccount staffAccount) throws NoSuchAlgorithmException {
        try {
            if (staffAccount != null) {
                String password = this.generatePassword();
                MessageDigest md = MessageDigest.getInstance("MD5");
                String passwordHash = "" + staffAccount.getStaffId().toString() + password;
                staffAccount.setStaffPassword(this.byteArrayToHexString(md.digest(passwordHash.getBytes())));
                em.merge(staffAccount);
                String response = "Password_" + staffAccount.getStaffId().toString() + "_" + password;
                return response;
            } else {
                return "staffaccount is null";
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean resetStaffAccountStatus(StaffAccount staffAccount) {
        try {
            staffAccount.setFrozen(!staffAccount.isFrozen());
            em.merge(staffAccount);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean frozeStaffAccount(Staff staff) {
        try {
            StaffAccount tempAcc = staff.getStaffAccount();
            tempAcc.setFrozen(true);
            staff.setStaffAccount(tempAcc);
            em.merge(staff);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Title> addExtraTitle(String extraTitle, Long tempStaffId) {

        try {
            List<Title> response;
            try {
                System.out.println("add extra title " + extraTitle);
                Title tempTit = em.find(Title.class, extraTitle);
                Staff tempStaff = em.find(Staff.class, tempStaffId);

                System.out.println("tempTit " + tempTit);
                System.out.println("tempStaff " + tempStaff);

                if ((tempTit != null) && (tempStaff != null)) {
                    //check staff has already have title?
                    List<Title> tempTitles = tempStaff.getTitles();
                    for (Title tit : tempTitles) {
                        if (tit.getLocationPosition().equals(extraTitle)) {
                            response = new ArrayList();
                            response.add(tempTit);
                            return response;
                        }
                    }

                    tempTit.getStaff().add(tempStaff);
                    tempStaff.getTitles().add(tempTit);
                    em.merge(tempStaff);
                    return tempStaff.getTitles();
                } else {
                    System.out.println("staff or title is null");
                    return null;//staff and title are null
                }

            } catch (Exception ex) {
                System.out.println("exception caught");
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    //utils create
    @Override
    public boolean createSuper() throws NoSuchAlgorithmException {
        this.initiateTitleCatalog();
        try {
            Query query = em.createQuery("SELECT t FROM Title t WHERE t.position = :inPosition");
            query.setParameter("inPosition", "StaffManager");
            Title temp = (Title) query.getSingleResult();
            if (temp.getStaff().isEmpty()) {
                System.out.println("create first staff");
                Staff superStaff = new Staff();
                superStaff.setFirstName("Grace");
                superStaff.setLastName("Hou");
                superStaff.setEmail("hyf94244280@gmail.com");
                this.addStaff(superStaff, true, "CIRStaffManager");
            } else {
                System.out.println("super staff already created");
            }
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public String addStaff(Staff staff, boolean createAccount, String titleKey) throws NoSuchAlgorithmException {
        String response;
        try {
            if (staff != null) {
                // set up title

                Title title = em.find(Title.class, titleKey);
                title.getStaff().add(staff);
                staff.getTitles().add(title);

                //for shop AAU only
                String shopLocation = title.getWorkLocation();
                String[] details = shopLocation.split("_");
                if (details.length > 1) {
                    System.out.println("shop id " + details[0]);
                    staff.setShopId(Long.valueOf(details[0]));
                }

                if (createAccount) {
                    StaffAccount staffAccount = new StaffAccount();
                    em.persist(staffAccount);
                    String password = this.generatePassword();
                    System.out.println("staffId " + staffAccount.getStaffId());
                    System.out.println("password " + password);
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    String passwordHash = "" + staffAccount.getStaffId().toString() + password;

                    staffAccount.setStaffPassword(this.byteArrayToHexString(md.digest(passwordHash.getBytes())));

                    response = "Password_" + staffAccount.getStaffId().toString() + "_" + password;
                    staff.setStaffAccount(staffAccount);
                    staffAccount.setStaff(staff);
                } else {
                    response = "NoAccount";
                }

                em.persist(staff);
                return response;
            } else {
                return "fail to add new staff";
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Task> addNewTaskToStaff(Long staffId, Task newStaffTask) {
        try {
            StaffAccount tempAccount = em.find(StaffAccount.class, staffId);
            if (tempAccount
                    != null) {
                em.persist(newStaffTask);
                tempAccount.getTasks().add(newStaffTask);
                em.merge(tempAccount);
                return tempAccount.getTasks();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean replaceStaff(Staff staff) {
        try {
            if (staff != null) {
                Staff temp = em.find(Staff.class, staff.getId());
                temp = staff;

                em.merge(temp);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    //utils delete
    @Override
    public List<Title> deleteExtraTitle(Staff tempStaff, Title extraTitle) {
        try {

            // get staff
            Staff focusStaff = em.find(Staff.class, tempStaff.getId());
            Title focusTitle = em.find(Title.class, extraTitle.getLocationPosition());
            // check if staff has existing workgroup, 
            // quit group if not working
            // return null otherwise

            focusStaff.getTitles().remove(focusTitle);
            focusTitle.getStaff().remove(focusStaff);
            em.merge(focusStaff);
            em.merge(focusTitle);

            return focusStaff.getTitles();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Task> deleteTaskToStaff(Long staffId, Task remTask) {
        try {
            StaffAccount tempAccount = em.find(StaffAccount.class, staffId);
            Task tempTask = em.find(Task.class, remTask.getId());
            if (tempAccount
                    != null) {
                tempAccount.getTasks().remove(tempTask);
                em.remove(tempTask);
                return tempAccount.getTasks();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Staff> remove(Long remStaffId) {
        try {
            Staff tempStaff = em.find(Staff.class, remStaffId);
            List<Title> tempTitles = tempStaff.getTitles();
            List<WorkGroup> tempWorkGroups = tempStaff.getWorkGroups();
            for (int i = 0; i < tempTitles.size(); i++) {
                tempTitles.get(i).getStaff().remove(tempStaff);
                tempStaff.getTitles().remove(tempTitles.get(i));
            }
            if (tempWorkGroups != null) {
                for (int j = 0; j < tempWorkGroups.size(); j++) {
                    tempWorkGroups.get(j).getStaffCrew().remove(tempStaff);
                    tempStaff.getWorkGroups().remove(tempWorkGroups.get(j));
                }
            }
            em.remove(tempStaff);
            return this.getAllStaff();
        } catch (Exception ex) {
            return null;
        }
    }

    private List<Title> initiateTitleCatalog() {
        Query query = em.createQuery("SELECT t FROM Title t");
        if ((query == null) || (query.getResultList() == null) || (query.getResultList().isEmpty())) {

            Department dep1 = new Department("CIRGeneral", "Human Resource");
            Title temp1 = new Title("CIRStaffManager", "CIR", "StaffManager", "CommonInfraWeb/Staff/StaffManagerWorkspace.xhtml", false);
            temp1.setDepartment(dep1);
            em.persist(temp1);
            dep1.getTitles().add(temp1);
            em.persist(dep1);

            Department dep2 = new Department("ShoppingMallGeneral", "General Management");
            Title temp4 = new Title("ShoppingMallManager", "ShoppingMall", "Manager", "ShoppingMallWeb/Space/ShoppingMallManagerPage.xhtml", false);
            temp4.setDepartment(dep2);
            em.persist(temp4);
            Title temp5 = new Title("ShoppingMallStaff", "ShoppingMall", "Staff", "ShoppingMallWeb/Space/ShoppingMallHomePage.xhtml", false);
            temp5.setDepartment(dep2);
            em.persist(temp5);
            dep2.getTitles().add(temp4);
            dep2.getTitles().add(temp5);
            em.persist(dep2);
            Department dep4 = new Department("ShoppingMallGeneral", "Tenants Management");
            em.persist(dep4);

            Department dep3 = new Department("AccomGeneral", "Daily Operations");
            Title temp6 = new Title("Singland HotelHouseKeeping", "Singland Hotel", "HouseKeeping", null, true);
            temp6.setDepartment(dep3);
            em.persist(temp6);
            Title temp7 = new Title("Singland HotelReceptionist", "Singland Hotel", "Receptionist", "AccommodationWeb/HotelInfoMgt/hotelInfo.xhtml", true);
            temp7.setDepartment(dep3);
            em.persist(temp7);
            Title temp8 = new Title("Singland HotelRoom Service", "Singland Hotel", "Room Service", "AccommodationWeb/HotelInfoMgt/hotelInfo.xhtml", true);
            temp8.setDepartment(dep3);
            em.persist(temp8);
            dep3.getTitles().add(temp6);
            dep3.getTitles().add(temp7);
            dep3.getTitles().add(temp8);
            em.persist(dep3);
            Department dep6 = new Department("AccomGeneral", "General Management");
            Title temp9 = new Title("Singland HotelManager", "Singland Hotel", "Manager", "AccommodationWeb/HotelInfoMgt/AccommodationHomePage.xhtml", false);
            temp9.setDepartment(dep6);
            em.persist(temp9);
            dep6.getTitles().add(temp9);
            em.persist(dep6);

            Department dep5 = new Department("AttractionGeneral", "General Management");
            Title temp10 = new Title("CIR AttractionManager", "CIR Attraction", "Manager", "AttractionWeb/AttractionInfo/AttractionHomePage.xhtml", false);
            temp10.setDepartment(dep5);
            em.persist(temp10);
            dep5.getTitles().add(temp10);
            em.persist(dep5);

            Department dep7 = new Department("ConventionGeneral", "General Management");
            Title temp11 = new Title("CIR ConventionManager", "CIR Convention", "Manager", "ConventionExhibitionWeb/VenueManagement/SearchVenue.xhtml", false);
            temp11.setDepartment(dep7);
            em.persist(temp11);
            dep7.getTitles().add(temp11);
            em.persist(dep7);

            Department dep11 = new Department("ConventionGeneral", "Daily Operations");
            Title temp16 = new Title("CIR Convention CenterUsher", "CIR Convention Center", "Usher", null, true);
            Title temp17 = new Title("CIR Convention CenterSecurity", "CIR Convention Center", "Security", null, true);
            Title temp18 = new Title("CIR Convention CenterEmcee", "CIR Convention Center", "Emcee", null, true);
            Title temp19 = new Title("CIR Convention CenterIT personnel", "CIR Convention Center", "IT personnel", null, true);
            Title temp20 = new Title("CIR Convention CenterAudio Visual", "CIR Convention Center", "Audio Visual", null, true);
            temp16.setDepartment(dep11);
            temp17.setDepartment(dep11);
            temp18.setDepartment(dep11);
            temp19.setDepartment(dep11);
            temp20.setDepartment(dep11);
            dep11.getTitles().add(temp16);
            dep11.getTitles().add(temp17);
            dep11.getTitles().add(temp18);
            dep11.getTitles().add(temp19);
            dep11.getTitles().add(temp20);
            em.persist(temp16);
            em.persist(temp17);
            em.persist(temp18);
            em.persist(temp19);
            em.persist(temp20);
            em.persist(dep11);

            Department dep8 = new Department("EntertainmentGeneral", "General Management");
            Title temp12 = new Title("CIR EntertainmentManager", "CIR Entertainment", "Manager", "EntertainmentShowWeb/ShowSchedule/EntertainmentShowHomePage.xhtml", false);
            temp12.setDepartment(dep8);
            dep8.getTitles().add(temp12);
            em.persist(temp12);
            em.persist(dep8);

            Department dep9 = new Department("CRMGeneral", "General Management");
            Title temp13 = new Title("CIR CRMManager", "CIR CRM", "Manager", "CRM/MemberCharts/memberCharts.xhtml", false);
            temp13.setDepartment(dep9);
            dep9.getTitles().add(temp13);
            em.persist(temp13);
            em.persist(dep9);

            Department dep10 = new Department("Food and BeverageGeneral", "General Management");
            Title temp14 = new Title("CIR FoodBeverageManager", "CIR FoodBeverage", "Manager", "FoodBeverageWeb/Space/FoodBeverageHomePage.xhtml", false);
            temp14.setDepartment(dep10);
            em.persist(temp14);
            Title temp15 = new Title("CIR FoodBeverageStaff", "CIR FoodBeverage", "Staff", "FoodBeverageWeb/Space/FoodBeverageHomePage.xhtml", false);
            temp15.setDepartment(dep10);
            em.persist(temp15);
            dep10.getTitles().add(temp14);
            dep10.getTitles().add(temp15);
            em.persist(dep10);

            Department dep12 = new Department("Food and BeverageGeneral", "Tenants Management");
            em.persist(dep12);




            em.flush();
        } else {
        }
        query = em.createQuery("SELECT t FROM Title t");
        return query.getResultList();
    }

    @Override
    public boolean checkStaffPassword(String username, String password) throws NoSuchAlgorithmException {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            String passwordHash = "" + username + password;
            //to be removed
            System.out.println("passwordHash in check " + passwordHash);

            Staff temp = this.getStaff(username);
            //to be removed
            String passwordInput = "" + username + password;
            String passwordInputHash = this.byteArrayToHexString(md.digest(passwordInput.getBytes()));

            if (passwordInputHash.equals(temp.getStaffAccount().getStaffPassword())) {
                System.out.println("passwords are the same");
            } else {
                System.out.println("passwords are different");
            }

            return passwordInputHash.equals(temp.getStaffAccount().getStaffPassword());
        } catch (Exception ex) {
            return false;
        }
    }

    private String byteArrayToHexString(byte[] bytes) {
        int lo = 0;
        int hi = 0;
        String hexString = "";

        for (int i = 0; i < bytes.length; i++) {
            lo = bytes[i];
            lo = lo & 0xff;
            hi = lo >> 4;
            lo = lo & 0xf;
            if (hi == 0) {
                hexString += "0";
            } else if (hi == 1) {
                hexString += "1";
            } else if (hi == 2) {
                hexString += "2";
            } else if (hi == 3) {
                hexString += "3";
            } else if (hi == 4) {
                hexString += "4";
            } else if (hi == 5) {
                hexString += "5";
            } else if (hi == 6) {
                hexString += "6";
            } else if (hi == 7) {
                hexString += "7";
            } else if (hi == 8) {
                hexString += "8";
            } else if (hi == 9) {
                hexString += "9";
            } else if (hi == 10) {
                hexString += "a";
            } else if (hi == 11) {
                hexString += "b";
            } else if (hi == 12) {
                hexString += "c";
            } else if (hi == 13) {
                hexString += "d";
            } else if (hi == 14) {
                hexString += "e";
            } else if (hi == 15) {
                hexString += "f";
            }

            if (lo == 0) {
                hexString += "0";
            } else if (lo == 1) {
                hexString += "1";
            } else if (lo == 2) {
                hexString += "2";
            } else if (lo == 3) {
                hexString += "3";
            } else if (lo == 4) {
                hexString += "4";
            } else if (lo == 5) {
                hexString += "5";
            } else if (lo == 6) {
                hexString += "6";
            } else if (lo == 7) {
                hexString += "7";
            } else if (lo == 8) {
                hexString += "8";
            } else if (lo == 9) {
                hexString += "9";
            } else if (lo == 10) {
                hexString += "a";
            } else if (lo == 11) {
                hexString += "b";
            } else if (lo == 12) {
                hexString += "c";
            } else if (lo == 13) {
                hexString += "d";
            } else if (lo == 14) {
                hexString += "e";
            } else if (lo == 15) {
                hexString += "f";
            }
        }

        return hexString;
    }

    private String generatePassword() throws NoSuchAlgorithmException {
        String password = "";

        SecureRandom wheel = SecureRandom.getInstance("SHA1PRNG");

        char[] alphaNumberic = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < getPassLength(); i++) {
            int random = wheel.nextInt(alphaNumberic.length);
            password += alphaNumberic[random];
        }
        return password;
    }

    /**
     * @return the passLength
     */
    public int getPassLength() {
        return passLength;
    }
}
