package course;

import java.util.TimerTask;

public class CourseCancellationTask extends TimerTask {

    CourseDirectory courseDirectory;

    public CourseCancellationTask(CourseDirectory courseDirectory) {
        this.courseDirectory = courseDirectory;
    }

    @Override
    public void run() {
        courseDirectory.getCourses().stream().filter(Course::isExpired).forEach(Course::expire);
    }
}
