package com.zovlanik.crud.repository.io.SQLIO;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SQLIODeveloperRepositoryImpl implements DeveloperRepository {
    private final SkillRepository skillRepo = new SQLIOSkillRepositoryImpl();
    private final AccountRepository accountRep = new SQLIOAccountRepositoryImpl();

    private static final String CREATE = "INSERT INTO developer (name,id_account) VALUES (?, ?);"; //id автоинкремент
    private static final String GET_ALL_DEVELOPER = "SELECT * FROM developer;";
    private static final String GET_ALL_SKILL_BY_DEVELOPER_ID = "SELECT * FROM skills JOIN skills_developers ON skills.id = skills_developers.id_skill WHERE id_developer = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM developer WHERE id = ?;";
    private static final String GET_BY_ID = "SELECT * FROM developer WHERE id = ?";
    private static final String UPDATE = "UPDATE developer SET name = ? where id = ?;";

    private final DBWorker dbWorker = new DBWorker();


    @Override
    public Developer getById(Long id) {
        Developer developer = null;

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            developer = createDeveloperFromSQL(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("id_account"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return getAllInternal();
    }

    public List<Developer> getAllInternal() {
        List<Developer> ld = new ArrayList<>();
        try (Statement statement = dbWorker.getConnection().createStatement()) {
            ResultSet resultSetDev = statement.executeQuery(GET_ALL_DEVELOPER);
            while (resultSetDev.next()) {
                ld.add(createDeveloperFromSQL(resultSetDev.getLong("id"),
                        resultSetDev.getString("name"),
                        resultSetDev.getLong("id_account")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ld;
    }

    private Developer createDeveloperFromSQL(Long id_developer, String name_developer, Long id_account) {
        //получим для нашего девелопера все его скиллы и создадим из них сет
        Set<Skill> skills = new HashSet<>();
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(GET_ALL_SKILL_BY_DEVELOPER_ID)) {
            preparedStatement.setLong(1, id_developer);
            preparedStatement.execute();
            ResultSet resultSetSkills = preparedStatement.getResultSet();
            while (resultSetSkills.next()) {
                skills.add(skillRepo.getById(resultSetSkills.getLong("id_skill")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Account developerAccount = accountRep.getById(id_account);

        return new Developer(id_developer, name_developer, skills, developerAccount);
    }

    @Override
    public void create(Developer developer) {
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(CREATE)) {
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setLong(2, developer.getAccount().getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer developer) {
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setString(1, developer.getName());
            preparedStatement.setLong(2, developer.getId());
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

}
