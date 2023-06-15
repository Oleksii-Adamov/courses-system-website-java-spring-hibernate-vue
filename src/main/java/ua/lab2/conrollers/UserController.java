package ua.lab2.conrollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lab2.exceptions.CourseServiceException;
import ua.lab2.services.UserService;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value= "/api/user/add-role")
    public ResponseEntity<?> addRole(@RequestParam String role, @RequestAttribute String userId, @RequestAttribute String fullName) {
        if (!(Objects.equals(role, "Teacher") || Objects.equals(role, "Student"))) {
            log.error("Invalid parameter role");
            return ResponseEntity.status(400).body("Invalid parameter role");
        }
        try {
            userService.addRole(userId, fullName, role);
            return ResponseEntity.ok().build();
        } catch (CourseServiceException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
