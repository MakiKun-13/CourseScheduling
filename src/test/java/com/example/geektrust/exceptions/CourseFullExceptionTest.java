package com.example.geektrust.exceptions;

import com.example.geektrust.course.Course;
import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseFullExceptionTest {
    CourseSchedulingManager courseSchedulingManager;

    @Test
    public void  testScenarioCourseFullException() throws CourseFullException {
        courseSchedulingManager = new CourseSchedulingManager();
        //    ADD-COURSE-OFFERING PYTHON JOHN 05062024 1 2
        Course course = courseSchedulingManager.addCourse(new Course("PYTHON","JOHN","05062024",1,2));
        Assertions.assertEquals("OFFERING-PYTHON-JOHN",course.getCourseId());
        //    REGISTERWOO@GMAIL.COM OFFERING-PYTHON-JOHN
        Registration registration = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-WOO-PYTHON ACCEPTED", registration.getRegistrationId()+" "+registration.getRegistrationStatus());
        //    REGISTERANDY@GMAIL.COM OFFERING-PYTHON-JOHN
        Registration registration1 = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-ANDY-PYTHON ACCEPTED",registration1.getRegistrationId()+" "+registration1.getRegistrationStatus());
        //    REGISTERBOBY@GMAIL.COM OFFERING-PYTHON-JOHN
        Exception exception = assertThrows(CourseFullException.class, () -> courseSchedulingManager.register("BOBY@GMAIL.COM", "OFFERING-PYTHON-JOHN"));
        String expectedMessage = "COURSE_FULL_ERROR";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
