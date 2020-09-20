import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    void create(Skill skillName){
        List <Skill> ls = getAll();
        long size = ls.size();
        try (FileWriter writer = new FileWriter("Skill.txt",true)){
            writer.write((size+1) + "," + skillName.name + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void update (Skill skill){
        List<Skill> listSkill = getAll();
        try (FileWriter writer = new FileWriter("Skill.txt")){
            for(Skill sk : listSkill){
                if(sk.id != skill.id) {
                    writer.write(sk.toString() + "\n");

                } else {
                    writer.write(skill.id + "," + skill.name + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Skill getById(Long id){
        Skill skill = null;
        long temp;
        try (FileReader reader = new FileReader("Skill.txt")){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                temp = Long.parseLong(line.substring(0,line.indexOf(',')));
                if(temp == id){
                    skill = new Skill(temp,line.substring(line.indexOf(',')+1));
                }

                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return skill;
    }

    List<Skill> getAll()  {
        List <Skill> ls = new ArrayList<>();
        try (FileReader reader = new FileReader("Skill.txt")){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
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
        List<Skill> listSkill = getAll();

        try (FileWriter writer = new FileWriter("Skill.txt")){
            for(Skill sk : listSkill){
                if(sk.id != id) {
                    writer.write(sk.toString() + "\n");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
