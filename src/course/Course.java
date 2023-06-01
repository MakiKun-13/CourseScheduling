package course;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public boolean isExpired() {
        int dd = Integer.parseInt(date.substring(0, 2));
        int mm = Integer.parseInt(date.substring(2, 4));
        int yyyy = Integer.parseInt(date.substring(4));
        Date courseStartDate = new GregorianCalendar(yyyy, mm - 1, dd).getTime();
        return courseStartDate.before(new Date());
    }

    public void expire() {
        this.courseStatus = CourseStatus.COURSE_CANCELED;
    }
}

