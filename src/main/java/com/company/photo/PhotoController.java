package com.company.photo;


import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PermitAll
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoResp> upload(@RequestParam MultipartFile file) {

        file.getOriginalFilename().endsWith(".PNG");
        return ResponseEntity.ok(photoService.upload(file));
    }


    @GetMapping(value = "/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> upload(@PathVariable UUID id) {

//        file.getOriginalFilename().endsWith(".PNG")
        return ResponseEntity.ok(photoService.getById(id));
    }
}
