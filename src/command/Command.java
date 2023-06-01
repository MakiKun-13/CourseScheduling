package command;

import exceptions.CancellationRejectedException;
import exceptions.CourseFullException;
import exceptions.InvalidInputException;

public interface Command {
    public void execute(String[] commandStrings) throws CourseFullException, CancellationRejectedException, InvalidInputException;
}
