package command;

import courseScheduling.CourseSchedulingManager;
import exceptions.CancelRejectedException;
import outputHandler.OutputHandler;
import registration.Registration;

public class CancelCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public CancelCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws CancelRejectedException {
        String registrationId = commandStrings[1];
        Registration registrationCancelled = courseSchedulingManager.cancel(registrationId);
        outputHandler.displayOutputForCancelRegistration(registrationCancelled);
    }
}
