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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseSchedulingManager {
    CourseDirectory courseDirectory = new CourseDirectory();
    CourseRegistry courseRegistry = new CourseRegistry();

    EmployeeDirectory employeeDirectory = new EmployeeDirectory();

    public Course addCourse(Course course) {
        String id = "OFFERING-"+course.getCourseName()+"-"+course.getInstructor();
        course.setCourseId(id);
        courseDirectory.add(course);
        return course;
    }

    public Registration register(String email, String courseId) throws CourseFullException, ParseException {
        Course courseToRegister = courseDirectory.getCourse(courseId);
        employeeDirectory.findOrInsert(email);
        String registrationId = "REG-COURSE-"+email.substring(0, email.indexOf('@'))
                +"-"+courseToRegister.getCourseName();
        if(courseRegistry.getValidRegistrations(courseId).size() >= courseToRegister.getMaxCandidateCount()) {
            throw new CourseFullException();
        }
        else if(pastCourseDate(courseToRegister.getDate())) {//Where to put this pasCourse logic?
            courseToRegister.setCourseStatus(CourseStatus.COURSE_CANCELED);
        }
        else {
            Registration newRegistration = new Registration(registrationId, courseId, email, RegistrationStatus.ACCEPTED);
            courseRegistry.add(newRegistration);
            return newRegistration;
        }
        return null;
    }

    public List<Registration> allot(String courseId) {
        Course course = courseDirectory.getCourse(courseId);
        List<Registration> validRegistrations = courseRegistry.getValidRegistrations(courseId);
        if(validRegistrations.size() >= course.getMinCandidateCount()) {
            course.setCourseStatus(CourseStatus.COURSE_CONFIRMED);
            validRegistrations.forEach(a -> a.setRegistrationStatus(RegistrationStatus.ALLOTTED));
            return validRegistrations;
        }
        else
            return null;
    }

    public Registration cancel(String RegistrationId) throws CancellationRejectedException {
        Registration registrationToCancel = courseRegistry.getRegistration(RegistrationId);
        String courseId = registrationToCancel.getCourseId();
        if(courseDirectory.getCourse(courseId).getCourseStatus().equals(CourseStatus.COURSE_CONFIRMED)) //Better to do with registration status?
            throw new CancellationRejectedException(registrationToCancel.getRegistrationId());
        else {
            courseRegistry.getRegistration(RegistrationId).setRegistrationStatus(RegistrationStatus.CANCELLED);
        }
        return registrationToCancel;
    }

    public Course getCourseById(String courseId) {
        return courseDirectory.getCourse(courseId);
    }

    public boolean pastCourseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        Date courseStartDate = format.parse(dateString);
        Date currentDate = new Date();
        if(courseStartDate.before(currentDate))
            return true;
        else
            return false;
    }

}

