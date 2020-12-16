package com.zovlanik.crud.repository.io.csvIO;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvIODeveloperRepositoryImpl implements DeveloperRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\csv\\developers.csv";
    private final SkillRepository skillRepo = new CsvIOSkillRepositoryImpl();
    private final AccountRepository accountRep = new CsvIOAccountRepisitoryImpl();

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
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> developersCSV = reader.readAll();
            for(String[] temp : developersCSV){
                ld.add(convertStringToDeveloper(temp));
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return ld;

    }

    @Override
    public void create(Developer developer) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            developer.setId(generateId());
            writer.writeNext(convertDeveloperToString(developer));
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


        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            for (Developer dev : listDeveloper) {
                writer.writeNext(convertDeveloperToString(dev));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> ld = getAll();
        ld.removeIf(a -> a.getId().equals(id));
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            for (Developer dev : ld) {
                writer.writeNext(convertDeveloperToString(dev));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Developer convertStringToDeveloper(String[] developerString) {

        Long id = Long.parseLong(developerString[0]);//конвертируем айдишник в лонг
        String name = developerString[1];//переписываем имя девелопера
        // по списку айдишников получаем набор скиллов
        Set<Skill> skills = new HashSet<>();
        String[] tempSkillsFromDevelopersString = developerString[2].replaceAll("^\\[|]$", "").split(",");

        for (String str : tempSkillsFromDevelopersString) {
            skills.add(skillRepo.getById(Long.parseLong(str.trim())));
        }
        //по айдишнику получаем нужный нам аккаунт

        Account account = accountRep.getById(Long.parseLong(developerString[3]));
        return new Developer(id, name, skills, account);
    }

    public String[] convertDeveloperToString(Developer developer) {
        String devId = String.valueOf(developer.getId());
        String devName = developer.getName();
        List<Long> devSkills = developer.getSkills().stream()
                .map(Skill::getId)
                .collect(Collectors.toList());

        String devAccountId = String.valueOf(developer.getAccount().getId());


        return new String[]{devId, devName, String.valueOf(devSkills), devAccountId};
    }

    private Long generateId() {
        return (long) (getAllInternal().size() + 1);
    }
}
