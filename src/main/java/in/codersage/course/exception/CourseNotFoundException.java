package in.codersage.course.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Integer id) {
        super("Course not found : " + id);
    }
}
