package com.codeup.springblog;

import com.codeup.springblog.Models.Pump;
import com.codeup.springblog.Repositories.MilkRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MilkController {
    private MilkRepository milkDao;

   private UserRepository userDao;

    public MilkController(MilkRepository milkDao, UserRepository userDao) {
        this.milkDao = milkDao;
        this.userDao = userDao;
    }

    @GetMapping("{today}/pumps.json")
    public  @ResponseBody Iterable<Pump> todaysPumps(
            @PathVariable String today
    ){
        return milkDao.findAllByDateOrderByTimeAsc(today);
    }

    @GetMapping("/milk")
    public String todayMilk(Model model){
        model.addAttribute(new Pump());
        return "milk-tracker/today";
    }

    @PostMapping("/milk/{volume}/{date}/{time}/{whiteRussian}")
    @ResponseBody
    public String addPump(
            @PathVariable int volume,
            @PathVariable String date,
            @PathVariable int time,
            @PathVariable boolean whiteRussian
    ){
        Pump pump = new Pump();
        pump.setMom(userDao.findOne(3L));
        pump.setVolumeInmL(volume);
        pump.setDate(date);
        pump.setTime(time);
        pump.setWhiteRussian(whiteRussian);

        milkDao.save(pump);
        return "success";
    }

}
