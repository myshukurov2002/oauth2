package com.company.article;

import com.company.article.dtos.ArticleCr;
import com.company.article.dtos.ArticleResp;
import com.company.component.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper implements BaseMapper<ArticleEntity, ArticleCr, ArticleResp> {

    @Override
    public ArticleEntity toEntity(ArticleCr articleCr) {
        return ArticleEntity.builder()
                .categoryId(articleCr.categoryId())
                .title(articleCr.title())
                .content(articleCr.content())
                .viewsCount(articleCr.viewsCount())
                .type(articleCr.type())
                .tags(articleCr.tags().toString())
                .authorId(articleCr.ownerId())
                .photoId(articleCr.photoId())
                .build();
    }


    @Override
    public ArticleResp toResponse(ArticleEntity articleEntity) {
        return new ArticleResp(
                articleEntity.getId(),
                articleEntity.getCategoryId(),
                articleEntity.getAuthorId(),
                articleEntity.getTitle(),
                articleEntity.getContent(),
                articleEntity.getType(),
                articleEntity.getTags().toString(),
                articleEntity.getPhotoId()
        );
    }

}