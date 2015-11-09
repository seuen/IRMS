/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Facility;
import ConventionExhibition.entity.FacilityNeed;
import ConventionExhibition.entity.FacilityType;
import java.util.List;
import javax.ejb.Local;

/** 
 *
 * @author zsy
 */
@Local
public interface FacilityManagementSessionBeanLocal {
    public void addFacilityType(FacilityType facilityType,String location);
    public void addFacility(Facility facility, int quantity);
    public List<Facility> getAllFacility();
    public List<FacilityType> getAllFacilityType();
    public void deleteFacility(List<Facility> facilities);
    public void returnFacility(List<Facility> facilities);
    public FacilityType findfacilityType(String type);
    public List<Facility> getonetypefacility(String type);
    public void updateeventfacility(FacilityNeed fn, List<Facility> facilities,String status);
  //  public boolean updateFacilityDetail(Facility facility, String detail);
  //  public boolean updateFacilityStatus(Facility facility, String status); 
}
