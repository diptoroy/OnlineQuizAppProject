package com.diptoroy.example.onlinequizappproject.Model;

public class SignInUpModel {

    private String fullName;
    private String userName;
    private String schoolName;
    private String className;
    private String division;
    private String phnNumber;
    private String emailAddress;
    private String reference;
    private String password;

    public SignInUpModel() {

    }

    public SignInUpModel(String fullName, String userName, String schoolName, String className, String division, String phnNumber, String emailAddress, String reference, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.schoolName = schoolName;
        this.className = className;
        this.division = division;
        this.phnNumber = phnNumber;
        this.emailAddress = emailAddress;
        this.reference = reference;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getPhnNumber() {
        return phnNumber;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    private String userName;
//    private String email;
//    private String phoneNumber;
//    private String password;
//
//    public SignInUpModel() {
//
//    }
//
//    public SignInUpModel(String userName, String email, String phoneNumber, String password) {
//        this.userName = userName;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.password = password;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
