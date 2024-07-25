package com.example.woufit.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Preferences implements Parcelable {

    private int freqPerWeek;
    private int durationWorkout;

    public Preferences() {
        this.freqPerWeek = 0;
        this.durationWorkout = 0;
    }

    public Preferences(int freqPerWeek, int durationWorkout) {
        this.freqPerWeek = freqPerWeek;
        this.durationWorkout = durationWorkout;
    }

    protected Preferences(Parcel in) {
        freqPerWeek = in.readInt();
        durationWorkout = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(freqPerWeek);
        dest.writeInt(durationWorkout);
    }

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "Preferences{" +
                "freqPerWeek=" + freqPerWeek +
                ", durationWorkout=" + durationWorkout +
                '}';
    }
}
