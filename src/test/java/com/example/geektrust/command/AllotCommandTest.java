package com.example.geektrust.command;

import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllotCommandTest {
    AllotCommand command;

    @BeforeEach
    void setUp() {
        command = new AllotCommand(new CourseSchedulingManager(), new OutputHandler());

    }

    @Test
    void execute() {
        Assertions.assertThrows(InvalidInputException.class, () -> command.execute(new String[]{"JON"}));
    }
}
