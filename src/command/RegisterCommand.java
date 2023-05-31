package command;

import courseScheduling.CourseSchedulingManager;

public class RegisterCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    public RegisterCommand(CourseSchedulingManager courseSchedulingManager) {
        this.courseSchedulingManager = courseSchedulingManager;
    }

    @Override
    public void execute(String[] commandStrings) {

    }
}
