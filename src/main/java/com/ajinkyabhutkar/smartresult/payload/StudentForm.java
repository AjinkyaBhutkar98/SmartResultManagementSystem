package com.ajinkyabhutkar.smartresult.payload;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentForm {

    private String name;

    private String rollNo;

    private String emailAddress;

    private String schoolName;

    private LocalDate dob;

    private String gender;

    private List<MarksForm> marksFormList=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<MarksForm> getMarksFormList() {
        return marksFormList;
    }

    public void setMarksFormList(List<MarksForm> marksFormList) {
        this.marksFormList = marksFormList;
    }
}
