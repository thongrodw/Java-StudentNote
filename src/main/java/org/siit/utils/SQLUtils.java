package org.siit.utils;

import java.sql.*;

public class SQLUtils {

    public static Connection connect() {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "admin";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
