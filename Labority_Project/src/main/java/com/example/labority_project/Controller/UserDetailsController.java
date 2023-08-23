package com.example.labority_project.Controller;


import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.DTO.UserDto;
import com.example.labority_project.Service.UserDetailsService;
import com.example.labority_project.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/userdetails")
public class UserDetailsController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/add")
    public ResponseEntity addUserDetailsController(@RequestBody @Valid UserDto userDto){


        userDetailsService.addUserDetails(userDto);
        return ResponseEntity.status(200).body(new ApiResponse("User Details added"));
    }

    @GetMapping("/get")
    public ResponseEntity getAllUsersDetailsController(){

        return ResponseEntity.status(200).body(userDetailsService.getAllUsersDetails());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUserDetailsController(@PathVariable  Integer id,@RequestBody @Valid UserDto userDto){
        userDetailsService.updateUserDetails(id,userDto);
        return ResponseEntity.status(200).body(new ApiResponse("User Details Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUserDetailsController(@PathVariable Integer id){
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.status(200).body(new ApiResponse("User Details deleted"));
    }

}