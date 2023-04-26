package com.mryzhan.repository;

import com.mryzhan.exception.RecordNotFoundException;
import com.mryzhan.model.Account;

import java.util.*;

public class AccountRepository {

    public static List<Account> accountsList = new ArrayList<>();


    public Account save(Account account){
        accountsList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountsList;
    }

    public Account findById(UUID id){
        return accountsList.stream()
                .filter(a->a.getId().equals(id))
                .findAny().orElseThrow(() -> new RecordNotFoundException("Account not exist in the database"));
    }
}
