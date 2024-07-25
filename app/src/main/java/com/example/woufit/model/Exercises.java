package com.example.woufit.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercises {
    @PrimaryKey(autoGenerate = true)
    private int exsID;
    private String exsName;
    private String exsDescription;
    private String exsBodyPart;
    private String exsQualifier;
    private String exsImageSrc;

    public Exercises() {
        this.exsID = 0;
        this.exsName = "";
        this.exsDescription = "";
        this.exsBodyPart = "";
        this.exsQualifier = "";
        this.exsImageSrc = "";
    }
    public Exercises(int exsID, String exsName, String exsDescription, String exsBodyPart, String exsQualifier, String exsImageSrc) {
        this.exsID = exsID;
        this.exsName = exsName;
        this.exsDescription = exsDescription;
        this.exsBodyPart = exsBodyPart;
        this.exsQualifier = exsQualifier;
        this.exsImageSrc = exsImageSrc;
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

    public String getExsDescription() {
        return exsDescription;
    }

    public void setExsDescription(String exsDescription) {
        this.exsDescription = exsDescription;
    }

    public String getExsBodyPart() {
        return exsBodyPart;
    }

    public void setExsBodyPart(String exsBodyPart) {
        this.exsBodyPart = exsBodyPart;
    }

    public String getExsQualifier() {
        return exsQualifier;
    }

    public void setExsQualifier(String exsQualifier) {
        this.exsQualifier = exsQualifier;
    }

    public String getExsImageSrc() {
        return exsImageSrc;
    }

    public void setExsImageSrc(String exsImageSrc) {
        this.exsImageSrc = exsImageSrc;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "exsID=" + exsID +
                ", exsName='" + exsName + '\'' +
                ", exsDescription='" + exsDescription + '\'' +
                ", exsBodyPart='" + exsBodyPart + '\'' +
                ", exsQualifier='" + exsQualifier + '\'' +
                ", exsImageSrc='" + exsImageSrc + '\'' +
                '}';
    }
}
