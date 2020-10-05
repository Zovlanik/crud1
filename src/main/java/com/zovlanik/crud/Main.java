package com.zovlanik.crud;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;
import com.zovlanik.crud.repository.io.JavaIODeveloperRepositoryImpl;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//        //здесь тестируем Account
//        Account acc = new Account(10L,"Marv Telos", AccountStatus.BANNED);
//        AccountRepository accRep = new JavaIOAccountRepositoryImpl();
//
//        //сначала выведем наш список аккаунтов
//        printAcc(accRep.getAll());
//
//        //Добавим новый
//        accRep.create(acc);
//
//        //выведем ещё раз
//        System.out.println("=====================разделитель======================");
//        printAcc(accRep.getAll());
//
//        //изменим его
//        accRep.update(new Account(7L,"Marv Telos", AccountStatus.ACTIVE));
//
//        //выведем ещё раз
//        System.out.println("=====================разделитель======================");
//        printAcc(accRep.getAll());


        //Здесь тестируем Developer
        SkillRepository skillRepoForDev = new JavaIOSkillRepositoryImpl();
        AccountRepository accountRepositoryForDev = new JavaIOAccountRepositoryImpl();
        Set<Skill> skillsForDev = new HashSet<>();
        skillsForDev.add(skillRepoForDev.getById(1L));
        skillsForDev.add(skillRepoForDev.getById(2L));
        Developer dev = new Developer(5L,"Ковалёв Аркадий Геннадьевич",skillsForDev, accountRepositoryForDev.getById(1L));

        DeveloperRepository devRep = new JavaIODeveloperRepositoryImpl();
//        devRep.create(dev);


        devRep.update(new Developer(3L,"Пичугин Вениамин Вольфович",skillsForDev, accountRepositoryForDev.getById(1L)));
    }

    public static void printAcc(List<Account> accounts){
        for (Account account : accounts){
            System.out.print(account.getId() + "," + account.getData() + "," + account.getAccountStatus() + "\n");
        }


    }
}
