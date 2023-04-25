package com.mryzhan.service;

import com.mryzhan.model.Account;
import com.mryzhan.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

        Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message);

        List<Transaction> findAllTransaction();
}
