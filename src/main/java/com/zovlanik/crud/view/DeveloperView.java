package com.zovlanik.crud.view;

import com.zovlanik.crud.controller.DeveloperController;
import com.zovlanik.crud.model.Developer;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView {
    DeveloperController developerController = new DeveloperController();

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
                    System.out.println("Введите имя новой сущности Developer:");
                    String developerName = "";
                    while (scan.hasNext()) {
                        developerName = scan.nextLine();
                        if (developerName.length() > 0){
                            break;
                        }
                    }

                    System.out.println("Введите id необходимых Skills новой сущности Developer через запятую:");
                    String developerSkills = "";
                    while (scan.hasNext()) {
                        developerSkills = scan.nextLine();
                        if (developerSkills.length() > 0){
                            break;
                        }
                    }

                    System.out.println("Введите id Account новой сущности Developer:");
                    String developerAccount = "";
                    while (scan.hasNext()) {
                        developerAccount = scan.nextLine();
                        if (developerAccount.length() > 0){
                            break;
                        }
                    }

                    if (createDeveloper(developerName,developerSkills,developerAccount)) {
                        System.out.println("Developer создан");
                    } else {
                        System.out.println("Тут возникла ошибка: ");
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

    private Developer getDeveloperById(Long id){
        return developerController.getById(id);
    }

    private void deleteDeveloperById(Long id){

    }

    private boolean createDeveloper(String developerName, String skillsIdString, String accountIdString){
        Set <Long> idSkills = new HashSet<>();
        Long idAccount = null;
        try{
            String[] skillsId = skillsIdString.split(",");
            for(String id : skillsId){
                idSkills.add(Long.parseLong(id));
            }
            idAccount = Long.parseLong(accountIdString);
        }catch(Exception ex){
            return false;
        }
        return developerController.create(developerName, idSkills, idAccount);
    }

    private void updateDeveloper(){

    }
}
