package ua.lab2.security;

public interface SecurityService {
    void giveUserTeacherRole(String userId);
    void giveUserStudentRole(String userId);
}
