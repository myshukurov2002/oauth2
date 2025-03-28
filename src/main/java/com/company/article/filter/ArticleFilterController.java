package com.company.article.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleFilterController {
    @Autowired
    ArticleFilterService service;

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ArticleFilterDto>> filter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "30") int size,
                                                             @RequestBody ArticleFilterDto filter) {
        PageImpl<ArticleFilterDto> result = service.articleFilter(filter, page - 1, size);
        return ResponseEntity.ok(result);
    }
}
