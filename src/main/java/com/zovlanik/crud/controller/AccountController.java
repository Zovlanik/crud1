package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;

import java.util.List;

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

    public void showAll(){
        List<Account> accounts = accountRepository.getAll();
        for(Account acc : accounts){

            System.out.println(acc.getId() + "," + acc.getData() + "," + acc.getAccountStatus());
        }
    }

    public boolean deleteById(Long accountId){
        int tempSizeBefore = accountRepository.getAll().size();
        accountRepository.deleteById(accountId);
        int tempSizeAfter = accountRepository.getAll().size();
        return tempSizeAfter < tempSizeBefore;
    }

    public void update(Account newAccount){
        accountRepository.update(newAccount);
    }
}
