package com.example.labority_project.Controller;

import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.Model.Laboratories;
import com.example.labority_project.Service.LaboartoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lab")
@RequiredArgsConstructor
public class LaboartoriesController {
    private final LaboartoriesService laboartoriesService;

    @GetMapping("/get")
    public ResponseEntity getAllLaboartories(){
        return ResponseEntity.status(200).body(laboartoriesService.getAllLaboratory());
    }

    @PostMapping("/add")
    public ResponseEntity addNewLaboartories(@RequestBody @Valid Laboratories laboratories){
        laboartoriesService.addLaboratory(laboratories);
        return ResponseEntity.status(200).body(new ApiResponse("New laboratory added successfully"));
    }

    @PutMapping("/update/{id}")

    public ResponseEntity updateLaboartories(@PathVariable Integer id, @RequestBody @Valid Laboratories laboratory){
        laboartoriesService.updateLaboratory(id,laboratory);

        return ResponseEntity.status(200).body(new ApiResponse("the laboratory updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLaboartories(@PathVariable Integer id){
        laboartoriesService.deleteLaboratory(id);

        return ResponseEntity.status(200).body(new ApiResponse("the laboratory deleted successfully"));
    }
}
