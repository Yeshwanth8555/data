package com.it.Spring.Zoo.repository;


import com.it.Spring.Zoo.entity.DeletedZooData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedZooDataRepository extends MongoRepository<DeletedZooData, String> {

}
