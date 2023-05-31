package command;

import exceptions.CancelRejectedException;
import exceptions.CourseFullException;

public interface Command {
    public void execute(String[] commandStrings) throws CourseFullException, CancelRejectedException;
}
