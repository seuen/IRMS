/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ClientBill;
import ConventionExhibition.entity.ClientReceipt;
import ConventionExhibition.entity.EventOrder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class ClientBillManagementSessionBean implements ClientBillManagementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<ClientBill> getAllClientBills() {
        Query q = em.createQuery("SELECT c FROM ClientBill c");
        return q.getResultList();
    }

    @Override
    public List<ClientBill> getAllUnpaidClientBills() {
        Query q = em.createQuery("SELECT c FROM ClientBill c WHERE c.status='FirstPaid'");
        return q.getResultList();
    }

    @Override
    public List<ClientBill> getMyClientBills(String clientIC) {
        Client client = em.find(Client.class, clientIC);
        return client.getClientbills();
    }

    @Override
    public List<Client> getAllClients() {
        Query q = em.createQuery("SELECT client FROM Client client");
        return q.getResultList();
    }

    @Override
    public Client getClient(String IC) {
        return em.find(Client.class, IC);
    }
    
    @Override
    public ClientBill getClientBill(Long billID) {
        return em.find(ClientBill.class, billID);
    }

    @Override
    public List<EventOrder> getBillOrders(Long billID) {
        ClientBill b = em.find(ClientBill.class, billID);
        return b.getEvents();
    }

    @Override
    public EventOrder getEventOrder(Long eventID) {
        return em.find(EventOrder.class, eventID);
    }

    @Override
    public void updateBillCharge(ClientBill bill) {
        em.merge(bill);
        em.flush();
    }

    @Override
    public void updateBillOrderCharges(Long billID) {
        ClientBill cb = em.find(ClientBill.class, billID);
        List<EventOrder> ed = cb.getEvents();
        for (EventOrder e : ed) {
            e.setTotalcharge(0);
            em.merge(e);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<ClientReceipt> getAllReceipts() {
        Query q = em.createQuery("SELECT r FROM ClientReceipt r");
        return q.getResultList();
    }

    @Override
    public ClientReceipt getReceipt(Long id) {
        return em.find(ClientReceipt.class, id);
    }

    @Override
    public ClientReceipt createReceipt(ClientBill cb) {
        Calendar curr = Calendar.getInstance();
        ClientReceipt r = new ClientReceipt();
        r.setPayer(cb.getPayer());
        r.setReceiver(cb.getReceiver());
        r.setTotalPayment(cb.getTotalcharges());
        r.setReceiptDate(curr.getTime());
        r.setReceiptDateString(curr.get(Calendar.YEAR) + "/" + (curr.get(Calendar.MONTH) + 1) + "/" + curr.get(Calendar.DATE));
        r.setClientBill(cb);
        em.persist(r);
        cb.setReceipt(r);
        em.merge(cb);
        return r;
    }

    @Override
    public void updateClientBills() {
        List<ClientBill> cbs = getAllUnpaidClientBills();
        for(ClientBill cb: cbs) {
            List<EventOrder> ods = cb.getEvents();
            float totalPrice=0;
            float totalCharges=0;
            for(EventOrder od: ods) {
                totalPrice+=od.getTotalprice();
                totalCharges+=od.getTotalcharge();
            }
            cb.setTotalprice(totalPrice);
            cb.setTotalcharges(totalCharges);
            em.merge(cb);
        }
    }
}
