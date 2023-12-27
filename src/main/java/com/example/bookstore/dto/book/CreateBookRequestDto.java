package com.example.bookstore.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public record CreateBookRequestDto(
        @NotNull
        String title,
        @NotNull
        String author,
        @NotNull
        String isbn,
        @NotNull
        @Min(0)
        BigDecimal price,
        @NotNull
        String description,
        @NotNull
        String coverImage,
        @NotEmpty
        Set<Long> categoryIdSet) {
}

