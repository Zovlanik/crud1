package com.zovlanik.crud.repository.io.SQLIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {


    private static final String URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "1qaz`2wsx";

    private Connection connection;

    public DBWorker()  {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
