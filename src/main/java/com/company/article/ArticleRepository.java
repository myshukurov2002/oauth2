package com.company.article;

import com.company.user.enums.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
    Optional<ArticleEntity> findByIdAndVisibilityTrue(UUID id);
    Page<ArticleEntity> findAllByVisibilityTrue(Pageable pageable);
    Optional<ArticleEntity> findByTitleAndVisibilityTrue(String title);
    List<ArticleEntity> findAllByCategoryIdAndVisibilityTrue(UUID categoryId);
    List<ArticleEntity> findAllByTagsAndVisibilityTrue(String tags);
    List<ArticleEntity> findAllByTypeAndVisibilityTrue(ArticleType type);

}
