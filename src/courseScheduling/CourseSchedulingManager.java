package courseScheduling;

import course.Course;
import course.CourseDirectory;
import exceptions.CancelRejectedException;
import exceptions.CourseFullException;
import registration.CourseRegistry;
import registration.Registration;

import java.util.Arrays;
import java.util.List;

public class CourseSchedulingManager {
    CourseDirectory courseDirectory = new CourseDirectory();
    CourseRegistry courseRegistry = new CourseRegistry();
    public Course addCourse(Course course) {
        String id = "OFFERING"+course.getCourseName()+course.getInstructor();
        course.setCourseId(id);
        courseDirectory.add(course);
        return course;
    }

    public Registration register(String email, String courseId) throws CourseFullException {
        Course courseToRegister = courseDirectory.getCourse(courseId);
        String registrationId = "REG-COURSE-"+Arrays.stream(email.split("@")).findFirst()
                +"-"+courseToRegister;
        if(courseRegistry.getValidRegistrations(courseId).size() > courseToRegister.getMaxCandidateCount())
            throw new CourseFullException();
        else
            return new Registration(registrationId, courseId, email, "ACCEPTED");
    }

    public List<Registration> allot(String courseId) {
        Course course = courseDirectory.getCourse(courseId);
        if(!(courseRegistry.getValidRegistrations(courseId).size() < course.getMinCandidateCount()))
            return courseRegistry.getValidRegistrations(courseId);
        else
            return null;
    }

    public Registration cancel(String RegistrationId) throws CancelRejectedException {
        Registration registrationToCancel = courseRegistry.getRegistration(RegistrationId);
        String courseId = registrationToCancel.getCourseId();
        if(courseDirectory.getCourse(courseId).getCourseStatus().equals("ALLOTTED"))
            throw new CancelRejectedException();
        else {
            courseRegistry.getRegistration(RegistrationId).setRegistrationStatus("CANCELLED");
        }
        return registrationToCancel;
    }

}
