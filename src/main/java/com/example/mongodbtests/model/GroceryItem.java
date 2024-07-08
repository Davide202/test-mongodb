package com.example.mongodbtests.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("groceryitems")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@FieldNameConstants//(asEnum = true)
@EqualsAndHashCode(callSuper=false)
public class GroceryItem  {

    @Id
    @NonNull
//    @JsonIgnore
    @JsonProperty("id")
    String _id;
    @NonNull
    String basketId;
    List<Basket> basket;
    @NonNull
    String name;
    @NonNull
    int quantity;
    @NonNull
    String category;
    @NonNull
    List<String> ingredients;



}
