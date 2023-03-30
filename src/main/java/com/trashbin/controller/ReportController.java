package com.trashbin.controller;

import com.trashbin.dto.ReportDto;
import com.trashbin.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/create")
    public ResponseEntity<?> createReport(@RequestBody ReportDto.PostDto postDto){
        return ResponseEntity.ok().body(reportService.createReport(postDto));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getReport(@RequestParam Long reportId){
        return ResponseEntity.ok().body(reportService.getReport(reportId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyReport(@RequestBody ReportDto.PatchDto patchDto){
        return ResponseEntity.ok().body(reportService.updateReport(patchDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteReport(@RequestParam Long reportId){
        reportService.deleteReport(new ReportDto.DeleteDto(reportId));
        return ResponseEntity.ok().body("deleted reportId:"+reportId);
    }
}
