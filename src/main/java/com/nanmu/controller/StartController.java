package com.nanmu.controller;

import com.nanmu.data.Parse;
import com.nanmu.data.User;
import com.nanmu.service.ParseService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: name
 * @description:
 * @author: 唐鹏
 * @date: 2021-07-15 11:53
 */
@Controller
@RequiredArgsConstructor
public class StartController {
    private final ParseService parseService;
    Map<String, String> map = new HashMap<>(4);

    @GetMapping("/start")
    public String start(User user) {
        if (user.getName() == null || user.getPhoneNumber() == null || user.getCity() == null) {
            return "start";
        }
        map.put("name", user.getName());
        map.put("phone", user.getPhoneNumber());
        map.put("city", user.getCity());

        return "start";
    }
    @GetMapping("/stop")
    public String stop() {
        map.clear();

        return "start";
    }

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/dataView")
    @ResponseBody
    public void dataView() {

    }
    @RequestMapping("doDeleteById")
    public String doDeleteById(Long id, Model model) {
        //return "redirect:doFindActivitys";
//        List<Activity> list=activityService.findActivitys();
//        model.addAttribute("list", list);
        return "activity";
    }
    /**
     * Desc:0 0 8 * * ? * * * * * ?
     * @author 唐鹏
     * @date 2021/7/21 13:56
     */
    @Scheduled(cron = "* * * * * ?")
    public void timing() {
        if (map.isEmpty()) {

            return;
        }
        String name = map.get("name");
        String phone = map.get("phone");
        String city = map.get("city");
        Parse send = parseService.send(name, phone, city);

        System.out.println("send = " + send);
    }

}
