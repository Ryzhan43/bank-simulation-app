package com.mryzhan.repository;

import com.mryzhan.enums.AccountStatus;
import com.mryzhan.enums.AccountType;
import com.mryzhan.exception.RecordNotFoundException;
import com.mryzhan.model.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Repository
public class AccountRepository {

    public static List<Account> accountsList = new ArrayList<>();

    static Account account1=Account.builder()
            .accountType(AccountType.CHECKING)
            .accountStatus(AccountStatus.ACTIVE)
            .creationDate(new Date())
            .id(UUID.randomUUID())
            .balance(BigDecimal.valueOf(123))
            .userId(123L)
            .build();
    static Account account2=Account.builder()
            .accountType(AccountType.CHECKING)
            .accountStatus(AccountStatus.ACTIVE)
            .creationDate(new Date())
            .id(UUID.randomUUID())
            .balance(BigDecimal.valueOf(123223))
            .userId(122323L)
            .build();

    static{
        accountsList.add(account1);
        accountsList.add(account2);
    }


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
