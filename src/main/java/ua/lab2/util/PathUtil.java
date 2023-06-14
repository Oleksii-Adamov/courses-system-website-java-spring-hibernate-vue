package ua.lab2.util;

public class PathUtil {
    private PathUtil() {}
    public static String withApiPath(String path) {
        return "/Lab1/api" + path;
    }
}
