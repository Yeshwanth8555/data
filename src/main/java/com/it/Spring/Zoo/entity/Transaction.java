package com.it.Spring.Zoo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "transaction")
public class Transaction {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String username;
    private String name;
    private String email;
    private Long phone;
    private String password;


}