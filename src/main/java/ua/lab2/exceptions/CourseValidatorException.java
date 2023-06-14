package ua.lab2.exceptions;

public class CourseValidatorException extends RuntimeException {
    public CourseValidatorException(Throwable exception){
        super(exception);
    }
    public CourseValidatorException(String message){
        super(message);
    }
}
