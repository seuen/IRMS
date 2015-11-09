/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExternalEventVenue;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.Enquiry;
import ConventionExhibition.session.EnquiryManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class ExternalEnquiryManagedBean implements  Serializable{

    @EJB
    EnquiryManagementSessionBeanLocal emsbl;
    private Enquiry enquiry = new Enquiry();
    private Client client = new Client();
    private List<Enquiry> unreadEnquiries=new ArrayList();

    public ExternalEnquiryManagedBean() {
    }

    public void displayMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    

    public void checkproposal() throws ParseException, IOException {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date today = sd.parse(sd.format(new Date()));
        String roomtype = getEnquiry().getRoomtype();
        if (roomtype.equals("Exhibition Hall") || roomtype.equals("Auditorium") || roomtype.equals("Open Space")) {
            if (getEnquiry().getDatetype().equals("day")) {
                if (getEnquiry().getStartingDate() != null && getEnquiry().getEndingDate() != null) {
                    if (today.compareTo(getEnquiry().getStartingDate()) < 0) {
                        if(getEnquiry().getStartingDate().after(getEnquiry().getEndingDate())){
                            enquiry=new Enquiry();
                            this.displayMessage("Starting Date must be before Ending Date");
                        }else{
                             Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                 if (getEnquiry().getStartingDate().compareTo(ca.getTime()) > 0) {
                                     enquiry=new Enquiry();
                                   this.displayMessage("Room can only be reserved half a year ahead");
                                }else{
                                     enquiry.setEventDate(null);
                                     enquiry.setTimeslot(null);
                                     enquiry.setStartTime(null);
                                     enquiry.setEndTime(null);
                                     FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryAddVenueClient.xhtml");
                                 }
                        }
                    }else{
                        enquiry=new Enquiry();
                        this.displayMessage("Starting Date cannot be after today");
                    }
                }else{
                    enquiry=new Enquiry();
                    this.displayMessage("Starting Date and Ending Date cannot be null");
                }
            } else {
                setEnquiry(new Enquiry());
                this.displayMessage(roomtype + " can only be booked By Day");
            }
        }else{
             if (getEnquiry().getDatetype().equals("day")) {
                if (getEnquiry().getStartingDate() != null && getEnquiry().getEndingDate() != null) {
                    if (today.compareTo(getEnquiry().getStartingDate()) < 0) {
                        if(getEnquiry().getStartingDate().after(getEnquiry().getEndingDate())){
                            enquiry=new Enquiry();
                            this.displayMessage("Starting Date must be before Ending Date");
                        }else{
                             Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                 if (getEnquiry().getStartingDate().compareTo(ca.getTime()) > 0) {
                                     enquiry=new Enquiry();
                                   this.displayMessage("Room can only be reserved half a year ahead");
                                }else{
                                     enquiry.setEventDate(null);
                                     enquiry.setTimeslot(null);
                                     enquiry.setStartTime(null);
                                     enquiry.setEndTime(null);
                                     FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryAddVenueClient.xhtml");
                                 }
                        }
                    }else{
                        enquiry=new Enquiry();
                        this.displayMessage("Starting Date cannot be after today");
                    }
                }else{
                    enquiry=new Enquiry();
                    this.displayMessage("Starting Date and Ending Date cannot be null");
                }
            } else if(getEnquiry().getDatetype().equals("half day")) {
                 if (getEnquiry().getEventDate()!= null && getEnquiry().getTimeslot() != null) {
                    if (today.compareTo(getEnquiry().getEventDate()) < 0) {
                             Calendar ca = new GregorianCalendar();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                 if (getEnquiry().getEventDate().compareTo(ca.getTime()) > 0) {
                                     enquiry=new Enquiry();
                                   this.displayMessage("Room can only be reserved half a year ahead");
                                }else{
                                     enquiry.setStartingDate(null);
                                     enquiry.setEndingDate(null);
                                     enquiry.setStartTime(null);
                                     enquiry.setEndTime(null);
                                     FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryAddVenueClient.xhtml");
                                 }
                    }else{
                        enquiry=new Enquiry();
                        this.displayMessage("Event Date cannot be after today");
                    }
                }else{
                     enquiry=new Enquiry();
                    this.displayMessage("Event Date and Timeslot cannot be empty");
                }
               
            }else{
                 if (getEnquiry().getEventDate()!= null && getEnquiry().getStartTime()!= null &&getEnquiry().getEndTime()!=null) {
                    if (today.compareTo(getEnquiry().getEventDate()) < 0) {
                             Calendar ca =Calendar.getInstance();
                                ca.setTime(today);
                                ca.add(Calendar.DATE, 180);
                                 if (getEnquiry().getEventDate().compareTo(ca.getTime()) > 0) {
                                     enquiry=new Enquiry();
                                   this.displayMessage("Room can only be reserved half a year ahead");
                                }else{
                                     if(getEnquiry().getStartTime().before(getEnquiry().getEndTime())){
                                         if((getEnquiry().getEndTime().getTime()-getEnquiry().getStartTime().getTime())/(1000*60*60)<2){
                                             enquiry=new Enquiry();
                                             this.displayMessage("Room cannot be booked less than 2 hours");
                                         }else{
                                             Calendar cal=Calendar.getInstance();
                                             cal.setTime(getEnquiry().getEventDate());
                                             ca.setTime(getEnquiry().getStartTime());
                                             ca.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                                             ca.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                                             ca.set(Calendar.DATE, cal.get(Calendar.DATE));
                                             getEnquiry().setStartTime(ca.getTime());
                                             
                                             ca.setTime(getEnquiry().getEndTime());
                                              ca.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                                             ca.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                                             ca.set(Calendar.DATE, cal.get(Calendar.DATE));
                                             getEnquiry().setEndTime(ca.getTime());
                                             enquiry.setStartingDate(null);
                                             enquiry.setEndingDate(null);
                                             enquiry.setTimeslot(null);
                                     FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryAddVenueClient.xhtml");
                                         }
                                     }else{
                                         enquiry=new Enquiry();
                                         this.displayMessage("Start Time must be before End Time");
                                     }
                                 }
                    }else{
                        enquiry=new Enquiry();
                        this.displayMessage("Event Date cannot be after today");
                    }
                }else{
                     enquiry=new Enquiry();
                    this.displayMessage("Event Date, Start Time and End Time cannot be empty");
                }
            }
            
        }
    }

    public void createEnquiry() {
        getEnquiry().setStatus(false);
        emsbl.createEnquiry(getEnquiry(), getClient());
        this.displayMessage("enquiry is created sucessfully");
        setEnquiry(new Enquiry());
        setClient(new Client());
    }
    
    public void navigateEnquiry(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/IRMSExternal/ExternalEventVenueWeb/AddVenueProposal.xhtml");
    }
    
    public void navigatorEnquiry(Enquiry enquiry1) throws IOException{
        enquiry=enquiry1;
        switch (enquiry.getDatetype()) {
            case "day":
                FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryDayDetail.xhtml");
                break;
            case "halfday":
                FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryHalfDayDetail.xhtml");
                break;
            default:
                FacesContext.getCurrentInstance().getExternalContext().redirect("EnquiryHourDetail.xhtml");
                break;
        }
    }

    /**
     * @return the enquiry
     */
    public Enquiry getEnquiry() {
        return enquiry;
    }

    /**
     * @param enquiry the enquiry to set
     */
    public void setEnquiry(Enquiry enquiry) {
        this.enquiry = enquiry;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the unreadEnquiries
     */
    public List<Enquiry> getUnreadEnquiries() {
        unreadEnquiries=emsbl.listUnreadEnquiry();
        return unreadEnquiries;
    }

    /**
     * @param unreadEnquiries the unreadEnquiries to set
     */
    public void setUnreadEnquiries(List<Enquiry> unreadEnquiries) {
        this.unreadEnquiries = unreadEnquiries;
    }
}
