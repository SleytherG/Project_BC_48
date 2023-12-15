package com.nttdata.emeal.msvc.product.service;

import com.nttdata.emeal.msvc.product.model.Client;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface ClientService {


  Maybe<Client> retrieveAClient(String clientId);

}
