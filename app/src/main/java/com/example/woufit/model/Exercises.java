package com.example.woufit.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercises {
    @PrimaryKey(autoGenerate = true)
    private int exsID;
    private String exsName;
    private String exsDescription;
    private String exsExtraTip;
    private String exsBodyPart;
    private String exsQualifier;
    private String exsStartImageSrc;
    private String exsEndImageSrc;

    public Exercises() {
        this.exsID = 0;
        this.exsName = "";
        this.exsDescription = "";
        this.exsExtraTip = "";
        this.exsBodyPart = "";
        this.exsQualifier = "";
        this.exsStartImageSrc = "";
        this.exsEndImageSrc = "";
    }
    public Exercises(int exsID, String exsName, String exsDescription, String exsExtraTip, String exsBodyPart, String exsQualifier, String exsStartImageSrc, String exsEndImageSrc) {
        this.exsID = exsID;
        this.exsName = exsName;
        this.exsDescription = exsDescription;
        this.exsExtraTip = exsExtraTip;
        this.exsBodyPart = exsBodyPart;
        this.exsQualifier = exsQualifier;
        this.exsStartImageSrc = exsStartImageSrc;
        this.exsEndImageSrc = exsEndImageSrc;
    }

    public Exercises(String exsName, String exsDescription, String exsExtraTip, String exsBodyPart, String exsQualifier, String exsStartImageSrc, String exsEndImageSrc) {
        this.exsName = exsName;
        this.exsDescription = exsDescription;
        this.exsExtraTip = exsExtraTip;
        this.exsBodyPart = exsBodyPart;
        this.exsQualifier = exsQualifier;
        this.exsStartImageSrc = exsStartImageSrc;
        this.exsEndImageSrc = exsEndImageSrc;
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

    public String getExsExtraTip() {
        return exsExtraTip;
    }

    public void setExsExtraTip(String exsExtraTip) {
        this.exsExtraTip = exsExtraTip;
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

    public String getExsStartImageSrc() {
        return exsStartImageSrc;
    }

    public void setExsStartImageSrc(String exsStartImageSrc) {
        this.exsStartImageSrc = exsStartImageSrc;
    }

    public String getExsEndImageSrc() {
        return exsEndImageSrc;
    }

    public void setExsEndImageSrc(String exsEndImageSrc) {
        this.exsEndImageSrc = exsEndImageSrc;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "exsID=" + exsID +
                ", exsName='" + exsName + '\'' +
                ", exsDescription='" + exsDescription + '\'' +
                ", exsExtraTip='" + exsExtraTip + '\'' +
                ", exsBodyPart='" + exsBodyPart + '\'' +
                ", exsQualifier='" + exsQualifier + '\'' +
                ", exsStartImageSrc='" + exsStartImageSrc + '\'' +
                ", exsEndImageSrc='" + exsEndImageSrc + '\'' +
                '}';
    }
}
