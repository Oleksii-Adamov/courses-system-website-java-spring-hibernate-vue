package ua.lab2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lab2.entities.Course;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findById(Long id);
}
