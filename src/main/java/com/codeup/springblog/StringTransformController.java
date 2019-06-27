package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringTransformController {

    @RequestMapping(path="/string/reverse/{string}")
    @ResponseBody
    public String reversedString(@PathVariable String string){
        String newString = "";
        for(int i=string.length() -1; i>-1; i--){
            newString = newString + string.charAt(i);
        }
        return newString;
    }

    @RequestMapping(path="/string/uppercase/{str}")
    @ResponseBody
    public String uppercase(@PathVariable String str){
        return str.toUpperCase();
    }

    @RequestMapping(path="/string/both/{string}")
    @ResponseBody
    public String both(@PathVariable String string){
        return uppercase(reversedString(string));
    }

    @RequestMapping(path="/string/{string}")
    @ResponseBody
    public String str(@RequestParam(value="reverse", required=false)boolean reverse, @RequestParam(value="capitalize", required=false) boolean capitalize, @PathVariable String string){
        if(reverse){
            string = reversedString(string);
        }
        if(capitalize){
            string = uppercase(string);
        }
        return string;
    }


}
