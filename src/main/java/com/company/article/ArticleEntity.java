package com.company.article;

import com.company.category.CategoryEntity;
import com.company.component.BaseEntity;
import com.company.photo.PhotoEntity;
import com.company.user.UserEntity;
import com.company.user.enums.ArticleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",
            nullable = false,
            insertable = false,
            updatable = false)
    private CategoryEntity category;

    @Column(name = "category_id")
    private UUID categoryId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, insertable=false, updatable=false)
    private UserEntity author;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long viewsCount;

    @Enumerated(EnumType.STRING)
    private ArticleType type;

    @Column
    private String tags;

    public Set<String> getTags() {
        return Collections.singleton(tags);
    }

    public void setTags(Set<String> tags) {
        this.tags = String.valueOf(tags);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id",
    insertable = false, updatable = false)
    private PhotoEntity photo;

    @Column(name = "photo_id", nullable = false)
    private UUID photoId;

    private long likes;
    private long dislikes;


}