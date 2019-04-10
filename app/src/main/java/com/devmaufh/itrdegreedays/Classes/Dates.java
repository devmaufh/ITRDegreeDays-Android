package com.devmaufh.itrdegreedays.Classes;

public class Dates {
    private String date;
    double tMin,tMax;

    public Dates(String date, double tMax, double tMin) {
        this.date = date;
        this.tMin = tMin;
        this.tMax = tMax;
    }

    public String getDate() {
        return date;
    }
    public double gettMin() {
        return tMin;
    }
    public double gettMax() {
        return tMax;
    }
}
