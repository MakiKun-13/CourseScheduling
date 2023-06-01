package registration;

import java.util.List;

public class CourseRegistry {
    List<Registration> registrations;
    public List<Registration> getValidRegistrations(String courseId) {
        List<Registration> validRegistrations = null;
        for (Registration registration:
             registrations) {
            if(registration.courseId.equalsIgnoreCase(courseId)) {
                validRegistrations.add(registration);
            }
        }
        return validRegistrations;
    }



    public Registration getRegistration(String registrationId) {
        return registrations.stream().filter(x -> x.registrationId.equalsIgnoreCase(registrationId)).findFirst().get();
    }
}
