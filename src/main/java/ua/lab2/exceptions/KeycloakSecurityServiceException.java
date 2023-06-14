package ua.lab2.exceptions;

public class KeycloakSecurityServiceException extends RuntimeException {

    public KeycloakSecurityServiceException(Throwable exception){
        super(exception);
    }
    public KeycloakSecurityServiceException(String message){
        super(message);
    }
}
