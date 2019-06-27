package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController{

    @RequestMapping(path="/add/{x}/and/{y}")
    @ResponseBody
    public String add(@PathVariable int x, @PathVariable int y){
        return x + " plus " + y + " equals " + (x+y);
    }

    @RequestMapping(path="/subtract/{x}/from/{y}")
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return y + " minus " + x + " equals " + (y-x);
    }

    @RequestMapping(path="/multiply/{x}/and/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y){
        return x + " times " + y + " equals " + (x*y);
    }

    @RequestMapping(path="/divide/{x}/by/{y}")
    @ResponseBody
    public String divide(@PathVariable int x, @PathVariable int y) throws ArithmeticException{
        return x + " divided by " + y + " equals " + (x/y);
    }


}
