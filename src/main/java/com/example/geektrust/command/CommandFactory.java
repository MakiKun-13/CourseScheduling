package com.example.geektrust.command;


import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    Map<String, Command> commandMap = new HashMap<>();

    public CommandFactory(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        commandMap.put("ADD-COURSE-OFFERING", new AddCourseCommand(courseSchedulingManager, outputHandler));
        commandMap.put("REGISTER", new RegisterCommand(courseSchedulingManager, outputHandler));
        commandMap.put("ALLOT", new AllotCommand(courseSchedulingManager, outputHandler));
        commandMap.put("CANCEL", new CancelCommand(courseSchedulingManager, outputHandler));
    }

    public void handleCommand(String input) throws InvalidInputException, CourseFullException, ParseException, CancellationRejectedException {
        String[] commandStrings = input.split(" ");
        String commandAction = commandStrings[0];
        if (commandMap.containsKey(commandAction)) {
            Command command = commandMap.get(commandAction);
                command.execute(commandStrings);
        } else
            throw new InvalidInputException();
    }
}
