package com.zovlanik.crud.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountTest {
    Account account1 = new Account(1L, "Ivanov",AccountStatus.ACTIVE);
    Account account2 = new Account(2L, "Petrov",AccountStatus.BANNED);
    Account account3 = new Account(3L, "Sidorov",AccountStatus.DELETED);

    @Test
    public void getId() {
        assertThat(account1.getId()).isEqualTo(1L);
        assertThat(account2.getId()).isEqualTo(2L);
        assertThat(account3.getId()).isEqualTo(3L);
    }

    @Test
    public void getData() {
        assertThat(account1.getData()).isEqualTo("Ivanov");
        assertThat(account2.getData()).isEqualTo("Petrov");
        assertThat(account3.getData()).isEqualTo("Sidorov");
    }


    @Test
    public void getAccountStatus() {
        assertThat(account1.getAccountStatus()).isEqualTo(AccountStatus.ACTIVE);
        assertThat(account2.getAccountStatus()).isEqualTo(AccountStatus.BANNED);
        assertThat(account3.getAccountStatus()).isEqualTo(AccountStatus.DELETED);
    }

}