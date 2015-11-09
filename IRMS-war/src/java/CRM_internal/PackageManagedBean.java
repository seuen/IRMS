/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRM_internal;

import Accommodation.entity.Hotel;
import Accommodation.entity.RoomType;
import Accommodation.session.HotelManagementSessionBeanLocal;
import Attraction.entity.Attraction;
import Attraction.entity.TicketType;
import Attraction.session.AttraTicketManagementSessionBeanLocal;
import CRM.entity.PackageItem;
import CRM.entity.ResortPackage;
import CRM.session.PackageManagementSessionBeanLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ARIEL CHENG
 */
@ManagedBean
@ViewScoped
public class PackageManagedBean implements Serializable {

    @EJB
    PackageManagementSessionBeanLocal pmsbl;
    @EJB
    HotelManagementSessionBeanLocal hmsbl;
    @EJB
    private AttraTicketManagementSessionBeanLocal atmsbl;
    private int quantity;
    private int quantity2;
    private double total2;
    private ResortPackage pkg;
    private ResortPackage selectP;
    private PackageItem packageItem;
    private PackageItem packageItem2 = new PackageItem();
    private PackageItem packageItem3;
    private RoomType hotelRoom;
    private TicketType ticket;
    private String roomtype;
    private String hotel;
    private String attraction;
    private String ticketType;
    private List<ResortPackage> packages = new ArrayList();
    private List<ResortPackage> selectPKG = new ArrayList();
    private List<PackageItem> allRelatedItems = new ArrayList();
    private List<PackageItem> Plist = new ArrayList();
    private List<RoomType> allRoomTypes = new ArrayList();
    private List<Hotel> allHotels = new ArrayList<Hotel>();
    private Map<String, String> attractions = new HashMap<String, String>();
    private Map<String, String> ticketTypes = new HashMap<String, String>();
    private Map<String, String> hotels = new HashMap<String, String>();
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, Map<String, String>> data2 = new HashMap<String, Map<String, String>>();
    private Map<String, String> roomtypes = new HashMap<String, String>();

    public PackageManagedBean() {

        pkg = new ResortPackage();
    }

