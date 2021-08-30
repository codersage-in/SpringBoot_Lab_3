package in.codersage.course.service;

import in.codersage.course.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ICourseService {
    List<Course> getAllCourses();
    Optional<Course> findById(int id);
    Course save(Course prd);
    void delete(int id);
}
