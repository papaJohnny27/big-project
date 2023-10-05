package domain.user;

import domain.Database;
import domain.role.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    public static void addUser(String email, String password,String fullname, Long roleId){
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("INSERT INTO users (email, password, full_name,role_id)VALUES (?, ?, ?, ?)")){

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullname);
            preparedStatement.setLong(4,roleId);

            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to add new user");
        }
    }

    public static User getUser(Long id){
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT u.id as id,u.email as email," +
                "u.password as password," +
                "u.full_name as fullname,r.id as role_id," +
                "r.name as role_name FROM users u INNER JOIN roles r on r.id=u.role_id " +
                "WHERE u.id = ?;") ){
            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("failed to get user by id");
        }
        return null;
    }

    public static List<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT u.id as id,u.email as email,u.password as password,u.full_name as fullname,r.id as role_id,r.name as role_name FROM users u INNER JOIN roles r on r.id=u.role_id;") ){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("failed to get user by id");
        }
        return users;
    }

    public static void updateUser(Long id, String email, String password, String fullname, Long roleId){

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("UPDATE users SET email = ?, password = ?,full_name = ?,role_id = ? WHERE id = ?")){
            preparedStatement.setLong(5,id);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,fullname);
            preparedStatement.setLong(4,roleId);

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to update user");
        }
    }


    public static User getUserByEmailAndPassword(String email, String password){
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT u.id as id,u.email as email,u.password as password,u.full_name as fullname,r.id as role_id,r.name as role_name FROM users u INNER JOIN roles r on r.id=u.role_id WHERE u.email = ? AND u.password = ?;") ){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname"),
                        new Role(
                                resultSet.getLong("role_id"),
                                resultSet.getString("role_name")
                        )
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("failed to get user by email and password");
        }
        return null;
    }
}
