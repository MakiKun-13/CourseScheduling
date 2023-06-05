package com.example.geektrust.outputHandler;


import com.example.geektrust.course.Course;
import com.example.geektrust.registration.Registration;

import java.util.List;

public class OutputHandler {
    public void displayOutputForAddCourse(Course course) {
        System.out.println(course.getCourseId());
    }

    public void displayOutputForAllotment(List<Registration> allottedRegistraionList, Course course) {
        for (Registration registration:
             allottedRegistraionList) {
            System.out.println(registration.getRegistrationId()+" "+registration.getEmail()
            +" "+registration.getCourseId()+" "+course.getCourseName()+" "+course.getInstructor()
            +" "+course.getDate() +" "+ registration.getRegistrationStatus());
        }
    }

    public void displayOutputForCancelRegistration(Registration registrationCancelled) {
        System.out.println(registrationCancelled.getRegistrationId()+ " " +registrationCancelled.getRegistrationStatus());
    }

    public void displayOutputForRegister(Registration registration) {
        System.out.println(registration.getRegistrationId()+" "+registration.getRegistrationStatus());
    }

}
