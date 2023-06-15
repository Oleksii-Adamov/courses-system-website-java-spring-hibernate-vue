package ua.lab2.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseId {
    private String studentUserId;
    private Long courseId;
}
