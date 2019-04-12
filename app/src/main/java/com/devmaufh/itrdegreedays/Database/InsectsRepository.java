package com.devmaufh.itrdegreedays.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;

import java.util.List;

public class InsectsRepository {
    private DaoAccess daoAccess;
    LiveData<List<Dates>> mAllDates;
    LiveData<List<InsectEntity>> mAllInsects;
    InsectsRepository(Application application){
        InsectsDatabase db=InsectsDatabase.getDatabase(application);
        daoAccess=db.daoAccess();
        mAllDates=daoAccess.getAllDAtes();
        mAllInsects=daoAccess.getAllInsects();
    }
    LiveData<List<Dates>> getAllDates(){
        return mAllDates;
    }
    LiveData<List<InsectEntity>> getAllInsects(){
        return mAllInsects;
    }
    public void insertInsect(InsectEntity insectE){
        new InsertAsyncTaskInsect(daoAccess).execute(insectE);
    }
    public void insertInsect(Dates date){
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
    private static class InsertAsyncTaskDate extends AsyncTask<Dates,Void,Void>{
        private DaoAccess mAsyncTaskDao;

        InsertAsyncTaskDate(DaoAccess daoAccess){
            mAsyncTaskDao=daoAccess;
        }
        @Override
        protected Void doInBackground(Dates... dates) {
            mAsyncTaskDao.insertDate(dates[0]);
            return null;
        }
    }
}
