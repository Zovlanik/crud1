import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository repo = new SkillRepository();
        List<Skill> listSkill = repo.getAll();

        System.out.println(repo.getAll());
        System.out.println(repo.getById(4L));


        // создадим новый скилл
        Skill sk = new Skill("temp");
        repo.create(sk);
        System.out.println(repo.getAll());

//        // обновим название скилла
//        Skill sk2 = new Skill(9L, "Абракадабра");
//        repo.update(sk2);
//        System.out.println(repo.getAll());

    }
}
