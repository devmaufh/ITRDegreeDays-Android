package com.devmaufh.itrdegreedays.Utilities;

import android.util.Log;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.ITRDegreeDays;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class DegreeDaysUtilities {
    public static LineGraphSeries<DataPoint> Series(ArrayList<Dates> dates,double[] values){
        DataPoint[]points= new DataPoint[values.length];
        for (int i = 0; i < points.length; i++) {
            points[i]=new DataPoint(i,values[i]);
        }        
        return new LineGraphSeries<>(points);
    }
    public static double getAcomulated(double[] numbers){
        double x=0;
        for(double n:numbers)
            x+=n;
        return x;
    }
    public static double calculateDegreeDay(Insect insect, Dates date){
        Log.w("UTILS Temp: ",date.get_tMax()+"\t"+date.get_tMin());
        Log.w("UTILS Umbr: ",insect.getTU()+"\t"+insect.getTL());
        //return new ITRDegreeDays(26,14,96,21).solve();
        return new ITRDegreeDays(insect.getTU(),insect.getTL(),date.get_tMax(),date.get_tMin()).solve();
    }
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    public static String getCurrentDate(){
        Calendar c= Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        String dateTime=dateFormat.format(c.getTime());
        return dateTime;
    }
}