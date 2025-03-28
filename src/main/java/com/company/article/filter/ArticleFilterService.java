package com.company.article.filter;

import com.company.article.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleFilterService {
    @Autowired
    private ArticleFilterRepository repository;

    public PageImpl<ArticleFilterDto> articleFilter(ArticleFilterDto filter, int page, int size) {
        Page<ArticleEntity> result = repository.filter(filter, page, size);

        long totalCount = result.getTotalElements();
        List<ArticleEntity> entityList = result.getContent();

        List<ArticleFilterDto> dtoList = new LinkedList<>();
        for (ArticleEntity entity : entityList) {
            ArticleFilterDto dto = new ArticleFilterDto();
            dto.setCategoryId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setViewsCount(entity.getViewsCount());
            dto.setType(entity.getType());
            dto.setTags(entity.getTags());
            dto.setPhotoId(entity.getPhotoId());
            dto.setOwnerId(entity.getAuthorId());
            dtoList.add(dto);
        }


        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<ArticleFilterDto>(dtoList, pageRequest, totalCount);
    }
}
