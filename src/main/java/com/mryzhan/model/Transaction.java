package com.mryzhan.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {

    @NotNull
    private UUID sender;
    @NotNull
    private UUID receiver;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    @Size(min = 2, max = 250)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String message;
    @NotNull
    private LocalDate creationDate;

}
