package ua.lab2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lab2.entities.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByUserId(String userId);
}
