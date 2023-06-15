package ua.lab2.exceptions;

public class CourseServiceException extends RuntimeException {
    public CourseServiceException(Throwable exception){
        super(exception);
    }
    public CourseServiceException(String message){
        super(message);
    }
}
