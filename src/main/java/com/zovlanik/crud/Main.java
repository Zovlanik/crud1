package com.zovlanik.crud;

public class Main {
    public static void main(String[] args) {
        Skill sk = new Skill(5L, "Бросание ножа");
        SkillRepository skillRepo = new SkillRepository();
        System.out.println(skillRepo.getAllInternal());
        skillRepo.update(sk);
        System.out.println(skillRepo.getAllInternal());
    }
}