    @PostConstruct
    public void init() {
        List<RoomType> rooms = new ArrayList<RoomType>();
        List<TicketType> types = new ArrayList<TicketType>();
        getAttractions().put("Disney World", "Disney World");
        getAttractions().put("Harry Potter Theme Park", "Harry Potter Theme Park");
        getAttractions().put("Marine Life Travel", "Marine Life Travel");
        getAttractions().put("The Botanic Experimental Museum", "The Botanic Experimental Museum");
        System.out.println(getAttractions());

        getHotels().put("City Club Hotel", "City Club Hotel");
        getHotels().put("Coral Bay Hotel", "Coral Bay Hotel");
        getHotels().put("Crone Plaza Hotel", "Crone Plaza Hotel");
        getHotels().put("Grand Park Hotel", "Grand Park Hotel");
        getHotels().put("Singland Hotel", "Singland Hotel");
        System.out.println(getHotels());

        Map<String, String> attraction1 = new HashMap<String, String>();
        types = getAtmsbl().getAllTicketType("Disney World");
        for (TicketType t : types) {
            String temp = t.getName();
            attraction1.put(temp, temp);
        }

        Map<String, String> attraction2 = new HashMap<String, String>();
        types = getAtmsbl().getAllTicketType("Harry Potter Theme Park");
        for (TicketType t : types) {
            String temp = t.getName();
            attraction2.put(temp, temp);
        }

        Map<String, String> attraction3 = new HashMap<String, String>();
        types = getAtmsbl().getAllTicketType("Marine Life Travel");
        for (TicketType t : types) {
            String temp = t.getName();
            attraction3.put(temp, temp);
        }

        Map<String, String> attraction4 = new HashMap<String, String>();
        types = getAtmsbl().getAllTicketType("The Botanic Experimental Museum");
        for (TicketType t : types) {
            String temp = t.getName();
            attraction4.put(temp, temp);
        }



        Map<String, String> hotel1 = new HashMap<String, String>();
        rooms = hmsbl.getAllRoom("City Club Hotel");
        for (RoomType r : rooms) {
            String temp = r.getType();
            hotel1.put(temp, temp);
        }

        Map<String, String> hotel2 = new HashMap<String, String>();
        rooms = hmsbl.getAllRoom("Coral Bay Hotel");
        for (RoomType r : rooms) {
            String temp = r.getType();
            hotel2.put(temp, temp);
        }

        Map<String, String> hotel3 = new HashMap<String, String>();
        rooms = hmsbl.getAllRoom("Crone Plaza Hotel");
        for (RoomType r : rooms) {
            String temp = r.getType();
            hotel3.put(temp, temp);
        }

        Map<String, String> hotel4 = new HashMap<String, String>();
        setAllRoomTypes(hmsbl.getAllRoom("Grand Park Hotel"));
        rooms = hmsbl.getAllRoom("Grand Park Hotel");
        for (RoomType r : rooms) {
            String temp = r.getType();
            hotel4.put(temp, temp);
        }

        Map<String, String> hotel5 = new HashMap<String, String>();
        rooms = hmsbl.getAllRoom("Singland Hotel");
        for (RoomType r : rooms) {
            String temp = r.getType();
            hotel5.put(temp, temp);
        }

        getData().put("City Club Hotel", hotel1);
        getData().put("Coral Bay Hotel", hotel2);
        getData().put("Crone Plaza Hotel", hotel3);
        getData().put("Grand Park Hotel", hotel4);
        getData().put("Singland Hotel", hotel5);

        getData2().put("Disney World", attraction1);
        getData2().put("Harry Potter Theme Park", attraction2);
        getData2().put("Marine Life Travel", attraction3);
        getData2().put("The Botanic Experimental Museum", attraction4);

    }

    public double calculator1() {
        double total = 0.0;
        total = total + getPackageItem2().getPrice();
        total = total + hmsbl.searchRoomType(getRoomtype()).getPrice_l() * getQuantity();
        return total;
    }

    public void handleHotelChange() {
        String hotelName = getHotel();
        System.err.println("hotel name : " + hotelName);
        if (hotelName != null && !hotelName.equals("")) {
            setRoomtypes(getData().get(hotelName));
        } else {
            setRoomtypes(new HashMap<String, String>());
        }
    }

    public void handleTicketChange() {
        if (getAttraction() != null && !attraction.equals("")) {
            this.setTicketTypes(getData2().get(getAttraction()));
        } else {
            setTicketTypes(new HashMap<String, String>());
        }
    }

