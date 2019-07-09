package com.codeup.springblog.Models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column (nullable=false, unique = true)
    private String title;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
