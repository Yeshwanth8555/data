package com.it.Spring.Zoo.service;

import com.it.Spring.Zoo.entity.ZooData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.it.Spring.Zoo.repository.ZooDataRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ZooDataService {

    @Autowired
    ZooDataRepository zooDataRepository;

    public String addZooData(ZooData zooData){
        if(checkingDietType(zooData.getDietType())){

        }
        else {
            return "Entered wrong diet type please enter the correct diet type.";
        }
        if(checkingType(zooData.getType())){

        }
        else {
            return "Entered wrong type please enter the correct type.";
        }

        if (zooDataRepository.existsByName(zooData.getName()) && zooDataRepository.existsByDietType(zooData.getDietType()) &&
        zooDataRepository.existsByType(zooData.getType())) {
            return "name/type/dietType already exist type another name/type/dietType";
        }

        zooDataRepository.save(zooData);

        return "Data added into the data base successfully";


    }
    public boolean checkingDietType(String dietType){
        return Stream.of("carnivore","herbivore","omnivore")
                .anyMatch(x->x.equalsIgnoreCase(dietType));
    }
    public boolean checkingType(String type) {
        return Stream.of("animal", "bird", "human")
                .anyMatch(t -> t.equalsIgnoreCase(type));
    }

    public  ZooData getData(String name){

        return zooDataRepository.findByName(name);
    }

    public List<ZooData> getListByDietType(String dietType){

        return zooDataRepository.findByDietType(dietType);
    }

    public ZooData updateDataByName(ZooData zooData){

        ZooData existingZooData = zooDataRepository.findById(zooData.getId()).get();
        existingZooData.setId(zooData.getId());
        existingZooData.setName(zooData.getName());
        existingZooData.setType(zooData.getType());
        existingZooData.setDietType(zooData.getDietType());
        ZooData zooData1=zooDataRepository.save(existingZooData);
        return zooData1;
     }
     public List<ZooData> zooDataListlist(){
        return zooDataRepository.findAll();
     }

     public ZooData deleteByName(String name){
        ZooData deletingData=zooDataRepository.findByName(name);
         zooDataRepository.delete(deletingData);
        return deletingData;
     }

}
