package com.example.geektrust.courseScheduling;


import com.example.geektrust.course.Course;
import com.example.geektrust.course.CourseDirectory;
import com.example.geektrust.employee.EmployeeDirectory;
import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.registration.CourseRegistry;
import com.example.geektrust.registration.Registration;
import com.example.geektrust.registration.RegistrationStatus;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedulingManager {
    CourseDirectory courseDirectory;
    CourseRegistry courseRegistry;
    EmployeeDirectory employeeDirectory;

    public CourseSchedulingManager() {
        courseDirectory = new CourseDirectory();
        courseRegistry = new CourseRegistry();
        employeeDirectory = new EmployeeDirectory();
    }

    public Course addCourse(Course course) {
        String id = "OFFERING-" + course.getCourseName() + "-" + course.getInstructor();
        course.setCourseId(id);
        courseDirectory.add(course);
        return course;
    }

    public Registration register(String email, String courseId) throws CourseFullException {
        Course courseToRegister = courseDirectory.getCourse(courseId);
        employeeDirectory.findOrInsert(email);
        String registrationId = "REG-COURSE-" + email.substring(0, email.indexOf('@'))
                + "-" + courseToRegister.getCourseName();
        if (courseRegistry.getValidRegistrations(courseId).size() >= courseToRegister.getMaxCandidateCount()) {
            throw new CourseFullException();
        } else {
            Registration newRegistration = new Registration(registrationId, courseId, email, RegistrationStatus.ACCEPTED);
            courseRegistry.add(newRegistration);
            return newRegistration;
        }
    }

    public List<Registration> allot(String courseId) {
        Course course = courseDirectory.getCourse(courseId);
        List<Registration> validRegistrations = courseRegistry.getValidRegistrations(courseId);
         if(validRegistrations.size() >= course.getMinCandidateCount()) {
            course.confirm();
            validRegistrations.forEach(a -> a.setRegistrationStatus(RegistrationStatus.CONFIRMED));
            return validRegistrations;
        } else {
             course.expire();
             validRegistrations.forEach(a -> a.setRegistrationStatus(RegistrationStatus.COURSE_CANCELED));
             return validRegistrations;
         }
    }

    public Registration cancel(String RegistrationId) throws CancellationRejectedException {
        Registration registrationToCancel = courseRegistry.getRegistration(RegistrationId);
        if (registrationToCancel.getRegistrationStatus().equals(RegistrationStatus.CONFIRMED))
            throw new CancellationRejectedException(registrationToCancel.getRegistrationId());
        else {
            courseRegistry.getRegistration(RegistrationId).setRegistrationStatus(RegistrationStatus.CANCEL_ACCEPTED);
        }
        return registrationToCancel;
    }

    public Course getCourseById(String courseId) {
        return courseDirectory.getCourse(courseId);
    }
}

