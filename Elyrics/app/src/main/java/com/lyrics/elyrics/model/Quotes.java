package com.lyrics.elyrics.model;

import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("content")
    private String content;
    @SerializedName("author")
    private String author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
