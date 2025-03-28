package com.company.article.filter;

import com.company.article.ArticleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ArticleFilterRepository {

    private final EntityManager entityManager;

    public Page<ArticleEntity> filter(ArticleFilterDto filter, int page, int size) {

        StringBuilder selectQueryBuilder = new StringBuilder("SELECT s FROM articles s ");
        StringBuilder countQueryBuilder = new StringBuilder("SELECT count(s) FROM articles s ");

        StringBuilder builder = new StringBuilder(" where s.visibility = true ");

        Map<String, Object> params = new HashMap<>();


//        if (filter.getId() != null) { // condition by id
//            builder.append(" and s.id =:id ");
//            params.put("id", filter.getId());
//        }
        if (filter.getCategoryId() != null) { // condition by surname with like
            builder.append(" and LOWER(s.category_id) like : category_id");
            params.put("category_id", "%" + filter.getCategoryId().toString().toLowerCase() + "%");
        }
        if (filter.getTitle() != null) { // condition by surname with like
            builder.append(" and LOWER(s.title) like : title");
            params.put("title", "%" + filter.getTitle().toLowerCase() + "%");
        }
        if (filter.getContent() != null) { // condition by surname with like
            builder.append(" and LOWER(s.content) like : content");
            params.put("content", "%" + filter.getContent().toLowerCase() + "%");
        }
        if (filter.getViewsCount() != null) {
            builder.append(" and s.views_count =: views_count ");
            params.put("views_count", filter.getViewsCount());
        }
        if (filter.getTags() != null) { // condition by surname with like
            builder.append(" and LOWER(s.tags) like : tags");
            params.put("tags", "%" + filter.getTags().toString().toLowerCase() + "%");
        }
        if (filter.getPhotoId() != null) { // condition by surname with like
            builder.append(" and LOWER(s.photo_id) like : photo_id");
            params.put("photo_id", "%" + filter.getPhotoId().toString().toLowerCase() + "%");
        }
        if (filter.getOwnerId() != null) { // condition by surname with like
            builder.append(" and LOWER(s.owner_id) like : owner_id");
            params.put("owner_id", "%" + filter.getOwnerId().toString().toLowerCase() + "%");
        }

        selectQueryBuilder.append(builder);
        countQueryBuilder.append(builder);

        // select query
        Query selectQuery = entityManager.createQuery(selectQueryBuilder.toString());
        selectQuery.setFirstResult((page) * size); // 50
        selectQuery.setMaxResults(size); // 30
        params.forEach(selectQuery::setParameter);

        List<ArticleEntity> articleList = selectQuery.getResultList();

        // totalCount query
        Query countQuery = entityManager.createQuery(countQueryBuilder.toString());
        params.forEach(countQuery::setParameter);

        Long totalElements = (Long) countQuery.getSingleResult();
        return new PageImpl<>(articleList, PageRequest.of(page, size), totalElements);
    }
}
