package com.example.geektrust.command;


import com.example.geektrust.course.Course;
import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;

public class AddCourseCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public AddCourseCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws InvalidInputException {
        if(commandStrings.length<6)
            throw new InvalidInputException();
        Course course = new Course(commandStrings[1], commandStrings[2], commandStrings[3],
                Integer.parseInt(commandStrings[4]), Integer.parseInt(commandStrings[5]));
        course = courseSchedulingManager.addCourse(course);
        outputHandler.displayOutputForAddCourse(course);
    }
}
