package com.it.Spring.Zoo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deletedZooData")
public class DeletedZooData {
    @Id
    private String id;
    private String name;
    private String type;
    private String dietType;

    public DeletedZooData(String name, String type, String dietType) {
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

    public DeletedZooData(String id, String name, String type, String dietType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dietType = dietType;
    }


    @Override
    public String toString() {
        return "DeletedZooData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dietType='" + dietType + '\'' +
                '}';
    }
}

