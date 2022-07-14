package com.example.hospital.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class ModelAuthCache {

    @PrimaryKey
    private  int  id = 0;

    private String token;
    private String type;
    private String username;
    private String phone;
    private String email;
    private String gender;
    private String birth;
    private String address;
    private String status;


    public ModelAuthCache() {
    }

    public ModelAuthCache(String token, String type, String username, String phone, String email, String gender, String birth, String address, String status) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
