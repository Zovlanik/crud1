package com.zovlanik.crud.view;


import com.zovlanik.crud.controller.SkillController;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();

    public void preview(){
        System.out.println("Добро пожаловать в меню работы с сущностью Skill");

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
                    System.out.println("Введите имя новой сущности Skill:");
                    String skillName = "";
                    while (scan.hasNext()) {
                        skillName = scan.nextLine();
                        if (skillName.length() > 0){
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

    private Skill getSkillById(Long id){
        return skillController.getById(id);
    }

    private void deleteSkillById(Long id){

    }

    private boolean createSkill(String skillName){
        return skillController.create(skillName);
    }

    private void updateSkill(){

    }
}
