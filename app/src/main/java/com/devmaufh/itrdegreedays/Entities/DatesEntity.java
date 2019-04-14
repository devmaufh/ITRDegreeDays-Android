package com.devmaufh.itrdegreedays.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "dates")
public class DatesEntity {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    @ColumnInfo(name = "TempMin")
    private double tMin;
    @ColumnInfo(name = "TempMax")
    private double tMax;
    public DatesEntity() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public double getTMin() {
        return tMin;
    }
    public void setTMin(double tMin) {
        this.tMin = tMin;
    }
    public double getTMax() {
        return tMax;
    }
    public void setTMax(double tMax) {
        this.tMax = tMax;
    }

}
