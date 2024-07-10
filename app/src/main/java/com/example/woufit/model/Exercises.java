package com.example.woufit.model;

public class Exercises {

    private int exsID;
    private String exsName;
    private String exsCategory;
    private String equipmentNeeded;
    private String difficulty;

    public Exercises() {
        this.exsID = 0;
        this.exsName = "";
        this.exsCategory = "";
        this.equipmentNeeded = "";
        this.difficulty = "";
    }

    public Exercises(int exsID,
                     String exsName,
                     String exsCategory,
                     String equipmentNeeded,
                     String difficulty) {
        this.exsID = exsID;
        this.exsName = exsName;
        this.exsCategory = exsCategory;
        this.equipmentNeeded = equipmentNeeded;
        this.difficulty = difficulty;
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

    @Override
    public String toString() {
        return "Exercises{" +
                "exsID=" + exsID +
                ", exsName='" + exsName + '\'' +
                ", exsCategory='" + exsCategory + '\'' +
                ", equipmentNeeded='" + equipmentNeeded + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
