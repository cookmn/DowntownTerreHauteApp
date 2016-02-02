package edu.rose_hulman.cookmn.downtownterrehaute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Dev on 02/01/2016.
 */
public class Status {

    //Default constructor for Firebase
    public Status(){}

    @JsonIgnore
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Number getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Number numLikes) {
        this.numLikes = numLikes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Number getFlagged() {
        return flagged;
    }

    public void setFlagged(Number flagged) {
        this.flagged = flagged;
    }

    private String text;
    private String user;
    private Number numLikes;
    private Number flagged;


}
