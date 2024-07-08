package com.example.mongodbtests.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
class MongoConfig {


    /* MONGO DB */
    @Value("${mongodb.uri}")
    String uri;
    @Value("${mongodb.database}")
    String db;
    @Bean
    MongoClient mongoClient() {
//        return MongoClients.create("mongodb://root:secret@localhost:27018");
        return MongoClients.create(uri);
    }
    @Bean
    MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, db);
    }
//    @Bean
//    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }

    /* POSTGRES DB */


}
