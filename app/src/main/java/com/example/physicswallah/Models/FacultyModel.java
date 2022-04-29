package com.example.physicswallah.Models;

import java.util.ArrayList;

public class FacultyModel {
    private String profileurl,name,id;
    private ArrayList<String> subjects,qualification;

    public FacultyModel(){}
    public FacultyModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getProfileurl() {
        return profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<String> getQualification() {
        return qualification;
    }

    public void setQualification(ArrayList<String> qualification) {
        this.qualification = qualification;
    }
}
