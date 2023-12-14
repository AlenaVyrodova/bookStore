package com.example.bookstore.controller;

import com.example.bookstore.dto.category.CategoryDto;
import com.example.bookstore.dto.category.CreateCategoryRequestDto;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new category", description = "Create a new category")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto categoryRequestDtoDto)
    {
        return categoryService.save(categoryRequestDtoDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get all categories", description = "Get a list of all available categories")
    public List<CategoryDto> getAll(Pageable pageable){
        return  categoryService.findAll(pageable);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    @Operation(summary = "get category by id", description = "get the specified category by id ")
    public CategoryDto getCategoryById(@PathVariable Long id){
        return  categoryService.getById(id);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "update category by id", description = "update category by id")
    public CategoryDto updateCategory(@PathVariable Long id,@RequestBody @Valid CreateCategoryRequestDto categoryRequestDto){
        return categoryService.update(id,categoryRequestDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete category by id", description = "Delete category  by id")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/books")
    @Operation(summary = "Get book by category id", description = "Get book by category id")
    public List getBooksByCategoryId(@PathVariable Long id,Pageable pageable){
        return  bookService.findBookByCategoryId(id,pageable);

    }
}
