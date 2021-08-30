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
    ResponseEntity<?> createProduct(@Valid @RequestBody CourseDTO incrs) {
        Course prd      = CourseMapper.DtoToEntity(incrs);
        Course addedprd = courseService.save(prd);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedprd.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping(value="/courses/{id}")
    ResponseEntity<Course> updateProduct(@PathVariable("id")  @Min(1) int id, @Valid @RequestBody CourseDTO incourse) {
        Course prd = courseService.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));

        Course newcrs = CourseMapper.DtoToEntity(incourse);
        newcrs.setId(prd.getId());
        courseService.save(newcrs);
        return ResponseEntity.ok().body(newcrs);
    }
    @DeleteMapping(value="/products/{id}")
    ResponseEntity deleteProduct(@PathVariable("id") @Min(1) int id) {
        Course prd = courseService.findById(id)
                .orElseThrow(()->new CourseNotFoundException(id));
        courseService.delete(prd.getId());
        return ResponseEntity.ok().body("Course with ID : "+id+" deleted with success!");
    }
}
