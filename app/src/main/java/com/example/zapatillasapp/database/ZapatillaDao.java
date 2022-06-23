package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.zapatillasapp.data.ZapatillaItem;

import java.util.List;

@Dao
public interface ZapatillaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertZapatilla(ZapatillaItem zapatilla);

    @Update
    void updateZapatilla(ZapatillaItem zapatilla);

    @Delete
    void deleteZapatilla(ZapatillaItem zapatilla);

    @Query("SELECT * FROM zapatillas")
    List<ZapatillaItem> loadZapatillas();

    @Query("SELECT * FROM zapatillas WHERE pk_idZapatilla = :pk_idZapatilla LIMIT 1")
    ZapatillaItem loadProduct(int pk_idZapatilla);

    @Query("SELECT * FROM zapatillas WHERE tienda_id=:fk_tiendaId")
    List<ZapatillaItem> loadProducts(final int fk_tiendaId);

    /*
    @Query("SELECT marcas FROM  zapatillas WHERE tienda_id=:pk_tienda ")
    List<ZapatillaItem> loadMarcas(final int pk_tienda);*/
}

