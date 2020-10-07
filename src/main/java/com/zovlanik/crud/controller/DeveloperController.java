package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOAccountRepositoryImpl;
import com.zovlanik.crud.repository.io.JavaIODeveloperRepositoryImpl;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

public class DeveloperController {
    private final DeveloperRepository developerRepository= new JavaIODeveloperRepositoryImpl();
    private final AccountRepository accountRepository = new JavaIOAccountRepositoryImpl();
    private final SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public boolean create(String developerName, Set<Long> idSkills, Long idAccount){

        if (developerName.length() > 2) {
            Set<Skill> skills = new HashSet<>();
            for(Long skillId : idSkills){
                skills.add(skillRepository.getById(skillId));
            }
            Account account = accountRepository.getById(idAccount);
            developerRepository.create(new Developer(1L, developerName, skills, account));

            return true;
        }
        return false;
    }

    public Developer getById(Long id){
        return developerRepository.getById(id);
    }
}
