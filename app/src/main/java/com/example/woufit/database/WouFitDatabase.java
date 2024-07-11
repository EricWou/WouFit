package com.example.woufit.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.woufit.dao.ExercisesDao;
import com.example.woufit.dao.ParametersDao;
import com.example.woufit.dao.PreferencesDao;
import com.example.woufit.dao.ProgramsDao;
import com.example.woufit.dao.SaltDao;
import com.example.woufit.dao.UsersDao;
import com.example.woufit.model.Exercises;
import com.example.woufit.model.Parameters;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Programs;
import com.example.woufit.model.Salt;
import com.example.woufit.model.Users;

@Database(version = 1, entities = {Users.class,
                                    Preferences.class,
                                    Exercises.class,
                                    Parameters.class,
                                    Salt.class})
//add Programs.class once figuring out TypeConverters and using Relationships
public abstract class WouFitDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
    public abstract PreferencesDao preferencesDao();
    public abstract ExercisesDao exercisesDao();
    public abstract ParametersDao parametersDao();
    //public abstract ProgramsDao programsDao();
    public abstract SaltDao saltDao();

}
