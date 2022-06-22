package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zapatillasapp.data.Tiendaitem;
import com.example.zapatillasapp.data.entityUser;

@Dao
public interface userDao {

    @Query("SELECT * FROM user WHERE email = :email and password = :password")
    entityUser getentityUser(String email, String password);

    @Insert
    void insert(entityUser user);

    @Delete
    void deleteUser(entityUser user);
}
