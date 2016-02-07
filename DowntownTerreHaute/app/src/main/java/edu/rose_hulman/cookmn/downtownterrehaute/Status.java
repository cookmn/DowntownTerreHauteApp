package edu.rose_hulman.cookmn.downtownterrehaute;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Dev on 02/01/2016.
 */
public class Status {
    private String text;
    private String user;
    private Number numLikes;
    private boolean flagged;

    //Default constructor for Firebase
    public Status(){}

    public Status(String text, String user, Number numLikes, boolean flagged){
        this.text = text;
        this.user = user;
        this.numLikes = numLikes;
        this.flagged = flagged;
    }

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

    public boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setValues(Status status) {
        text = status.getText();
        user = status.getUser();
        numLikes = status.getNumLikes();
        flagged = status.getFlagged();
    }


}
