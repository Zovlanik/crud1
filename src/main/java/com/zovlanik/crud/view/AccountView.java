package com.zovlanik.crud.view;

import com.zovlanik.crud.controller.AccountController;
import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.model.Skill;

import java.util.Scanner;

public class AccountView {
    AccountController accountController = new AccountController();

    public void preview() {
        System.out.println("Добро пожаловать в меню работы с сущностью Account");

        Scanner scan = new Scanner(System.in);
        String UserChoice = "";
        boolean programON = true;
        do {
            System.out.println("Выберите, что хотите сделать:\n" +
                    "1 = создать\n" +
                    "2 = вывести на экран полный список Account\n" +
                    "3 = удалить Account по ИД\n" +
                    "4 = переименовать Account по ИД\n" +
                    "q = подняться на предыдущий уровень программы");

            UserChoice = scan.hasNext() ? scan.next().trim() : "ничего";
            switch (UserChoice) {
                case "1":
                    System.out.println("выбран 1 вариант");
                    System.out.println("Введите имя новой сущности Account:");
                    String accountName = "";
                    while (scan.hasNext()) {
                        accountName = scan.nextLine();
                        if (accountName.length() > 0) {
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
                    showAllAccounts();
                    break;
                case "3":
                    System.out.println("выбран 3 вариант: УДАЛИТЬ Account\n" +
                            "Введите ИД сущности, которую нужно удалить (по одной за раз):");
                    Long id = 0L;
                    while (scan.hasNext()) {
                        id = scan.nextLong();
                        if (id > 0) {
                            break;
                        }
                    }
                    if (deleteAccountById(id)) {
                        System.out.println("Account с ИД = " + id + " удалён.");
                    } else {
                        System.out.println("Вы ввели неверный ИД.");
                    }
                    break;
                case "4":
                    System.out.println("выбран 4 вариант: ПЕРЕИМЕНОВАТЬ Account\n" +
                            "Введите ИД сущности Account и через запятую новое имя: ");
                    Long idForRename = 0L;
                    String NewAccountName = "";
                    while (scan.hasNext()) {
                        String tempStr = scan.nextLine();
                        if (tempStr.length() > 0) {
                            idForRename = Long.parseLong(tempStr.substring(0, tempStr.indexOf(",")));
                            NewAccountName = tempStr.substring(tempStr.indexOf(",") + 1).trim();
                            break;
                        }
                    }
                    updateAccount(idForRename, NewAccountName);

                    System.out.println("Account с ИД = " + idForRename + " переименован.\n" + "Новое имя: " + NewAccountName);

                    break;
                default:
                    programON = false;


            }

        } while (programON);
    }

    private Account getAccountById(Long id) {
        return accountController.getById(id);
    }

    private boolean deleteAccountById(Long id) {
        return accountController.deleteById(id);
    }

    private boolean createAccount(String accountName) {
        return accountController.create(accountName);
    }

    private void updateAccount(Long accountId, String accountName) {
        accountController.update(new Account(accountId, accountName, AccountStatus.ACTIVE));
    }

    private void showAllAccounts() {
        accountController.showAll();
    }

}
