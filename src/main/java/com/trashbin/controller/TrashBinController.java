package com.trashbin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashbin.domain.TrashBinEntity;
import com.trashbin.dto.TrashBinDto;
import com.trashbin.service.TrashBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("trashbin")
public class TrashBinController {
    private final TrashBinService trashBinService;
    @PostMapping("/create")
    public ResponseEntity<?> createTrashBin(@RequestBody TrashBinDto.PostDto postDto){
        return ResponseEntity.ok().body(trashBinService.createTrashBin(postDto));
    }


    @GetMapping("/getbins")
    public ResponseEntity<?> getTrashBins() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<TrashBinEntity> trashBins = trashBinService.getTrashBins();
        return ResponseEntity.ok().body(trashBins);
    }

    @GetMapping("/getbin")
    public ResponseEntity<?> getTrashBin(@RequestParam Long trashBinId){
        return ResponseEntity.ok().body(trashBinService.getTrashBin(trashBinId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<?> modifyTrashBin(@RequestBody TrashBinDto.PatchDto patchDto){
        return ResponseEntity.ok().body(trashBinService.updateTrashBin(patchDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrashBin(@RequestParam Long trashBinId){
        trashBinService.deleteTrashBin(new TrashBinDto.DeleteDto(trashBinId));
        return ResponseEntity.ok().body("deleted trashBinId:"+trashBinId);
    }
}
