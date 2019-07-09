package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findAllByAuthor_Id(Long id);
}
