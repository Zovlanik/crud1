package com.zovlanik.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SkillRepository {
    private static final String FILE_PATH = "src\\main\\resources\\skills.txt";

    void create(Skill skillName){
        try (FileWriter writer = new FileWriter(FILE_PATH,true)){
            writer.write( generateNewId() + "," + skillName.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void update (Skill skill){
        List<Skill> listSkill = getAllInternal();
        /*
        *
        * В чём разница в двух строках ниже?
        *
        * про findFirst понятно. В первом случае может вернуться десяток значений, если у нас совпадут их id. Но
        * у нас id уникальные должны быть. Да и в skillToUpdate не запишется больше одного элемента. Или запишется?
        *
        * */
//        Skill skillToUpdate = (Skill)listSkill.stream().filter(s -> s.getId().equals(skill.getId()));
        Skill skillToUpdate = listSkill.stream().filter(s -> s.getId().equals(skill.getId())).findFirst().orElse(null);
//        listSkill.remove(skillToUpdate);
//        listSkill.add(skill);


        /*
        *
        * Либо можно сделать через метод по умолчанию
        * Но у нас же список и мы можем удалить элемент сразу, не ища его по айдишнику
        *
        * */
        listSkill.removeIf(s -> s.getId().equals(skill.getId()));
        listSkill.add(skill);


        //Записываем весь наш список в файл:
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Skill sk : listSkill){
                    writer.write(sk.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Skill getById(Long id){
        return getAllInternal().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    //вернуть все элементы
    List<Skill> getAll()  {
        return getAllInternal();
    }

    //вернуть вообще все элементы
    List<Skill> getAllInternal(){
        List <Skill> ls = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                ls.add(convertStringToSkill(line));
                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    void deleteById(Long id){
        List<Skill> listSkill = getAll();
        listSkill.removeIf(s -> s.getId().equals(id)); //здесь же удяляются ВСЕ элементы с таким id?
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Skill sk : listSkill){
                writer.write(sk.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private long generateNewId(){
        return getAllInternal().size() + 1;
    }

    private Skill convertStringToSkill (String skillFromFile){
        return new Skill(Long.parseLong(skillFromFile.substring(0,skillFromFile.indexOf(','))),skillFromFile.substring(skillFromFile.indexOf(',')+1));
    }
}
