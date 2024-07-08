package com.example.mongodbtests.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

@Document("basket")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@FieldNameConstants
public class Basket {

    @Id
    @JsonProperty("id")
    @NonNull
    String _id;

    List<GroceryItem> items;

    @NonNull
    Instant created;


}
