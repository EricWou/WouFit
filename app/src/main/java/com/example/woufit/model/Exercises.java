package com.example.woufit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercises {

    @PrimaryKey(autoGenerate = true)
    private int exsID;
    private String exsName;
    private String exsCategory;
    private String equipmentNeeded;
    private String difficulty;
    private String primaryMuscle;
    private String secondaryMuscle;

    public Exercises() {
        this.exsID = 0;
        this.exsName = "";
        this.exsCategory = "";
        this.equipmentNeeded = "";
        this.difficulty = "";
        this.primaryMuscle = "";
        this.secondaryMuscle = "";
    }

    public Exercises(int exsID,
                     String exsName,
                     String exsCategory,
                     String equipmentNeeded,
                     String difficulty,
                     String primaryMuscle,
                     String secondaryMuscle) {
        this.exsID = exsID;
        this.exsName = exsName;
        this.exsCategory = exsCategory;
        this.equipmentNeeded = equipmentNeeded;
        this.difficulty = difficulty;
        this.primaryMuscle = primaryMuscle;
        this.secondaryMuscle = secondaryMuscle;
    }

    public int getExsID() {
        return exsID;
    }

    public void setExsID(int exsID) {
        this.exsID = exsID;
    }

    public String getExsName() {
        return exsName;
    }

    public void setExsName(String exsName) {
        this.exsName = exsName;
    }

    public String getExsCategory() {
        return exsCategory;
    }

    public void setExsCategory(String exsCategory) {
        this.exsCategory = exsCategory;
    }

    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrimaryMuscle() {
        return primaryMuscle;
    }

    public void setPrimaryMuscle(String primaryMuscle) {
        this.primaryMuscle = primaryMuscle;
    }

    public String getSecondaryMuscle() {
        return secondaryMuscle;
    }

    public void setSecondaryMuscle(String secondaryMuscle) {
        this.secondaryMuscle = secondaryMuscle;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "exsID=" + exsID +
                ", exsName='" + exsName + '\'' +
                ", exsCategory='" + exsCategory + '\'' +
                ", equipmentNeeded='" + equipmentNeeded + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", primaryMuscle='" + primaryMuscle + '\'' +
                ", secondaryMuscle='" + secondaryMuscle + '\'' +
                '}';
    }
}
