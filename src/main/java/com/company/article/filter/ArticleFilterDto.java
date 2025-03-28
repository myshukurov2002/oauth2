package com.company.article.filter;
import com.company.user.enums.ArticleType;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ArticleFilterDto {
    private UUID categoryId;
    private String title;
    private String content;
    private Long viewsCount;
    private ArticleType type;
    private Set<String> tags;
    private UUID photoId;
    private UUID ownerId;
}
