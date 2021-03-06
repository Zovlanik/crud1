package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.io.SQLIO.SQLIODeveloperRepositoryImpl;
import com.zovlanik.crud.repository.io.hibernate.HibernateIODeveloperRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperController {
//    private final DeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();
//    private final DeveloperRepository developerRepository = new CsvIODeveloperRepositoryImpl();
    private final DeveloperRepository developerRepository = new HibernateIODeveloperRepositoryImpl();
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
                Developer dev = new Developer(1L, developerName, skills, account);
                developerRepository.create(dev);
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

    public void showAll(){
        List<Developer> developers = developerRepository.getAll();

        for(Developer dev : developers){
            System.out.println(dev.getId() + " " + dev.getName());
        }
    }

    public boolean deleteById(Long id){
        int tempSizeBefore = developerRepository.getAll().size();
        developerRepository.deleteById(id);
        int tempSizeAfter = developerRepository.getAll().size();
        return tempSizeAfter < tempSizeBefore;
    }

    public void updateDeveloperName(Developer newDeveloper){
        developerRepository.update(newDeveloper);
    }
}
