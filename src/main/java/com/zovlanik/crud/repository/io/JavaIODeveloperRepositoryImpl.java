package com.zovlanik.crud.repository.io;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\developers.txt";
    private final SkillRepository skillRepo = new JavaIOSkillRepositoryImpl();
    private final AccountRepository accountRep = new JavaIOAccountRepositoryImpl();

    @Override
    public Developer getById(Long id) {
        return getAllInternal().stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return getAllInternal();
    }

    public List<Developer> getAllInternal() {
        List<Developer> ld = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while (line != null && line.length() > 1) {
                ld.add(convertStringToDeveloper(line));
                line = buffReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ld;
    }

    @Override
    public void create(Developer developer) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            developer.setId(generateId());
            writer.write(convertDeveloperToString(developer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {
        List<Developer> listDeveloper = getAll();

        listDeveloper.removeIf(a -> a.getId().equals(developer.getId()));
        listDeveloper.add(developer);

        //сортируем наших developers перед записью
        listDeveloper = listDeveloper.stream().sorted((a, a1) -> (int) (a.getId() - a1.getId())).collect(Collectors.toList());


        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Developer dev : listDeveloper) {
                writer.write(convertDeveloperToString(dev));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> ld = getAll();
        ld.removeIf(a -> a.getId().equals(id));
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Developer dev : ld) {
                writer.write(convertDeveloperToString(dev));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Developer convertStringToDeveloper(String developerString) {
        String[] tempValues = developerString.split("-"); //парсим строку на составляющие
        Long id = Long.parseLong(tempValues[0]);//конвертируем айдишник в лонг
        String name = tempValues[1];//переписываем имя девелопера
        // по списку айдишников получаем набор скиллов
        Set<Skill> skills = new HashSet<>();
        String[] tempSkillsFromDevelopersString = tempValues[2].replaceAll("^\\[|\\]$", "").split(",");

        for (String str : tempSkillsFromDevelopersString) {
            skills.add(skillRepo.getById(Long.parseLong(str.trim())));
        }
        //по айдишнику получаем нужный нам аккаунт

        Account account = accountRep.getById(Long.parseLong(tempValues[3]));
        return new Developer(id, name, skills, account);
    }

    public String convertDeveloperToString(Developer developer) {
        StringBuilder devString = new StringBuilder()
                .append(developer.getId())
                .append("-")
                .append(developer.getName())
                .append("-");
        Set<Skill> skills = developer.getSkills();
        for (Skill skill : skills) {
            devString.append(skill.getId())
                    .append(",");
        }
        devString.setLength(devString.length() - 1);
        devString.append("-");
        Account acc = developer.getAccount();
        devString.append(acc.getId());

        return devString + "\n";
    }

    private Long generateId() {
        return (long) (getAllInternal().size() + 1);
    }
}
