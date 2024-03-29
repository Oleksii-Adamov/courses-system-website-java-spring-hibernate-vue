package ua.lab2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lab2.entities.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {
    Optional<Teacher> findByUserId(String userId);
}
