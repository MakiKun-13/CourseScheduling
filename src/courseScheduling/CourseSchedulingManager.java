package courseScheduling;

import course.Course;
import course.CourseDirectory;

import java.util.UUID;

public class CourseSchedulingManager {
    CourseDirectory courseDirectory = new CourseDirectory();
    public Course addCourse(Course course) {
        String id = UUID.randomUUID().toString();
        course.setCourseId(id);
        courseDirectory.add(course);
        return course;
    }

}
