package com.devmaufh.itrdegreedays.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;

import java.util.List;

public class InsectsRepository {
    private DaoAccess daoAccess;
    LiveData<List<DatesEntity>> mAllDates;
    LiveData<List<InsectEntity>> mAllInsects;
    InsectsRepository(Application application){
        InsectsDatabase db=InsectsDatabase.getDatabase(application);
        daoAccess=db.daoAccess();
        mAllDates=daoAccess.getAllDAtes();
        mAllInsects=daoAccess.getAllInsects();
    }
    LiveData<List<DatesEntity>> getAllDates(){
        return mAllDates;
    }
    LiveData<List<InsectEntity>> getAllInsects(){
        return mAllInsects;
    }
    public void insertInsect(InsectEntity insectE){
        new InsertAsyncTaskInsect(daoAccess).execute(insectE);
    }
    public void insertDate(DatesEntity date){
        new InsertAsyncTaskDate(daoAccess).execute(date);
    }
    private static class InsertAsyncTaskInsect extends AsyncTask<InsectEntity,Void,Void>{
        private DaoAccess mAsyncTaskDao;

        InsertAsyncTaskInsect(DaoAccess daoAccess){
            mAsyncTaskDao=daoAccess;
        }
        @Override
        protected Void doInBackground(InsectEntity... insectEntities) {
            mAsyncTaskDao.insertInsect(insectEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncTaskDate extends AsyncTask<DatesEntity,Void,Void>{
        private DaoAccess mAsyncTaskDao;

        InsertAsyncTaskDate(DaoAccess daoAccess){
            mAsyncTaskDao=daoAccess;
        }
        @Override
        protected Void doInBackground(DatesEntity... dates) {
            mAsyncTaskDao.insertDate(dates[0]);
            return null;
        }
    }
}
