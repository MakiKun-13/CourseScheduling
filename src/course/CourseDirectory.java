package course;

import java.util.ArrayList;
import java.util.List;

public class CourseDirectory {
    List<Course> courses = new ArrayList<>();

    public Course getCourse(String courseId) {
        return courses.stream().filter(x -> x.getCourseId().equalsIgnoreCase(courseId)).findFirst().get();
    }

    public void add(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }
}
