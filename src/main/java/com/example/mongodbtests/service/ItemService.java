package com.example.mongodbtests.service;

import com.example.mongodbtests.model.BasketDto;
import com.example.mongodbtests.model.GroceryItem;
import com.example.mongodbtests.repository.BasketRepository;
import com.example.mongodbtests.repository.ItemRepository;
import com.example.mongodbtests.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class ItemService {


    @Autowired
    ItemRepository groceryItemRepo;
    @Autowired
    BasketRepository basketRepository;


    //CREATE
    @PostConstruct
    void createGroceryItems() throws JsonProcessingException {
        log.info("Data creation started...");
        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit","null", "Whole Wheat Biscuit", 5, "snacks",Arrays.asList("milk")));
        groceryItemRepo.save(new GroceryItem("Kodo Millet","null", "XYZ Kodo Millet healthy", 2, "millets",Arrays.asList("chocolate")));
        groceryItemRepo.save(new GroceryItem("Dried Red Chilli","null", "Dried Whole Red Chilli", 2, "spices",Arrays.asList("caffe")));
        groceryItemRepo.save(new GroceryItem("Pearl Millet","null", "Healthy Pearl Millet", 1, "millets",Arrays.asList("pasta")));
        groceryItemRepo.save(new GroceryItem("Cheese Crackers","null", "Bonny Cheese Crackers Plain", 6, "snacks",Arrays.asList("merd")));
//        log.debug(JsonUtil.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(new GroceryItem("Whole Wheat Biscuit","null", "Whole Wheat Biscuit", 5, "snacks",Arrays.asList("milk"))));
        log.info("Data creation complete...");
    }

    // 1. Show all the data
    @Transactional
    public List<GroceryItem> findAll(){
        log.info("Called method findAll()");
        return groceryItemRepo.findAll();
    }
    // 2. Get item by name
    @Transactional
    @SneakyThrows
    public List<GroceryItem> getGroceryItemByName(String name)  {
        log.info("Getting item by name: " + name);
        return groceryItemRepo.findItemByName(name);
    }

    // 3. Get name and quantity of a all items of a particular category
    public List<GroceryItem> getItemsByCategory(String category) {
        log.info("Getting items for the category " + category);
        return groceryItemRepo.findAll(category);
    }

    // 4. Get count of documents in the collection
    public long findCountOfGroceryItems() {
        return groceryItemRepo.count();
    }


    public BasketDto getBasketById(String idBasket) {
        BasketDto basketDto = new BasketDto();
        if (basketRepository.findById(idBasket).isPresent()){
            List<GroceryItem> items = groceryItemRepo.findByBasketId(idBasket);
            basketDto.setItems(items);
        }

        return basketDto;
    }
}
