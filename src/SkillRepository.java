import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    Skill create(Skill skill){
        return skill;
    }

    Skill update (Skill skill){
        return skill;
    }

    Skill getById(Long id){
        return new Skill();
    }

    List<Skill> getAll()  {
        List <Skill> ls = new ArrayList<>();
        return ls;
    }

    void deleteById(Long id){

    }
}
