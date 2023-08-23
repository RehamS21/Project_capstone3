package com.example.labority_project.Controller;

import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.Model.Laboratories;
import com.example.labority_project.Model.TestType;
import com.example.labority_project.Service.TestTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
public class TestTypeController {
    private final TestTypeService testTypeService;

    @GetMapping("/get")
    public ResponseEntity getAllTestTypes(){
        return ResponseEntity.status(200).body(testTypeService.getAllTestType());
    }

    @PostMapping("/add/{laboratary_id}")
    public ResponseEntity addNewTestType(@PathVariable Integer laboratary_id , @RequestBody @Valid TestType testType){
        testTypeService.addTestType(laboratary_id, testType);
        return ResponseEntity.status(200).body(new ApiResponse("New test added successfully"));
    }

    @PutMapping("/update/{id}")

    public ResponseEntity updateTestTypes(@PathVariable Integer id, @RequestBody @Valid TestType testType){
        testTypeService.updateTestType(id,testType);

        return ResponseEntity.status(200).body(new ApiResponse("the test type updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTestTypes(@PathVariable Integer id){
        testTypeService.deleteTestType(id);

        return ResponseEntity.status(200).body(new ApiResponse("the test deleted successfully"));
    }

    @PutMapping("/book/{user_id}/{testType_id}")
    public ResponseEntity bookingTestTypeAppointment(@PathVariable Integer user_id , @PathVariable Integer testType_id){
        testTypeService.booking_appointment(user_id, testType_id);
        return ResponseEntity.status(200).body(new ApiResponse("The test type successfully booking"));
    }
}
