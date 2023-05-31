package command;

import courseScheduling.CourseSchedulingManager;

public class CancelCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    public CancelCommand(CourseSchedulingManager courseSchedulingManager) {
        this.courseSchedulingManager = courseSchedulingManager;
    }

    @Override
    public void execute(String[] commandStrings) {

    }
}
