package com.mryzhan.service;

import com.mryzhan.model.Account;
import com.mryzhan.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionService {

        Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, LocalDate creationDate, String message);

        List<Transaction> findAllTransaction();

        List<Transaction> last10Transactions();
}
