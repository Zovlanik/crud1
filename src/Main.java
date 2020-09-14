public class Main {
    public static void main(String[] args) {
        Skill skill = new Skill();
        skill.create("somefile.txt");
        skill.delete("somefile.txt");
        skill.read("somefile.txt");
        skill.update("somefile.txt", "Некоторый текст2");
    }
}
