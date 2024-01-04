package com.it.Spring.Zoo.repository;

import com.it.Spring.Zoo.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
    Optional<Transaction> findByUsernameAndPassword(String username, String password);

}

