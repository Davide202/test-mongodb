package com.example.mongodbtests;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import java.util.Properties;
import java.util.UUID;

@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
@EnableMongoRepositories
public class MongodbTestsApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MongodbTestsApplication.class, args);
		ThreadContext.put("cid", UUID.randomUUID().toString());
		SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(MongodbTestsApplication.class);
		appBuilder.build(args);
		Properties properties = new Properties();
		properties.put("key","value");

		appBuilder.properties(properties);
		appBuilder.run();
	}

}
