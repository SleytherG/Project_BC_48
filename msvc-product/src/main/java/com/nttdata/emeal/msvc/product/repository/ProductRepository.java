package com.nttdata.emeal.msvc.product.repository;

import com.nttdata.emeal.msvc.product.model.Product;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface ProductRepository extends RxJava3CrudRepository<Product, String> {
}
