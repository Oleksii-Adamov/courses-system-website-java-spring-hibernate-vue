package ua.lab2.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="teacher_id", nullable=false)
    private Teacher teacher;

    @Column(name = "max_grade", nullable = false)
    private Integer maxGrade;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students;
}
