package com.devmaufh.itrdegreedays.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;

import java.util.List;

@Dao
public interface DaoAccess {

    //Insects Entity
    @Insert
    void insertInsect(InsectEntity insectE);

    @Insert
    void insertMultipleInsects(List<InsectEntity> insectList);

    @Query("Select * from insects")
    LiveData<List<InsectEntity>> getAllInsects();
    @Query("Delete from insects")
    void deleteAllInsects();
    @Update
    void updateInsect(InsectEntity insectE);

    @Delete
    void deleteInsect(InsectEntity insectE);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDate(DatesEntity date);

    @Query("Select * from dates")
    LiveData<List<DatesEntity>> getAllDAtes();

    @Query("Delete from dates")
    void deleteAllDates();

}
