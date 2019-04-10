package com.devmaufh.itrdegreedays.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.Adapters.InsectsAdapter;
import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.ITRDegreeDays;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.devmaufh.itrdegreedays.Models.HomeCard;
import com.devmaufh.itrdegreedays.R;
import com.devmaufh.itrdegreedays.Utilities.DegreeDaysUtilities;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_fragment_home, container, false);
     bindUI(view);
     return view; //Return fragment view
    }
    private void bindUI(View view) {
        rv=(RecyclerView)view.findViewById(R.id.fh_recyclerView);
        fillRecycler(view);
    }
    private void fillRecycler(View view){
        layoutManager= new LinearLayoutManager(view.getContext());
        mAdapter= new InsectsAdapter(testListHomeCard(), R.layout.home_card, new InsectsAdapter.ClickListener() {
            @Override
            public void clickListener(HomeCard insect, int position) {
                Toast.makeText(view.getContext(), "Click al insecto: "+insect.getInsect().getName(), Toast.LENGTH_SHORT).show();
            }
        });
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(mAdapter);
    }





    // Only for test
    private ArrayList<HomeCard> testListHomeCard(){
                return new ArrayList<HomeCard>(){{
            add(new HomeCard(new Insect("Agrotis ipsilon",26,14),testDates()));
            add(new HomeCard(new Insect("Caliothrips phaseoli",26,14),testDates()));
            add(new HomeCard(new Insect("Helicoverpa zea",26,14),testDates()));
            add(new HomeCard(new Insect("Chinches apestosas",26,14),testDates()));
            add(new HomeCard(new Insect("Araña roja",26,14),testDates()));
            add(new HomeCard(new Insect("Dalbulus maidis",26,14),testDates()));
            }};
    }
    private ArrayList<Dates> testDates(){
        ArrayList<Dates> arr= new ArrayList<Dates>();
        for (int i = 0; i < 30; i++) {
            arr.add(new Dates("01-01-2019",DegreeDaysUtilities.getRandomNumberInRange(20,60),DegreeDaysUtilities.getRandomNumberInRange(5,10)));
        }
        return arr;
    }
}
