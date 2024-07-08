package com.example.mongodbtests.repository;

import com.example.mongodbtests.model.Basket;
import com.example.mongodbtests.model.GroceryItem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class BasketRepositoryImpl implements CustomBasketRepository{

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Basket findByBasketId(String idBasket) {

        AggregationOperation match = Aggregation.match(
                Criteria.where(Basket.Fields._id)
                        .is("597ee973-2e10-4df0-a584-279522011bdb")
        );
        AggregationOperation lookup = Aggregation.lookup(
                mongoTemplate.getCollectionName(GroceryItem.class),
                Basket.Fields._id,
                GroceryItem.Fields.basketId,
                Basket.Fields.items
        );
        Aggregation aggregation = Aggregation.newAggregation(
                match,
                lookup
        );
        AggregationResults<Basket> result = mongoTemplate.aggregate(
                aggregation, mongoTemplate.getCollectionName(Basket.class), Basket.class);


        return result.getUniqueMappedResult();
    }
}
