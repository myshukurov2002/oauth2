package com.company.article.dtos;

import com.company.user.enums.ArticleType;

import java.util.Set;
import java.util.UUID;

public record ArticleCr(
        UUID categoryId,
        String title,
        String content,
        Long viewsCount,
        ArticleType type,
        Set<String> tags,
        UUID photoId,
        UUID ownerId
) {
}
