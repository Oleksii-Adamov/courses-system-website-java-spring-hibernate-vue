package ua.lab2.services;

import org.springframework.stereotype.Service;
import ua.lab2.enitities.Course;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    public List<Course> getTeacherCourses(String teacherUserId) {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course(1, "Test course", "teacherId", 100));
        return courses;
    }
}
