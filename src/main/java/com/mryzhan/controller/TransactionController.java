package com.mryzhan.controller;

import com.mryzhan.model.Transaction;
import com.mryzhan.service.AccountService;
import com.mryzhan.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Controller
public class TransactionController {

    private AccountService accountService;
    private TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model){
        model.addAttribute("transaction", Transaction.builder().build());
        model.addAttribute("accounts", accountService.listAllAccount());
        model.addAttribute("transactions", transactionService.findAllTransaction());


        return "transaction/make-transfer";
    }

    @PostMapping("/make-transfer")
    public String postMakeTransfer(@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors())
        {
            model.addAttribute("accouint", accountService.listAllAccount());
            return "transaction/make-transfer";
        }

        System.out.println("transaction: "+ transaction.toString());
        transactionService.makeTransaction(
                accountService.findById(transaction.getSender()),
                accountService.findById(transaction.getReceiver()),
                transaction.getAmount(),
                LocalDate.now(),
                transaction.getMessage()
        );
        transactionService.findAllTransaction()
                .stream()
                .forEach(System.out::println);

        return "redirect:/make-transfer";
    }


}
