package command;

import courseScheduling.CourseSchedulingManager;
import outputHandler.OutputHandler;
import registration.Registration;

import java.util.List;

public class AllotCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public AllotCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) {
        String courseId = commandStrings[1];
        List<Registration> allottedRegistrationList = courseSchedulingManager.allot(courseId);
        if(allottedRegistrationList != null) {
            outputHandler.displayOutputForAllotment(allottedRegistrationList, courseSchedulingManager.getCourseById(courseId));
        }
    }
}
