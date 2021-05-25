package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;



@Setter
@Getter
@Document(indexName = "esapplication")
public class ESApplication {
    @Id
    private String id;
    @Field(type = FieldType.Text, fielddata = true,name = "sqlid")
    private String sqlId;
    @Field(type = FieldType.Text, fielddata = true)
    private String url;
    @Field(type = FieldType.Text, fielddata = true)
    private String brand;
    @Field(type = FieldType.Text, fielddata = true)
    private String productName;
}
