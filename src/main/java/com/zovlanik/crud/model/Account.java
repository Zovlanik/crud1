package com.zovlanik.crud.model;

public class Account {
    Long id;
    String data;
    AccountStatus accountStatus;

    public Account(Long id, String data, AccountStatus accountStatus) {
        this.id = id;
        this.data = data;
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

}

