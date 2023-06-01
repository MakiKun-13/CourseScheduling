package command;

import courseScheduling.CourseSchedulingManager;
import exceptions.CancellationRejectedException;
import exceptions.CourseFullException;
import exceptions.InvalidInputException;
import outputHandler.OutputHandler;

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

    public void handleCommand(String input) throws InvalidInputException, CancellationRejectedException, CourseFullException {
        String[] commandStrings = input.split(" ");
        String commandAction = commandStrings[0];
        if (commandMap.containsKey(commandAction)) {
            Command command = commandMap.get(commandAction);
            try {
                command.execute(commandStrings);
            } catch(Exception e) {
                System.out.println(e);
            }
        } else
            throw new InvalidInputException();
    }
}
