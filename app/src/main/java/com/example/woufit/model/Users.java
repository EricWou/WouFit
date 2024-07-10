package com.example.woufit.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Users implements Parcelable {

    private int userID;
    private String userFName;
    private String userLName;
    private String userEmail;
    private String userPassword;
    //change to hashcode for better security
    private Boolean firstLogin;
    private int prefID;
    private int programID;
    private Parcel dest;
    private int flags;

    public Users() {
        this.userID = 0;
        this.userFName = "";
        this.userLName = "";
        this.userEmail = "";
        this.userPassword = "";
        this.firstLogin = false;
        this.prefID = 0;
        this.programID = 0;
    }

    public Users(int userID,
                 String userFName,
                 String userLName,
                 String userEmail,
                 String userPassword,
                 Boolean firstLogin,
                 int prefID,
                 int programID) {
        this.userID = userID;
        this.userFName = userFName;
        this.userLName = userLName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstLogin = firstLogin;
        this.prefID = prefID;
        this.programID = programID;
    }

    protected Users(Parcel in) {
        userID = in.readInt();
        userFName = in.readString();
        userLName = in.readString();
        userEmail = in.readString();
        userPassword = in.readString();
        byte tmpFirstLogin = in.readByte();
        firstLogin = tmpFirstLogin == 0 ? null : tmpFirstLogin == 1;
        prefID = in.readInt();
        programID = in.readInt();
        flags = in.readInt();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public int getPrefID() {
        return prefID;
    }

    public void setPrefID(int prefID) {
        this.prefID = prefID;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userFName='" + userFName + '\'' +
                ", userLName='" + userLName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstLogin=" + firstLogin +
                ", prefID=" + prefID +
                ", programID=" + programID +
                ", dest=" + dest +
                ", flags=" + flags +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeInt(userID);
        dest.writeString(userFName);
        dest.writeString(userLName);
        dest.writeString(userEmail);
        dest.writeString(userPassword);
        dest.writeByte((byte) (firstLogin == null ? 0 : firstLogin ? 1 : 2));
        dest.writeInt(prefID);
        dest.writeInt(programID);
        dest.writeInt(flags);
    }
}
