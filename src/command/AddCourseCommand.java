package command;

import course.Course;
import courseScheduling.CourseSchedulingManager;
import outputHandler.OutputHandler;

public class AddCourseCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    OutputHandler outputHandler;
    public AddCourseCommand(CourseSchedulingManager courseSchedulingManager, OutputHandler outputHandler) {
        this.courseSchedulingManager = courseSchedulingManager;
        this.outputHandler = outputHandler;
    }

    @Override
    public void execute(String[] commandStrings) {
        Course course = new Course(commandStrings[1], commandStrings[2], commandStrings[3],
                Integer.parseInt(commandStrings[4]), Integer.parseInt(commandStrings[5]));
        course = courseSchedulingManager.addCourse(course);
        outputHandler.displayOutputForAddCourse(course);
    }
}
