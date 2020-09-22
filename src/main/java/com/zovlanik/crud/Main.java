package main.java.com.zovlanik.crud;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository repo = new SkillRepository();
        System.out.println(repo.getAll());
        System.out.println(repo.getById(4L));



        Skill sk = new Skill("tempura");
        repo.create(sk);
        System.out.println(repo.getAll());

        // обновим название скилла
        Skill sk2 = new Skill(10L, "Абракадабура");
        repo.update(sk2);
        System.out.println(repo.getAll());

        Account acc = new Account(1L,"Некоторое название аккаунта", main.java.com.zovlanik.crud.AccountStatus.ACTIVE);
        AccountRepository accRepo = new AccountRepository();
        accRepo.create(acc);


        // пробуем создать девелопера
        SkillRepository skillRepo = new SkillRepository();
        List<Skill> listSkill = new ArrayList<>();

        listSkill.add(skillRepo.getById(1L));


        Developer dev = new Developer(2L,"Сергеев Сергей Сергеевич", new long[]{2L,3L,4L},4L);

        DeveloperRepository devRepo = new DeveloperRepository();
        devRepo.create(dev);
        System.out.println(devRepo.getAll());
    }
}
