package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/t")
    public String thyme(Model model){
        model.addAttribute("username", "fred");
        return "thyme";
    }

    @GetMapping("/roll-dice")
    public String roll(@RequestParam(value="guess", required=false) String user, Model model){
        int roll = (int) Math.floor(Math.random() * 6 ) +1;
        int guess = user == null ? 0 : Integer.parseInt(user);

        String correct = roll == guess ? "you guessed " + guess + " and rolled " + roll +" you guessed correctly!" : "you guessed " + guess + " and rolled " + roll +" you guessed incorrectly!";

        model.addAttribute("correct", correct);
        model.addAttribute("guess", guess);
        return "roll";
    }



}
