package com.example.geektrust.command;

import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    CommandFactory commandFactory;
    @BeforeEach
    public void setUp() {
        commandFactory= new CommandFactory(new CourseSchedulingManager(), new OutputHandler());
    }

    @Test
    public void testHandleInvalidCommand() {
        assertThrows(InvalidInputException.class, () -> commandFactory.handleCommand("INVALID COMMAND"));
    }

    @Test
    public void testHandleValidCommand() {
        assertDoesNotThrow(() -> commandFactory.handleCommand("ADD-COURSE-OFFERING PYTHON JOHN 05062022 1 3"));
    }

}