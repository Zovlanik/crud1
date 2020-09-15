import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository repo = new SkillRepository();
        List<Skill> listSkill = repo.getAll();

        System.out.println(repo.getAll());
        System.out.println(repo.getById(4L));


//        repo.deleteById(4L);

        repo.create("Lisp");
        System.out.println(repo.getAll());


    }
}
