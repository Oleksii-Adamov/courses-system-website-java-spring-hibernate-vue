package ua.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Integer id;
    private String name;

    private String teacherId;
    private Integer maxGrade;
}
