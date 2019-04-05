package com.devmaufh.itrdegreedays.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devmaufh.itrdegreedays.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSync extends Fragment {


    public FragmentSync() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_sync, container, false);
        return view;
    }

}
