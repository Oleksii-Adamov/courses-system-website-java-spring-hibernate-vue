package ua.lab2.conrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lab2.exceptions.CourseServiceException;
import ua.lab2.services.CourseService;

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
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value= "/api/courses/teacher-courses")
    public ResponseEntity<?> getTeacherCourses(@RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.getTeacherCourses(userId));
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value= "/api/courses/course-students")
    public ResponseEntity<?> getCourseStudents(@RequestParam Integer courseId) {
        try {
            return ResponseEntity.ok(courseService.getCourseStudents(courseId));
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping(value= "/api/courses/student-grade")
    public ResponseEntity<?> getStudentGrade(@RequestParam Integer courseId, @RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.getStudentGrade(userId, courseId));
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value= "/api/courses/create")
    public ResponseEntity<?> create(@RequestParam String name, @RequestParam Integer maxGrade, @RequestAttribute String userId) {
        try {
            return ResponseEntity.ok(courseService.create(name, userId, maxGrade));
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value= "/api/courses/join")
    public ResponseEntity<?> joinCourse(@RequestParam Integer courseId, @RequestAttribute String userId) {
        try {
            courseService.joinCourse(userId, courseId);
            return ResponseEntity.ok().build();
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping(value= "/api/courses//grade-student")
    public ResponseEntity<?> gradeStudent(@RequestParam Integer courseId, @RequestParam Integer grade,
                                          @RequestParam String teacherResponse, @RequestAttribute String userId) {
        try {
            courseService.gradeStudent(courseId, userId, grade, teacherResponse);
            return ResponseEntity.ok().build();
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
