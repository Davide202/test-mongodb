package com.example.mongodbtests.model.csv;


import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class NamedColumnBean extends CsvBean {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "age")
    private int age;

}
