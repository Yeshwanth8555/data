package com.it.Spring.Zoo.service;

import com.it.Spring.Zoo.entity.ZooData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.it.Spring.Zoo.repository.ZooDataRepository;

import java.util.*;

@Service
public class ZooDataService {

    @Autowired
    ZooDataRepository zooDataRepository;

    public String addZooData(ZooData zooData) {

        if (zooDataRepository.existsByName(zooData.getName())) {
            return "Name already exists. Please choose another name.";
        }

        zooDataRepository.save(zooData);

        return "Data added to the database successfully";
    }


    public List<ZooData> getListByDietType(String dietType){

        return zooDataRepository.findByDietType(dietType);
    }

    public void updateDataByName(ZooData zooData){

        ZooData existingZooData = zooDataRepository.findById(zooData.getId()).get();
        existingZooData.setId(zooData.getId());
        existingZooData.setName(zooData.getName());
        existingZooData.setType(zooData.getType());
        existingZooData.setDietType(zooData.getDietType());
        ZooData zooData1=zooDataRepository.save(zooData);
    }
     public List<ZooData> zooDataList(){
        return zooDataRepository.findAll();
     }

     public void deleteByName(String name){
        ZooData deletingData=zooDataRepository.findByName(name);
         zooDataRepository.delete(deletingData);
     }

}
