package com.example.woufit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Parameters {

    @PrimaryKey(autoGenerate = true)
    private int paramID;
    private int exsID;
    private String resistance;
    private int repetitions;
    private int sets;
    private int rest;

    public Parameters() {
        this.paramID = 0;
        this.exsID = 0;
        this.resistance = "";
        this.repetitions = 0;
        this.sets = 0;
        this.rest = 0;
    }

    public Parameters(int paramID,
                      int exsID,
                      String resistance,
                      int repetitions,
                      int sets,
                      int rest) {
        this.paramID = paramID;
        this.exsID = exsID;
        this.resistance = resistance;
        this.repetitions = repetitions;
        this.sets = sets;
        this.rest = rest;
    }

    public int getParamID() {
        return paramID;
    }

    public void setParamID(int paramID) {
        this.paramID = paramID;
    }

    public int getExsID() {
        return exsID;
    }

    public void setExsID(int exsID) {
        this.exsID = exsID;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "paramID=" + paramID +
                ", exsID=" + exsID +
                ", resistance='" + resistance + '\'' +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                ", rest=" + rest +
                '}';
    }
}
