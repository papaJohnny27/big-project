package domain.comment;

import domain.user.User;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private LocalDateTime postDate;
    private String content;
    private User user;
    private Long newsId;


    public Comment() {
    }

    public Comment(Long id,LocalDateTime postDate, String content, User user, Long newsId) {
        this.id = id;
        this.postDate = postDate;
        this.content = content;
        this.user = user;
        this.newsId = newsId;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }
}
