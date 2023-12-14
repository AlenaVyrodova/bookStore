package com.example.bookstore.mapper;

import com.example.bookstore.config.MapperConfig;
import com.example.bookstore.dto.category.CategoryDto;
import com.example.bookstore.dto.category.CreateCategoryRequestDto;
import com.example.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE ,config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toCategory(CreateCategoryRequestDto categoryRequestDto);
}

