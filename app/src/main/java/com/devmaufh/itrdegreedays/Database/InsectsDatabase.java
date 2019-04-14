package com.devmaufh.itrdegreedays.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Entities.DatesEntity;
import com.devmaufh.itrdegreedays.Entities.InsectEntity;
import com.devmaufh.itrdegreedays.Utilities.DatabaseUtilities;

@Database(entities = {InsectEntity.class, DatesEntity.class},version = 1,exportSchema = false)
public  abstract  class InsectsDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
    private static volatile InsectsDatabase INSTANCE;

    static InsectsDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (InsectsDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext()
                            ,InsectsDatabase.class, DatabaseUtilities.DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
