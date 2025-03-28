
package com.company.article;

import com.company.component.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "likes")
@Getter
@Setter
public class LikeEntity extends BaseEntity {

    private UUID userId;
    private UUID articleId;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        LIKE, DISLIKE, NEUTRAL
    }

}
