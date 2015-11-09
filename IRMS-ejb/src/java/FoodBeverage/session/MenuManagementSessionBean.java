/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FoodBeverage.session;

import FoodBeverage.entity.MenuItem;
import java.util.ArrayList;
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
public class MenuManagementSessionBean implements MenuManagementSessionBeanLocal {
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void addMenuItem(MenuItem item){
        em.persist(item);
}
    
    @Override
     public List<MenuItem> listAllMenuItem(){
         List<MenuItem> temp=new ArrayList();
         Query q=em.createQuery("SELECT m FROM MenuItem m");
         for(Object o: q.getResultList()){
             MenuItem me=(MenuItem) o;
             temp.add(me);
         }
         return temp;
     }
     
    @Override
     public MenuItem findmenuitem(Long itemid){
         return em.find(MenuItem.class, itemid);
     }
    
    @Override
    public boolean checkItemconflict(MenuItem item){
        String name=item.getName();
        int i=0;
        Query q=em.createQuery("SELECT m FROM MenuItem m");
        for(Object o:q.getResultList()){
            MenuItem mi=(MenuItem) o;
            if(mi.getName().equals(name)){
                i++;
            }
        }
        if(i==0){
            return true;
        }else{
            return false;
        }
    }

}
