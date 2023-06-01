package command;

import exceptions.CancellationRejectedException;
import exceptions.CourseCancelledException;
import exceptions.CourseFullException;
import exceptions.InvalidInputException;

import java.text.ParseException;

public interface Command {
    public void execute(String[] commandStrings) throws CourseFullException, CancellationRejectedException, InvalidInputException, ParseException, CourseCancelledException;
}
