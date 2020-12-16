package com.zovlanik.crud.repository.io.SQLIO;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.AccountStatus;
import com.zovlanik.crud.repository.AccountRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLIOAccountRepositoryImpl implements AccountRepository {

    private static final String CREATE = "INSERT INTO account (name,id_account_status) VALUES (?, ?);"; //id автоинкремент
    private static final String GET_ALL = "SELECT account.id, name, status_name FROM account JOIN account_status on account.id_account_status=account_status.id;";
    private static final String DELETE_BY_ID = "DELETE FROM account WHERE id = ?;";
    private static final String GET_BY_ID = "SELECT account.id, name, status_name FROM account JOIN account_status on account.id_account_status=account_status.id WHERE account.id = ?;";
    private static final String UPDATE = "UPDATE account SET name = ? where id = ?;";

    private final DBWorker dbWorker = new DBWorker();

    @Override
    public Account getById(Long id) {
        Account account = null;

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            account = new Account(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    AccountStatus.valueOf(resultSet.getString("status_name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public List<Account> getAll() {
        return getAllInternal();
    }

    @Override
    public void create(Account account) {
        String name = account.getData();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(CREATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, 1); //мы всегда создаём активный аккаунт
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Account account) {
        String name = account.getData();
        Long id = account.getId();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Account> getAllInternal() {
        List<Account> la = new ArrayList<>();

        try (Statement statement = dbWorker.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                la.add(new Account(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        AccountStatus.valueOf(resultSet.getString("status_name"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return la;
    }

}
