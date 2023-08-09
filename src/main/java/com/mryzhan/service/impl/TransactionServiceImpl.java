package com.mryzhan.service.impl;

import com.mryzhan.enums.AccountType;
import com.mryzhan.exception.AccountOwnershipException;
import com.mryzhan.exception.BadRequestException;
import com.mryzhan.exception.BalanceNotSufficientException;
import com.mryzhan.exception.UnderConstructionException;
import com.mryzhan.model.Account;
import com.mryzhan.model.Transaction;
import com.mryzhan.repository.AccountRepository;
import com.mryzhan.repository.TransactionRepository;
import com.mryzhan.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
            this.accountRepository = accountRepository;
            this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransaction(Account sender, Account receiver, BigDecimal amount, LocalDate creationDate, String message) {

        if(!underConstruction) {

            validateAccount(sender,receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount,sender,receiver);
        } else {
            throw new UnderConstructionException("App is under construction, try again");
        }

        /*  TASK
            after all validations are completed, and money is transferred, Transaction class should be created
        */

        Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId())
                .receiver(receiver.getId()).creationDate(creationDate).message(message).build();

        return transactionRepository.save(transaction);
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(checkSenderBalance(sender, amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }
        else {
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }
    }

    private  boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {

        if ((sender.getAccountType().equals(AccountType.SAVING)||receiver.getAccountType().equals(AccountType.SAVING))
                && !sender.getUserId().equals(receiver.getUserId())) {
            throw new AccountOwnershipException("Since you are using a saving account, the sender and receiver userId must not be same");
        }
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
              -if any of the accounts is null
              -if account ids are the same
         */
        if(sender==null||receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }

        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }
}
