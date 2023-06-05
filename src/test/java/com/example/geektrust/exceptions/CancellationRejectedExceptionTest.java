package com.example.geektrust.exceptions;

import com.example.geektrust.course.Course;
import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.registration.Registration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CancellationRejectedExceptionTest {
    CourseSchedulingManager courseSchedulingManager;

    @Test
    public void  testScenarioCancellationRejectedException() throws CourseFullException {
        courseSchedulingManager = new CourseSchedulingManager();
        //    ADD-COURSE-OFFERING PYTHON JOHN 05062024 1 1
        Course course = courseSchedulingManager.addCourse(new Course("PYTHON","JOHN","05062024",1,1));
        Assertions.assertEquals("OFFERING-PYTHON-JOHN",course.getCourseId());
        //    REGISTERBOBY@GMAIL.COM OFFERING-PYTHON-JOHN
        Registration registration = courseSchedulingManager.register("BOBY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-BOBY-PYTHON ACCEPTED", registration.getRegistrationId()+" "+registration.getRegistrationStatus());
        //    ALLOTOFFERING-PYTHON-JOHN
        List<Registration> registrationList = courseSchedulingManager.allot("OFFERING-PYTHON-JOHN");
        List<Registration> registrationListExpected = new ArrayList<>();
        registrationListExpected.add(registration);
        Assertions.assertEquals(registrationListExpected, registrationList);
        //    CANCELREG-COURSE-BOBY-PYTHON
        Exception exception = assertThrows(CancellationRejectedException.class, () -> courseSchedulingManager.cancel("REG-COURSE-BOBY-PYTHON"));
        String expectedMessage = "REG-COURSE-BOBY-PYTHON"+" CANCEL_REJECTED";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
