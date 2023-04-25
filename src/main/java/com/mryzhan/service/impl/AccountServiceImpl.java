package com.mryzhan.service.impl;

import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;
import com.mryzhan.repository.AccountRepository;
import com.mryzhan.service.AccountService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    @Override
    public Account createNewAccount(BigDecimal balance, Date creationaDate, AccountType accountType, Long userId) {

        Account account = Account.builder().id(UUID.randomUUID()).
                userId(userId).balance(balance).accountType(accountType).
                creationDate(creationaDate).build();


        return accountRepository.save(account);

    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }
}
