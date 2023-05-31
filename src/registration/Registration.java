package registration;

public class Registration {
    String registrationId;
    String courseId;
    String email;
    String registrationStatus;

    public Registration(String registrationId, String courseId, String email, String registrationStatus) {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.email = email;
        this.registrationStatus = registrationStatus;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
