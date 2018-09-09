package com.example.alvin.listview;

/**
 * Created by Alvin on 04/09/2018.
 */

import android.app.Application;
import android.arch.persistence.room.Room;

public class SiswaApp extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "siswa.db").allowMainThreadQueries().build();
    }
}

