package com.gochyou.models;

public class User {

    private int id;
    private String firstNname;
    private String lastName;
    private String username;
    private String email;
    private Long fbid;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstNname() {
        return firstNname;
    }

    public void setFirstNname(String firstNname) {
        this.firstNname = firstNname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFbid() {
        return fbid;
    }

    public void setFbid(Long fbid) {
        this.fbid = fbid;
    }
}
