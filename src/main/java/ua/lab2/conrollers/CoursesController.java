package ua.lab2.conrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lab2.dto.CourseDTO;
import ua.lab2.dto.StudentGradeDTO;
import ua.lab2.entities.Course;
import ua.lab2.entities.Student;
import ua.lab2.exceptions.CourseServiceException;
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
    public ResponseEntity<?> getStudentCourses(@RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.getStudentCourses(userId));
        } catch (CourseServiceException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value= "/api/courses/teacher-courses")
    public ResponseEntity<?> getTeacherCourses(@RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.getTeacherCourses(userId));
        } catch (CourseServiceException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value= "/api/courses/course-students")
    public ResponseEntity<?> getCourseStudents(@RequestParam Integer courseId) {
        try {
            return ResponseEntity.ok(courseService.getCourseStudents(courseId));
        } catch (CourseServiceException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping(value= "/api/courses/student-grade")
    public ResponseEntity<?> getStudentGrade(@RequestParam Integer courseId, @RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.getStudentGrade(userId, courseId));
        } catch (CourseServiceException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
