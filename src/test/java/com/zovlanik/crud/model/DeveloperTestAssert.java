package com.zovlanik.crud.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DeveloperTestAssert {
    Set<Skill> skills = new HashSet<Skill>(){{
        new Skill(1L,"Test1");
        new Skill(2L,"Test2");
    }};
    Account account = new Account(1L,"Test1Account",AccountStatus.ACTIVE);
    Developer developer = new Developer(1l,"TestDeveloper",skills,account);

    @Test
    public void checkDeveloperStatus(){
        assert developer.getId().equals(1L);
        assertThat(developer.getAccount().getAccountStatus()).isEqualTo(AccountStatus.ACTIVE);
    }

    @Test
    public void checkDeveloperName(){
        assertThat(developer.getName()).isEqualTo("TestDeveloper");
    }

    @Test
    public void checkDeveloperAccount(){
        assertThat(developer.getAccount().getData()).isEqualTo("Test1Account");
    }

}
