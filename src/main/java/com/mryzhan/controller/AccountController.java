package com.mryzhan.controller;

import com.mryzhan.enums.AccountStatus;
import com.mryzhan.enums.AccountType;
import com.mryzhan.model.Account;
import com.mryzhan.repository.AccountRepository;
import com.mryzhan.repository.TransactionRepository;
import com.mryzhan.service.AccountService;
import com.mryzhan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IModel;

import java.util.Date;
import java.util.UUID;

@Controller

public class AccountController {
    AccountService accountService;
    TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/index1")
    public String showTransactionPage(Model model){
        model.addAttribute("transactions", transactionService.findAllTransaction());

        return "transaction/transactions";
    }
    @GetMapping("/index")
    public String showIndexPage(Model model){
        model.addAttribute("transactions", transactionService.findAllTransaction());

        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";
    }

    @GetMapping("/delete/{accountId}")
    public String deleteAccount(@PathVariable("accountId") UUID accountId){
        accountService.deleteAccount(accountId);
        return "redirect:/index";
    }


    @GetMapping("/activate/{accountId}")
    public String activateAccount(@PathVariable("accountId") UUID accountId){
        accountService.activateAccount(accountId);
        return "redirect:/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model){

        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account)
    {
        System.out.println(account);
        accountService.createNewAccount(account.getBalance(),new Date(), account.getAccountType(), account.getUserId());
        return "redirect:/index";
    }
}


