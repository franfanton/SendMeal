package com.example.lab1.Repository.Pedido;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab1.Daos.PedidoDao;
import com.example.lab1.model.Pedido;

import java.util.concurrent.Executor;

@Database(entities = {Pedido.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //private static final AppDatabase INSTANCE = null;
    public static Executor databaseWriteExecutor;

    public abstract PedidoDao pedidoDao();
    /* .... */
    static AppDatabase getInstance(final Context context) {
        /* .... */
        //return INSTANCE;
        return Room.databaseBuilder(context, AppDatabase.class, "database-SendMeal").build();
    }
}
