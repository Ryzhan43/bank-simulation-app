package com.mryzhan.repository;

import com.mryzhan.model.Transaction;
import com.mryzhan.service.TransactionService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }


    public List<Transaction> findAll() {
        return transactionList;
    }
}
