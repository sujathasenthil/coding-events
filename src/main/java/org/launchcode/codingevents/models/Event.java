package org.launchcode.codingevents.models;

public class Event {
    //create new string name and using generate create constructor and getter and setter and generate tostring(edit to return name alone
    private String name;
  //  private String description;
//    public Event(String name, String description) {
 public Event(String name) {
        this.name = name;
//        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Event(String description) {
//        this.description = description;
//    }
//
    @Override
    public String toString() {
        return name;
    }
}
