package util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;



@ManagedBean
@RequestScoped

public class FacesViewManagedBean 
{
    public FacesViewManagedBean() 
    {
    }
    
    
    
    @PostConstruct
    public void init()
    {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    
    
    public void defaultBeforePhaseListener(PhaseEvent event)
    {
    }
}
