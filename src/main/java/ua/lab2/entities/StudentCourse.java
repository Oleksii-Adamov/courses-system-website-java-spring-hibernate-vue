package ua.lab2.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "students_courses")
@IdClass(StudentCourseId.class)
public class StudentCourse {

    @Id
    @Column(name = "student_user_id", nullable = false)
    private String studentUserId;

    @Id
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "grade", nullable = true)
    private Integer grade;

    @Column(name = "teacher_response", nullable = true)
    private String teacherResponse;
}
