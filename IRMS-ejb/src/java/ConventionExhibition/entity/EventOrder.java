/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConventionExhibition.entity;

import EntertainmentShow.entity.Theater;
import FoodBeverage.entity.BanquetHall;
import FoodBeverage.entity.BanquetItemOrder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Acer
 */
@Entity
public class EventOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;
    private String type; //conventionExhibition; Banquet; EntertainmentShow
    private String description;
    private float totalcharge;
    private float totalprice;
    private float facilityprice;
    private float foodprice;
    private float venueprice;
    private float employeeprice;
   
    
    private String venuetype;
    private String roomnum;
    private int capacity;
    private String datetype;//Day HalfDay Hour
    
    private String status;//Before; In ;After; Cancelled
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<FacilityNeed> facilitiesNeed;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<EmployeeNeed> employeesNeed;
    
    private Long eventorderid;
    
    
    
    @OneToOne(cascade={CascadeType.ALL})
    private DayTime daytime=new DayTime();
    
    @OneToOne(cascade={CascadeType.ALL})
    private HalfDayTime halfdaytime=new HalfDayTime();
    
    @OneToOne(cascade={CascadeType.ALL})
    private HourTime hourtime=new HourTime();
    
    @OneToOne
    private OtherVenue othervenue=new OtherVenue();
    
    @OneToOne
    private Auditorium auditorium=new Auditorium();
    
    @OneToOne
    private OpenSpace openspace=new OpenSpace();
    
    @OneToMany
    private List<ExhibitionSection> exhibitionsections;
    
    @OneToOne
    private BanquetHall banquethall=new BanquetHall();
    
    @OneToOne
    private Theater theater=new Theater();
  
    
    @ManyToOne(cascade={CascadeType.MERGE})
    private Client client=new Client();
    
    @OneToOne(cascade={CascadeType.ALL})
    private BanquetItemOrder itemorder;
    
