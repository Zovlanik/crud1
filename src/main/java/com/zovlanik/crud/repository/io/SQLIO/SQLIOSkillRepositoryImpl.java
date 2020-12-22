package com.zovlanik.crud.repository.io.SQLIO;

import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SQLIOSkillRepositoryImpl implements SkillRepository {
    private static final String CREATE = "INSERT INTO skills (name) VALUES(?);"; //id автоинкремент
    private static final String GET_ALL = "SELECT * FROM crud.skills;";
    private static final String DELETE_BY_ID = "DELETE FROM skills WHERE id = ?;";
    private static final String GET_BY_ID = "SELECT * FROM skills WHERE id = ?;";
    private static final String UPDATE = "UPDATE skills SET name = ? where id = ?;";

    private final DBWorker dbWorker = new DBWorker();

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
//        уточнить что более выгодно. Один запрос с указанием одного ИД или запрос вообще всех элементов, а затем их фильтрация.
//        подозреваю, что мой реализованный вариант хоть и длиннее описывает, но по ресурсам выгоднее
//        И чем больше значений в базе данных, тем более выгоднее
//        return getAllInternal().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()) {

                skill = new Skill(resultSet.getLong("id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    @Override

    //вернуть все элементы
    public List<Skill> getAll() {
        return getAllInternal();
    }

    //вернуть вообще все элементы
    List<Skill> getAllInternal() {
        List<Skill> ls = new ArrayList<>();

        try (Statement statement = dbWorker.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            while (resultSet.next()) {
                ls.add(new Skill(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ls;
    }

    @Override
    public void create(Skill skill) {
        String name = skill.getName();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(CREATE)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Skill skill) {
        Long id = skill.getId();
        String name = skill.getName();

        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(UPDATE)) {
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
