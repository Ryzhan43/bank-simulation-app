package com.mryzhan.service.impl;

import com.mryzhan.exception.BadRequestException;
import com.mryzhan.model.Account;
import com.mryzhan.model.Transaction;
import com.mryzhan.repository.AccountRepository;
import com.mryzhan.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        /*
            - if sender or receiver is null
            - if sender has enough balance ?
            - if both account are checking, if not, one of them saving, it needs to be same userId
            -if both accounts are checking, if mot, one of them saving, it needs to be the same userId
         */

        validateAccount(sender,receiver);

        return null;
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
              -if any of the accounts is null
              -if account ids are the same
         */

        if(sender==null||receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
