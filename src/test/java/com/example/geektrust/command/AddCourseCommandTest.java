package com.example.geektrust.command;

import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AddCourseCommandTest {

    AddCourseCommand command;

    @BeforeEach
    void setUp() {
        command = new AddCourseCommand(new CourseSchedulingManager(), new OutputHandler());
    }

    @Test
    void testShouldThrowInputDataError() {
        /*
            INPUT: ADD-COURSE-OFFERING PYTHON
            EXP_OUT: INPUT_DATA_ERROR
        */
        Assertions.assertThrows(InvalidInputException.class, () -> command.execute(new String[]{"PYTHON"}));
    }
}