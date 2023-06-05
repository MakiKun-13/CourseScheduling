package com.example.geektrust.registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CourseRegistry {
    List<Registration> registrations = new ArrayList<>();
    public List<Registration> getValidRegistrations(String courseId) {
         List<Registration> registrationList =registrations.stream()
                .filter(x -> x.getCourseId().equalsIgnoreCase(courseId))
                .filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACCEPTED))
                .collect(Collectors.toList());
         Collections.sort(registrationList);
         return registrationList;
    }

    public Registration getRegistration(String registrationId) {
        return registrations.stream().filter(x -> x.registrationId.equalsIgnoreCase(registrationId)).findFirst().get();
    }

    public void add(Registration newRegistration) {
        registrations.add(newRegistration);
    }
}
