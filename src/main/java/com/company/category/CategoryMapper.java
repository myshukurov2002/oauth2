package com.company.category;

import com.company.category.dtos.CategoryCr;
import com.company.category.dtos.CategoryResp;
import com.company.component.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements BaseMapper<CategoryEntity, CategoryCr, CategoryResp> {

    @Override
    public CategoryEntity toEntity(CategoryCr categoryCr) {
        return CategoryEntity.builder()
                .name(categoryCr.name())
                .build();
    }

    @Override
    public CategoryResp toResponse(CategoryEntity categoryEntity) {
        return new CategoryResp(
                categoryEntity.getId(),
                categoryEntity.getName()
        );
    }

}
