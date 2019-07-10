package com.hcitest.testapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetArticle {

    @SerializedName("section")
    String section;
    @SerializedName("section_title")
    String section_title;
    @SerializedName("items")
    List<Article> articleList;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection_title() {
        return section_title;
    }

    public void setSection_title(String section_title) {
        this.section_title = section_title;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
