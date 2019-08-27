package com.codeup.springblog;

import com.codeup.springblog.Models.Pump;
import com.codeup.springblog.Repositories.MilkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MilkController {
    private MilkRepository milkDao;

    public MilkController(MilkRepository milkDao) {
        this.milkDao = milkDao;
    }

    @GetMapping("{today}/pumps.json")
    public  @ResponseBody Iterable<Pump> todaysPumps(
            @PathVariable String today
    ){
        return milkDao.findAllByDateOrderByTimeAsc(today);
    }

    @GetMapping("/milk")
    public String todayMilk(){
        return "milk-tracker/today";
    }

}
