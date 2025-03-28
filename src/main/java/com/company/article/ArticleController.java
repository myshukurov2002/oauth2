package com.company.article;

import com.company.article.dtos.ArticleCr;
import com.company.article.dtos.ArticleResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleResp> create(@RequestBody ArticleCr cr){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.create(cr));
    }

    @GetMapping("/open/get-by-title")
    public ResponseEntity<ArticleResp> getByTitle(@RequestParam String title){
        return ResponseEntity.ok(articleService.getByTitle(title));
    }

    @GetMapping("/open/get-by-id")
    public ResponseEntity<ArticleResp> getById(@RequestParam String title){
        return ResponseEntity.ok(articleService.getByTitle(title));
    }

    @GetMapping("/open/get-by-type")
    public ResponseEntity<List<ArticleResp>> getByType(@RequestParam String type){
        return ResponseEntity.ok(articleService.getByType(type));
    }

    @GetMapping("/open/get-by-tags")
    public ResponseEntity<List<ArticleResp>> getByTags(@RequestParam String tags){
        return ResponseEntity.ok(articleService.getByTags(tags));
    }

    @GetMapping("/open/get-by-category")
    public ResponseEntity<List<ArticleResp>> getByCategory(@RequestParam String category){
        return ResponseEntity.ok(articleService.getByCategory(category));
    }

    @GetMapping
    public ResponseEntity<Page<ArticleResp>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(articleService.getAll(page, size));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleResp> update(@PathVariable UUID id,
                                              @RequestBody ArticleCr cr){
        return ResponseEntity.ok(articleService.update(id, cr));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create-article")
    public ResponseEntity<ArticleResp> createArticle (@RequestBody ArticleCr cr){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.createArticle(cr));
    }

    @PostMapping("/view")
    public ResponseEntity<?> view(UUID userId, UUID articleId) {
        return articleService.view(userId, articleId);
    }




}
