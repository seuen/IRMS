/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.ClientBill;
import ConventionExhibition.entity.ClientReceipt;
import ConventionExhibition.entity.EventOrder;
import ConventionExhibition.session.ClientBillManagementSessionBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.EmailManager;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class ClientBillingManagedBean {

    private List<ClientBill> allBills = new ArrayList();
    private List<Client> clients = new ArrayList();
    private List<ClientBill> myBills = new ArrayList();
    private List<ClientBill> unpaidBills = new ArrayList();
    private ClientBill viewBill;
    private List<EventOrder> billOrders = new ArrayList();
    private List<ClientReceipt> receipts = new ArrayList();
    private EventOrder viewOrder;
    private Client viewClient;
    private ClientReceipt viewReceipt;
    @EJB
    ClientBillManagementSessionBeanLocal cbmsb;

    /**
     * Creates a new instance of ClientBillingManagedBean
     */
    public ClientBillingManagedBean() {
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewBill") != null) {
            viewBill = (ClientBill) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewBill");
//            System.out.println("Initilized selectedNego: " + selectedNego.getNegotiatorName());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewOrder") != null) {
            viewOrder = (EventOrder) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewOrder");
//            System.out.println("Initilized selectedNego: " + selectedNego.getNegotiatorName());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewClient") != null) {
            viewClient = (Client) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewClient");
//            System.out.println("Initilized selectedNego: " + selectedNego.getNegotiatorName());
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewReceipt") != null) {
            viewReceipt = (ClientReceipt) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("viewReceipt");
//            System.out.println("Initilized selectedNego: " + selectedNego.getNegotiatorName());
        }
    }

    public void navigateAllBills(ActionEvent event) throws IOException {
        cbmsb.updateClientBills();
        FacesContext.getCurrentInstance().getExternalContext().redirect("allBills.xhtml");
    }

    public void navigateAllClients(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("allClients.xhtml");
    }

    public void navigateMyBills(ActionEvent event) throws IOException {
        cbmsb.updateClientBills();
        // get viewClient, push to session
        Client c = (Client) event.getComponent().getAttributes().get("viewClient");
        viewClient = cbmsb.getClient(c.getIc());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewClient", viewClient);
        FacesContext.getCurrentInstance().getExternalContext().redirect("allMyBills.xhtml");
    }

    public void navigateAllReceipts(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("allReceipts.xhtml");
    }

    public void navigateAllUnpaidBills(ActionEvent event) throws IOException {
        cbmsb.updateClientBills();
        FacesContext.getCurrentInstance().getExternalContext().redirect("allUnpaidBills.xhtml");
    }

    public void navigateBillOrders(ActionEvent event) throws IOException {
        // get viewBill, push to session
        ClientBill c;
        c = (ClientBill) event.getComponent().getAttributes().get("viewBill");
        viewBill = cbmsb.getClientBill(c.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewBill", viewBill);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewBillOrders.xhtml");
    }

    public void navigateOrderDetails(ActionEvent event) throws IOException {
        // get viewOrder, push to session
        EventOrder eo = (EventOrder) event.getComponent().getAttributes().get("viewOrder");
        viewOrder = cbmsb.getEventOrder(eo.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewBill", viewBill);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewOrder", viewOrder);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewOrderDetails.xhtml");
    }

    public void navigateReceiptDetails(ActionEvent event) throws IOException {
        // get viewReceipt, push to session
        ClientReceipt tr = (ClientReceipt) event.getComponent().getAttributes().get("viewReceipt");
        viewReceipt = cbmsb.getReceipt(tr.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("viewReceipt", viewReceipt);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewReceipt.xhtml");
    }

    public void sendBill(ActionEvent event) {
        // get event attribute
        try {
            ClientBill b = (ClientBill) event.getComponent().getAttributes().get("sendBill");
            System.err.println("inside sendClientBill");
            EmailManager emailManager = new EmailManager();
            System.out.println("receipt email sender");
            emailManager.sendClientBill(b);
            System.out.println("after client bill email sent");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Client bill sent successfully!", "Billnumber is " + b.getId()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown Error Occurred", ""));
        }
    }

    public void sendReceipt(ActionEvent event) {
        // get event attribute
        try {
            ClientReceipt b = (ClientReceipt) event.getComponent().getAttributes().get("sendReceipt");
            if (b == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot send receipt since the bill is not paid!", ""));
            } else {
                System.err.println("inside sendClientReceipt");
                EmailManager emailManager = new EmailManager();
                System.out.println("clientReceipt email sender");
                emailManager.sendClientReceipt(b);
                System.out.println("after client receipt email sent");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Client receipt sent successfully!", "Billnumber is " + b.getId()));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown Error Occurred", ""));
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    public void recordCheckoutPayment(ActionEvent event) throws IOException {
        // get event attribute
        if (viewBill.getReceipt() != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cannot make payment since the bill has already been paid!", ""));
        } else {
            ClientReceipt cr = cbmsb.createReceipt(viewBill);
            cbmsb.updateBillOrderCharges(viewBill.getId());
            viewBill.setTotalcharges(0);
            viewBill.setStatus("Complete");
            cbmsb.updateBillCharge(viewBill);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Payment (" + cr.getTotalPayment() + "SGD) made successfully!", ""));
        }
    }

    /**
     * @return the allBills
     */
    public List<ClientBill> getAllBills() {
        cbmsb.updateClientBills();
        allBills = cbmsb.getAllClientBills();
        return allBills;
    }

    /**
     * @param allBills the allBills to set
     */
    public void setAllBills(List<ClientBill> allBills) {
        this.allBills = allBills;
    }

    /**
     * @return the clients
     */
    public List<Client> getClients() {
        clients = cbmsb.getAllClients();
        return clients;
    }

    /**
     * @param clients the clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * @return the myBills
     */
    public List<ClientBill> getMyBills() {
        cbmsb.updateClientBills();
        myBills = cbmsb.getMyClientBills(viewClient.getIc());
        return myBills;
    }

    /**
     * @param myBills the myBills to set
     */
    public void setMyBills(List<ClientBill> myBills) {
        this.myBills = myBills;
    }

    /**
     * @return the unpaidBills
     */
    public List<ClientBill> getUnpaidBills() {
        cbmsb.updateClientBills();
        unpaidBills = cbmsb.getAllUnpaidClientBills();
        return unpaidBills;
    }

    /**
     * @param unpaidBills the unpaidBills to set
     */
    public void setUnpaidBills(List<ClientBill> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }

    /**
     * @return the viewBill
     */
    public ClientBill getViewBill() {
        cbmsb.updateClientBills();
        return viewBill;
    }

    /**
     * @param viewBill the viewBill to set
     */
    public void setViewBill(ClientBill viewBill) {
        this.viewBill = viewBill;
    }

    /**
     * @return the billOrders
     */
    public List<EventOrder> getBillOrders() {
        billOrders = cbmsb.getBillOrders(viewBill.getId());
        return billOrders;
    }

    /**
     * @param billOrders the billOrders to set
     */
    public void setBillOrders(List<EventOrder> billOrders) {
        this.billOrders = billOrders;
    }

    /**
     * @return the viewOrder
     */
    public EventOrder getViewOrder() {
        return viewOrder;
    }

    /**
     * @param viewOrder the viewOrder to set
     */
    public void setViewOrder(EventOrder viewOrder) {
        this.viewOrder = viewOrder;
    }

    /**
     * @return the viewClient
     */
    public Client getViewClient() {
        return viewClient;
    }

    /**
     * @param viewClient the viewClient to set
     */
    public void setViewClient(Client viewClient) {
        this.viewClient = viewClient;
    }

    /**
     * @return the receipts
     */
    public List<ClientReceipt> getReceipts() {
        receipts = cbmsb.getAllReceipts();
        return receipts;
    }

    /**
     * @param receipts the receipts to set
     */
    public void setReceipts(List<ClientReceipt> receipts) {
        this.receipts = receipts;
    }

    /**
     * @return the viewReceipt
     */
    public ClientReceipt getViewReceipt() {
        return viewReceipt;
    }

    /**
     * @param viewReceipt the viewReceipt to set
     */
    public void setViewReceipt(ClientReceipt viewReceipt) {
        this.viewReceipt = viewReceipt;
    }
}
