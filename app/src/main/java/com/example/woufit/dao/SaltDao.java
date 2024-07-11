package com.example.woufit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.woufit.model.Salt;

@Dao
public interface SaltDao {

    @Insert
    void createSaltString(Salt salt);

    @Query("select saltString from salt where saltID = 1")
    String readSaltString();
}
