package org.launchcode.codingevents.models;

public class Event {
    //create new string name and using generate create constructor and getter and setter and generate tostring(edit to return name alone
    private String name;
    private String description;gn   AQaQW234EDCDR45TGB BGT5R4
    public Event(String name, String description) {
        this.name = name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
