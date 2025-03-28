package com.company.article.views;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ViewRepository extends JpaRepository<ViewEntity, UUID> {
    Optional<ViewEntity> findByUserIdAndArticleId(UUID userId, UUID articleId);
}
