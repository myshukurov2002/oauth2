package com.company.article.views;

import com.company.component.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;


@Data
@Entity(name = "view")
public class ViewEntity extends BaseEntity {

    private UUID userId;
    private UUID articleId;
}
