package com.example.striblood;

public class Request {
    private String name, telephone, group;

    public Request() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Request(String name, String telephone, String group) {
        this.name = name;
        this.telephone = telephone;
        this.group = group;
    }

    public Request(String nom1, String telephone1) {
        this.name = nom1;
        this.telephone = telephone1;
    }

}
