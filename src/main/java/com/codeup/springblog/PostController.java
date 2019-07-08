package com.codeup.springblog;

import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String createPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPostForm(@ModelAttribute Post post){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setAuthor(user);
        postDao.save(post);
        emailService.prepareAndSend(post, "Post " + post.getTitle(), "You" + post.getBody());
        return "redirect:/posts";
    }



    @GetMapping("/posts/edit")
    public String editPostForm(@RequestParam(value="post", required=false) long id, Model model){
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
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
