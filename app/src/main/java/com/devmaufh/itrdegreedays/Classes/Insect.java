package com.devmaufh.itrdegreedays.Classes;

public class Insect {
    private String name;
    private double TU,TL;
    public Insect(String name, double TU, double TL) {
        this.name = name;
        this.TU = TU;
        this.TL = TL;
    }
    public String getName() {
        return name;
    }
    public double getTU() {
        return TU;
    }
    public double getTL() {
        return TL;
    }
}
