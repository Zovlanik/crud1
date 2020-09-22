package main.java.com.zovlanik.crud;

public class Account {
    long id;
    String data;
    AccountStatus accountStatus;

    Account (long id, String data, AccountStatus accountStatus){
        this.id = id;
        this.data = data;
        this.accountStatus = accountStatus;

    }
    public String toString(){

        return id + "," + data + "," + accountStatus;
    }
}

enum AccountStatus{
    ACTIVE, BANNED, DELETED
}