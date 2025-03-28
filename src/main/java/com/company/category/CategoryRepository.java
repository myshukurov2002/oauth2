package com.company.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByIdAndVisibilityTrue(UUID id);
    Page<CategoryEntity> findAllByVisibilityTrue(Pageable pageable);
    Optional<CategoryEntity> findByNameAndVisibilityTrue(String name);
}
