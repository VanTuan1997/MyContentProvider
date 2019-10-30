package com.example.windows10timt.myapplication;

public class Author {
    private int id_author;
    private String name,address,email;

    Author(int anInt, String string, int cursorInt){
        this.id_author=0;
        this.name=null;
        this.address=null;
        this.email=null;
    }

    public Author() {
        this.id_author = id_author;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

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
}
