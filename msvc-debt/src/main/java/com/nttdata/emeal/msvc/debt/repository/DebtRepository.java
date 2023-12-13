package com.nttdata.emeal.msvc.debt.repository;

import com.nttdata.emeal.msvc.debt.model.Debt;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface DebtRepository extends RxJava3CrudRepository<Debt, String> {
}
