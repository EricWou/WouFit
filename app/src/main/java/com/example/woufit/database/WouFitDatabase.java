package com.example.woufit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.woufit.dao.ExercisesDao;
import com.example.woufit.model.Exercises;
import com.example.woufit.model.Parameters;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;

@Database(version = 1, entities = {Exercises.class})
//add Programs.class once figuring out TypeConverters and using Relationships
public abstract class WouFitDatabase extends RoomDatabase {
    public abstract ExercisesDao exercisesDao();

}
