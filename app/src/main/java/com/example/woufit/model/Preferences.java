package com.example.woufit.model;

public class Preferences {

    private int prefID;
    private String fitnessGoal;
    private int freqPerWeek;
    private int durationWorkout;
    private String workoutSpace;
    private Boolean weights;
    private Boolean resistanceBands;
    private Boolean machines;
    private Boolean noEquipment;

    public Preferences() {
        this.prefID = 0;
        this.fitnessGoal = "";
        this.freqPerWeek = 0;
        this.durationWorkout = 0;
        this.workoutSpace = "";
        this.weights = false;
        this.resistanceBands = false;
        this.machines = false;
        this.noEquipment = false;
    }

    public Preferences(int prefID,
                       String fitnessGoal,
                       int freqPerWeek,
                       int durationWorkout,
                       String workoutSpace,
                       Boolean weights,
                       Boolean resistanceBands,
                       Boolean machines,
                       Boolean noEquipment) {
        this.prefID = prefID;
        this.fitnessGoal = fitnessGoal;
        this.freqPerWeek = freqPerWeek;
        this.durationWorkout = durationWorkout;
        this.workoutSpace = workoutSpace;
        this.weights = weights;
        this.resistanceBands = resistanceBands;
        this.machines = machines;
        this.noEquipment = noEquipment;
    }

    public int getPrefID() {
        return prefID;
    }

    public void setPrefID(int prefID) {
        this.prefID = prefID;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public int getFreqPerWeek() {
        return freqPerWeek;
    }

    public void setFreqPerWeek(int freqPerWeek) {
        this.freqPerWeek = freqPerWeek;
    }

    public int getDurationWorkout() {
        return durationWorkout;
    }

    public void setDurationWorkout(int durationWorkout) {
        this.durationWorkout = durationWorkout;
    }

    public String getWorkoutSpace() {
        return workoutSpace;
    }

    public void setWorkoutSpace(String workoutSpace) {
        this.workoutSpace = workoutSpace;
    }

    public Boolean getWeights() {
        return weights;
    }

    public void setWeights(Boolean weights) {
        this.weights = weights;
    }

    public Boolean getResistanceBands() {
        return resistanceBands;
    }

    public void setResistanceBands(Boolean resistanceBands) {
        this.resistanceBands = resistanceBands;
    }

    public Boolean getMachines() {
        return machines;
    }

    public void setMachines(Boolean machines) {
        this.machines = machines;
    }

    public Boolean getNoEquipment() {
        return noEquipment;
    }

    public void setNoEquipment(Boolean noEquipment) {
        this.noEquipment = noEquipment;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "prefID=" + prefID +
                ", fitnessGoal='" + fitnessGoal + '\'' +
                ", freqPerWeek=" + freqPerWeek +
                ", durationWorkout=" + durationWorkout +
                ", workoutSpace='" + workoutSpace + '\'' +
                ", weights=" + weights +
                ", resistanceBands=" + resistanceBands +
                ", machines=" + machines +
                ", noEquipment=" + noEquipment +
                '}';
    }
}
