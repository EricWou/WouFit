package com.example.woufit.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Arrays;
import java.util.List;
import com.example.woufit.dao.ExercisesDao;
import com.example.woufit.model.Exercises;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, entities = {Exercises.class})
public abstract class WouFitDatabase extends RoomDatabase {

    public abstract ExercisesDao exercisesDao();

    /*
    private static volatile WouFitDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static WouFitDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (WouFitDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    private static WouFitDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        WouFitDatabase.class, "WouFit.db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // Insert the data on the IO Thread
                        databaseWriteExecutor.execute(() -> {
                            WouFitDatabase database = getInstance(context);
                            database.exercisesDao().addListExercise(PREPOPULATE_DATA);
                        });
                    }
                })
                .build();
    }

    private static final List<Exercises> PREPOPULATE_DATA = Arrays.asList(
            new Exercises("Squat",
                    "Stand with your feet shoulder width apart, squat down as low as you can and come back up",
                    "You can start with your feet pointing outwards if it feels more comfortable. Too easy? Try with holding weights",
                    "lower body",
                    "push",
                    "squatStart.jpg",
                    "squatEnd.jpg"),

            new Exercises("Deadlift",
                    "Stand with your feet shoulder width apart, bend down as low as you can while keeping your back straight and come back up",
                    "You can start with your feet more spread out if it feels more comfortable. Too difficult? Start with no weight in your hands",
                    "lower body",
                    "pull",
                    "deadliftStart.jpg",
                    "deadliftEnd.jpg"),

            new Exercises("Lunge",
                    "Stand with your feet shoulder width apart, then place one foot forward. Try to bend down as if to touch your knee to the floor and come back up",
                    "Too difficult? Place your feet closer together. Too easy? Place your rear foot on a chair or other elevated object",
                    "lower body",
                    "push",
                    "lungeStart.jpg",
                    "lungeEnd.jpg"),

            new Exercises("Knee raise",
                    "With your feet shoulder width apart, lift one leg up to the sky and back down, alternate legs",
                    "If it is more comfortable, you can lift the leg to the side",
                    "lower body",
                    "",
                    "kneeRaiseStart.jpg",
                    "kneeRaiseEnd.jpg"),

            new Exercises("Glut Bridge",
                    "Lying on your back with your knees bent, lift your bum as high up as you can and back down",
                    "Too difficult? Limit how high up you go. Too easy? Start with your feet further away from you",
                    "lower body",
                    "",
                    "glutBridgeStart.jpg",
                    "glutBridgeEnd.jpg"),

            new Exercises("Push-up",
                    "With your hands shoulder width apart, lower yourself to the ground as far as you can and then come back up",
                    "You can start with your hands further apart if it feels more comfortable. Too difficult? You can place your knees on the floor instead of your feet",
                    "upper body",
                    "push",
                    "pushupStart.jpg",
                    "pushupEnd.jpg"),

            new Exercises("Dip",
                    "With your hands on a chair or other stable surface behind you and your feet in front, lower yourself to the ground as far as you can and then come back up",
                    "Too difficult? Start with your knees bent. Too easy? Place your feet as far forward as possible",
                    "upper body",
                    "push",
                    "dipStart.jpg",
                    "dipEnd.jpg"),

            new Exercises("Bicep curl",
                    "Keeping your elbows by your side, lift your hand up towards the sky",
                    "You can do either alternating one side at a time or both at the same time. Too difficult? Start with no weight in your hands ",
                    "upper body",
                    "pull",
                    "bicepCurlStart.jpg",
                    "bicepCurlEnd.jpg"),

            new Exercises("Front raise",
                    "Start with your arms by your side, lift them up in front of you until about 90 degrees",
                    "You can do either alternating one side at a time or both at the same time. Too difficult? Start with no weight in your hands ",
                    "upper body",
                    "pull",
                    "frontRaiseStart.jpg",
                    "frontRaiseEnd.jpg"),

            new Exercises("Lateral raise",
                    "With your feet shoulder width apart, raise your arms to the side and then back down",
                    "You can bring your arms more forward if it feels more comfortable. Too difficult? Start with no weight in your hands",
                    "upper body",
                    "shoulder",
                    "lateralRaiseStart.jpg",
                    "lateralRaiseEnd.jpg"),

            new Exercises("Shoulder press",
                    "With your feet shoulder width apart, lift your arms straight up towards the sky and then back down",
                    "Too difficult? Start with no weight in your hands",
                    "upper body",
                    "shoulder",
                    "shoulderPressStart.jpg",
                    "shoulderPressEnd.jpg"),

            new Exercises("Russian twist",
                    "Sitting on your bum, rotate your trunk to one side and then to the other",
                    "Too difficult? Keep your feet on the floor. Too easy? Perform the movement while keeping your feet off the floor ",
                    "core",
                    "",
                    "russianTwistStart.jpg",
                    "russianTwistEnd.jpg"),

            new Exercises("Crunch",
                    "Lying on our back with your knees bent, lift your trunk to reach for your knees and then come back down",
                    "Too difficult? Only reach towards your thighs",
                    "core",
                    "",
                    "crunchStart.jpg",
                    "crunchEnd.jpg"),

            new Exercises("Leg flutters",
                      "Lying on our back, alternate lifting one leg up and down",
                      "Too difficult? Do the movement with your knees bent. Too easy? Try and raise your trunk while performing the movement",
                      "core",
                      "",
                      "legFlutterStart.jpg",
                      "legFlutterEnd.jpg")
    );

     */

}
