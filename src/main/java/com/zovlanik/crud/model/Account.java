package com.zovlanik.crud.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String data;
    @Enumerated (EnumType.ORDINAL) //EnumType.ORDINAL означает, что перечисление будет передано в БД как Integer
//    @Type(type = "com/zovlanik/crud/model/AccountStatus")
    //OR
//    @Column(name="id_account_status")
    private AccountStatus accountStatus;

    public Account(Long id, String data, AccountStatus accountStatus) {
        this.id = id;
        this.data = data;
        this.accountStatus = accountStatus;
    }

    public Account() {

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

