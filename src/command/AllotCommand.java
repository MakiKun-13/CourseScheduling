package command;

import courseScheduling.CourseSchedulingManager;

public class AllotCommand implements Command {
    CourseSchedulingManager courseSchedulingManager;
    public AllotCommand(CourseSchedulingManager courseSchedulingManager) {
        this.courseSchedulingManager = courseSchedulingManager;
    }

    @Override
    public void execute(String[] commandStrings) {

    }
}
