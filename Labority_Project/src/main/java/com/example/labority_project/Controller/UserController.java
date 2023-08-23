package com.example.labority_project.Controller;

import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.Model.Laboratories;
import com.example.labority_project.Model.User;
import com.example.labority_project.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){

        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user){
        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
    }


}
