package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity {

    @NotBlank(message="Name is required")
    @Size(min = 3, max=50, message="Name must be between 3 and 50 characters long")
    private String name;

    public EventCategory(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
