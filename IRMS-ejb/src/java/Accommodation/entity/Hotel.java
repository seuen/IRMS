package Accommodation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Hotel implements Serializable {

    @Id
    private String name;
    private String description;
    private String address;
    
    //relationship fields
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<Picture> pictures;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "hotel")
    private Collection<RoomType> roomTypes;
    @OneToMany(mappedBy = "hotel")
 
    private Collection<Item> items;
    @OneToMany(mappedBy = "hotel")
    private Collection<HotelFoodMaterial> foodMaterials;

    public Hotel() {
        pictures = new ArrayList();
        roomTypes = new ArrayList();
        items = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getName() != null ? getName().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.getName() == null && other.getName() != null) || (this.getName() != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accommodation.entity.Hotel[ name=" + getName() + " ]";
    }

    public Collection<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(Collection<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    

    /**
     * @return the foodMaterials
     */
    public Collection<HotelFoodMaterial> getFoodMaterials() {
        return foodMaterials;
    }

    /**
     * @param foodMaterials the foodMaterials to set
     */
    public void setFoodMaterials(Collection<HotelFoodMaterial> foodMaterials) {
        this.foodMaterials = foodMaterials;
    }
}
