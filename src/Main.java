import java.util.List;

public class Main {
    public static void main(String[] args) {
        SkillRepository repo = new SkillRepository();
        List<Skill> listSkill = repo.getAll();
        System.out.println(listSkill.toString());


    }
}
