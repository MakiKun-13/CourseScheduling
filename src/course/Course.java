package course;

public class Course {
    String courseId;
    String courseName;
    String instructor;
    String date;
    String minCandidateCount;
    String maxCandidateCount;

    public Course(String courseName, String instructor, String date, String minCandidateCount, String maxCandidateCount) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minCandidateCount = minCandidateCount;
        this.maxCandidateCount = maxCandidateCount;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
