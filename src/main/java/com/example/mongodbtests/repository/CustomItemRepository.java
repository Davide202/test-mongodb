package com.example.mongodbtests.repository;

import com.example.mongodbtests.model.GroceryItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomItemRepository {

    void updateItemQuantity(String name, float newQuantity);

    List<GroceryItem> findItemByName(String name) throws JsonProcessingException;

    List<GroceryItem> findByBasketId(String idBasket);
}
