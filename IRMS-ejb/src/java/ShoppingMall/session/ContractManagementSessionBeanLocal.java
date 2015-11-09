/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ShoppingMall.session;

import ShoppingMall.entity.Contract;
import ShoppingMall.entity.Shop;
import ShoppingMall.entity.TenantVenue;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cindylulu
 */ 
@Local
public interface ContractManagementSessionBeanLocal {
    public Contract createContract(String lessee, Date leaseDateFrom, int leaseTerm,float monthlyRental, float commissionRate, float deposit, List<TenantVenue> venues, String negoId, float baselineRental);
    public Contract renewContract(Date leaseDateFrom, int leaseTerm, float monthlyRental, float commissionRate, float deposit,float baselineRental,Shop shop);
    public boolean attachContractShop(Contract con, Shop s);
    public Contract updateContract(Contract contract);
    public String deleteContract(Long contractId);
    public Contract getContract(Long contractId);
    public List<Contract> getAllContracts();
    public List<Contract> getAllResContracts();
    public List<Contract> getAllActiveContracts();
    public List<Contract> getAllActiveResContracts();
    public List<Contract> getAllInActiveContracts();
    public List<Contract> getAllInactiveResContracts();
    public List<Contract> getMyContracts(Long shopId);
    public boolean setStatusContracts();
    public boolean contractLapseAndRenewRemind();
}
