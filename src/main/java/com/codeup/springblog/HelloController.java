package com.codeup.springblog;


import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping(path="/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @GetMapping(path="/goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye, cruel world";
    }

//    path variables
    @GetMapping("/hello/{name}")
    @ResponseBody
    public String helloName(@PathVariable String name){
        return "Howdy, " + name;
    }

    @GetMapping("/favnum/{num}")
    @ResponseBody
    public String favNum(@PathVariable int num){


            return "Fav num is: " + num;

    }

    @RequestMapping(path = "/increment/{number}", method= RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus 1 is " + (number + 1);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }

}
