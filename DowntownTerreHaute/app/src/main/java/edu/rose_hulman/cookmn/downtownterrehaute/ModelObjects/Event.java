package edu.rose_hulman.cookmn.downtownterrehaute.ModelObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Dev on 02/01/2016.
 */
public class Event {
    @JsonIgnore
    private String id;

    private String name;
    private long date;
    private String time;
    private String location;
    private String description;
    private String logo;

    public Event(String name, long date, String time, String location, String description, String logo){
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
        this.logo = logo;
    }

    public Event(){
        //Empty for firebase
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public void setValues(Event event) {
        this.name = event.getName();
        this.date = event.getDate();
        this.time = event.getTime();
        this.location = event.getLocation();
        this.description = event.getDescription();
        this.logo = event.getLogo();
    }
}
