package com.example.demo.Controller;

import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
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
    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addAllUsers(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addAllUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse("Success"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUsers(@PathVariable Integer id ,@RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }
        boolean isUpdateUser =userService.updateUser(id, user);
        if (isUpdateUser){
            return ResponseEntity.status(200).body(new ApiResponse("user updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        boolean isDelete = userService.deleteUser(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("user deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @GetMapping("/get/{role}/{id}")
    public ResponseEntity getUserByRole(@PathVariable String role,@PathVariable Integer id) {
        User user = userService.getUserByRole(role,id);
        if (user == null) {
            return ResponseEntity.status(400).body(new ApiResponse("User not found"));
        }
        return ResponseEntity.status(200).body(user);
    }
    //Retrieve user purchase statistics (number of purchases and total money spent)
    @GetMapping("/stats/{id}")
    public ResponseEntity getUserStats(@PathVariable Integer id) {
        List<User> response = userService.getUserStatistics(id);
        return ResponseEntity.status(200).body(response);
    }
}
