package com.zovlanik.crud.view;

import com.zovlanik.crud.controller.AccountController;
import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Skill;

import java.util.Scanner;

public class AccountView {
    AccountController accountController = new AccountController();

    public void preview(){
        System.out.println("Добро пожаловать в меню работы с сущностью Account");

        Scanner scan = new Scanner(System.in);
        String UserChoice = "";
        boolean programON = true;
        do {
            System.out.println("Выберите, что хотите сделать:\n" +
                    "1 = создать\n" +
                    "q = подняться на предыдущий уровень программы");

            UserChoice = scan.hasNext() ? scan.next().trim() : "ничего";
            switch (UserChoice) {
                case "1":
                    System.out.println("выбран 1 вариант");
                    System.out.println("Введите имя новой сущности Account:");
                    String accountName = "";
                    while (scan.hasNext()) {
                        accountName = scan.nextLine();
                        if (accountName.length() > 0){
                            break;
                        }
                    }
                    if (createAccount(accountName)) {
                        System.out.println("Account создан");
                    } else {
                        System.out.println("Тут возникла ошибка");
                    }

                    break;
                case "2":
                    System.out.println("выбран 2 вариант");
                    break;
                case "3":
                    System.out.println("выбран 3 вариант");
                    break;
                default:
                    programON = false;


            }

        } while (programON);
    }

    private Account getAccountById(Long id){
        return accountController.getById(id);
    }

    private void deleteAccountById(Long id){

    }

    private boolean createAccount(String accountName){
        return accountController.create(accountName);
    }

    private void updateAccount(){

    }
}
