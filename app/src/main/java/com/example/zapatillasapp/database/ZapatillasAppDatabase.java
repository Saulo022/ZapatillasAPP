package com.example.zapatillasapp.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.data.entityUser;

@Database(entities = {entityUser.class, Tiendaitem.class, ZapatillaItem.class},version = 2)
public abstract class ZapatillasAppDatabase extends RoomDatabase {

    public abstract userDao getUserDao();

    public abstract TiendaDao tiendaDao();
    public abstract ZapatillaDao zapatillaDao();
}
