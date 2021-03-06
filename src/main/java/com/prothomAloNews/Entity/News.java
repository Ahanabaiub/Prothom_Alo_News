package com.prothomAloNews.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class News {

    @Id
    @GeneratedValue
    private Long id;

    private String newsLink;

    private String newsCategory;

    private Date date;

    public News() {

    }

    public News(String newsLink, Date date, String newsCategory) {
        this.newsLink = newsLink;
        this.date = date;
        this.newsCategory = newsCategory;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsLink() {
        return newsLink;
    }


    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsLink);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        News other = (News) obj;
        return Objects.equals(newsLink, other.newsLink);
    }



}
