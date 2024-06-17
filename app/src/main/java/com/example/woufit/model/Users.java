package com.example.woufit.model;

public class Users {

    private String userID;
    private String userFName;
    private String userLName;
    private String userEmail;
    private String userPassword;
    //change to hashcode for better security
    private Boolean firstLogin;

    public Users() {
        this.userID = "";
        this.userFName = "";
        this.userLName = "";
        this.userEmail = "";
        this.userPassword = "";
        this.firstLogin = false;
    }

    public Users(String userID,
                 String userFName,
                 String userLName,
                 String userEmail,
                 String userPassword,
                 Boolean firstLogin) {
        this.userID = userID;
        this.userFName = userFName;
        this.userLName = userLName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.firstLogin = firstLogin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    @Override
    public String toString() {
        return "Users{" +
                "userID='" + userID + '\'' +
                ", userFName='" + userFName + '\'' +
                ", userLName='" + userLName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstLogin=" + firstLogin +
                '}';
    }
}
