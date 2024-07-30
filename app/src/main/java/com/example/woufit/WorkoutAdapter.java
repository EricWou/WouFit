package com.example.woufit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woufit.model.Exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>{

    //sets the adapter for ViewPager2 (to allow for horizontal swiping between workouts)

    private final HashMap<String, HashMap<String, Integer>> workouts;

    public WorkoutAdapter(HashMap<String, HashMap<String, Integer>> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item_workout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {

        //gets the name of the workout
        String workoutName = (String) workouts.keySet().toArray()[position];

        //gets the map of exercises related to that workout
        HashMap<String, Integer> exercisesMap = workouts.get(workoutName);

        holder.mainWorkoutTitleTextView.setText(workoutName);

        ArrayList<String> exerciseList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : exercisesMap.entrySet()) {
            Integer exsID = entry.getValue();
            String exsName = "";

            switch(exsID) {
                case 1:
                    exsName = "Squat";
                    break;
                case 2:
                    exsName = "Lunge";
                    break;
                case 3:
                    exsName = "Deadlift";
                    break;
                case 4:
                    exsName = "Glut bridge";
                    break;
                case 5:
                    exsName = "Calf raise";
                    break;
                case 6:
                    exsName = "Knee raise";
                    break;
                case 7:
                    exsName = "Push-up";
                    break;
                case 8:
                    exsName = "Dip";
                    break;
                case 9:
                    exsName = "Bicep curl";
                    break;
                case 10:
                    exsName = "Front raise";
                    break;
                case 11:
                    exsName = "Lateral raise";
                    break;
                case 12:
                    exsName = "Shoulder press";
                    break;
                case 13:
                    exsName = "Russian twist";
                    break;
                case 14:
                    exsName = "Crunch";
                    break;
                case 15:
                    exsName = "Leg flutters";
                    break;
            }

            Log.d("WorkoutAdapter", exsName);
            if (!exsName.isEmpty()) {
                exerciseList.add(exsName);
            }
        }

        //exerciseList is a list of String exsName
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(exerciseList);

        //sets the adapter for RecyclerView (to allow vertical scrolling for list of exercises)
        holder.mainExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.mainExerciseRecyclerView.setAdapter(exerciseAdapter);

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {

        //ViewHolder for workout title and workout RecyclerView

        TextView mainWorkoutTitleTextView;
        RecyclerView mainExerciseRecyclerView;

        WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            mainWorkoutTitleTextView = itemView.findViewById(R.id.main_workout_title_textView);
            mainExerciseRecyclerView = itemView.findViewById(R.id.main_exercise_recyclerView);
        }
    }
}
