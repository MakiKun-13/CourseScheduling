import command.CommandFactory;
import courseScheduling.CourseSchedulingManager;
import exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CourseSchedulingApplication {
    public static void main(String args[]) throws IOException, InvalidInputException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        CommandFactory commandFactory = new CommandFactory(new CourseSchedulingManager());
        String input;
        do {
            input = bufferedReader.readLine();
            commandFactory.handleCommand(input);
        } while(input.length()>0);
    }
}
