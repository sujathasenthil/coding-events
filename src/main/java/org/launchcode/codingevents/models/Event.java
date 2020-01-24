package org.launchcode.codingevents.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import javax.websocket.OnError;
import java.util.Date;
import java.util.Objects;
@Entity
public class Event extends AbstractEntity {

    //create new string name and using generate create constructor and getter and setter and generate tostring(edit to return name alone
//    @Id
//    @GeneratedValue
//    private int id;
//    public int getId() {
//        return id;
//    }


    @NotBlank(message="Name is required")
    @Size(min = 3, max=50, message="Name must be between 3 and 50 characters long")
    private String name;
    @Size(max= 500, message="Description is too Long!")
    private String description;
   @NotBlank(message="Email is required")
    @Email(message = "Invalid Email. Try Again!!")
    private String contactEmail;
   @NotNull
   @NotBlank(message="Location should not be empty or null!")
   @Size(min = 2, max= 30)
   private String location;
   @AssertTrue(message="Must Register")
   private boolean register;
   @Range(min = 1)
   private int numberOfAttendees;

//@Past(message = "Date of Birth must be the past")
//@NotNull

    @FutureOrPresent(message="enter valid date")
    @NotNull(message="enter valid date")
    private Date dateOfParticipation;
    private EventTypes type;
    public Event(String name, String description, String contactEmail, String location, boolean register,int numberOfAttendees, Date dateOfParticipation, EventTypes type) {

        this.name = name;
         this.description=description;
         this.contactEmail=contactEmail;
         this.location=location;
         this.register=register;
         this.numberOfAttendees=numberOfAttendees;
         this.dateOfParticipation=dateOfParticipation;
         this.type=type;
        }
public Event(){
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public Date getDateOfParticipation() {
        return dateOfParticipation;
    }

    public void setDateOfParticipation(Date dateOfParticipation) {
        this.dateOfParticipation = dateOfParticipation;
    }



    public EventTypes getType() {
        return type;
    }

    public void setType(EventTypes type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return name;
    }
}
