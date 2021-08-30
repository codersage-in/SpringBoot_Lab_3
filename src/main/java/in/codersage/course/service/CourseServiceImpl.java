package in.codersage.course.service;

import in.codersage.course.entity.Course;
import in.codersage.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    @Override
    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }
    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }
}
