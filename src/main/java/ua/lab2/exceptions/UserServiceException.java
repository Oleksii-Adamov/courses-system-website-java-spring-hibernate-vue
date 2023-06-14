package ua.lab2.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(Throwable exception){
        super(exception);
    }
    public UserServiceException(String message){
        super(message);
    }
}
