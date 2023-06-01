package com.trashbin.controller;

import com.trashbin.dto.ClientDto;
import com.trashbin.dto.TrashBinDto;
import com.trashbin.service.TrashBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("trashbin")
public class TrashBinController {
    private final TrashBinService trashBinService;

    @PostMapping("/create")
    public ResponseEntity<?> createTrashBin(@RequestBody TrashBinDto.TrashBinPostDto trashBinPostDto) {
        return ResponseEntity.ok().body(trashBinService.createTrashBin(trashBinPostDto));
    }

    @GetMapping("/getallbins")
    public ResponseEntity<?> getAllTrashBins() {
        return ResponseEntity.ok().body(trashBinService.getAllTrashBin());
    }


    @GetMapping("/getnearbins")
    public ResponseEntity<?> getNearBins(@ModelAttribute("gpsdistance") ClientDto.ClientGPSDistanceDto clientGpsDistanceDto) {
        return ResponseEntity.ok().body(trashBinService.getNearTrashBin(clientGpsDistanceDto));
    }

    @GetMapping("/getbin")
    public ResponseEntity<?> getTrashBin(@RequestParam Long trashBinId) {
        return ResponseEntity.ok().body(trashBinService.getTrashBin(trashBinId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyTrashBin(@RequestBody TrashBinDto.TrashBinPatchDto trashBinPatchDto) {
        return ResponseEntity.ok().body(trashBinService.updateTrashBin(trashBinPatchDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrashBin(@RequestParam Long trashBinId) {
        trashBinService.deleteTrashBin(new TrashBinDto.TrashBinDeleteDto(trashBinId));
        return ResponseEntity.ok().body("deleted trashBinId:" + trashBinId);
    }
}
