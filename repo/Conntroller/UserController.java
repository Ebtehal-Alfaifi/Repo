package com.example.repo.Conntroller;

import com.example.repo.Model.User;
import com.example.repo.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    public ResponseEntity getAll(){
        List <User> users=userService.getAll();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.update(id, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }


    @GetMapping("/check-credentials/{username}/{password}")
    public ResponseEntity checkCredentials(@PathVariable String username, @PathVariable String password) {
        userService.check(username, password);
        return ResponseEntity.status(200).body("Credentials are correct");
    }


    @GetMapping("/email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.status(200).body(user);
    }


    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getRole(role);
        return ResponseEntity.status(200).body(users);
    }


    @GetMapping("/age/{age}")
    public ResponseEntity<?> getUsersByAge(@PathVariable Integer age) {
        List<User> users = userService.getUsersByAge(age);
        return ResponseEntity.status(200).body(users);
    }
}
