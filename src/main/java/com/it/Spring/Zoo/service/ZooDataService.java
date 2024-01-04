package com.it.Spring.Zoo.service;

import com.it.Spring.Zoo.entity.DeletedZooData;
import com.it.Spring.Zoo.entity.Transaction;
import com.it.Spring.Zoo.entity.ZooData;
import com.it.Spring.Zoo.repository.DeletedZooDataRepository;
import com.it.Spring.Zoo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.it.Spring.Zoo.repository.ZooDataRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZooDataService {

    @Autowired
    ZooDataRepository zooDataRepository;


    @Autowired
    DeletedZooDataRepository deletedZooDataRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public void addData(Transaction transaction) {
        transactionRepository.save(transaction);
    }
    public Optional<Transaction> findByUsernameAndPassword(String username, String password) {
        return transactionRepository.findByUsernameAndPassword(username, password);
    }


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

    public void deleteAndMoveToAnotherCollection(List<String> dataToDeleteNames) {
        try {
            List<ZooData> dataToDelete = zooDataRepository.findAllByNameIn(dataToDeleteNames);

            List<DeletedZooData> deletedDataList = dataToDelete.stream()
                    .map(zooData -> new DeletedZooData(zooData.getId(), zooData.getName(), zooData.getType(), zooData.getDietType()))
                    .collect(Collectors.toList());
            deletedZooDataRepository.saveAll(deletedDataList);

            zooDataRepository.deleteAll(dataToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAllAndMoveToAnotherCollection() {
        try {
            List<ZooData> allDataList = zooDataRepository.findAll();
            List<DeletedZooData> deletedAllDataList = convertToDeletedZooData(allDataList);

            deletedZooDataRepository.saveAll(deletedAllDataList);

            zooDataRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<DeletedZooData> convertToDeletedZooData(List<ZooData> zooDataList) {
        return zooDataList.stream()
                .map(zooData -> new DeletedZooData(zooData.getId(), zooData.getName(), zooData.getType(), zooData.getDietType()))
                .collect(Collectors.toList());
    }
}
