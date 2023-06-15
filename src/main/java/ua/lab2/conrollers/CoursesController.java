package ua.lab2.conrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.lab2.dto.CourseDTO;
import ua.lab2.dto.StudentGradeDTO;
import ua.lab2.entities.Course;
import ua.lab2.entities.Student;
import ua.lab2.services.CourseService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class CoursesController {
    private final CourseService courseService;
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value= "/api/courses/student-courses")
    public List<CourseDTO> getStudentCourses(@RequestAttribute String userId) {
        try
        return courseService.getStudentCourses(userId);
    }

    @GetMapping(value= "/api/courses/teacher-courses")
    public List<CourseDTO> getTeacherCourses(@RequestAttribute String userId) {
        return courseService.getTeacherCourses(userId);
    }

    @GetMapping(value= "/api/courses/course-students")
    public List<Student> getCourseStudents(@RequestParam Integer courseId) {
        return courseService.getCourseStudents(courseId);
    }
    @GetMapping(value= "/api/courses/student-grade")
    public StudentGradeDTO getStudentGrade(@RequestParam Integer courseId, @RequestAttribute String userId) {
        return courseService.getStudentGrade(userId, courseId);
    }
}
