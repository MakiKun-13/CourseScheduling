package course;

public class Course {
    String courseId;
    String courseName;
    String instructor;
    String date;
    int minCandidateCount;
    int maxCandidateCount;

    CourseStatus courseStatus;

    public Course(String courseName, String instructor, String date, int minCandidateCount, int maxCandidateCount) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minCandidateCount = minCandidateCount;
        this.maxCandidateCount = maxCandidateCount;
        this.courseStatus = CourseStatus.DRAFT;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDate() {
        return date;
    }

    public int getMinCandidateCount() {
        return minCandidateCount;
    }

    public int getMaxCandidateCount() {
        return maxCandidateCount;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}

