package com.mryzhan.model;

import com.mryzhan.enums.AccountStatus;
import com.mryzhan.enums.AccountType;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
public class Account {

    private UUID id;
    @NotNull
    @Positive
    private BigDecimal balance;
    @NotNull
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Date creationDate;
    @NotNull
    private Long userId;

}
