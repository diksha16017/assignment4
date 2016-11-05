package com.example.ajay.myevent;

/**
 * Created by AJAY on 04-Nov-16.
 */
public class Event {

    //attributes of event
    private String title;
    private String details;


    public Event() {
    }

    public Event(String title, String details) {
        this.title = title;
        this.details = details;

    }


    //getters and setters of event class
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }




}
