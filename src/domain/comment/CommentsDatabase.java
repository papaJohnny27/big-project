package domain.comment;

import domain.Database;
import domain.user.UserDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentsDatabase {

    public static void addNewComment(LocalDateTime postDate, Long userId,Long newsId, String content){
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("INSERT INTO comment(post_date,content,user_id,news_id) VALUES (?,?,?,?);")){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(postDate));
            preparedStatement.setString(2,content);
            preparedStatement.setLong(3,userId);
            preparedStatement.setLong(4,newsId);

            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to add new comment");
        }
    }

    public static List<Comment> getAllCommentsByNewsId(Long newsid){
        ArrayList<Comment> comments = new ArrayList<>();
        try (PreparedStatement preparedStatement = Database.connection.prepareStatement("SELECT c.id as id,c.content as content, c.news_id as news_id, c.post_date as post_date, u.id as user_id,u.email as user_email, u.full_name as user_full_name,u.password as user_password,u.role_id as roleId FROM comment c INNER JOIN users u on c.user_id = u.id WHERE news_id = ?;")){
            preparedStatement.setLong(1,newsid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                comments.add(new Comment(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("post_date").toLocalDateTime(),
                        resultSet.getString("content"),
                        UserDatabase.getUser(resultSet.getLong("user_id")),
                        resultSet.getLong("news_id")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to get all comments by news");
        }
        return comments;
    }
}
