package ua.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentGradeDTO {
    private Integer grade;
    private String teacherResponse;
    private Integer maxGrade;
}
