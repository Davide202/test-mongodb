package com.example.mongodbtests.repository;

import com.example.mongodbtests.model.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket,String>,CustomBasketRepository {
}
