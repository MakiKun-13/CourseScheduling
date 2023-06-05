package com.example.geektrust.command;


import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;
import com.example.geektrust.registration.Registration;

import java.util.List;

public class AllotCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public AllotCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws InvalidInputException {
        if(commandStrings.length<2)
            throw new InvalidInputException();
        String courseId = commandStrings[1];
        List<Registration> allottedRegistrationList = courseSchedulingManager.allot(courseId);
        if(allottedRegistrationList != null) {
            outputHandler.displayOutputForAllotment(allottedRegistrationList, courseSchedulingManager.getCourseById(courseId));
        }
    }
}
