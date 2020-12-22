package com.zovlanik.crud.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillTestAssert_1 {

    private Skill skill;
    private Set<Skill> skills = new HashSet<>();
    private Developer developer;
    private Account account;

    @Before
    public void setUp() throws Exception {
        this.skill = new Skill(11l,"FireBird");
        skills.add(skill);
        this.account = new Account(1l,"FirstAcc",AccountStatus.ACTIVE);
        this.developer = new Developer(1l,"FirstDev",skills,account);
    }

    @Test
    public void checkSkillName(){
        assertThat(skill.getName()).isEqualTo("FireBird");
    }

    @Test
    public void checkDeveloper_have_this_Skill() {
        assertThat(developer.getSkills().stream().anyMatch(s->s.equals(skill))).isTrue();
    }
}
