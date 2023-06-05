package com.example.geektrust.command;


import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;

import java.text.ParseException;

public interface Command {
    public void execute(String[] commandStrings) throws CourseFullException, CancellationRejectedException, InvalidInputException, ParseException;
}
