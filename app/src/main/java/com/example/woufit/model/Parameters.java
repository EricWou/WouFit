package com.example.woufit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Parameters {

    private String resistance;
    private int repetitions;
    private int sets;

    public Parameters() {
        this.resistance = "";
        this.repetitions = 0;
        this.sets = 0;
    }

    public Parameters(String resistance, int repetitions, int sets) {
        this.resistance = resistance;
        this.repetitions = repetitions;
        this.sets = sets;
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

    @Override
    public String toString() {
        return "Parameters{" +
                "resistance='" + resistance + '\'' +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                '}';
    }
}
