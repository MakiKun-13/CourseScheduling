package courseScheduling;

import course.Course;
import course.CourseDirectory;
import course.CourseStatus;
import employee.EmployeeDirectory;
import exceptions.CancellationRejectedException;
import exceptions.CourseFullException;
import registration.CourseRegistry;
import registration.Registration;
import registration.RegistrationStatus;

import java.util.Arrays;
import java.util.List;

public class CourseSchedulingManager {
    CourseDirectory courseDirectory = new CourseDirectory();
    CourseRegistry courseRegistry = new CourseRegistry();

    EmployeeDirectory employeeDirectory = new EmployeeDirectory();

    public Course addCourse(Course course) {
        String id = "OFFERING"+course.getCourseName()+course.getInstructor();
        course.setCourseId(id);
        courseDirectory.add(course);
        return course;
    }

    public Registration register(String email, String courseId) throws CourseFullException {
        Course courseToRegister = courseDirectory.getCourse(courseId);
        employeeDirectory.findOrInsert(email);
        String registrationId = "REG-COURSE-"+Arrays.stream(email.split("@")).findFirst()
                +"-"+courseToRegister;
        if(courseRegistry.getValidRegistrations(courseId).size() > courseToRegister.getMaxCandidateCount())
            throw new CourseFullException();
        else
            return new Registration(registrationId, courseId, email, RegistrationStatus.ACCEPTED);
    }

    public List<Registration> allot(String courseId) {
        Course course = courseDirectory.getCourse(courseId);
        List<Registration> validRegistrations = courseRegistry.getValidRegistrations(courseId);
        if(!(validRegistrations.size() < course.getMinCandidateCount())) {
            validRegistrations.forEach(a -> a.setRegistrationStatus(RegistrationStatus.ALLOTTED));
            return validRegistrations;
        }
        else
            return null;
    }

    public Registration cancel(String RegistrationId) throws CancellationRejectedException {
        Registration registrationToCancel = courseRegistry.getRegistration(RegistrationId);
        String courseId = registrationToCancel.getCourseId();
        if(courseDirectory.getCourse(courseId).getCourseStatus().equals(CourseStatus.CONFIRMED))
            throw new CancellationRejectedException(registrationToCancel.getRegistrationId());
        else {
            courseRegistry.getRegistration(RegistrationId).setRegistrationStatus(RegistrationStatus.CANCELLED);
        }
        return registrationToCancel;
    }

    public Course getCourseById(String courseId) {
        return courseDirectory.getCourse(courseId);
    }

}

