package ua.lab2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lab2.entities.StudentCourse;
import ua.lab2.entities.StudentCourseId;

import java.util.Optional;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourse, StudentCourseId> {
    Optional<StudentCourse> findByCourseIdAndStudentUserId(Long courseId, String studentUserId);
}
