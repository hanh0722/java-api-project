package com.example.demo.model.Blog;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.model.User.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Document(collection = "blogs")
@AllArgsConstructor
public class Blog {


    private String title;
    private String category;
    private String content;
    private String cover_image;
    private String short_description;
    private long time_created = System.currentTimeMillis();
    private Boolean is_public;

    private String user_id;

    public String getId() {
        return user_id;
    }

    public void setId(String id) {
        this.user_id = id;
    }


    public void setTime_created(long time_created) {
        this.time_created = time_created;
    }

    public long getTime_created() {
        return time_created;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

}
