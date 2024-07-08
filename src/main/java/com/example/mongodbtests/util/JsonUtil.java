package com.example.mongodbtests.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;

@UtilityClass
public class JsonUtil {




    public ObjectMapper getObjectMapper(){
        return JsonMapper
                .builder()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false)
                .configure(SerializationFeature.WRITE_SELF_REFERENCES_AS_NULL,false)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)

                .build()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }
}
