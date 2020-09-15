public class Main {
    public static void main(String[] args) {
        SkillRepository repo = new SkillRepository();
        Skill skill = repo.getById(1L);

    }
}
