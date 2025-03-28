package com.company.article.dtos;

import com.company.user.enums.ArticleType;

import java.util.UUID;

public record ArticleResp(
        UUID id,
        UUID categoryId,
        UUID authorId,
        String title,
        String content,
        ArticleType type,
        String tags,
        UUID photoId


) {
}
