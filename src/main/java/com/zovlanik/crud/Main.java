package com.zovlanik.crud;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        //здесь тестировали Skill
//        Skill sk = new Skill(5L, "abc");
//        SkillRepository skillRepo = new JavaIOSkillRepositoryImpl();
//
//        skillRepo.create(sk);
//        System.out.println(skillRepo.getAll());
//
//        sk.setName("abc1");
//        skillRepo.update(sk);
//        System.out.println(skillRepo.getAll());

        //здесь тестируем Account
        Account acc = new Account(10L,"Marv Telos", AccountStatus.BANNED);
        AccountRepository accRep = new JavaIOAccountRepositoryImpl();

        //сначала выведем наш список аккаунтов
        printAcc(accRep.getAll());

        //Добавим новый
        accRep.create(acc);

        //выведем ещё раз
        System.out.println("=====================разделитель======================");
        printAcc(accRep.getAll());

        //изменим его
        accRep.update(new Account(7L,"Marv Telos", AccountStatus.ACTIVE));

        //выведем ещё раз
        System.out.println("=====================разделитель======================");
        printAcc(accRep.getAll());

    }

    public static void printAcc(List<Account> accounts){
        for (Account account : accounts){
            System.out.print(account.getId() + "," + account.getData() + "," + account.getAccountStatus() + "\n");
        }


    }
}
