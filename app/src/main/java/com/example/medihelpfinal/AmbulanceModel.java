package com.example.medihelpfinal;

public class AmbulanceModel {
    private String dNumber;
    private String email;
    private Boolean isAvailable;
    private String phone;

    public AmbulanceModel() {
        // Default constructor required for Firebase
    }

    public AmbulanceModel(String dNumber, Boolean isAvailable, String phone, String email) {
        this.dNumber = dNumber;
        this.isAvailable = isAvailable;
        this.phone = phone;
        this.email = email;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
