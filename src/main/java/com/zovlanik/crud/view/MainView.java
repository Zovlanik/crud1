package com.zovlanik.crud.view;

import java.util.Scanner;

public class MainView {
    public void begin(){
        SkillView skillView = new SkillView();
        AccountView accountView = new AccountView();
        DeveloperView developerView = new DeveloperView();

        boolean programON = true;
        Scanner scan = new Scanner(System.in);
        String UserChoice = "";
        do {
            System.out.println("Выберите с какой сущностью работать:\n" +
                    "1 = сущность Skill\n" +
                    "2 = сущность Account\n" +
                    "3 = сущность Developer\n" +
                    "q = выйти из программы");
            UserChoice = scan.hasNext() ? scan.next().trim() : "ничего";
            switch (UserChoice) {
                case "1":
                    System.out.println("выбран 1 вариант");
                    skillView.preview();
                    break;
                case "2":
                    System.out.println("выбран 2 вариант");
                    accountView.preview();
                    break;
                case "3":
                    System.out.println("выбран 3 вариант");
                    developerView.preview();
                    break;
                default:
                    programON = false;


            }

        } while (programON);
    }
}
