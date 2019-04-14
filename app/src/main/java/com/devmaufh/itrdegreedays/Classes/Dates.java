package com.devmaufh.itrdegreedays.Classes;


public class Dates {
    private String date;
    private double tMin,tMax;
    public Dates(String date, double tMax, double tMin) {
        this.date = date;
        this.tMin = tMin;
        this.tMax = tMax;
    }
    public String getDate() {
        return date;
    }
    public double get_tMin() {
        return tMin;
    }

    public double get_tMax() {
        return tMax;
    }
}
