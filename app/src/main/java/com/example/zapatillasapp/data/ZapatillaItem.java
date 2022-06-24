package com.example.zapatillasapp.data;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "zapatillas",
        foreignKeys = @ForeignKey(
                entity = Tiendaitem.class,
                parentColumns = "pk_tienda",
                childColumns = "tienda_id",
                onDelete = CASCADE
        )
)
public class ZapatillaItem {

    @PrimaryKey
    public int pk_idZapatilla;

    public String nombre;
    public String tallas;
    public String colores;
    public String marcas;

    @ColumnInfo(name = "tienda_id")
    public int fk_tiendaId;

    @Override
    public String toString() {
        return nombre;
    }
}
