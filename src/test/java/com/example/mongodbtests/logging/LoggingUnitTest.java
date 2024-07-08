//package com.example.mongodbtests.logging;
//
//import com.mongodb.client.MongoClients;
//import de.flapdoodle.embed.mongo.config.Net;
//import de.flapdoodle.embed.mongo.distribution.Version;
//import de.flapdoodle.embed.mongo.transitions.MongodStarter;
//import de.flapdoodle.embed.process.runtime.Network;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.TestPropertySource;
//
//@SpringBootTest
//@TestPropertySource(properties = { "logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG" })
//public class LoggingUnitTest {
//
//    private static final String CONNECTION_STRING = "mongodb://%s:%d";
//
//    private MongodExecutable mongodExecutable;
//    private MongoTemplate mongoTemplate;
//
//    @AfterEach
//    void clean() {
//        mongodExecutable.stop();
//    }
//
//    @BeforeEach
//    void setup() throws Exception {
//        String ip = "localhost";
//        int port = 27017;
//
//        ImmutableMongodConfig mongodbConfig = MongodConfig.builder()
//                .version(Version.Main.PRODUCTION)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        mongodExecutable = starter.prepare(mongodbConfig);
//        mongodExecutable.start();
//        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
//    }
//    // tests
//}
