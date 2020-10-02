package com.zovlanik.crud.controller;

import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;

public class AccountController {
    private AccountRepository accountRepository= new JavaIOAccountRepositoryImpl();
}
