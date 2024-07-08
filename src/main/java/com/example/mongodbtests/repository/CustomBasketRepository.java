package com.example.mongodbtests.repository;

import com.example.mongodbtests.model.Basket;

public interface CustomBasketRepository {
    Basket findByBasketId(String idBasket);
}
