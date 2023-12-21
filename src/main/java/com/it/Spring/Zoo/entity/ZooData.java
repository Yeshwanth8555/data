package com.it.Spring.Zoo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "zoodata")

public class ZooData {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String type;
    private String dietType;

    public ZooData() {
    }

    public ZooData(String name, String type, String dietType) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    @Override
    public String toString() {
        return "ZooController{" +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dietType='" + dietType + '\'' +
                '}';
    }
}
