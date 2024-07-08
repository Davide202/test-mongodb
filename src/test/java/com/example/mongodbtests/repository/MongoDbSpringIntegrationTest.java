package com.example.mongodbtests.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataMongoTest
@Profile("integration")
@ExtendWith(SpringExtension.class)
@Log4j2
public class MongoDbSpringIntegrationTest
        extends ManualEmbeddedMongoDbIntegrationTest
{
    @DisplayName("insert and get all")
    @Test
    public void test() {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        Assertions.assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");

        List<DBObject> list = mongoTemplate.findAll(DBObject.class, "collection");
        log.info("RESULT {}",mongoTemplate.findAll(DBObject.class, "collection"));
    }
}
