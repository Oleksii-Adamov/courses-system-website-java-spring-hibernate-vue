package ua.lab2.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_courses",
            joinColumns = { @JoinColumn(name = "student_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> courses;
}
