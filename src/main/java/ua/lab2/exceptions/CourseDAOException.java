package ua.lab2.exceptions;

public class CourseDAOException extends RuntimeException {
    public CourseDAOException(Throwable exception){
        super(exception);
    }
    public CourseDAOException(String message){
        super(message);
    }
}
