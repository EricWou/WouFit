package com.example.woufit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Salt {

    @PrimaryKey(autoGenerate = true)
    private int saltID;
    private String saltString;

    public Salt(String saltString) {
        this.saltString = saltString;
    }

    public int getSaltID() {
        return saltID;
    }

    public void setSaltID(int saltID) {
        this.saltID = saltID;
    }

    public String getSaltString() {
        return saltString;
    }

    public void setSaltString(String saltString) {
        this.saltString = saltString;
    }

    @Override
    public String toString() {
        return "Salt{" +
                "saltID='" + saltID + '\'' +
                ", saltString='" + saltString + '\'' +
                '}';
    }
}
