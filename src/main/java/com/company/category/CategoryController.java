package com.company.category;

import com.company.category.dtos.CategoryCr;
import com.company.category.dtos.CategoryResp;
import com.company.user.dtos.response.UserResp;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResp> create(@RequestBody CategoryCr cr){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.create(cr));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResp> update(
            @PathVariable UUID id,
            @RequestBody CategoryCr cr
            ){
        return ResponseEntity.ok(categoryService.update(id, cr));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResp>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoryService.getAll(page, size));
    }


    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get-by-id")
    @PermitAll
    public ResponseEntity<CategoryResp> getById(@PathVariable UUID id){
        categoryService.getById(id);
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("get-by-name")
    @PermitAll
    public ResponseEntity<CategoryResp> getByName(@PathVariable String name){
        categoryService.getByName(name);
        return ResponseEntity.ok(categoryService.getByName(name));
    }



}
