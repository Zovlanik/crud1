package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;

public class AccountController {
    private final AccountRepository accountRepository= new JavaIOAccountRepositoryImpl();

    public boolean create(String accountName){

        if (accountName.length() > 2) {
            accountRepository.create(new Account(1L, accountName, AccountStatus.ACTIVE));
            return true;
        }
        return false;
    }

    public Account getById(Long id){
        return accountRepository.getById(id);
    }
}
