package com.example.quizapp;

public class UsersData {

    public String Fullname,Username,EmailId,MobileNumber;
    public UsersData(){
    }
    public UsersData(String username, String emailId, String mobileNumber) {
        Username = username;
        EmailId = emailId;
        MobileNumber = mobileNumber;
    }
}
