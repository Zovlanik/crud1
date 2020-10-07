package com.zovlanik.crud;

import com.zovlanik.crud.view.SkillView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в меню создания объектов:");
        SkillView skillView = new SkillView();

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
                    break;
                case "3":
                    System.out.println("выбран 3 вариант");
                    break;
                default:
                    programON = false;


            }

        } while (programON);
    }
}
