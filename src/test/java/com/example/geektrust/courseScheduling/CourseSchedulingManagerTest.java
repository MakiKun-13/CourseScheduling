package com.example.geektrust.courseScheduling;

import com.example.geektrust.course.Course;
import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.registration.Registration;
import com.example.geektrust.registration.RegistrationStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CourseSchedulingManagerTest {
    CourseSchedulingManager courseSchedulingManager;

    @BeforeEach
    void setUp() {
        courseSchedulingManager = new CourseSchedulingManager();
    }

    @Test
    public void testFirstUseCase() throws CourseFullException {
        /*
        INPUT: "ADD-COURSE-OFFERING JAVA JAMES 15062024 1 2"
        EXPECTED_OUTPUT: "OFFERING-JAVA-JAMES"
        */
        Course course = courseSchedulingManager.addCourse(new Course("JAVA", "JAMES", "15062024", 1, 2));
        Assertions.assertEquals("OFFERING-JAVA-JAMES", course.getCourseId());


        /*
        INPUT: "ADD-COURSE-OFFERING KUBERNETES WOODY 16062024 2 5"
        EXPECTED_OUTPUT: "OFFERING-KUBERNETES-WOODY"
        */
        course = courseSchedulingManager.addCourse(new Course("KUBERNETES", "WOODY", "16062024", 2, 5));
        Assertions.assertEquals("OFFERING-KUBERNETES-WOODY", course.getCourseId());

        /*
        REGISTER ANDY@GMAIL.COM OFFERING-JAVA-JAMES
        REG-COURSE-ANDY-JAVA ACCEPTED
         */
        Registration andyJavaReg = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-JAVA-JAMES");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, andyJavaReg.getRegistrationStatus());


        /*
        REGISTER WOO@GMAIL.COM OFFERING-JAVA-JAMES
        REG-COURSE-WOO-JAVA ACCEPTED
         */
        Registration wooJavaReg = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-JAVA-JAMES");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, wooJavaReg.getRegistrationStatus());

        /*
        ALICE@GMAIL.COM OFFERING-JAVA-JAMES
        COURSE_FULL_ERROR
         */
        Assertions.assertThrows(CourseFullException.class, () -> courseSchedulingManager.register("ALICE@GMAIL.COM", "OFFERING-JAVA-JAMES"));

        /*
        INPUT: ALLOT OFFERING-JAVA-JAMES
        OUTPUT:
        REG-COURSE-ANDY-JAVA ANDY@GMAIL.COM OFFERING-JAVA-JAMES JAVA JAMES 15062022 CONFIRMED
        REG-COURSE-WOO-JAVA WOO@GMAIL.COM OFFERING-JAVA-JAMES JAVA JAMES 15062024 CONFIRMED
         */
        List<Registration> registrations = courseSchedulingManager.allot("OFFERING-JAVA-JAMES");
        Assertions.assertTrue(registrations.contains(andyJavaReg) && registrations.contains(wooJavaReg));

        /*
        INPUT: CANCEL REG-COURSE-ANDY-JAVA
        OUTPUT: REG-COURSE-ANDY-JAVA CANCEL_REJECTED
         */
        Assertions.assertThrows(CancellationRejectedException.class, () -> courseSchedulingManager.cancel("REG-COURSE-ANDY-JAVA"));
    }

    @Test
    public void testSecondUseCase() throws CourseFullException {
        /*
        INPUT: ADD-COURSE-OFFERING DATASCIENCE BOB 05062024 1 3
        OUTPUT: OFFERING-DATASCIENCE-BOB
         */
        Course course = courseSchedulingManager.addCourse(new Course("DATASCIENCE", "BOB", "05062024", 1, 3));
        Assertions.assertEquals("OFFERING-DATASCIENCE-BOB", course.getCourseId());

        /*
        REGISTERWOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
        REG-COURSE-WOO-DATASCIENCE ACCEPTED
         */
        Registration wooDataSceReg = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals("REG-COURSE-WOO-DATASCIENCE ACCEPTED", wooDataSceReg.getRegistrationId() + " " + wooDataSceReg.getRegistrationStatus());

        /*
        REGISTERANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        REG-COURSE-ANDY-DATASCIENCE ACCEPTED
         */
        Registration andyDataSceReg = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals("REG-COURSE-ANDY-DATASCIENCE ACCEPTED", andyDataSceReg.getRegistrationId() + " " + andyDataSceReg.getRegistrationStatus());

        /*
        INPUT : ALLOT OFFERING-DATASCIENCE-BOB
        OUTPUT:
        REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
        REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
         */
        List<Registration> registrationList = courseSchedulingManager.allot("OFFERING-DATASCIENCE-BOB");
        Assertions.assertTrue(registrationList.contains(wooDataSceReg) && registrationList.contains(andyDataSceReg));
    }

    @Test
    public void testThirdUseCase() throws CourseFullException, CancellationRejectedException {
        /*
        ADD-COURSE-OFFERING PYTHON JOHN 05062024 1 3
        OFFERING-PYTHON-JOHN
         */
        Course course = courseSchedulingManager.addCourse(new Course("PYTHON", "JOHN", "05062024", 1, 3));
        Assertions.assertEquals("OFFERING-PYTHON-JOHN", course.getCourseId());
        /*
        REGISTER WOO@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-WOO-PYTHON ACCEPTED
         */
        Registration registration = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-WOO-PYTHON ACCEPTED", registration.getRegistrationId() + " " + registration.getRegistrationStatus());
        /*
        REGISTER ANDY@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-ANDY-PYTHON ACCEPTED
         */
        Registration registration1 = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-ANDY-PYTHON ACCEPTED", registration1.getRegistrationId() + " " + registration1.getRegistrationStatus());
        /*
        REGISTER BOBY@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-BOBY-PYTHON ACCEPTED
         */
        Registration registration2 = courseSchedulingManager.register("BOBY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-BOBY-PYTHON ACCEPTED", registration2.getRegistrationId() + " " + registration2.getRegistrationStatus());
        /*
        CANCEL REG-COURSE-BOBY-PYTHON
        REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED
         */
        Registration registrationCancelled = courseSchedulingManager.cancel("REG-COURSE-BOBY-PYTHON");
        Assertions.assertEquals("REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED", registrationCancelled.getRegistrationId() + " " + registrationCancelled.getRegistrationStatus());
        /*
        ALLOT OFFERING-PYTHON-JOHN
        REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN 05062022 CONFIRMED
        REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN  PYTHON  JOHN  05062022 CONFIRMED
         */
        List<Registration> registrationList = courseSchedulingManager.allot("OFFERING-PYTHON-JOHN");
        Assertions.assertTrue(registrationList.contains(registration) && registrationList.contains(registration1));
    }

    @Test
    public void testFourthUseCase() throws CourseFullException {
        /*
        ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3
        OFFERING-DATASCIENCE-BOB
         */
        Course course = courseSchedulingManager.addCourse(new Course("DATASCIENCE", "BOB", "05062022", 1, 3));
        Assertions.assertEquals("OFFERING-DATASCIENCE-BOB", course.getCourseId());

        /*
        REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
        REG-COURSE-WOO-DATASCIENCE ACCEPTED
         */
        Registration registration = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals("REG-COURSE-WOO-DATASCIENCE ACCEPTED", registration.getRegistrationId() + " " + registration.getRegistrationStatus());

        /*
        REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        REG-COURSE-ANDY-DATASCIENCE ACCEPTED
         */
        Registration registration1 = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals("REG-COURSE-ANDY-DATASCIENCE ACCEPTED", registration1.getRegistrationId() + " " + registration1.getRegistrationStatus());

        /*
        ALLOT OFFERING-DATASCIENCE-BOB
        REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
        REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
         */
        List<Registration> registrationList = courseSchedulingManager.allot("OFFERING-DATASCIENCE-BOB");
        Assertions.assertTrue(registrationList.contains(registration) && registrationList.contains(registration1));
    }

    @Test
    public void testFifthUseCase() throws CourseFullException, CancellationRejectedException {
        /*
        ADD-COURSE-OFFERING PYTHON JOHN 05062022 1 3
        OFFERING-PYTHON-JOHN
         */
        Course course = courseSchedulingManager.addCourse(new Course("PYTHON", "JOHN", "05062022", 1, 3));
        Assertions.assertEquals("OFFERING-PYTHON-JOHN", course.getCourseId());
        /*
        REGISTER WOO@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-WOO-PYTHON ACCEPTED
         */
        Registration registration = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-WOO-PYTHON ACCEPTED", registration.getRegistrationId() + " " + registration.getRegistrationStatus());
        /*
        REGISTER ANDY@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-ANDY-PYTHON ACCEPTED
         */
        Registration registration1 = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-ANDY-PYTHON ACCEPTED", registration1.getRegistrationId() + " " + registration1.getRegistrationStatus());
        /*
        REGISTER BOBY@GMAIL.COM OFFERING-PYTHON-JOHN
        REG-COURSE-BOBY-PYTHON ACCEPTED
         */
        Registration registration2 = courseSchedulingManager.register("BOBY@GMAIL.COM", "OFFERING-PYTHON-JOHN");
        Assertions.assertEquals("REG-COURSE-BOBY-PYTHON ACCEPTED", registration2.getRegistrationId() + " " + registration2.getRegistrationStatus());
        /*
        CANCEL REG-COURSE-BOBY-PYTHON
        REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED
         */
        Registration registrationCancelled = courseSchedulingManager.cancel("REG-COURSE-BOBY-PYTHON");
        Assertions.assertEquals("REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED", registrationCancelled.getRegistrationId() + " " + registrationCancelled.getRegistrationStatus());
        /*
        ALLOT OFFERING-PYTHON-JOHN
        REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED
        REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED
         */
        List<Registration> registrationList = courseSchedulingManager.allot("OFFERING-PYTHON-JOHN");
        Assertions.assertTrue(registrationList.contains(registration) && registrationList.contains(registration1));
    }

    @Test
    public void testSixthUseCase() throws CourseFullException {
        /*
        ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 2
        OFFERING-DATASCIENCE-BOB
         */
        Course datascienceByBob = courseSchedulingManager.addCourse(new Course("DATASCIENCE", "BOB", "05062022", 1, 2));
        Assertions.assertEquals("OFFERING-DATASCIENCE-BOB", datascienceByBob.getCourseId());
        /*
        REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        REG-COURSE-ANDY-DATASCIENCE ACCEPTED
         */
        Registration andyDataScience = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, andyDataScience.getRegistrationStatus());
        /*
         REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
         REG-COURSE-WOO-DATASCIENCE ACCEPTED
         */
        Registration wooDataScience = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, wooDataScience.getRegistrationStatus());
        /*
        REGISTER BOBY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        COURSE_FULL_ERROR
         */
        Assertions.assertThrows(CourseFullException.class, () -> courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB"));
        /*
        ALLOT OFFERING-DATASCIENCE-BOB

        REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
        REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED
         */
        List<Registration> registrations = courseSchedulingManager.allot("OFFERING-DATASCIENCE-BOB");
        Assertions.assertTrue(registrations.contains(andyDataScience) && registrations.contains(wooDataScience));
    }

    @Test
    public void testSeventhUseCases() throws CourseFullException {
        // INPUT: ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 3 5
        // OUTPUT: OFFERING-DATASCIENCE-BOB
        Course course = courseSchedulingManager.addCourse(new Course("DATASCIENCE", "BOB", "05062022", 3, 5));
        Assertions.assertEquals("OFFERING-DATASCIENCE-BOB", course.getCourseId());
        // INPUT: REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB
        // OUTPUT: REG-COURSE-ANDY-DATASCIENCE ACCEPTED
        Registration registrationOfAndy = courseSchedulingManager.register("ANDY@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, registrationOfAndy.getRegistrationStatus());
        // INPUT: REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB
        // OUTPUT: REG-COURSE-WOO-DATASCIENCE ACCEPTED
        Registration registrationOfWoo = courseSchedulingManager.register("WOO@GMAIL.COM", "OFFERING-DATASCIENCE-BOB");
        Assertions.assertEquals(RegistrationStatus.ACCEPTED, registrationOfWoo.getRegistrationStatus());
        // INPUT: ALLOT OFFERING-DATASCIENCE-BOB
        // OUTPUT: REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 COURSE_CANCELED
        // REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 COURSE_CANCELED
        List<Registration> registrations = courseSchedulingManager.allot("OFFERING-DATASCIENCE-BOB");
        registrations.forEach(r -> Assertions.assertEquals(RegistrationStatus.COURSE_CANCELED, r.getRegistrationStatus()));
    }
}