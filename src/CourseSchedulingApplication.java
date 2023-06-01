import command.CommandFactory;
import courseScheduling.CourseSchedulingManager;
import exceptions.CancellationRejectedException;
import exceptions.CourseFullException;
import exceptions.InvalidInputException;
import outputHandler.OutputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CourseSchedulingApplication {
    public static void main(String args[]) throws IOException, InvalidInputException, CancellationRejectedException, CourseFullException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        CommandFactory commandFactory = new CommandFactory(new CourseSchedulingManager(), new OutputHandler());
        String input;
        do {
            input = bufferedReader.readLine();
            commandFactory.handleCommand(input);
        } while(input.length()>0);
    }
}
