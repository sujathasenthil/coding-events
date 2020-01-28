package org.launchcode.codingevents.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    //create new string name and using generate create constructor and getter and setter and generate tostring(edit to return name alone

    @NotBlank(message="Name is required")
    @Size(min = 3, max=50, message="Name must be between 3 and 50 characters long")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    // private EventTypes type;
    @ManyToOne
    @NotNull(message="Category is required")
    private EventCategory eventCategory;

    @ManyToMany
    private final List<Tag> tags=new ArrayList<>();

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory=eventCategory;
        }
public Event(){
}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
    @Override
    public String toString() {
        return name;
    }
}
