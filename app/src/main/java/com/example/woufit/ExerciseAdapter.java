package com.example.woufit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woufit.model.Exercises;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>{

    //sets the adapter for RecyclerView (to allow vertical scrolling for list of exercises)

    private final ArrayList<String> exerciseList;

    public ExerciseAdapter(ArrayList<String> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ExerciseViewHolder holder, int position) {

        //sets the exercise inside ViewHolder
        String exsName = exerciseList.get(position);
        holder.mainExerciseTextView.setText(exsName);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        //ViewHolder for TextView inside of RecyclerView for individual exercises

        TextView mainExerciseTextView;

        ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            mainExerciseTextView = itemView.findViewById(R.id.main_item_exercise_textView);
        }
    }
}
