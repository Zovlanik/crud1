package com.zovlanik.crud.controller;

import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;
import com.zovlanik.crud.repository.io.JavaIOSkillRepositoryImpl;

import java.util.List;

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

    public void showAll(){
        List<Skill> skills = skillRepository.getAll();
        for(Skill skill : skills){

            System.out.println(skill.getId() + "," + skill.getName());
        }
    }
    public boolean deleteById(Long skillId){
        int tempSizeBefore = skillRepository.getAll().size();
        skillRepository.deleteById(skillId);
        int tempSizeAfter = skillRepository.getAll().size();
        return tempSizeAfter < tempSizeBefore;
    }

    public void updateSkill(Long skillId, String NewSkillName){
        skillRepository.update(new Skill(skillId,NewSkillName));
    }
}
