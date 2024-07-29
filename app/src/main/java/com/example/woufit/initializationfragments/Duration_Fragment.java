package com.example.woufit.initializationfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.woufit.MainActivity;
import com.example.woufit.R;
import com.example.woufit.StartActivity;
import com.example.woufit.account_initialization;
import com.example.woufit.dao.ExercisesDao;
import com.example.woufit.database.WouFitDatabase;
import com.example.woufit.model.Exercises;
import com.example.woufit.model.Preferences;
import com.example.woufit.model.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Duration_Fragment extends Fragment {

    private RadioGroup durationFragmentRadioGroup;
    private RadioButton firstRadioButton;
    private RadioButton secondRadioButton;
    private RadioButton thirdRadioButton;
    private Button durationFragmentNextButton;
    private Users currentUser;
    private String userID;
    private Preferences preferences;
    DatabaseReference databaseRef;
    private WouFitDatabase sqlDB;
    private ExercisesDao exercisesDao;

    public Duration_Fragment() {
        super(R.layout.init_duration_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //retrieving bundle data from previous fragment
        currentUser = requireArguments().getParcelable("user");
        userID = requireArguments().getString("userID");
        preferences = requireArguments().getParcelable("preferences");

        findViews(view);

        durationFragmentNextButton.setOnClickListener(v -> {
            //checks to see if there are no checked Radio Buttons belonging to the Radio Group
            if (durationFragmentRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Please select one of the options" ,
                        Toast.LENGTH_SHORT).show();
            }
            else {
                if (firstRadioButton.isChecked()) {
                    preferences.setDurationWorkout(20);
                }
                else if (secondRadioButton.isChecked()) {
                    preferences.setDurationWorkout(30);
                }
                else if (thirdRadioButton.isChecked()) {
                    preferences.setDurationWorkout(45);
                }

                //saves preferences under user object
                databaseRef = FirebaseDatabase.getInstance().getReference();
                databaseRef.child("Users")
                            .child(userID)
                            .child("Preferences")
                            .setValue(preferences);

                //to update, needs to be in the form of a map so that there is key-value pairs
                currentUser.setFirstLogin(false);
                Map<String, Object> updateUser = new HashMap<>();
                updateUser.put("firstLogin", currentUser.getFirstLogin());

                databaseRef.child("Users")
                            .child(userID)
                            .updateChildren(updateUser);

                //generates the workouts based on user preferences
                createProgram(databaseRef, userID, preferences);

                //sending along just userID to MainActivity (similar to StartActivity flow)
                Bundle bundle = new Bundle();
                bundle.putString("userID", userID);

                Intent nextActivity = new Intent(getActivity(), MainActivity.class);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            }
        });

    }

    //used to create programs for the user once all the preferences are selected
    private void createProgram(DatabaseReference databaseRef, String userID, Preferences preferences) {
        int numberOfWorkouts = preferences.getFreqPerWeek();
        int durationOfWorkout = preferences.getDurationWorkout();

        //creating connection to sqlDB
        sqlDB = Room.databaseBuilder(getContext(), WouFitDatabase.class,"woufit.db").build();

        //accessing exercisesDao
        exercisesDao = sqlDB.exercisesDao();
        //List<Exercises> exerciseList = new ArrayList<>();
        ListWrapper wrapper = new ListWrapper();

        execute(() -> {
            wrapper.list = exercisesDao.selectAllExercises();
        });
        ArrayList<Exercises> exerciseArrayList = new ArrayList<>(wrapper.list);

        //initializing data if first run
        if (exerciseArrayList.isEmpty()) {
            initializeExerciseData(exercisesDao);
            execute(() -> {
                wrapper.list = exercisesDao.selectAllExercises();
            });
            exerciseArrayList = new ArrayList<>(wrapper.list);
        }

        //creates a number of workouts depending on user preference
        for (int i=0; i<numberOfWorkouts; i++) {
            Map<String, Integer> addWorkout = new HashMap<>(generateWorkouts(durationOfWorkout, exerciseArrayList));

            databaseRef.child("Users")
                    .child(userID)
                    .child("Program")
                    .child("Workout"+(i+1))
                    .setValue(addWorkout);
        }
    }

    //part of createProgram method
    private Map<String, Integer> generateWorkouts(int durationOfWorkout,
                                                      ArrayList<Exercises> exerciseArrayList) {

        Map<String, Integer> newWorkout = new HashMap<>();

        //randomizes the order of the exerciseArrayList
        Collections.shuffle(exerciseArrayList);

        //defining the limit of each exercise group and each exercise qualifier
        //per exercise duration
        int upperBodyCount = 0;
        int lowerBodyCount = 0;
        int coreCount = 0;
        int upperPushCount = 1;
        int upperPullCount = 1;
        int shoulderCount = 1;
        int lowerPushCount = 1;
        int lowerPullCount = 1;

        if (durationOfWorkout == 20) {
            upperBodyCount = 1;
            lowerBodyCount = 1;
            coreCount = 1;
        }
        else if (durationOfWorkout == 30) {
            upperBodyCount = 2;
            lowerBodyCount = 2;
            coreCount = 1;
        }
        else if (durationOfWorkout == 45) {
            upperBodyCount = 3;
            lowerBodyCount = 3;
            coreCount = 2;
        }

        //adds exercises to the HashMap but making sure to not exceed the limit
        //per exercise group and per exercise qualifier
        for (int i=0, k=1; i<exerciseArrayList.size(); i++) {
            if (exerciseArrayList.get(i).getExsBodyPart().equals("upper body")) {
                if (upperBodyCount > 0) {
                    if (exerciseArrayList.get(i).getExsQualifier().equals("push") && upperPushCount > 0) {
                        upperPushCount--;
                    }
                    else if (exerciseArrayList.get(i).getExsQualifier().equals("pull") && upperPullCount > 0) {
                        upperPullCount--;
                    }
                    else if (exerciseArrayList.get(i).getExsQualifier().equals("shoulder") && shoulderCount > 0) {
                        shoulderCount--;
                    }
                    newWorkout.put("exercise"+(k), exerciseArrayList.get(i).getExsID());
                    upperBodyCount--;
                    k++;
                }
            }
            else if (exerciseArrayList.get(i).getExsBodyPart().equals("lower body")) {
                if (lowerBodyCount > 0) {
                    if (exerciseArrayList.get(i).getExsQualifier().equals("push") && lowerPushCount > 0) {
                        lowerPushCount--;
                    }
                    else if (exerciseArrayList.get(i).getExsQualifier().equals("pull") && lowerPullCount > 0) {
                        lowerPullCount--;
                    }
                    newWorkout.put("exercise"+(k), exerciseArrayList.get(i).getExsID());
                    lowerBodyCount--;
                    k++;
                }
            }
            else if (exerciseArrayList.get(i).getExsBodyPart().equals("core")) {
                if (coreCount > 0) {
                    newWorkout.put("exercise"+(k), exerciseArrayList.get(i).getExsID());
                    coreCount--;
                    k++;
                }
            }
        }

        return newWorkout;
    }

    //wrapper class to hold the exercise list
    class ListWrapper {
        List<Exercises> list = new ArrayList<>();
    }

    public void execute(Runnable command) {
        Thread thread = new Thread(command);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeExerciseData(ExercisesDao exercisesDao) {
        execute(() -> {
            exercisesDao.addListExercise(exerciseDataV1);
        });
    }

    private void findViews(View view) {
        durationFragmentRadioGroup = view.findViewById(R.id.duration_fragment_radio_group);
        firstRadioButton = view.findViewById(R.id.first_radio_button);
        secondRadioButton = view.findViewById(R.id.second_radio_button);
        thirdRadioButton = view.findViewById(R.id.third_radio_button);
        durationFragmentNextButton = view.findViewById(R.id.duration_fragment_next_button);
    }

    //initial database data
    private static final List<Exercises> exerciseDataV1 = Arrays.asList(
            new Exercises("Squat",
                    "Stand with your feet shoulder width apart, squat down as low as you can and come back up",
                    "You can start with your feet pointing outwards if it feels more comfortable. Too easy? Try with holding weights",
                    "lower body",
                    "push",
                    "squatStart.jpg",
                    "squatEnd.jpg"),

            new Exercises("Lunge",
                    "Stand with your feet shoulder width apart, then place one foot forward. Try to bend down as if to touch your knee to the floor and come back up",
                    "Too difficult? Place your feet closer together. Too easy? Place your rear foot on a chair or other elevated object",
                    "lower body",
                    "push",
                    "lungeStart.jpg",
                    "lungeEnd.jpg"),

            new Exercises("Deadlift",
                    "Stand with your feet shoulder width apart, bend down as low as you can while keeping your back straight and come back up",
                    "You can start with your feet more spread out if it feels more comfortable. Too difficult? Start with no weight in your hands",
                    "lower body",
                    "pull",
                    "deadliftStart.jpg",
                    "deadliftEnd.jpg"),

            new Exercises("Glut Bridge",
                    "Lying on your back with your knees bent, lift your bum as high up as you can and back down",
                    "Too difficult? Limit how high up you go. Too easy? Start with your feet further away from you",
                    "lower body",
                    "pull",
                    "glutBridgeStart.jpg",
                    "glutBridgeEnd.jpg"),

            new Exercises("Calf raise",
                    "Stand with your feet shoulder width apart, rise up onto your toes as high as possible and come back down",
                    "Too difficult? Place your hands on the wall for support. Too easy? Perform the movement on only one foot",
                    "lower body",
                    "",
                    "calfRaiseStart.jpg",
                    "calfRaiseEnd.jpg"),

            new Exercises("Knee raise",
                    "With your feet shoulder width apart, lift one leg up to the sky and back down, alternate legs",
                    "If it is more comfortable, you can lift the leg to the side",
                    "lower body",
                    "",
                    "kneeRaiseStart.jpg",
                    "kneeRaiseEnd.jpg"),

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
}
