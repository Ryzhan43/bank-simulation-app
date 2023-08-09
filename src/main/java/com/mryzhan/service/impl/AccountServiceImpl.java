package com.mryzhan.service.impl;

import com.mryzhan.enums.AccountStatus;
import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;
import com.mryzhan.repository.AccountRepository;
import com.mryzhan.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationaDate, AccountType accountType, Long userId) {

        Account account = Account.builder().id(UUID.randomUUID()).
                userId(userId).balance(balance).accountType(accountType).accountStatus(AccountStatus.ACTIVE).
                creationDate(creationaDate).build();

        return accountRepository.save(account);

    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID accountId) {
        accountRepository.findById(accountId).setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public void activateAccount(UUID accountId) {
        accountRepository.findById(accountId).setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id);
    }


}
