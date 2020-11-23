package com.example.lab1.Repository.Plato;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab1.Daos.PlatoDao;
import com.example.lab1.model.Plato;

import java.util.concurrent.Executor;

@Database(entities = {Plato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final AppDatabase INSTANCE = null;
    public static Executor databaseWriteExecutor;

    public abstract PlatoDao platoDao();
    /* .... */
    static AppDatabase getInstance(final Context context) {
        /* .... */
        //return INSTANCE;
        return Room.databaseBuilder(context, AppDatabase.class, "database-name").build();
    }
}
