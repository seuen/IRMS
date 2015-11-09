/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ClientBill;
import ConventionExhibition.entity.ClientReceipt;
import ConventionExhibition.entity.EventOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ClientBillManagementSessionBeanLocal {
    public List<ClientBill> getAllClientBills();
    public List<ClientBill> getAllUnpaidClientBills();
    public List<ClientBill> getMyClientBills(String clientIC);
    public List<Client> getAllClients();
    public Client getClient(String IC);
    public ClientBill getClientBill(Long billID);
    public List<EventOrder> getBillOrders(Long billID);
    public EventOrder getEventOrder(Long evetID);
    public void updateBillCharge(ClientBill bill);
    public void updateBillOrderCharges(Long billID);
    public List<ClientReceipt> getAllReceipts();
    public ClientReceipt getReceipt(Long id);
    public ClientReceipt createReceipt(ClientBill cb);
    public void updateClientBills();
}
