package com.example.facereg.controller;

public class control {

    public static String pert;

    public control(String pert) {
        this.pert = pert;
    }

    public control(){

    }

    public String getPert() {
        return pert;
    }

    public void setPert(String pert) {
        this.pert = pert;
    }
}
