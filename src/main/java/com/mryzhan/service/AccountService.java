package com.mryzhan.service;

import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationaDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();
}
