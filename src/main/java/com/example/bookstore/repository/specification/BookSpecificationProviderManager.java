package com.example.bookstore.repository.specification;

import com.example.bookstore.exception.SpecificationNotFoundException;
import com.example.bookstore.model.Book;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new SpecificationNotFoundException(
                        "Can't find  specification provider with key" + key));
    }
}
