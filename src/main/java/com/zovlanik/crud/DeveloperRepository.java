package com.zovlanik.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Long.parseLong;

public class DeveloperRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\developers.txt";

    void create(Developer developer){
        List<Developer> ld = getAll();
        long size = ld.size();
        try (FileWriter writer = new FileWriter(FILE_PATH,true)){
            writer.write((size+1) + "-" + developer.name + "-" + developer.skills + "-" + developer.account + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void update (Developer developer){
        List<Developer> listDeveloper = getAll();
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Developer dev : listDeveloper){
                if(dev.id != developer.id) {
                    writer.write(dev.toString() + "\n");

                } else {
                    writer.write(developer.id + "-" + developer.name + "-" + developer.skills + "-" + developer.account + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Developer getById(long id){
        Developer developer = null;
        long temp;
        try (FileReader reader = new FileReader(FILE_PATH)){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                temp = parseLong(line.substring(0,line.indexOf(',')));
                if(temp == id){
                    String[] tempStr = line.split("-");



                    String[] tempStrSkillsID = tempStr[2].split(",");
                    long[] listSkills = new long[tempStrSkillsID.length];

                    for(int i = 0; i < tempStrSkillsID.length; i++){
                        listSkills[i] = (parseLong(tempStrSkillsID[i]));
                    }

                    developer = new Developer(parseLong(tempStr[0]),tempStr[1], listSkills, parseLong(tempStr[3]));
                }

                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return developer;
    }

    List<Developer> getAll()  {
        List <Developer> ld = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                String[] tempStr = line.split("-");
                String[] tempStrSkillsIdName = tempStr[2].replaceAll("[\\[\\] ]","").split(",");

                String[] tempStrSkillsID = new String[tempStrSkillsIdName.length / 2];
                for(int i = 0; i < tempStrSkillsID.length; i++){
                    tempStrSkillsID[i] = tempStrSkillsIdName[i*2];
                }
                long[] listSkills = new long[tempStrSkillsID.length];

                for(int i = 0; i < tempStrSkillsID.length; i++){

                    listSkills[i] = parseLong(tempStrSkillsID[i]);
                }

                Developer tempDeveloper = new Developer(parseLong(tempStr[0]),tempStr[1], listSkills, parseLong(tempStr[3].substring(0,1)));
                ld.add(tempDeveloper);
                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ld;
    }

    void deleteById(Long id){
        List<Developer> ld = getAll();

        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Developer dev : ld){
                if(dev.id != id) {
                    writer.write(dev.toString() + "\n");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

