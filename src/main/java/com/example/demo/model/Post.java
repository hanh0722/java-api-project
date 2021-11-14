package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@AllArgsConstructor
@Document(collection = "blogs")
public class Post {
    @Id
    private String _id;
    private String title,des,content,url;
    private boolean publish;

    public Post() {
        super();
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPublish() {
        return this.publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }
}
