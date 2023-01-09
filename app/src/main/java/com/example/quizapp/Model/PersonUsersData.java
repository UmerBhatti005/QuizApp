package com.example.quizapp.Model;

public class PersonUsersData {

    public String Username;
    public String EmailId;
    public String MobileNumber;
    public String marks;

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String profile;
    public PersonUsersData(){
    }
    public PersonUsersData(String username, String emailId, String mobileNumber) {
        Username = username;
        EmailId = emailId;
        MobileNumber = mobileNumber;
    }


    public String getUserId() {
        return UserId;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String UserId;

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

}
