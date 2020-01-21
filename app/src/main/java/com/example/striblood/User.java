package com.example.striblood;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name, birthady, email, id;
    private String Images;
    private String tel;
    private String address;

    public User(){}
    public User(String name, String birthady, String email) {
        this.name = name;
        this.birthady = birthady;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthady(String birthady) {
        this.birthady = birthady;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getImages(){
        return Images;
    }
    public String getBirthady() {
        return birthady;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }
    public String getAddress() {
        return address;
    }

   /**@Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", name);
        result.put("birthday", birthady);
        result.put("email", email);
        return result;
    }**/
}
