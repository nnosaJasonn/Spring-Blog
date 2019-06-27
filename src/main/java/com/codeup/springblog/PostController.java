package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {


    @GetMapping("/posts")
    public String posts(Model model){
        List<Post> posts = new ArrayList<>();
        posts.add(
                new Post("first post", " blah blah blah blah blah")
        );
        posts.add(new Post("second post", "yada yada yada"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String posts(@PathVariable long id, Model model){
        Post post = new Post("here's a blog post", "it's about nothing");
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "<label for='title'>blog title</label><input id='title' type='text'><br> <label for='body'>body</label><textarea id='body'/><button>Submit</button>";
    }


    @RequestMapping(path ="/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createdPost(){
        return "created";
    }

}
