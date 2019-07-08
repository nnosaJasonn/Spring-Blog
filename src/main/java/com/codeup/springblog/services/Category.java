package com.codeup.springblog.services;

import com.codeup.springblog.Post;

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

}
