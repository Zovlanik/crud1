package com.zovlanik.crud.view;


import com.zovlanik.crud.controller.SkillController;
import com.zovlanik.crud.model.Skill;

import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();

    public void preview() {
        System.out.println("Добро пожаловать в меню работы с сущностью Skill");

        Scanner scan = new Scanner(System.in);
        String UserChoice = "";
        boolean programON = true;
        do {
            System.out.println("Выберите, что хотите сделать:\n" +
                    "1 = создать\n" +
                    "2 = вывести на экран полный список Skill\n" +
                    "3 = удалить скилл по ИД\n" +
                    "4 = переименовать скилл по ИД\n" +
                    "q = подняться на предыдущий уровень программы");

            UserChoice = scan.hasNext() ? scan.next().trim() : "ничего";
            switch (UserChoice) {
                case "1":
                    System.out.println("выбран 1 вариант");
                    System.out.println("Введите имя новой сущности Skill:");
                    String skillName = "";
                    while (scan.hasNext()) {
                        skillName = scan.nextLine();
                        if (skillName.length() > 0) {
                            break;
                        }
                    }
                    if (createSkill(skillName)) {
                        System.out.println("Скилл создан");
                    } else {
                        System.out.println("Тут возникла ошибка");
                    }

                    break;
                case "2":

                    System.out.println("выбран 2 вариант: ПОКАЗАТЬ ВСЁ");
                    showAllSkills();
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
                    if (deleteSkillById(id)) {
                        System.out.println("Скилл с ИД = " + id + " удалён.");
                    } else {
                        System.out.println("Вы ввели неверный ИД.");
                    }
                    break;

                case "4":
                    System.out.println("выбран 4 вариант: ПЕРЕИМЕНОВАТЬ\n" +
                            "Введите ИД сущности Skill и через запятую новое имя: ");
                    Long idForRename = 0L;
                    String NewSkillName = "";
                    while (scan.hasNext()) {
                        String tempStr = scan.nextLine();
                        if (tempStr.length() > 0) {
                            idForRename = Long.parseLong(tempStr.substring(0, tempStr.indexOf(",")));
                            NewSkillName = tempStr.substring(tempStr.indexOf(",")+1).trim();
                            break;
                        }
                    }
                    updateSkill(idForRename, NewSkillName);

                    System.out.println("Скилл с ИД = " + idForRename + " переименован.\n" + "Новое имя: " + NewSkillName);

                    break;
                default:
                    programON = false;
            }

        } while (programON);
    }

    private Skill getSkillById(Long id) {
        return skillController.getById(id);
    }

    private boolean deleteSkillById(Long id) {
        return skillController.deleteById(id);
    }

    private boolean createSkill(String skillName) {
        return skillController.create(skillName);
    }

    private void updateSkill(Long skillId, String NewSkillName) {
        skillController.updateSkill(skillId, NewSkillName);
    }

    private void showAllSkills() {
        skillController.showAll();
    }
}
