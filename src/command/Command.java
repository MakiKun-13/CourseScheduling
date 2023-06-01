package command;

import exceptions.CancellationRejectedException;
import exceptions.CourseFullException;

public interface Command {
    public void execute(String[] commandStrings) throws CourseFullException, CancellationRejectedException;
}
