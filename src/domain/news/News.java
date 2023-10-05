package domain.news;


import domain.news_category.NewsCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class News {

    private Long id;
    private String title;
    private String content;
    private NewsCategory category;
    private LocalDate postdate;


    public News() {
    }

    public News(Long id, String title, String content, NewsCategory category, LocalDate postdate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.postdate = postdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    public LocalDate getPostdate() {
        return postdate;
    }

    public void setPostdate(LocalDate postdate) {
        this.postdate = postdate;
    }
}
