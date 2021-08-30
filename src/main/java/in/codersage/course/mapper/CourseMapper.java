package in.codersage.course.mapper;


import in.codersage.course.dto.CourseDTO;
import in.codersage.course.entity.Course;

public class CourseMapper {
    public static Course DtoToEntity(CourseDTO prd) {
        return new Course().setName(prd.getName())
                .setAmount(prd.getAmount());
    }
    public static CourseDTO EntityToDto(Course crs) {
        return new CourseDTO().setName(crs.getName())
                .setAmount(crs.getAmount());
    }
}
