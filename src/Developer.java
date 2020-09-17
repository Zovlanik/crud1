import java.util.List;

public class Developer {
    long id;
    String name;
    List<Skill> skills;
    Account account;

    Developer (long id, String name, List<Skill> skills, Account account ) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.account = account;
    }
    public String toString(){

        return id + "-" + name + "-" + skills + "-" + account;
    }
}
