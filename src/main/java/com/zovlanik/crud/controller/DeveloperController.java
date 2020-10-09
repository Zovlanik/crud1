package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.io.JavaIODeveloperRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

public class DeveloperController {
    private final DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();
    private final AccountController accController = new AccountController();
    private final SkillController skillController = new SkillController();


    public boolean create(String developerName, Set<Long> idSkills, Long idAccount) {

        if (developerName.length() > 2) {
            try {
                Set<Skill> skills = new HashSet<>();
                for (Long skillId : idSkills) {
                    skills.add(skillController.getById(skillId));
                }
                Account account = accController.getById(idAccount);
                developerRepository.create(new Developer(1L, developerName, skills, account));
                return true;
            } catch (Exception ex){
                System.out.println("Тут произошла ошибка с созданием сущности Developer. Но ничего страшного, попробуй ещё раз.");
                return false;
            }

        }
        return false;
    }

    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }
}
