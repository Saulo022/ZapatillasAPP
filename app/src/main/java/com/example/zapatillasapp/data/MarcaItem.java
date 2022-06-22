package com.example.zapatillasapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "marcas",
        foreignKeys = @ForeignKey(
                entity = Tiendaitem.class,
                parentColumns = "pk_tienda",
                childColumns = "fk_tiendaId",
                onDelete = CASCADE
        )
)
public class MarcaItem {

    @PrimaryKey
    public int pk_idMarca;

    public String nombre;

    @ColumnInfo(name = "fk_tiendaId")
    public int fk_tiendaID;


    @Override
    public String toString() {
        return nombre;
    }
}
