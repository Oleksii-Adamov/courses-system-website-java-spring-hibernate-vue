package ua.lab2.conrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.lab2.entities.Course;
import ua.lab2.entities.Student;
import ua.lab2.services.CourseService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class CoursesController {
    private CourseService courseService;
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value= "/api/courses/teacher-courses")
    public List<Course> getTeacherCourses(@RequestAttribute String userId) {
        log.debug("getTeacherCourses userId=" + userId);
//        List<Course> courses = new ArrayList<Course>();
//        courses.add(new Course(1, "Test course", "teacherId", 100));
//        return courses;
        return courseService.getTeacherCourses("123");
    }

    @GetMapping(value= "/api/courses/course-students")
    public List<Student> getCourseStudents(@RequestParam Integer courseId, @RequestAttribute String userId) {
        List<Student> students = new ArrayList<Student>();
        //students.add(new Student(String.valueOf(courseId), "test name"));
        return students;
    }
}
