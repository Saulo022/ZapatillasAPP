package com.example.zapatillasapp.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.data.entityUser;

@Database(
        entities = {entityUser.class, Tiendaitem.class, ZapatillaItem.class, MarcaItem.class},
        version = 4
   )

public abstract class ZapatillasAppDatabase extends RoomDatabase {


    public abstract userDao getUserDao();

    public abstract TiendaDao tiendaDao();
    public abstract ZapatillaDao zapatillaDao();
    public abstract MarcaDao marcaDao();
}
