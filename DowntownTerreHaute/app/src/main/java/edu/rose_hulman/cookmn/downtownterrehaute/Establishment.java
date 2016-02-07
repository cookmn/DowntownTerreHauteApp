package edu.rose_hulman.cookmn.downtownterrehaute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Dev on 01/18/2016.
 */
public class Establishment {
    @JsonIgnore
    private String id;

    public Establishment(String name, String hours, String location, String description, String logo, String type) {
        this.name = name;
        this.hours = hours;
        this.location = location;
        this.description = description;
        this.logo = logo;
        this.type = type;
    }

    public Establishment() {
        //Need this for firebase
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    private String hours;
    private String location;
    private String description;
    private String logo;
    private String type;

    public void setValues(Establishment establishment) {
        name = establishment.getName();
        hours = establishment.getHours();
        location = establishment.getLocation();
        description = establishment.getDescription();
        logo = establishment.getLogo();
        type = establishment.getType();
    }
}
