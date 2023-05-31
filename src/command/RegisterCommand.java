package command;

import courseScheduling.CourseSchedulingManager;
import exceptions.CourseFullException;
import outputHandler.OutputHandler;
import registration.Registration;

public class RegisterCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public RegisterCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws CourseFullException {
        String email = commandStrings[1];
        String courseId = commandStrings[2];
        Registration registration = courseSchedulingManager.register(email, courseId);
        outputHandler.displayOutputForRegister(registration);
    }
}
