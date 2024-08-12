package org.twspring.lab10.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab10.Api.ApiResponse;
import org.twspring.lab10.Model.User;
import org.twspring.lab10.Service.UserService;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //GET
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        if(userService.getAllUsers().isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No users found"));
        }
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
       if(errors.hasErrors()) {
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(new ApiResponse(message));
       }
       userService.addUser(user);
       return ResponseEntity.status(201).body(new ApiResponse("User added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if(userService.updateUser(id, user)) {
            return ResponseEntity.status(201).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No user found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if(userService.deleteUser(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No user found"));

    }
}