//    @OneToOne
//    private ClientBill clientbill=new ClientBill();
    
    
    public EventOrder(){
        facilitiesNeed=new ArrayList();
        employeesNeed=new ArrayList();
        exhibitionsections=new ArrayList();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventOrder)) {
            return false;
        }
        EventOrder other = (EventOrder) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConventionExhibition.entity.EventOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the totalcharge
     */
    public float getTotalcharge() {
        return totalcharge;
    }

    /**
     * @param totalcharge the totalcharge to set
     */
    public void setTotalcharge(float totalcharge) {
        this.totalcharge = totalcharge;
    }

    /**
     * @return the totalprice
     */
    public float getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * @return the facilityprice
     */
    public float getFacilityprice() {
        return facilityprice;
    }

    /**
     * @param facilityprice the facilityprice to set
     */
    public void setFacilityprice(float facilityprice) {
        this.facilityprice = facilityprice;
    }

    /**
     * @return the foodprice
     */
    public float getFoodprice() {
        return foodprice;
    }

    /**
     * @param foodprice the foodprice to set
     */
    public void setFoodprice(float foodprice) {
        this.foodprice = foodprice;
    }

    /**
     * @return the venueprice
     */
    public float getVenueprice() {
        return venueprice;
    }

    /**
     * @param venueprice the venueprice to set
     */
    public void setVenueprice(float venueprice) {
        this.venueprice = venueprice;
    }

    /**
     * @return the employeeprice
     */
    public float getEmployeeprice() {
        return employeeprice;
    }

    /**
     * @param employeeprice the employeeprice to set
     */
    public void setEmployeeprice(float employeeprice) {
        this.employeeprice = employeeprice;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the facilitiesNeed
     */
    public List<FacilityNeed> getFacilitiesNeed() {
        return facilitiesNeed;
    }

    /**
     * @param facilitiesNeed the facilitiesNeed to set
     */
    public void setFacilitiesNeed(List<FacilityNeed> facilitiesNeed) {
        this.facilitiesNeed = facilitiesNeed;
    }

    /**
     * @return the employeesNeed
     */
    public List<EmployeeNeed> getEmployeesNeed() {
        return employeesNeed;
    }

    /**
     * @param employeesNeed the employeesNeed to set
     */
    public void setEmployeesNeed(List<EmployeeNeed> employeesNeed) {
        this.employeesNeed = employeesNeed;
    }

    /**
     * @return the daytime
     */
    public DayTime getDaytime() {
        return daytime;
    }

    /**
     * @param daytime the daytime to set
     */
    public void setDaytime(DayTime daytime) {
        this.daytime = daytime;
    }

    /**
     * @return the halfdaytime
     */
    public HalfDayTime getHalfdaytime() {
        return halfdaytime;
    }

    /**
     * @param halfdaytime the halfdaytime to set
     */
    public void setHalfdaytime(HalfDayTime halfdaytime) {
        this.halfdaytime = halfdaytime;
    }

    /**
     * @return the hourtime
     */
    public HourTime getHourtime() {
        return hourtime;
    }

    /**
     * @param hourtime the hourtime to set
     */
    public void setHourtime(HourTime hourtime) {
        this.hourtime = hourtime;
    }

    /**
     * @return the othervenue
     */
    public OtherVenue getOthervenue() {
        return othervenue;
    }

    /**
     * @param othervenue the othervenue to set
     */
    public void setOthervenue(OtherVenue othervenue) {
        this.othervenue = othervenue;
    }

    /**
     * @return the auditorium
     */
    public Auditorium getAuditorium() {
        return auditorium;
    }

    /**
     * @param auditorium the auditorium to set
     */
    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    /**
     * @return the openspace
     */
    public OpenSpace getOpenspace() {
        return openspace;
    }

    /**
     * @param openspace the openspace to set
     */
    public void setOpenspace(OpenSpace openspace) {
        this.openspace = openspace;
    }

    /**
     * @return the exhibitionsections
     */
    public List<ExhibitionSection> getExhibitionsections() {
        return exhibitionsections;
    }

    /**
     * @param exhibitionsections the exhibitionsections to set
     */
    public void setExhibitionsections(List<ExhibitionSection> exhibitionsections) {
        this.exhibitionsections = exhibitionsections;
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
     * @return the venuetype
     */
    public String getVenuetype() {
        return venuetype;
    }

    /**
     * @param venuetype the venuetype to set
     */
    public void setVenuetype(String venuetype) {
        this.venuetype = venuetype;
    }

    /**
     * @return the roomnum
     */
    public String getRoomnum() {
        return roomnum;
    }

    /**
     * @param roomnum the roomnum to set
     */
    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the datetype
     */
    public String getDatetype() {
        return datetype;
    }

    /**
     * @param datetype the datetype to set
     */
    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }

//    /**
//     * @return the clientbill
//     */
//    public ClientBill getClientbill() {
//        return clientbill;
//    }
//
//    /**
//     * @param clientbill the clientbill to set
//     */
//    public void setClientbill(ClientBill clientbill) {
//        this.clientbill = clientbill;
//    }

    /**
     * @return the itemorder
     */
    public BanquetItemOrder getItemorder() {
        return itemorder;
    }

    /**
     * @param itemorder the itemorder to set
     */
    public void setItemorder(BanquetItemOrder itemorder) {
        this.itemorder = itemorder;
    }

    /**
     * @return the eventorderid
     */
    public Long getEventorderid() {
        return eventorderid;
    }

    /**
     * @param eventorderid the eventorderid to set
     */
    public void setEventorderid(Long eventorderid) {
        this.eventorderid = eventorderid;
    }

    /**
     * @return the banquethall
     */
    public BanquetHall getBanquethall() {
        return banquethall;
    }

    /**
     * @param banquethall the banquethall to set
     */
    public void setBanquethall(BanquetHall banquethall) {
        this.banquethall = banquethall;
    }

    /**
     * @return the theater
     */
    public Theater getTheater() {
        return theater;
    }

    /**
     * @param theater the theater to set
     */
    public void setTheater(Theater theater) {
        this.theater = theater;
    }

   
    
}
