package com.example.woufit.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Preferences implements Parcelable {

    @PrimaryKey(autoGenerate = true)
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

    protected Preferences(Parcel in) {
        prefID = in.readInt();
        fitnessGoal = in.readString();
        freqPerWeek = in.readInt();
        durationWorkout = in.readInt();
        workoutSpace = in.readString();
        byte tmpWeights = in.readByte();
        weights = tmpWeights == 0 ? null : tmpWeights == 1;
        byte tmpResistanceBands = in.readByte();
        resistanceBands = tmpResistanceBands == 0 ? null : tmpResistanceBands == 1;
        byte tmpMachines = in.readByte();
        machines = tmpMachines == 0 ? null : tmpMachines == 1;
        byte tmpNoEquipment = in.readByte();
        noEquipment = tmpNoEquipment == 0 ? null : tmpNoEquipment == 1;
    }

    public static final Creator<Preferences> CREATOR = new Creator<Preferences>() {
        @Override
        public Preferences createFromParcel(Parcel in) {
            return new Preferences(in);
        }

        @Override
        public Preferences[] newArray(int size) {
            return new Preferences[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(prefID);
        dest.writeString(fitnessGoal);
        dest.writeInt(freqPerWeek);
        dest.writeInt(durationWorkout);
        dest.writeString(workoutSpace);
        dest.writeByte((byte) (weights == null ? 0 : weights ? 1 : 2));
        dest.writeByte((byte) (resistanceBands == null ? 0 : resistanceBands ? 1 : 2));
        dest.writeByte((byte) (machines == null ? 0 : machines ? 1 : 2));
        dest.writeByte((byte) (noEquipment == null ? 0 : noEquipment ? 1 : 2));
    }
}
