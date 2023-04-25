package com.mryzhan.repository;

import com.mryzhan.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    public static List<Account> accountsList = new ArrayList<>();


    public Account save(Account account){
        accountsList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountsList;
    }
}
