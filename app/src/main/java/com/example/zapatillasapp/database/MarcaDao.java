package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.zapatillasapp.data.MarcaItem;

import java.util.List;

@Dao
public interface MarcaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMarca(MarcaItem marca);

    @Update
    void updateMarca(MarcaItem marca);

    @Delete
    void deleteMarca(MarcaItem marca);

    @Query("SELECT * FROM marcas WHERE fk_tiendaId=:fk_tiendaID")
    List<MarcaItem> loadMarcas(final int fk_tiendaID);
}
