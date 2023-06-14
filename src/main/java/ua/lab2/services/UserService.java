package ua.lab2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lab2.entities.Teacher;
import ua.lab2.exceptions.KeycloakSecurityServiceException;
import ua.lab2.exceptions.UserServiceException;
import ua.lab2.repositories.TeacherRepository;
import ua.lab2.security.SecurityService;

import java.util.ArrayList;

@Service
public class UserService {
//    @Autowired
    private TeacherRepository teacherRepository;
//    @Autowired
    private SecurityService securityService;

//    public UserService(TeacherRepository teacherRepository, SecurityService securityService) {
//        this.teacherRepository = teacherRepository;
//        this.securityService = securityService;
//    }

    @Transactional
    public void addRole(String userId, String fullName, String role) throws UserServiceException {
        if ("Teacher".equals(role)) {
            Teacher teacherFromUserId = teacherRepository.findByUserId(userId);
            if (teacherFromUserId != null) {
                throw new UserServiceException("Teacher with such userId already exists in database");
            }
            try {
                securityService.giveUserTeacherRole(userId);
            } catch (KeycloakSecurityServiceException e) {
                throw new UserServiceException(e);
            }
            //Teacher teacher = new Teacher(userId, fullName, new ArrayList<>());
//            Teacher teacher = new Teacher();
//            teacher.setUserId(userId);
//            teacher.setFullName(fullName);
//            teacher.setCourses(new ArrayList<>());
//            teacherRepository.save(teacher);
        }
//        else {
//            TransactionFactory.getInstance().beginTransaction();
//            try {
//                DAOFactory.getInstance().getStudentDAO().create(new Student(userId, fullName));
//                securityService.giveUserStudentRole(userId);
//            } catch (KeycloakSecurityServiceException | SQLException e) {
//                TransactionFactory.getInstance().rollbackTransaction();
//                throw e;
//            }
//            TransactionFactory.getInstance().endTransaction();
//        }
    }
}
