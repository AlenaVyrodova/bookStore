package com.example.bookstore.repository.specification;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
