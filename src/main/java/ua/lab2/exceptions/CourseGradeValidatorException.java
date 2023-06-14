package ua.lab2.exceptions;

public class CourseGradeValidatorException extends RuntimeException {
    public CourseGradeValidatorException(Throwable exception){
        super(exception);
    }
    public CourseGradeValidatorException(String message){
        super(message);
    }
}
