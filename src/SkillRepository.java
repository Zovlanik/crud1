import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        return new Skill(0L,"asd");
    }

    List<Skill> getAll()  {
        List <Skill> ls = new ArrayList<>();
        try (FileReader reader = new FileReader("Skill.txt")){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null){
                Skill tempSkill = new Skill(Long.parseLong(line.substring(0,line.indexOf(','))),line.substring(line.indexOf(',')+1));
                ls.add(tempSkill);
                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    void deleteById(Long id){

    }
}
