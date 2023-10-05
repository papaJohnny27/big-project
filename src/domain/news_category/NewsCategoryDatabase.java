package domain.news_category;

import domain.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NewsCategoryDatabase {

    public static void addNewNewsCategory(String name){
       try ( PreparedStatement preparedStatement = Database.connection.prepareStatement("INSERT INTO news_categories VALUES (default,? );")){

           preparedStatement.setString(1,name);
           preparedStatement.executeUpdate();
       }catch (Exception e){
           e.printStackTrace();
           System.out.println("");
       }
    }

    public static List<NewsCategory> getAllNewsCategories(){
        List<NewsCategory> newsCategories = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT * FROM  news_categories;")){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                newsCategories.add(new NewsCategory(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ) );
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("");
        }
        return newsCategories;
    }
}
