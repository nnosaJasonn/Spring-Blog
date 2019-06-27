package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){
        Iterable<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @PostMapping("/posts")
    public String posts(@RequestParam(name="post") long id){

        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{id}")
    public String posts(@PathVariable long id, Model model){
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "posts/show";
    }




    @GetMapping("/posts/create")
    public String createPostForm(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPostForm(@RequestParam(value="title", required=false) String title, @RequestParam(value="body", required=false) String body){
        Post post = new Post(title, body);
        postDao.save(post);
        return "redirect:/posts";
    }



    @GetMapping("/posts/edit")
    public String editPostForm(@RequestParam(value="post", required=false) long id, Model model){
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editedPost(@RequestParam(value="title", required=false) String title,
                             @RequestParam(value="body", required=false) String body,
                             @RequestParam(value="post", required=false) long id,
                             @RequestParam(value="delete", required=false) String delete){

        if(delete != null){
            postDao.delete(id);
        } else {

            Post updatePost = postDao.findOne(id);
            updatePost.setTitle(title);
            updatePost.setBody(body);
            postDao.save(updatePost);
        }
            return "redirect:/posts";
    }


//    @RequestMapping(path ="/posts/create", method = RequestMethod.POST)
//    @ResponseBody
//    public String createdPost(){
//        return "created";
//    }

}
