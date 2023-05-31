package command;

import course.Course;
import courseScheduling.CourseSchedulingManager;

public class AddCourseCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    public AddCourseCommand(CourseSchedulingManager courseSchedulingManager) {
        this.courseSchedulingManager = courseSchedulingManager;
    }

    @Override
    public void execute(String[] commandStrings) {
        Course course = new Course(commandStrings[1], commandStrings[2], commandStrings[3], commandStrings[4], commandStrings[5]);
        courseSchedulingManager.addCourse(course);
    }
}
