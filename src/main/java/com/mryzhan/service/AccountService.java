package com.mryzhan.service;

import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationaDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

    void deleteAccount(UUID accountId);

    void activateAccount(UUID accountId);

    Account findById(UUID sender);

}
