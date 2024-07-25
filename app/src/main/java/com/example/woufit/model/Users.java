package com.example.woufit.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

public class Users implements Parcelable {
    private String userFName;
    private String userLName;
    private Boolean firstLogin;

    public Users() {
        this.userFName = "";
        this.userLName = "";
        this.firstLogin = true;
    }

    public Users(String userFName, String userLName, Boolean firstLogin) {
        this.userFName = userFName;
        this.userLName = userLName;
        this.firstLogin = firstLogin;
    }

    protected Users(Parcel in) {
        userFName = in.readString();
        userLName = in.readString();
        byte tmpFirstLogin = in.readByte();
        firstLogin = tmpFirstLogin == 0 ? null : tmpFirstLogin == 1;
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

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    @Override
    public String toString() {
        return "Users{" +
                ", userFName='" + userFName + '\'' +
                ", userLName='" + userLName + '\'' +
                ", firstLogin=" + firstLogin +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(userFName);
        dest.writeString(userLName);
        dest.writeByte((byte) (firstLogin == null ? 0 : firstLogin ? 1 : 2));
    }
}
