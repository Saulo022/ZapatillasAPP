package com.example.zapatillasapp.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.zapatillasapp.data.entityUser;

@Database(entities = {entityUser.class},version = 1)
public abstract class ZapatillasAppDatabase extends RoomDatabase {

    public abstract userDao getUserDao();
}
