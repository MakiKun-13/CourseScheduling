package com.example.geektrust.registration;

public class Registration implements Comparable<Registration> {
    String registrationId;
    String courseId;
    String email;
    RegistrationStatus registrationStatus;

    public Registration(String registrationId, String courseId, String email, RegistrationStatus registrationStatus) {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.email = email;
        this.registrationStatus = registrationStatus;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getEmail() {
        return email;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    @Override
    public int compareTo(Registration o) {
        return registrationId.compareTo(o.registrationId);
    }
}
