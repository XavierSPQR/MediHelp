package com.example.medihelpfinal;

public class ToDo {
    private String name,address,email,age;
    private int id;

    public ToDo(){

    }

    public ToDo(String name, String address, String email, String age, int id) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
        this.id = id;
    }

    public ToDo(String name, String address, String email, String age) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }


    //============================Getters and Setters===================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
