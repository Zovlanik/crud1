package com.zovlanik.crud;

import java.util.HashSet;
import java.util.Set;

public class Developer {
    long id;
    String name;
    Set<Skill> skills = new HashSet<>();
    Account account;

    Developer(long id, String name, long[] skills, long account) {
        this.id = id;
        this.name = name;
        SkillRepository skRep = new SkillRepository();
        for (long l : skills) {
            this.skills.add(skRep.getById(l));
        }
        this.account = AccountRepository.getById(account);
    }

    public String toString() {
        return id + "-" + name + "-" + skills + "-" + account;
    }

}
