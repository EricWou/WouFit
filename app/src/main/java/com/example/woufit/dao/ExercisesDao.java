package com.example.woufit.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.woufit.model.Exercises;

@Dao
public interface ExercisesDao {

    @Query("Select * from Exercises")
    public Exercises[] selectAllExercises();

    @Query("Select * from Exercises where exsID = :exsID")
    public Exercises findExercise(int exsID);
}
