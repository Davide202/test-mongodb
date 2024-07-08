package com.example.mongodbtests.repository;

import com.example.mongodbtests.aop.LogExecutionTime;
import com.example.mongodbtests.model.Basket;
import com.example.mongodbtests.model.GroceryItem;
import com.example.mongodbtests.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Log4j2

public class CustomItemRepositoryImpl implements CustomItemRepository{

    private static final String TABLE_NAME_KEY = "id";
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoClient mongoClient;

    ObjectMapper mapper = JsonUtil.getObjectMapper();


    MongoCollection collection;


    @PostConstruct
    void setting(){
        MongoDatabase db = mongoClient.getDatabase("geospatial");
        collection = db.getCollection("groceryitems");
    }

    @Override
    public void updateItemQuantity(String name, float newQuantity) {
//        mongoTemplate.getCollectionName(GroceryItem.class);


        Query query = new Query(
                Criteria.where("name").is(name)
        );

        Update update = new Update();
        update.set("quantity", newQuantity);

        UpdateResult result = mongoTemplate.updateFirst(query, update, GroceryItem.class);

        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");

    }

    @Override
    public List<GroceryItem> findItemByName(String name) throws JsonProcessingException {

//        playing();

//        Update update = Update.update("name",name);
//        mongoTemplate.update(GroceryItem.class).apply(update).all();

        Query query = new Query(
                Criteria.where("name")
                        .regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE))
        );
//        oppure si può aggiungere
//        query.addCriteria(Criteria.where("name")
//                .regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE)));
//        Per solo alcuni campi
//        query.fields().include("name","quantity","category");
//        query.fields().include("name","quantity");


        String quantity = "quantity";
        String ingredients = "ingredients";

        Update update;
        long modified;
        update = new Update().inc(quantity,2);//increment value
        update = new Update().set(quantity,2);//setValue


        String[] added = new String[]{"sugar","lemon"};

        update = new Update().push(ingredients).each(added);//metto lo zucchero
        modified = mongoTemplate.update(GroceryItem.class)
                        .matching(query)
                        .apply(update)
                        .all().getModifiedCount();
        update = new Update().push(ingredients,"fruits");//metto lo zucchero
        modified = mongoTemplate.update(GroceryItem.class)
                .matching(query)
                .apply(update)
                .all().getModifiedCount();

//        update = new Update().pull(ingredients,"pasta");//tolgo la pasta
//        modified = mongoTemplate.update(GroceryItem.class)
//                .matching(query)
//                .apply(update)
//                .all().getModifiedCount();
//
//        update = new Update().pop(ingredients, Update.Position.FIRST);//tolgo il primo
//        modified = mongoTemplate.update(GroceryItem.class)
//                .matching(query)
//                .apply(update)
//                .all().getModifiedCount();




        log.info("Query {} modified {}",query.toString(),modified);

        return mongoTemplate.find(query, GroceryItem.class,mongoTemplate.getCollectionName(GroceryItem.class));
    }


    void playing() throws JsonProcessingException {
        String json = mapper.writeValueAsString(
                GroceryItem.builder().name("custom").quantity(1).category("custom").build()
        );
        collection.insertOne(Document.parse(json));
//        https://www.baeldung.com/mongodb-find

        Bson filter = Filters.eq("name", "custom");
        Bson projection = Projections.fields(Projections.include("name", "category"));
        FindIterable<Document> documents = collection.find(filter).projection(projection);

        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            Document result = cursor.next();
            collection.deleteOne(result);
            GroceryItem item = new GroceryItem();
            item.setName((String) result.get("name"));
            item.setCategory((String) result.get("category"));

            System.out.println(item.toString());
        }
    }

/*

 */
    @Override
    @LogExecutionTime
    public List<GroceryItem> findByBasketId(String idBasket){

        AggregationOperation match = Aggregation.match(
                Criteria.where(GroceryItem.Fields.basketId)
                        .exists(true)
//                        .is("597ee973-2e10-4df0-a584-279522011bdb")
                        .ne("null")
        );

        AggregationOperation lookup = Aggregation.lookup(
                    mongoTemplate.getCollectionName(Basket.class),
                    GroceryItem.Fields.basketId,
                    Basket.Fields._id,
                    GroceryItem.Fields.basket
                );

        AggregationOperation sort = sort(Sort.by(Sort.Direction.DESC, GroceryItem.Fields.quantity));

        AggregationOperation include = project()
                .andInclude(
                        GroceryItem.Fields._id,
                        GroceryItem.Fields.basketId,
                        GroceryItem.Fields.name,
                        GroceryItem.Fields.quantity,
                        GroceryItem.Fields.category,
                        GroceryItem.Fields.ingredients,
                        GroceryItem.Fields.basket
                );


        Aggregation aggregation = Aggregation.newAggregation(
                match,
                lookup,
                sort,
                include
        );
        AggregationResults<GroceryItem> result = mongoTemplate.aggregate(
                aggregation, mongoTemplate.getCollectionName(GroceryItem.class), GroceryItem.class);

        return result.getMappedResults();
    }
}
