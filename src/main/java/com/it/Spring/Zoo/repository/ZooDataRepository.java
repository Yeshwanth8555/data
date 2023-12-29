package com.it.Spring.Zoo.repository;

import com.it.Spring.Zoo.entity.ZooData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZooDataRepository  extends MongoRepository<ZooData ,String> {

    boolean existsByName(String name);

    ZooData findByName(String name);

    List<ZooData> findByDietType(String dietType);

    List<ZooData> findAllByNameIn(List<String> dataToDelete);
}
