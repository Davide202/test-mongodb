package com.example.mongodbtests.service;

import com.example.mongodbtests.model.Basket;
import com.example.mongodbtests.model.BasketDto;
import com.example.mongodbtests.model.GroceryItem;
import com.example.mongodbtests.repository.BasketRepository;
import com.example.mongodbtests.repository.ItemRepository;
import lombok.Synchronized;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ItemRepository itemRepository;

    public String save(BasketDto basketDto) {
        Basket basket = new Basket(UUID.randomUUID().toString(), Instant.now());
        basketRepository.save(basket);
        for (GroceryItem item : basketDto.getItems()){
            item.set_id(UUID.randomUUID().toString());
            item.setBasketId(basket.get_id());
        }
        itemRepository.saveAll(basketDto.getItems());
        return basket.get_id();

    }


    public Basket getBasketById(String idBasket) {
        BasketDto basketDto = new BasketDto();
//        if (basketRepository.findById(idBasket).isPresent()){
//            List<GroceryItem> items = itemRepository.findByBasketId(idBasket);
//            basketDto.setItems(items);
//        }

        Basket basket = basketRepository.findByBasketId(idBasket);
        basketDto.setItems(basket.getItems());
        return basket;
    }
}
