package com.company.category;

import com.company.article.ArticleEntity;
import com.company.component.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name",
            nullable = false,
            unique = true, length = 25)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ArticleEntity> articles;
}