    public void createPackage1() throws IOException {
        setPackageItem(new PackageItem());

        this.setHotelRoom(hmsbl.searchRoomType(getRoomtype()));
        setPackageItem(pmsbl.createAccomItem(getHotelRoom(), getQuantity()));
        System.err.println("New package Item :" + getPackageItem());
        setPackageItem2(pmsbl.createFBItem(getPackageItem2().getPrice(), getPackageItem2().getItemName()));
        System.err.println("New package Item :" + getPackageItem2());

        getPlist().add(getPackageItem());
        getPlist().add(getPackageItem2());
        this.setPkg(pmsbl.createPackage(getPkg().getPackageName(), getPkg().getPrice(), getPkg().getExpireDate(), getPkg().isPostToWeb(), "Hotel + Accommodation", getPlist()));
        FacesMessage msg = new FacesMessage("New package ID is " + getPkg().getId());
        FacesContext.getCurrentInstance().addMessage("Package Added Successfully", msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pkg", getPkg());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("addPackageAfter.xhtml");
    }

    public void createPackage2() throws IOException {
        setPackageItem(new PackageItem());
        this.setHotelRoom(hmsbl.searchRoomType(getRoomtype()));
        setPackageItem(pmsbl.createAccomItem(getHotelRoom(), getQuantity()));
        this.setTicket(getAtmsbl().searchTicketType(getTicketType()));
        this.setPackageItem2(pmsbl.createTicketItem(getTicket(), getQuantity2()));

        this.getPlist().add(getPackageItem());
        this.getPlist().add(getPackageItem2());
        this.setPkg(pmsbl.createPackage(getPkg().getPackageName(), getPkg().getPrice(), getPkg().getExpireDate(), getPkg().isPostToWeb(), "Hotel + Ticket", getPlist()));
        FacesMessage msg = new FacesMessage("New package ID is " + getPkg().getId());
        FacesContext.getCurrentInstance().addMessage("Package Added Successfully", msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pkg", getPkg());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("addPackageAfter.xhtml");
    }

    public void createPackage3() throws IOException {
        packageItem = new PackageItem();
        this.setTicket(atmsbl.searchTicketType(ticketType));
        this.setPackageItem(pmsbl.createTicketItem(ticket, quantity));
        setPackageItem2(pmsbl.createFBItem(getPackageItem2().getPrice(), getPackageItem2().getItemName()));

        this.getPlist().add(getPackageItem());
        this.getPlist().add(getPackageItem2());
        this.setPkg(pmsbl.createPackage(getPkg().getPackageName(), getPkg().getPrice(), getPkg().getExpireDate(), getPkg().isPostToWeb(), "Restaurant + Ticket", getPlist()));
        FacesMessage msg = new FacesMessage("New package ID is " + getPkg().getId());
        FacesContext.getCurrentInstance().addMessage("Package Added Successfully", msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pkg", getPkg());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("addPackageAfter.xhtml");
    }

    public void createPackage4() throws IOException {
        packageItem = new PackageItem();
        packageItem3 = new PackageItem();
        this.setHotelRoom(hmsbl.searchRoomType(getRoomtype()));
        setPackageItem(pmsbl.createAccomItem(getHotelRoom(), getQuantity()));
        this.setPackageItem2(pmsbl.createFBItem(packageItem2.getPrice(), packageItem2.getItemName()));
        this.setTicket(atmsbl.searchTicketType(ticketType));
        this.setPackageItem3(pmsbl.createTicketItem(ticket, quantity2));

        this.getPlist().add(getPackageItem());
        this.getPlist().add(getPackageItem2());
        this.getPlist().add(packageItem3);
        this.setPkg(pmsbl.createPackage(pkg.getPackageName(), pkg.getPrice(), pkg.getExpireDate(), pkg.isPostToWeb(), "Restaurant + Ticket + Hote", getPlist()));
        FacesMessage msg = new FacesMessage("New package ID is " + getPkg().getId());
        FacesContext.getCurrentInstance().addMessage("Package Added Successfully", msg);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pkg", getPkg());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("addPackageAfter.xhtml");

    }

    public void removePackage(ResortPackage pkg) {
        getPackages().remove(pkg);
        pmsbl.deletePackage(pkg);
        FacesMessage msg = new FacesMessage("Package ID is " + getPkg().getId() + " is removed. ");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void updatePackage(ResortPackage pkg) {
        pmsbl.updatePackage(pkg);
        FacesMessage msg = new FacesMessage("Package is updated successfully. ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ResortPackage getPkg() {
        return pkg;
    }

    public void setPkg(ResortPackage pkg) {
        this.pkg = pkg;
    }

    public PackageItem getPackageItem() {
        return packageItem;
    }

    public void setPackageItem(PackageItem packageItem) {
        this.packageItem = packageItem;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public List<RoomType> getAllRoomTypes() {

        return allRoomTypes;
    }

    public void setAllRoomTypes(List<RoomType> allRoomTypes) {
        this.allRoomTypes = allRoomTypes;
    }

    public List<Hotel> getAllHotels() {
        allHotels = hmsbl.getAllHotel();
        return allHotels;
    }

    public void setAllHotels(List<Hotel> allHotels) {
        this.allHotels = allHotels;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Map<String, String> getHotels() {
        return hotels;
    }

    public void setHotels(Map<String, String> hotels) {
        this.hotels = hotels;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public Map<String, String> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(Map<String, String> roomtypes) {
        this.roomtypes = roomtypes;
    }

    public RoomType getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(RoomType hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PackageItem getPackageItem2() {
        return packageItem2;
    }

    public void setPackageItem2(PackageItem packageItem2) {
        this.packageItem2 = packageItem2;
    }

    public PackageItem getPackageItem3() {
        return packageItem3;
    }

    public void setPackageItem3(PackageItem packageItem3) {
        this.packageItem3 = packageItem3;
    }

    public List<PackageItem> getPlist() {
        return Plist;
    }

    public void setPlist(List<PackageItem> Plist) {
        this.Plist = Plist;
    }

    public List<ResortPackage> getPackages() {
        if ((packages == null) || packages.isEmpty()) {
            packages = pmsbl.ListAllPackage();
        }
        return packages;
    }

    public void setPackages(List<ResortPackage> packages) {
        this.packages = packages;
    }

    public List<ResortPackage> getSelectPKG() {
        return selectPKG;
    }

    public void setSelectPKG(List<ResortPackage> selectPKG) {
        this.selectPKG = selectPKG;
    }

    public List<PackageItem> getAllRelatedItems() {
        return allRelatedItems;
    }

    public void setAllRelatedItems(List<PackageItem> allRelatedItems) {
        this.allRelatedItems = allRelatedItems;
    }

    public void setRelatedItemsForDlg(ResortPackage p) {
        System.err.println("ahhahahahah");
        this.setAllRelatedItems(pmsbl.listItems(p));
        System.err.println("testhahahahah" + getAllRelatedItems());
    }

    /**
     * @return the attraction
     */
    public String getAttraction() {
        return attraction;
    }

    /**
     * @param attraction the attraction to set
     */
    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    /**
     * @return the ticketType
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * @param ticketType the ticketType to set
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * @return the allRelatedItems
     */
    /**
     * @return the attractions
     */
    public Map<String, String> getAttractions() {
        return attractions;
    }

    /**
     * @param attractions the attractions to set
     */
    public void setAttractions(Map<String, String> attractions) {
        this.attractions = attractions;
    }

    /**
     * @return the ticketTypes
     */
    public Map<String, String> getTicketTypes() {
        return ticketTypes;
    }

    /**
     * @param ticketTypes the ticketTypes to set
     */
    public void setTicketTypes(Map<String, String> ticketTypes) {
        this.ticketTypes = ticketTypes;
    }

    /**
     * @return the data2
     */
    public Map<String, Map<String, String>> getData2() {
        return data2;
    }

    /**
     * @param data2 the data2 to set
     */
    public void setData2(Map<String, Map<String, String>> data2) {
        this.data2 = data2;
    }

    /**
     * @return the atmsbl
     */
    public AttraTicketManagementSessionBeanLocal getAtmsbl() {
        return atmsbl;
    }

    /**
     * @param atmsbl the atmsbl to set
     */
    public void setAtmsbl(AttraTicketManagementSessionBeanLocal atmsbl) {
        this.atmsbl = atmsbl;
    }

    /**
     * @return the ticket
     */
    public TicketType getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(TicketType ticket) {
        this.ticket = ticket;
    }

    /**
     * @return the quantity2
     */
    public int getQuantity2() {
        return quantity2;
    }

    /**
     * @param quantity2 the quantity2 to set
     */
    public void setQuantity2(int quantity2) {
        this.quantity2 = quantity2;
    }

    /**
     * @return the selectP
     */
    public ResortPackage getSelectP() {
        return selectP;
    }

    /**
     * @param selectP the selectP to set
     */
    public void setSelectP(ResortPackage selectP) {
        this.selectP = selectP;
    }

    /**
     * @return the total2
     */
    public double getTotal2() {
        return total2;
    }

    /**
     * @param total2 the total2 to set
     */
    public void setTotal2(double total2) {
        this.total2 = total2;
    }
}
