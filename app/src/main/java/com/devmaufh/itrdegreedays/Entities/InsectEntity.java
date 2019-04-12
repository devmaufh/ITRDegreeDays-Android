package com.devmaufh.itrdegreedays.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.devmaufh.itrdegreedays.Classes.Insect;

@Entity(tableName = "insects")
public class InsectEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    private double tu;
    private double tl;
    public InsectEntity() {}
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
    public double getTu() {
        return tu;
    }
    public void setTu(double tu) {
        this.tu = tu;
    }
    public double getTl() {
        return tl;
    }
    public void setTl(double tl) {
        this.tl = tl;
    }
}
