package in.codersage.course.controller;

import in.codersage.course.dto.CourseDTO;
import in.codersage.course.entity.Course;
import in.codersage.course.exception.CourseNotFoundException;
import in.codersage.course.mapper.CourseMapper;
import in.codersage.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    ICourseService courseService;

    @GetMapping(value="/courses")
    List<Course> getAll(){
        return courseService.getAllCourses();
    }
    @GetMapping(value="/courses/{id}")
    ResponseEntity<Course> getById(@PathVariable("id") @Min(1) int id) {
        Course crs = courseService.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));
        return ResponseEntity.ok().body(crs);
    }
    @PostMapping(value="/courses")
    ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO incrs) {
        Course course      = CourseMapper.DtoToEntity(incrs);
        Course addedcrs = courseService.save(course);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedcrs.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping(value="/courses/{id}")
    ResponseEntity<Course> updateCourse(@PathVariable("id")  @Min(1) int id, @Valid @RequestBody CourseDTO incourse) {
        Course course = courseService.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));

        Course newcrs = CourseMapper.DtoToEntity(incourse);
        newcrs.setId(course.getId());
        courseService.save(newcrs);
        return ResponseEntity.ok().body(newcrs);
    }
    @DeleteMapping(value="/courses/{id}")
    ResponseEntity deleteCourse(@PathVariable("id") @Min(1) int id) {
        Course course = courseService.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));
        courseService.delete(course.getId());
        return ResponseEntity.ok().body("Course with ID : "+id+" deleted with success!");
    }
}
