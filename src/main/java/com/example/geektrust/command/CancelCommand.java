package com.example.geektrust.command;


import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;
import com.example.geektrust.registration.Registration;

public class CancelCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public CancelCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) throws CancellationRejectedException, InvalidInputException {
        if(commandStrings.length<2)
            throw new InvalidInputException();
        String registrationId = commandStrings[1];
        Registration registrationCancelled = courseSchedulingManager.cancel(registrationId);
        outputHandler.displayOutputForCancelRegistration(registrationCancelled);
    }
}
