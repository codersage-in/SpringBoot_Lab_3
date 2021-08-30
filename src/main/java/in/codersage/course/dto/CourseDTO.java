package in.codersage.course.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import in.codersage.course.validation.NamePrefix;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CourseDTO {
    @NamePrefix(message = "Name must start with CRS")
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull
    @Min(value = 200)
    @Positive(message = "Amount cannot be Zero or negative")
    private int amount;
}