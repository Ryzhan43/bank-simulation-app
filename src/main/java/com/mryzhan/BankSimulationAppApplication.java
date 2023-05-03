package com.mryzhan;

import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;
import com.mryzhan.service.AccountService;
import com.mryzhan.service.TransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {

        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);

        //
        Account sender = accountService.createNewAccount(BigDecimal.valueOf(78), new Date(), AccountType.CHECKING, 1L);
        Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50), new Date(), AccountType.SAVING, 1L);


        accountService.listAllAccount().forEach(System.out::println);

        transactionService.makeTransaction(sender, receiver, new BigDecimal(40), new Date(), "Transaction 1");

        System.out.println(transactionService.findAllTransaction().get(0));
        accountService.listAllAccount().forEach(System.out::println);
    }

}
