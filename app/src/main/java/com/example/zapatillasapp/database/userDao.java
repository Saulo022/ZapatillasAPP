package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.zapatillasapp.data.entityUser;

@Dao
public interface userDao {

    @Query("SELECT * FROM entityUser WHERE email = :email and password = :password")
    entityUser getentityUser(String email, String password);

    @Insert
    void insert(entityUser user);
}
