package com.devmaufh.itrdegreedays.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.Activities.Register_in;
import com.devmaufh.itrdegreedays.Adapters.InsectsAdapter;
import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.ITRDegreeDays;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.devmaufh.itrdegreedays.Database.InsectViewModel;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;
import com.devmaufh.itrdegreedays.Models.HomeCard;
import com.devmaufh.itrdegreedays.R;
import com.devmaufh.itrdegreedays.Utilities.DegreeDaysUtilities;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MaterialButton btnAdd;
    private InsectViewModel mInsectViewModel;
    private ArrayList<InsectEntity> InsectsE;
    private  ArrayList<DatesEntity>datesE;
    private ArrayList<HomeCard> finalList;
    public FragmentHome() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.fragment_fragment_home, container, false);
     bindUI(view);
     btnAdd.setOnClickListener(v->showDialog(v));
     return view; //Return fragment view
    }
    private void bindUI(View view) {
        finalList= new ArrayList<HomeCard>();
        InsectsE= new ArrayList<InsectEntity>();
        datesE= new ArrayList<DatesEntity>();
        rv=(RecyclerView)view.findViewById(R.id.fh_recyclerView);
        btnAdd=(MaterialButton)view.findViewById(R.id.fh_btnAddNew);
        mInsectViewModel= ViewModelProviders.of(this).get(InsectViewModel.class);
        mInsectViewModel.getmAllInsects().observe(this, new Observer<List<InsectEntity>>() {
            @Override
            public void onChanged(@Nullable List<InsectEntity> insectEntities) {
                if(insectEntities!=null)
                    for(InsectEntity e:insectEntities)
                        InsectsE.add(e);

            }
        });
        mInsectViewModel.getmAllDates().observe(this, new Observer<List<DatesEntity>>() {
            @Override
            public void onChanged(@Nullable List<DatesEntity> datesEntities) {
                if(datesEntities!=null)
                    for(DatesEntity d:datesEntities)
                        datesE.add(d);

            }
        });
        if(InsectsE!=null&&datesE!=null){
            for (int i = 0; i <InsectsE.size() ; i++) {
               // finalList.add(new HomeCard(new Insect(InsectsE.get(i).getName(),
                 //       InsectsE.get(i).getTu(),
                   //     InsectsE.get(i).getTl()),));
            }
        }
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

    private void showDialog(View view){
        //Toast.makeText(getContext(), "Mostrando bottom sheet", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(),Register_in.class));
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
