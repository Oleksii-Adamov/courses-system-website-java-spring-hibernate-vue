package ua.lab2.security;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import ua.lab2.exceptions.KeycloakSecurityServiceException;

import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class KeycloakSecurityService implements SecurityService{

    private final HttpClient client = HttpClient.newHttpClient();
    public KeycloakSecurityService() {
        // default constructor
    }

    private String getAdminToken() throws IOException, InterruptedException, KeycloakSecurityServiceException {
        Map<String, String> formData = new HashMap<>();
        formData.put("username", "admin");
        formData.put("password", "root");
        formData.put("grant_type", "password");
        formData.put("client_id", "admin-cli");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/realms/master/protocol/openid-connect/token"))
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.debug("keycloak login as admin response code: " + response.statusCode());
        if (response.statusCode() == 200) {
            JSONObject jsonObject = new JSONObject(response.body());
            String accessToken = (String) jsonObject.get("access_token");
            log.debug("admin access token: " + accessToken);
            return accessToken;
        }
        else {
            throw new KeycloakSecurityServiceException("cannot login as admin to keycloak");
        }
    }

    @Override
    public void giveUserTeacherRole(String userId) {
        try {
            String accessToken = getAdminToken();
            String teacherRoleRepresantation = "[{\"id\":\"db7c7ece-dd60-48d6-849a-790b062cba39\",\"name\":\"Teacher\",\"description\":\"\",\"composite\":false,\"clientRole\":false,\"containerId\":\"1e290e5d-4fcc-44ff-af1b-2629cfa69905\"}]";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/realms/CoursesRealm/users/" + userId + "/role-mappings/realm"))
                    .POST(HttpRequest.BodyPublishers.ofString(teacherRoleRepresantation))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.debug("giveUserTeacherRole response code: " + response.statusCode());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
            throw new KeycloakSecurityServiceException(e);
        } catch (IOException | KeycloakSecurityServiceException e) {
            log.error(e.getMessage());
            throw new KeycloakSecurityServiceException(e);
        }
    }

    @Override
    public void giveUserStudentRole(String userId) {
        try {
            String accessToken = getAdminToken();
            String studentRoleRepresantation = "[{\"id\":\"5b4f5c42-7de6-45b7-a818-085e8adfcd07\",\"name\":\"Student\",\"description\":\"\",\"composite\":false,\"clientRole\":false,\"containerId\":\"1e290e5d-4fcc-44ff-af1b-2629cfa69905\"}]";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/admin/realms/CoursesRealm/users/" + userId + "/role-mappings/realm"))
                    .POST(HttpRequest.BodyPublishers.ofString(studentRoleRepresantation))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + accessToken)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.debug("giveUserStudentRole response code: " + response.statusCode());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
            throw new KeycloakSecurityServiceException(e);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new KeycloakSecurityServiceException(e);
        }
    }

    private static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }
}
