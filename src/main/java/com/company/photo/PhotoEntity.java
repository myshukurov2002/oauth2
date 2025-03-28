package com.company.photo;

import com.company.article.ArticleEntity;
import com.company.component.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "photos")
public class PhotoEntity extends BaseEntity {


    @OneToOne(mappedBy = "photo")
    private ArticleEntity articleEntity;

    @Column
    private String fileName;

    private byte[] content;//bytea
}
