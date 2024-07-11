package com.example.woufit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

//@Entity
public class Programs {

    //@PrimaryKey(autoGenerate = true)
    private int programID;
    private ArrayList<Integer> workout1;
    private ArrayList<Integer> workout2;
    private ArrayList<Integer> workout3;
    private ArrayList<Integer> workout4;
    private ArrayList<Integer> workout5;
    private ArrayList<Integer> workout6;
    private ArrayList<Integer> workout7;

    public Programs() {
        this.programID = 0;
        this.workout1 = new ArrayList<>();
        this.workout2 = new ArrayList<>();
        this.workout3 = new ArrayList<>();
        this.workout4 = new ArrayList<>();
        this.workout5 = new ArrayList<>();
        this.workout6 = new ArrayList<>();
        this.workout7 = new ArrayList<>();
    }

    public Programs(int programID,
                    ArrayList<Integer> workout1,
                    ArrayList<Integer> workout2,
                    ArrayList<Integer> workout3,
                    ArrayList<Integer> workout4,
                    ArrayList<Integer> workout5,
                    ArrayList<Integer> workout6,
                    ArrayList<Integer> workout7) {
        this.programID = programID;
        this.workout1 = workout1;
        this.workout2 = workout2;
        this.workout3 = workout3;
        this.workout4 = workout4;
        this.workout5 = workout5;
        this.workout6 = workout6;
        this.workout7 = workout7;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public ArrayList<Integer> getWorkout1() {
        return workout1;
    }

    public void setWorkout1(ArrayList<Integer> workout1) {
        this.workout1 = workout1;
    }

    public ArrayList<Integer> getWorkout2() {
        return workout2;
    }

    public void setWorkout2(ArrayList<Integer> workout2) {
        this.workout2 = workout2;
    }

    public ArrayList<Integer> getWorkout3() {
        return workout3;
    }

    public void setWorkout3(ArrayList<Integer> workout3) {
        this.workout3 = workout3;
    }

    public ArrayList<Integer> getWorkout4() {
        return workout4;
    }

    public void setWorkout4(ArrayList<Integer> workout4) {
        this.workout4 = workout4;
    }

    public ArrayList<Integer> getWorkout5() {
        return workout5;
    }

    public void setWorkout5(ArrayList<Integer> workout5) {
        this.workout5 = workout5;
    }

    public ArrayList<Integer> getWorkout6() {
        return workout6;
    }

    public void setWorkout6(ArrayList<Integer> workout6) {
        this.workout6 = workout6;
    }

    public ArrayList<Integer> getWorkout7() {
        return workout7;
    }

    public void setWorkout7(ArrayList<Integer> workout7) {
        this.workout7 = workout7;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programID=" + programID +
                ", workout1=" + workout1 +
                ", workout2=" + workout2 +
                ", workout3=" + workout3 +
                ", workout4=" + workout4 +
                ", workout5=" + workout5 +
                ", workout6=" + workout6 +
                ", workout7=" + workout7 +
                '}';
    }
}
