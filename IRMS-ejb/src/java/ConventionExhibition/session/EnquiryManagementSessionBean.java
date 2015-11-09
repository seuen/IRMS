/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.session;

import ConventionExhibition.entity.Client;
import ConventionExhibition.entity.Enquiry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Acer
 */
@Stateless
public class EnquiryManagementSessionBean implements EnquiryManagementSessionBeanLocal {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Enquiry> listUnreadEnquiry(){
        List<Enquiry> es=new ArrayList();
        Query q=em.createQuery("SELECT e FROM Enquiry e WHERE e.status=:status AND e.type=:type");
        q.setParameter("status", false);
        q.setParameter("type", "Convention Exhibition");
        for(Object o:q.getResultList()){
            Enquiry en=(Enquiry) o;
            es.add(en);
        }
        return es;
    }
    
    @Override
    public List<Enquiry> listFBUnreadEnquiry(){
         List<Enquiry> es=new ArrayList();
        Query q=em.createQuery("SELECT e FROM Enquiry e WHERE e.status=:status AND e.type=:type");
        q.setParameter("status", false);
        q.setParameter("type", "Banquet");
        for(Object o:q.getResultList()){
            Enquiry en=(Enquiry) o;
            es.add(en);
        }
        return es;
    }
    
    @Override
     public List<Enquiry> listEntertainmentUnreadEnquiry(){
         List<Enquiry> es=new ArrayList();
        Query q=em.createQuery("SELECT e FROM Enquiry e WHERE e.status=:status AND e.type=:type");
        q.setParameter("status", false);
        q.setParameter("type", "Entertainment Show");
        for(Object o:q.getResultList()){
            Enquiry en=(Enquiry) o;
            es.add(en);
        }
        return es;
     }
    

    
   @Override
   public void createEnquiry(Enquiry enquiry, Client client){
       Date today=new Date();
       List<Enquiry> enquiries=new ArrayList();
       enquiry.setEnquiryDate(today);
       Client temp=em.find(Client.class, client.getIc());
       if(temp!=null){
           enquiry.setClient(temp);
               em.persist(enquiry);
               enquiries=temp.getEnquiries();
               enquiries.add(enquiry);
               temp.setEnquiries(enquiries);
               em.flush();
               em.merge(temp);
      
       }else{
           em.persist(client);//the enquiry in client not set
           enquiry.setClient(client);
           em.persist(enquiry); //enquiry totally done
           enquiries.add(enquiry);
           client.setEnquiries(enquiries); //set enquiry in client
           em.flush();
           em.merge(client); //client totally done
       }
   }
}
