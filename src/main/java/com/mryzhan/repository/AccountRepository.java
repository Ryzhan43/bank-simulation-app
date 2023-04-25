package com.mryzhan.repository;

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
        Optional<Account> account = accountsList.stream()
                .filter(a->a.getId() == id)
                .findFirst();
        return (account == null ? null : account.get());
    }
}
