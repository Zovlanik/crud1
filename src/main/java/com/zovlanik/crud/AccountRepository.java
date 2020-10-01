package com.zovlanik.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private static final String FILE_PATH = "src\\main\\resources\\";
    void create(Account account){
        List<Account> la = getAll();
        long size = la.size();
        try (FileWriter writer = new FileWriter(FILE_PATH + "accounts.txt",true)){
            writer.write((size+1) + "," + account.data + "," + account.accountStatus + "\n");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void update (Account account){
        List<Account> listAccount = getAll();
        try (FileWriter writer = new FileWriter(FILE_PATH + "accounts.txt")){
            for(Account acc : listAccount){
                if(acc.id != account.id) {
                    writer.write(acc.toString() + "\n");

                } else {
                    writer.write(account.id + "," + account.data + account.accountStatus + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Account getById(long id){
        Account account = null;
        long temp;
        try (FileReader reader = new FileReader(FILE_PATH + "accounts.txt")){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                temp = Long.parseLong(line.substring(0,line.indexOf(',')));
                if(temp == id){
                    String[] tempStr = line.split(",");
                    account = new Account(Long.parseLong(tempStr[0]),tempStr[1],AccountStatus.valueOf(tempStr[2]));
                }

                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    List<Account> getAll()  {
        List <Account> ls = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH + "accounts.txt")){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                String[] temp = line.split(",");
                Account tempAccount = new Account(Long.parseLong(temp[0]),temp[1],AccountStatus.valueOf(temp[2]));
                ls.add(tempAccount);
                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    void deleteById(Long id){
        List<Account> la = getAll();

        try (FileWriter writer = new FileWriter(FILE_PATH + "accounts.txt")){
            for(Account acc : la){
                if(acc.id != id) {
                    writer.write(acc.toString() + "\n");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
