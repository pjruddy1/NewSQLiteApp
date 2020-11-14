package com.zybooks.newsqliteapp;

public class Course {
    private int mId;
    private String mName;
    private String mDescription;
    private String mLocation;
    private String mDaysTimes;
    private String mInstructor;

    public Course(){

    }

    public Course(Course course){
        mId = course.mId;
        mName = course.mName;
        mDescription = course.mDescription;
        mLocation = course.mLocation;
        mDaysTimes = course.mDaysTimes;
        mInstructor = course.mInstructor;
    }

    public Course(int id, String name, String description, String location, String daysTimes, String instructor) {
        mId = id;
        mName = name;
        mDescription = description;
        mLocation = location;
        mDaysTimes = daysTimes;
        mInstructor = instructor;
    }

    public Course(String name, String description, String location, String daysTimes, String instructor) {
        mName = name;
        mDescription = description;
        mLocation = location;
        mDaysTimes = daysTimes;
        mInstructor = instructor;
    }

    public String getName() {
        return mName;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setName(String text) {
        this.mName = text;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String text) {
        this.mDescription = text;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String text) {
        this.mLocation = text;
    }

    public void setDaysTimes(String text) {
        this.mDaysTimes = text;
    }

    public String getDaysTimes() {
        return mDaysTimes;
    }

    public String getInstructor() {
        return mInstructor;
    }

    public void setInstructor(String text) {
        this.mInstructor = text;
    }
}
