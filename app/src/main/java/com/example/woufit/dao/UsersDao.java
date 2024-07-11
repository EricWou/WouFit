package com.example.woufit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.woufit.model.Users;

@Dao
public interface UsersDao {

    @Insert
    void createUser(Users user);

    @Query("select userPassword from Users where userEmail = :userEmail")
    String compareLogin(String userEmail);

    @Query("select * from Users where userEmail = :userEmail")
    Users retrieveLoginUser(String userEmail);
}
