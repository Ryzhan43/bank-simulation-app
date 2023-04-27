package com.mryzhan.repository;

import com.mryzhan.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactionList.add(transaction);
        return transaction;
    }
}
