package com.zovlanik.crud.view;


import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.Scanner;

public class SkillView {
    SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public void preview(){
        System.out.println("Добро пожаловать в меню работы с сущностью Skill");

        Scanner scan = new Scanner(System.in);
        String UserChoice = "";
        boolean programON = true;
        do {
            System.out.println("Выберите, что хотите сделать:\n" +
                    "1 = создать");

            UserChoice = scan.hasNext() ? scan.next().trim() : "ничего";
            switch (UserChoice) {
                case "1":
                    System.out.println("выбран 1 вариант");
                    System.out.println("Введите имя новой сущности Skill:");
                    String skillName = "";
                    while (scan.hasNext()) {
                        skillName = scan.nextLine();
                        if (skillName.length() > 1){
                            break;
                        }
                    }
                    createSkill(skillName);
                    System.out.println("Скилл создан");
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
        return skillRepository.getById(id);
    }

    private void deleteSkillById(Long id){

    }

    private void createSkill(String skillName){
        skillRepository.create(new Skill(1L,skillName));
    }

    private void updateSkill(){

    }
}
