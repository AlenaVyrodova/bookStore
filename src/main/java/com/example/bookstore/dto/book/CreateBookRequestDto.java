package com.example.bookstore.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;


public record CreateBookRequestDto (
    @NotNull
    String title,
    @NotNull
     String author,
    @NotNull
   String isbn,
    @NotNull
    @Min(0)
     BigDecimal price,

   String description,

    String coverImage,

   Set<Long> categoryIdSet)
    {
}
