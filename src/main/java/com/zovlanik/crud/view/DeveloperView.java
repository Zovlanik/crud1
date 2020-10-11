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
                    "2 = вывести на экран полный список Developers\n" +
                    "3 = удалить Developer по ИД\n" +
                    "4 = переименовать Developer по ИД\n" +
                    "5 = получить сущность Developer по ИД\n " +
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
                    showAllDevelopers();
                    break;
                case "3":
                    System.out.println("выбран 3 вариант: УДАЛИТЬ\n" +
                            "Введите ИД сущности, которую нужно удалить (по одной за раз):");
                    Long id = 0L;
                    while (scan.hasNext()) {
                        id = scan.nextLong();
                        if (id > 0) {
                            break;
                        }
                    }
                    if (deleteDeveloperById(id)) {
                        System.out.println("Developer с ИД = " + id + " удалён.");
                    } else {
                        System.out.println("Вы ввели неверный ИД.");
                    }
                    break;
                case "4":
                    System.out.println("выбран 4 вариант: ПЕРЕИМЕНОВАТЬ\n" +
                            "Введите ИД сущности Developer и через запятую новое имя: ");
                    Long idForRename = 0L;
                    String NewDeveloperName = "";
                    while (scan.hasNext()) {
                        String tempStr = scan.nextLine();
                        if (tempStr.length() > 0) {
                            idForRename = Long.parseLong(tempStr.substring(0, tempStr.indexOf(",")));
                            NewDeveloperName = tempStr.substring(tempStr.indexOf(",")+1).trim();
                            break;
                        }
                    }
                    updateDeveloper(idForRename, NewDeveloperName);

                    System.out.println("Developer с ИД = " + idForRename + " переименован.\n" + "Новое имя: " + NewDeveloperName);

                    break;
                case "5":
                    System.out.println("выбран 5 вариант: \n" +
                            "Введите ИД сущности Developer: ");
                    Long developerId = 0L;
                    while (scan.hasNext()) {
                        developerId = scan.nextLong();
                        if (developerId > 0) {
                            break;
                        }
                    }
                    System.out.println(getDeveloperById(developerId));

                    break;
                default:
                    programON = false;


            }

        } while (programON);
    }

    private String getDeveloperById(Long id){
        Developer dev = developerController.getById(id);
        return "ИД и имя Developer: \n" + dev.getId() + " " + dev.getName();
    }

    private boolean deleteDeveloperById(Long id){
        return developerController.deleteById(id);
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

    private void updateDeveloper(Long id, String NewDeveloperName){
        Developer devToUpdate = developerController.getById(id);
        devToUpdate.setName(NewDeveloperName);
        developerController.updateDeveloperName(devToUpdate);
    }
    private void showAllDevelopers(){
        developerController.showAll();
    }
}
