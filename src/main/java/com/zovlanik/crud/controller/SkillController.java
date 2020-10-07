package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

public class SkillController {
    private final SkillRepository skillRepository = new JavaIOSkillRepositoryImpl();

    public boolean create(String skillName){
        //проверка на то, что длина имени нашего скилла больше двух букв
        if (skillName.length() > 2) {
            skillRepository.create(new Skill(1L, skillName));
            return true;
        }
        return false;
    }

    public Skill getById(Long id){
        return skillRepository.getById(id);
    }
}
