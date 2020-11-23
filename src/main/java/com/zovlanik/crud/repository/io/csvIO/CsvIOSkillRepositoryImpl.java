package com.zovlanik.crud.repository.io.csvIO;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvIOSkillRepositoryImpl implements SkillRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\csv\\skills.csv";

    @Override
    public Skill getById(Long id) {
        return getAllInternal().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override

    //вернуть все элементы
    public List<Skill> getAll() {
        return getAllInternal();
    }

    //вернуть вообще все элементы
    List<Skill> getAllInternal() {
        List<Skill> ls = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> skillsCSV = reader.readAll();
            for(String[] temp : skillsCSV){
                ls.add(convertStringToSkill(temp));
            }



        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public void create(Skill skillName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH,true))) {
            skillName.setId(generateNewId());
            writer.writeNext(convertSkillToString(skillName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Skill skill) {
        List<Skill> listSkill = getAllInternal();
        /*
         *
         * В чём разница в двух строках ниже?
         *
         * про findFirst понятно. В первом случае может вернуться десяток значений, если у нас совпадут их id. Но
         * у нас id уникальные должны быть. Да и в skillToUpdate не запишется больше одного элемента. Или запишется?
         *
         * Собственно, вот и вся разница:
         * Exception in thread "main" java.lang.ClassCastException: class java.util.stream.ReferencePipeline$2 cannot be
         * cast to class com.zovlanik.crud.model.Skill (java.util.stream.ReferencePipeline$2 is in module java.base of
         * loader 'bootstrap'; com.zovlanik.crud.model.Skill is in unnamed module of loader 'app')
         *
         * */
//        Skill skillToUpdate = (Skill)listSkill.stream().filter(s -> s.getId().equals(skill.getId())); //ТАК НЕ ПОЛУЧИТСЯ
//        Skill skillToUpdate = listSkill.stream().filter(s -> s.getId().equals(skill.getId())).findFirst().orElse(null);
//        listSkill.remove(skillToUpdate); //НЕ РАБОТАЕТ, потому, что надо переопределить equals и hashcode
//        listSkill.add(skill);


        /*
         *
         * Либо можно сделать через метод по умолчанию
         * Но у нас же список и мы можем удалить элемент сразу, не ища его по айдишнику
         *
         * */
        listSkill.removeIf(s -> s.getId().equals(skill.getId()));
        listSkill.add(skill);


        /*
         *
         * Мне кажется, что здесь плохая сортировка, т.к. я превращаю лонг в int, что будет проблемой при больших числах
         * Но я не знаю как лучше отсортировать стрим...
         *
         * */
        listSkill = listSkill.stream().sorted((s, s1) -> (int) (s.getId() - s1.getId())).collect(Collectors.toList());

        //Записываем весь наш список в файл:
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            for (Skill sk : listSkill) {
                writer.writeNext(convertSkillToString(sk));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteById(Long id) {
        List<Skill> listSkill = getAll();
        listSkill.removeIf(s -> s.getId().equals(id)); //здесь же удяляются ВСЕ элементы с таким id?

        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            for (Skill sk : listSkill) {
                writer.writeNext(convertSkillToString(sk));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private long generateNewId() {
        return getAllInternal().size() + 1;
    }

    private Skill convertStringToSkill(String[] skillFromCSV) {
        return new Skill(Long.parseLong(skillFromCSV[0]),skillFromCSV[1]);
    }

    public String[] convertSkillToString(Skill skill) {
        return new String[] {String.valueOf(skill.getId()), skill.getName()};
    }
}
