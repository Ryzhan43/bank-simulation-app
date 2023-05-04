package com.mryzhan.model;

import com.mryzhan.enums.AccountStatus;
import com.mryzhan.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
public class Account {

    private UUID id;
    private BigDecimal balance;
    private AccountType accountType;
    private AccountStatus accountStatus;

    private Date creationDate;
    private Long userId;

}
