/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM.session;

import CRM.entity.MemberAccount;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ARIEL CHENG
 */
@Local
public interface CRManalysisSessionBeanLocal {

    public List<MemberAccount> ListAllMale();

    public List<MemberAccount> ListAllFemale();

    public List<MemberAccount> ListAllClassic();

    public List<MemberAccount> ListAllSilver();

    public List<MemberAccount> ListAllGold();

    public List<MemberAccount> ListAllDiamond();

    public List<MemberAccount> ListAgeGroup1();

    public List<MemberAccount> ListAgeGroup2();

    public List<MemberAccount> ListAgeGroup3();

    public List<MemberAccount> ListAgeGroup4();

    public List<MemberAccount> ListAgeGroup5();

    public List<Double> transactionReport(MemberAccount member, int month, int year);

    
}
