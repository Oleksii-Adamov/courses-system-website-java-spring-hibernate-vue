package ua.lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lab2.entities.Teacher;
@Repository
//public interface TeacherRepository extends JpaRepository<Teacher, String> {
public interface TeacherRepository extends CrudRepository<Teacher, String> {
    Teacher findByUserId(String userId);
}
