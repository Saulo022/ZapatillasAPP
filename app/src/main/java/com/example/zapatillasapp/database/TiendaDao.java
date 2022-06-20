package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.entityUser;

import java.util.List;

@Dao
public interface TiendaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTienda(Tiendaitem tienda);

    @Update
    void updateTienda(Tiendaitem tienda);

    @Delete
    void deleteTienda(Tiendaitem tienda);

    @Query("SELECT * FROM tiendas")
    List<Tiendaitem> loadTiendas();


    @Query("SELECT * FROM tiendas WHERE nombre = :nombre and direccion = :direccion")
    Tiendaitem getTienda(String nombre, String direccion);
}
