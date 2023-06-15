package ua.lab2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lab2.entities.Student;
import ua.lab2.entities.Teacher;
import ua.lab2.exceptions.KeycloakSecurityServiceException;
import ua.lab2.exceptions.UserServiceException;
import ua.lab2.repositories.StudentRepository;
import ua.lab2.repositories.TeacherRepository;
import ua.lab2.security.KeycloakSecurityService;
import ua.lab2.security.SecurityService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SecurityService securityService = new KeycloakSecurityService();

    @Autowired
    public UserService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void addRole(String userId, String fullName, String role) throws UserServiceException {
        try {
            if ("Teacher".equals(role)) {
                Optional<Teacher> teacherFromUserIdOpt = teacherRepository.findByUserId(userId);
                if (teacherFromUserIdOpt.isPresent()) {
                    throw new UserServiceException("Teacher with such userId already exists in database");
                }
                securityService.giveUserTeacherRole(userId);
                Teacher teacher = new Teacher();
                teacher.setUserId(userId);
                teacher.setFullName(fullName);
                teacher.setCourses(new ArrayList<>());
                teacherRepository.save(teacher);
            } else {
                Optional<Student> studentFromUserId = studentRepository.findByUserId(userId);
                if (studentFromUserId.isPresent()) {
                    throw new UserServiceException("Student with such userId already exists in database");
                }
                securityService.giveUserStudentRole(userId);
                Student student = new Student();
                student.setUserId(userId);
                student.setFullName(fullName);
                student.setCourses(new ArrayList<>());
                studentRepository.save(student);
            }
        } catch (KeycloakSecurityServiceException e) {
            throw new UserServiceException(e);
        }
    }
}
