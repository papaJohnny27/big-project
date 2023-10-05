package domain.news;

import domain.Database;
import domain.news_category.NewsCategory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewsDatabase {

    public static void addNewNews(String title, String content, Long categoryId, LocalDate postDate) {
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("INSERT INTO news (category_id,title,content,post_date)VALUES (?,?,?,?);")) {
            preparedStatement.setLong(1, categoryId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, content);
            preparedStatement.setDate(4, Date.valueOf(postDate));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to add new news");
        }

    }

    public static List<News> getAllnews() {
        ArrayList news = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT n.id as id,n.title as title,n.content as content,n.post_date as post_date,nc.id as news_categories_id, nc.name as news_category_name FROM news n INNER JOIN news_categories nc on nc.id = n.category_id;")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(
                                resultSet.getLong("id"),
                                resultSet.getString("title"),
                                resultSet.getString("content"),

                                new NewsCategory(
                                        resultSet.getLong("news_categories_id"),
                                        resultSet.getString("news_category_name")
                                ),
                                resultSet.getDate("post_date").toLocalDate()
                        )

                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get all news");
        }
        return news;
    }


    public static News getNewsById(Long id) {

        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT n.id as id,n.title as title,n.content as content,n.post_date as post_date,nc.id as news_categories_id, nc.name as news_category_name FROM news n INNER JOIN news_categories nc on nc.id = n.category_id WHERE n.id = ?;")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return new News(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),

                    new NewsCategory(
                            resultSet.getLong("news_categories_id"),
                            resultSet.getString("news_category_name")
                    ),
                    resultSet.getDate("post_date").toLocalDate()


            );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to get  news");
        }
        return null;
    }
}

