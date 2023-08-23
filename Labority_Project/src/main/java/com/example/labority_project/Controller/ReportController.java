package com.example.labority_project.Controller;

import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.DTO.ReportDTO;
import com.example.labority_project.DTO.UserDto;
import com.example.labority_project.Service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/add")
    public ResponseEntity addReportController(@RequestBody @Valid ReportDTO reportDTO){
        reportService.addReport(reportDTO);
        return ResponseEntity.status(200).body(new ApiResponse("The report added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getAllReportController(){

        return ResponseEntity.status(200).body(reportService.getAllReport());
    }
    @PutMapping("/update")
    public ResponseEntity updateReportController(@RequestBody @Valid ReportDTO reportDTO){
        reportService.updateReport(reportDTO);
        return ResponseEntity.status(200).body(new ApiResponse("The report updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReportController(@PathVariable Integer id){
        reportService.deleteReport(id);
        return ResponseEntity.status(200).body(new ApiResponse("the report deleted successfully"));
    }
}
