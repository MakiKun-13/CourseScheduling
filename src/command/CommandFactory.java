package command;

import courseScheduling.CourseSchedulingManager;
import exceptions.InvalidInputException;

import java.util.Map;

public class CommandFactory {
    Map<String, Command> commandMap;

    public CommandFactory(CourseSchedulingManager courseSchedulingManager) {
        commandMap.put("ADD-COURSE-OFFERING", new AddCourseCommand(courseSchedulingManager));
        commandMap.put("REGISTER", new RegisterCommand(courseSchedulingManager));
        commandMap.put("ALLOT", new AllotCommand(courseSchedulingManager));
        commandMap.put("CANCEL", new CancelCommand(courseSchedulingManager));
    }

    public void handleCommand(String input) throws InvalidInputException {
        String[] commandStrings = input.split(" ");
        String commandAction = commandStrings[0];
        if(commandMap.containsKey(commandAction)) {
            Command command = commandMap.get(commandAction);
            command.execute(commandStrings);
        }
        else
            throw new InvalidInputException();
    }
}
