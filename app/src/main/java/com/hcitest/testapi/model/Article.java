package com.hcitest.testapi.model;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("article_title")
    String article_title;
    @SerializedName("article_image")
    String article_image;
    @SerializedName("link")
    String link;

    public Article(String article_title, String article_image, String link) {
        this.article_title = article_title;
        this.article_image = article_image;
        this.link = link;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_image() {
        return article_image;
    }

    public void setArticle_image(String article_image) {
        this.article_image = article_image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
