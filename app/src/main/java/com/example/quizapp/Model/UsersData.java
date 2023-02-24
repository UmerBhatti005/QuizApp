package com.example.quizapp.Model;

public class UsersData {

    private String Username,EmailId, MobileNumber;
    private int marks;
    public UsersData(){
    }
    public UsersData(String username, String emailId, String mobileNumber) {
        this.Username = username;
        this.EmailId = emailId;
        this.MobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }




    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }


}

