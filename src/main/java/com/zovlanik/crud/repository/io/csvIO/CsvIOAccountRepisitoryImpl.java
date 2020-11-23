package com.zovlanik.crud.repository.io.csvIO;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvIOAccountRepisitoryImpl implements AccountRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\csv\\accounts.csv";

    @Override
    public Account getById(Long id){
        return getAllInternal().stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Account> getAll()  {
        return getAllInternal();
    }

    @Override
    public void create(Account account){
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH,true))){
            account.setId(generateId());
            writer.writeNext(convertAccountToString(account));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update (Account account){
        List<Account> listAccount = getAll();
        listAccount.removeIf(a -> a.getId().equals(account.getId()));
        listAccount.add(account);

        //сортируем наши аккаунты перед записью
        listAccount = listAccount.stream().sorted((a, a1) -> (int) (a.getId() - a1.getId())).collect(Collectors.toList());

        //пишем весь наш список в файл
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))){
            for(Account acc : listAccount){
                writer.writeNext(convertAccountToString(acc));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Long id){
        List<Account> la = getAll();
        la.removeIf(a -> a.getId().equals(id));
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))){
            for(Account acc : la){
                writer.writeNext(convertAccountToString(acc));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Account> getAllInternal(){
        List <Account> la = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))){
            List<String[]> accountsCSV = reader.readAll();
            for(String[] temp : accountsCSV){
                la.add(convertStringToAccount(temp));
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return la;
    }

    private Account convertStringToAccount(String[] accountStr){
        Long id = Long.parseLong(accountStr[0]);
        String data = accountStr[1];
        AccountStatus accountStatus = AccountStatus.valueOf(accountStr[2]);
        return new Account(id, data, accountStatus);
    }

    public String[] convertAccountToString(Account account){
        return new String[]{String.valueOf(account.getId()),account.getData(), String.valueOf(account.getAccountStatus())};
    }

    private Long generateId(){
        return (long) (getAllInternal().size() + 1);
    }
}
