package ua.lab2.enitities;

public class Teacher implements Entity {
    private String userId;

    private String fullName;

    public Teacher() {
    }

    public Teacher(String userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
