package com.devmaufh.itrdegreedays.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;

import java.util.List;

public class InsectViewModel extends AndroidViewModel {
    private InsectsRepository mRepository;
    private LiveData<List<InsectEntity>> mAllInsects;
    private LiveData<List<DatesEntity>> mAllDates;

    public InsectViewModel(@NonNull Application application) {
        super(application);
        mRepository=new InsectsRepository(application);
        mAllInsects= mRepository.getAllInsects();
        mAllDates=mRepository.getAllDates();
    }

    public LiveData<List<DatesEntity>> getmAllDates() {
        return mAllDates;
    }

    public LiveData<List<InsectEntity>> getmAllInsects() {
        return mAllInsects;
    }
    public void insertInsect(InsectEntity insect){
        mRepository.insertInsect(insect);
        Toast.makeText(getApplication().getApplicationContext(), "Data storage succesfull", Toast.LENGTH_SHORT).show();
    }
    public void insertDate(DatesEntity date){
        mRepository.insertDate(date);
    }
}
