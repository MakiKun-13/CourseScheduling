package command;

import courseScheduling.CourseSchedulingManager;
import exceptions.CourseFullException;
import exceptions.InvalidInputException;
import outputHandler.OutputHandler;
import registration.Registration;

import java.text.ParseException;

public class RegisterCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public RegisterCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws CourseFullException, InvalidInputException, ParseException {
        if(commandStrings.length<3)
            throw new InvalidInputException();
        String email = commandStrings[1];
        String courseId = commandStrings[2];
        Registration registration = courseSchedulingManager.register(email, courseId);
        outputHandler.displayOutputForRegister(registration);
    }
}
