package com.example.zapatillasapp.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.zapatillasapp.data.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);
}
