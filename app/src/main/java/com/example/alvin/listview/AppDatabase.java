package com.example.alvin.listview;

/**
 * Created by Alvin on 04/09/2018.
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {SiswaModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SiswaDAO userDao();
}

