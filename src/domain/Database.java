package domain;

import domain.role.Role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    public static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/big_project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "eldos";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASSWORD);
            connection = DriverManager.getConnection(URL,props);

            System.out.println("Successfully connected to database");
        }catch (Exception e){
            System.out.println("Failed to connect to database");
        }
    }

}
