package com.devmaufh.itrdegreedays.Classes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "dates")
public class Dates {
    @PrimaryKey
     @NonNull
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
