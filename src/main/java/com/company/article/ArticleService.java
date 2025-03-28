package com.company.article;

import com.company.article.dtos.ArticleCr;
import com.company.article.dtos.ArticleResp;
import com.company.article.views.ViewEntity;
import com.company.article.views.ViewRepository;
import com.company.expections.exp.DuplicateItemException;
import com.company.user.enums.ArticleType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ViewRepository viewRepository;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleResp create(ArticleCr cr){
        ArticleEntity entity = articleMapper.toEntity(cr);
//        entity.setAuthorId(cr.authorId());
        entity.setCategoryId(cr.categoryId());
        entity.setContent(cr.content());
        entity.setTags(Collections.singleton(cr.tags().toString()));
        entity.setType(cr.type());

        return articleMapper.toResponse(articleRepository.save(entity));
    }

    public ArticleResp update(UUID uuid, ArticleCr cr){
        ArticleEntity entity = articleRepository.findByIdAndVisibilityTrue(uuid)
                .orElseThrow(() -> new RuntimeException("Article not found!!!"));
//        entity.setAuthorId(cr.authorId());
        entity.setCategoryId(cr.categoryId());
        entity.setContent(cr.content());
        entity.setTags(Collections.singleton(cr.tags().toString()));
        entity.setType(cr.type());

        return articleMapper.toResponse(articleRepository.save(entity));
    }

    public ArticleResp getById(UUID id){
        return articleMapper.toResponse(articleRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("Article not found!!!")));

    }

    public ArticleResp getByTitle(String title){
        return articleMapper.toResponse(articleRepository.findByTitleAndVisibilityTrue(title)
                .orElseThrow(() -> new RuntimeException("Article not found!!!")));
    }

    public List<ArticleResp> getByTags(String tags){
        return articleRepository
                .findAllByTagsAndVisibilityTrue(tags)
                .stream()
                .map(articleMapper::toResponse)
                .toList();

    }

    public List<ArticleResp> getByType(String type){
        return articleRepository
                .findAllByTypeAndVisibilityTrue(ArticleType.valueOf(type))
                .stream()
                .map(articleMapper::toResponse)
                .toList();

    }

    public List<ArticleResp> getByCategory(String categoryId){
        return articleRepository
                .findAllByCategoryIdAndVisibilityTrue(UUID.fromString(categoryId))
                .stream()
                .map(articleMapper::toResponse)
                .toList();

    }

    public Page<ArticleResp> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ArticleEntity> all = articleRepository.findAllByVisibilityTrue(pageable);

        List<ArticleResp> list = all.stream()
                .map(articleMapper::toResponse)
                .toList();

        return new PageImpl<>(list ,pageable, list.size());
    }

    public void deleteById(UUID id){
        ArticleEntity entity = articleRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("Article not found!!!"));

        entity.setVisibility(false);
        articleMapper.toResponse(articleRepository.save(entity));
    }

    public ArticleResp createArticle(ArticleCr cr) {
        ArticleEntity entity=articleMapper.toEntity(cr);
        entity.setCategoryId(cr.categoryId());
        entity.setAuthorId(cr.ownerId());
        entity.setPhotoId(cr.photoId());
        return articleMapper.toResponse(articleRepository.save(entity));
    }

    public ResponseEntity<?> view(UUID userId, UUID articleId) {

        Optional<ViewEntity> byUserIdAndArticleId = viewRepository.findByUserIdAndArticleId(userId, articleId);

        if (byUserIdAndArticleId.isPresent()) {
            throw new DuplicateItemException();
        }

        ViewEntity entity = new ViewEntity();
        entity.setArticleId(articleId);
        entity.setUserId(userId);

        viewRepository.save(entity);
        return ResponseEntity.ok("SUCCESS");
    }


}
