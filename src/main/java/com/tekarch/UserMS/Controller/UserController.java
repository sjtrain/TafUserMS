package com.tekarch.UserMS.Controller;

import com.tekarch.UserMS.DTO.UserDTO;
import com.tekarch.UserMS.Services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }


    // Update a user by userId
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO updatedUser) {
        UserDTO updated = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updated);
    }

    //Delete user by userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        String message = "User with ID " + userId + " has been successfully deleted.";
        return ResponseEntity.ok(message);
    }
}