package com.example.th2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name,  date, nganh , active;
    private double hocphi;


    public Item() {
    }

    public Item(int id, String name, String date, String nganh, String active, double hocphi) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.nganh = nganh;
        this.active = active;
        this.hocphi = hocphi;
    }

    public Item(String name, String date, String nganh, String active, double hocphi) {
        this.name = name;
        this.date = date;
        this.nganh = nganh;
        this.active = active;
        this.hocphi = hocphi;
    }

    public double getHocphi() {
        return hocphi;
    }

    public void setHocphi(double hocphi) {
        this.hocphi = hocphi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
