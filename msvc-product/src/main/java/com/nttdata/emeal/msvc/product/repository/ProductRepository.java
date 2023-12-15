package com.nttdata.emeal.msvc.product.repository;

import com.nttdata.emeal.msvc.product.model.BankAccount;
import com.nttdata.emeal.msvc.product.model.BankCredit;
import com.nttdata.emeal.msvc.product.model.Product;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends RxJava3CrudRepository<Product, String> {

  Flowable<Product> getProductsByIdClient(String idClient);

  Maybe<Product> findProductById(String productId);


}
