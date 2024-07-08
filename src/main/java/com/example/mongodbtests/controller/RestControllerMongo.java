package com.example.mongodbtests.controller;


import com.example.mongodbtests.model.BasketDto;
import com.example.mongodbtests.model.GroceryItem;
import com.example.mongodbtests.service.BasketService;
import com.example.mongodbtests.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class RestControllerMongo {

    @Autowired
    ItemService itemService;
    @Autowired
    BasketService basketService;

    /*
    * http://localhost:8080/all
    * */
    @GetMapping("/all")
    public ResponseEntity<?> firstController(
        @RequestParam(value = "name",defaultValue = "default")String name
    ){
        ThreadContext.put("cid","***ALL***");
        List<GroceryItem> all;
        if ("default".equalsIgnoreCase(name)){
            all = itemService.findAll();
        }else{
            all = itemService.getGroceryItemByName(name);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /* POST http://localhost:8080/createbasket
{
	"items": [
		{
			"id": "Whole Wheat Biscuit",
			"basketId": "null",
			"name": "Whole Wheat Biscuit",
			"quantity": 5,
			"category": "snacks",
			"ingredients": [
				"milk"
			]
		}
	]
}
     */
    @PostMapping("/createbasket")
    public ResponseEntity<?> createBasket(@RequestBody BasketDto basketDto){
        String idBasket = basketService.save(basketDto);
//        BasketDto basketDto1 =  basketService.getBasketById( idBasket);
        return new ResponseEntity<>(itemService.getBasketById(idBasket),HttpStatus.OK);
//        return new ResponseEntity<>(idBasket,HttpStatus.OK);

    }

    @GetMapping("/getbasket")
    public ResponseEntity<?> getBasketWithItems(
            @RequestParam(value = "idBasket")String idBasket
    ){
        return new ResponseEntity<>(basketService.getBasketById(idBasket),HttpStatus.OK);
    }
}
