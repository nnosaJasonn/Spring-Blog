package com.codeup.springblog;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.Repositories.CategoryRepository;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.UserRepository;
import com.codeup.springblog.Models.Category;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    private final CategoryRepository catDao;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService, CategoryRepository catDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.catDao = catDao;
    }

//    @GetMapping("/posts.json")
//    public @ResponseBody Iterable<Post> viewAllPostsInJSONFormat() {
//        return postDao.findAll();
//    }
//
//    @GetMapping("/posts")
//    public String viewAllPostsWithAjax() {
//        return "posts/ajax";
//    }

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
    public String createPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        Iterable<Category> categories = catDao.findAll();
        model.addAttribute("categories", categories);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPostForm(@ModelAttribute Post post, @RequestParam(value="categories", required=false) List<Long> catIds){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Category> categories = new ArrayList<>();
        for(Long id : catIds){
            categories.add(catDao.findOne(id));
        }
        catDao.save(categories);
        post.setCategories(categories);
        post.setAuthor(user);
        postDao.save(post);
        emailService.prepareAndSend(post, "Post " + post.getTitle(), "You" + post.getBody());
        return "redirect:/posts";
    }

    @GetMapping("/users/{username}")
    public String postsFromUser(@PathVariable String username, Model model){

        User user = userDao.findByUsername(username);

        model.addAttribute("posts", postDao.findAllByAuthor_Id(user.getId()));
        model.addAttribute("user", user);
        return "users/user-posts";
    }

    @GetMapping("/categories/{category}")
    public String postsInCat(@PathVariable Long category, Model model){
       Category cat = catDao.findOne(category);
       model.addAttribute("posts",cat.getPosts());
       model.addAttribute("cat", cat);
       return "categories/category-posts";
    }



    @GetMapping("/posts/edit")
    public String editPostForm(@RequestParam(value="post", required=false) long id, Model model){
        Post post = postDao.findOne(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getAuthor() != user) {
            return "redirect:/posts";
        } else {
            model.addAttribute("post", post);
            return "posts/edit";
        }
    }

    @PostMapping("/posts/edit")
    public String editedPost(@ModelAttribute Post post,
                             @RequestParam(value="delete", required=false) String delete){

        if(delete != null){
            postDao.delete(post.getId());
        } else {

//            Post updatePost = postDao.findOne(id);
            post.setTitle(post.getTitle());
            post.setBody(post.getBody());
            postDao.save(post);
        }
            return "redirect:/posts";
    }


//    @RequestMapping(path ="/posts/create", method = RequestMethod.POST)
//    @ResponseBody
//    public String createdPost(){
//        return "created";
//    }

}
