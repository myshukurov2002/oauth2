package com.company.category;

import com.company.category.dtos.CategoryCr;
import com.company.category.dtos.CategoryResp;
import com.company.user.UserMapper;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    public CategoryResp create(CategoryCr cr){
        CategoryEntity entity = categoryMapper.toEntity(cr);
        entity.setName(cr.name());
        return categoryMapper.toResponse(categoryRepository.save(entity));
    }

    public CategoryResp update(UUID uuid, CategoryCr cr){
        CategoryEntity entity = categoryRepository.findByIdAndVisibilityTrue(uuid)
                .orElseThrow(() -> new RuntimeException("Category not found!!!"));

        entity.setName(cr.name());
        return categoryMapper.toResponse(categoryRepository.save(entity));
    }

    public CategoryResp getById(UUID id){
        return categoryMapper.toResponse(categoryRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("Category not found!!!")));

    }

    public CategoryResp getByName(String name){
        return categoryMapper.toResponse(categoryRepository.findByNameAndVisibilityTrue(name)
                .orElseThrow(() -> new RuntimeException("Category not found!!!")));
    }

    public Page<CategoryResp> getAll(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> all = categoryRepository.findAllByVisibilityTrue(pageable);

        List<CategoryResp> list = all
                .stream()
                .map(categoryMapper::toResponse)
                .toList();

        return new PageImpl<>(list, pageable, list.size());

    }


    public void deleteById(UUID id){
        CategoryEntity entity = categoryRepository.findByIdAndVisibilityTrue(id)
                .orElseThrow(() -> new RuntimeException("Category not found!!!"));

        entity.setVisibility(false);
        categoryMapper.toResponse(categoryRepository.save(entity));
    }





    




}
