package com.nttdata.emeal.msvc.yanki.repository;

import com.nttdata.emeal.msvc.yanki.model.Yanki;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface YankiRepository extends RxJava3CrudRepository<Yanki, String> {

}
