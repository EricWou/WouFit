package com.example.woufit.database;

public class WouFitDbSchema {

    public static final class UsersTable {

        public static final String NAME = "users";

        public static final class Columns {

            public static final String USER_ID = "userID";
            public static final String USER_FNAME = "userFName";
            public static final String USER_LNAME = "userLName";
            public static final String USER_EMAIL = "userEmail";
            public static final String USER_PASSWORD = "userPassword";
            public static final String FIRST_LOGIN = "firstLogin";
            public static final String PREF_ID = "prefID";
            public static final String PROGRAM_ID = "programID";
        }
    }

    public static final class PreferencesTable {

        public static final String NAME = "preferences";

        public static final class Columns {

            public static final String PREF_ID = "prefID";
            public static final String FITNESS_GOAL = "fitnessGoal";
            public static final String FREQ_PER_WEEK = "freqPerWeek";
            public static final String DURATION_WORKOUT = "durationWorkout";
            public static final String WORKOUT_SPACE = "workoutSpace";
            public static final String WEIGHTS = "weights";
            public static final String RESISTANCE_BANDS = "resistanceBands";
            public static final String MACHINES = "machines";
            public static final String NO_EQUIPMENT = "noEquipment";
        }
    }

    public static final class ExercisesTable {

        public static final String NAME = "exercises";

        public static final class Columns {

            public static final String EXS_ID = "exsID";
            public static final String EXS_NAME = "exsName";
            public static final String EXS_CATEGORY = "exsCategory";
            public static final String EQUIPMENT_NEEDED = "equipmentNeeded";
            public static final String DIFFICULTY = "difficulty";
        }
    }

    public static final class ParametersTable {

        public static final String NAME = "parameters";

        public static final class Columns {

            public static final String PARAM_ID = "paramID";
            public static final String EXS_ID = "exsID";
            public static final String RESISTANCE = "resistance";
            public static final String REPETITIONS = "repetitions";
            public static final String SETS = "sets";
            public static final String REST = "rest";
        }
    }

    public static final class ProgramsTable {

        public static final String NAME = "programs";

        public static final class Columns {

            public static final String PROGRAM_ID = "programID";
            public static final String WORKOUT_1 = "workout1";
            public static final String WORKOUT_2 = "workout2";
            public static final String WORKOUT_3 = "workout3";
            public static final String WORKOUT_4 = "workout4";
            public static final String WORKOUT_5 = "workout5";
            public static final String WORKOUT_6 = "workout6";
            public static final String WORKOUT_7 = "workout7";
        }
    }
}
