package com.zovlanik.crud.repository.io;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.repository.AccountRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOAccountRepositoryImpl implements AccountRepository {
    private static final String FILE_PATH = "src\\main\\resources\\files\\accounts.txt";

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
        try (FileWriter writer = new FileWriter(FILE_PATH,true)){
            writer.write(generateId() + "," + account.getData() + "," + account.getAccountStatus() + "\n");
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
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Account acc : listAccount){
                writer.write(convertAccountToString(acc));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Long id){
        List<Account> la = getAll();
        la.removeIf(a -> a.getId().equals(id));
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            for(Account acc : la){
                    writer.write(convertAccountToString(acc));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Account> getAllInternal(){
        List <Account> la = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)){
            BufferedReader buffReader = new BufferedReader(reader);
            String line = buffReader.readLine();
            while(line != null && line.length() > 1){
                la.add(convertStringToAccount(line));
                line = buffReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return la;
    }

    private Account convertStringToAccount(String accountStr){
        String[] tempValues = accountStr.split(",");
        Long id = Long.parseLong(tempValues[0]);
        String data = tempValues[1];
        AccountStatus accountStatus = AccountStatus.valueOf(tempValues[2]);
        return new Account(id, data, accountStatus);
    }

    private String convertAccountToString(Account account){
        return account.getId() + "," + account.getData() + "," + account.getAccountStatus() + "\n";
    }

    private Long generateId(){
        return (long) (getAllInternal().size() + 1);
    }
}
