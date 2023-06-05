package com.example.geektrust;

import com.example.geektrust.command.CommandFactory;
import com.example.geektrust.courseScheduling.CourseSchedulingManager;
import com.example.geektrust.exceptions.CancellationRejectedException;
import com.example.geektrust.exceptions.CourseFullException;
import com.example.geektrust.exceptions.InvalidInputException;
import com.example.geektrust.outputHandler.OutputHandler;

import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandFactory commandFactory = new CommandFactory(new CourseSchedulingManager(), new OutputHandler());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        String input = bufferedReader.readLine();
        while (input != null) {
            try {
                commandFactory.handleCommand(input);
            } catch (Exception e) {
                System.out.println(e);
            }
            input = bufferedReader.readLine();
        }
    }
}