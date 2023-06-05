package com.example.geektrust.course;

import java.util.Date;
import java.util.GregorianCalendar;

public class Course {
    String courseId;
    String courseName;
    String instructor;
    String date;
    int minCandidateCount;
    int maxCandidateCount;

    CourseStatus courseStatus;

    public Course(String courseName, String instructor, String date, int minCandidateCount, int maxCandidateCount) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minCandidateCount = minCandidateCount;
        this.maxCandidateCount = maxCandidateCount;
        this.courseStatus = CourseStatus.DRAFT;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDate() {
        return date;
    }

    public int getMinCandidateCount() {
        return minCandidateCount;
    }

    public int getMaxCandidateCount() {
        return maxCandidateCount;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void expire() {
        this.courseStatus = CourseStatus.COURSE_CANCELED;
    }

    public void confirm() {
        this.courseStatus = CourseStatus.COURSE_CONFIRMED;
    }
}

