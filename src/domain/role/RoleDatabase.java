package domain.role;

import domain.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDatabase {

    public static void addRole(String name) {
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("INSERT INTO roles VALUES (default, ?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to add new role");
        }
    }

    public static List<Role> getAllRoles() {
        ArrayList<Role> roles = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT * FROM roles;")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (Exception e) {
            System.out.println("Failed to get all roles");
        }

        return roles;
    }


}
