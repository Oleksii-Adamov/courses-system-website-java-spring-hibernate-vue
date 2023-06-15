package ua.lab2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lab2.dto.StudentGradeDTO;
import ua.lab2.entities.Course;
import ua.lab2.entities.Student;
import ua.lab2.entities.StudentCourse;
import ua.lab2.entities.Teacher;
import ua.lab2.exceptions.CourseServiceException;
import ua.lab2.mappers.CourseMapper;
import ua.lab2.repositories.CourseRepository;
import ua.lab2.repositories.StudentCourseRepository;
import ua.lab2.repositories.StudentRepository;
import ua.lab2.repositories.TeacherRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;


    @Autowired
    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository,
                         StudentCourseRepository studentCourseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public Long create(String name, String teacherId, int maxGrade) throws CourseServiceException {
        Course course = new Course();
        course.setName(name);
        course.setMaxGrade(maxGrade);
        Teacher teacher = teacherRepository.findByUserId(teacherId).orElseThrow(() -> new CourseServiceException("Cannot find teacher with userId = " + teacherId));
        course.setTeacher(teacher);
        return courseRepository.save(course).getId();
    }

    public List<Course> getTeacherCourses(String teacherUserId) throws CourseServiceException {
        Teacher teacher = teacherByUserId(teacherUserId);
        return teacher.getCourses();
    }

    public List<Course> getStudentCourses(String studentUserId) throws CourseServiceException {
        Student student = studentByUserId(studentUserId);
        return student.getCourses();
    }

    public void joinCourse(String studentUserId, Integer courseId) throws CourseServiceException {
        Course course = courseById(courseId);
        Student student = studentByUserId(studentUserId);
        student.getCourses().add(course);
    }

    public List<Student> getCourseStudents(Integer courseId) throws CourseServiceException {
        Course course = courseById(courseId);
        return course.getStudents();
    }

    public void gradeStudent(Integer courseId, String studentUserId, Integer grade, String teacherResponse) throws CourseServiceException {
        StudentCourse studentCourse = studentCourseByCourseIdAndStudentUserId(courseId, studentUserId);
        studentCourse.setGrade(grade);
        studentCourse.setTeacherResponse(teacherResponse);
        studentCourseRepository.save(studentCourse);
    }

    public StudentGradeDTO getStudentGrade(String studentUserId, Integer courseId) throws CourseServiceException {
        StudentCourse studentCourse = studentCourseByCourseIdAndStudentUserId(courseId, studentUserId);
        Course course = courseById(courseId);
        return new StudentGradeDTO(studentCourse.getGrade(), studentCourse.getTeacherResponse(), course.getMaxGrade());
    }

    private Course courseById(Integer id) throws CourseServiceException {
        return courseRepository.findById(Long.valueOf(id)).orElseThrow(() -> new CourseServiceException("No course with id = " + id));
    }

    private Teacher teacherByUserId(String userId) throws CourseServiceException {
        return teacherRepository.findByUserId(userId).orElseThrow(() -> new CourseServiceException("Cannot find teacher with userId = " + userId));
    }

    private Student studentByUserId(String userId) throws CourseServiceException {
        return studentRepository.findByUserId(userId).orElseThrow(() -> new CourseServiceException("Cannot find student with userId = " + userId));
    }

    private StudentCourse studentCourseByCourseIdAndStudentUserId(Integer courseId, String studentUserId) throws CourseServiceException {
        return studentCourseRepository.findByCourseIdAndStudentUserId(Long.valueOf(courseId), studentUserId)
                .orElseThrow(() -> new CourseServiceException("No such course with id = " + courseId + " or no such student with userId=" + studentUserId));
    }
}
