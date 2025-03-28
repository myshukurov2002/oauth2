package com.company.article;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService service;

    @PostMapping("/insert")
    public ResponseEntity<LikeEntity> insert(){
        return ResponseEntity.ok(service.insert());
    }

    @PutMapping("/update")
    public ResponseEntity<LikeEntity> update(){
        return ResponseEntity.ok(service.update());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<LikeEntity> delete(){
        return ResponseEntity.ok(service.delete());
    }
}
