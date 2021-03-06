package com.example.zapatillasapp.data;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "tiendas")
public class Tiendaitem {

    @PrimaryKey
    public int pk_tienda;

    public String nombre;
    public String direccion;


    @Ignore
    @SerializedName("zapatillas")
    public List<ZapatillaItem> items;

    @Ignore
    @SerializedName("marcas")
    public List<MarcaItem> marcaItems;

    @Override
    public String toString() {
        return nombre;
    }
}
