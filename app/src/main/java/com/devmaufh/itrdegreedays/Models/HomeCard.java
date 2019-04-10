package com.devmaufh.itrdegreedays.Models;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.Insect;

import java.util.ArrayList;

public class HomeCard {
    private Insect insect;
    private ArrayList<Dates> dates;

    public HomeCard(Insect insect, ArrayList<Dates> dates) {
        this.insect = insect;
        this.dates = dates;
    }

    public Insect getInsect() {
        return insect;
    }

    public ArrayList<Dates> getDates() {
        return dates;
    }
}
