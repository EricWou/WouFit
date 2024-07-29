package com.example.woufit.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.woufit.model.Exercises;

import java.util.List;

@Dao
public interface ExercisesDao {

    @Insert
    void addExercise(Exercises exercise);

    @Insert
    void addListExercise(List<Exercises> exerciseList);

    @Query("Select * from Exercises")
    List<Exercises> selectAllExercises();

    @Query("Select * from Exercises where exsID = :exsID")
    Exercises findExercise(int exsID);
}
