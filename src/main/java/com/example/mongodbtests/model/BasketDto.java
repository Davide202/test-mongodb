package com.example.mongodbtests.model;

import lombok.Data;
import java.util.List;

@Data
public class BasketDto {

    List<GroceryItem> items;

}
