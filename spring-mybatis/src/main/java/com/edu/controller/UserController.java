package com.edu.controller;

import com.edu.domain.User;
import com.edu.service.UserService;
import com.edu.util.JsonP;
import com.edu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    @ResponseBody
    public JsonP insert(User user) {
        userService.insert(user);
        return JsonP.success();
    }

    @RequestMapping("findAll")
    @ResponseBody
    public JsonP findAll(String name,Page page) {
        page=userService.findAll(name,page);
        return JsonP.success(page.getData());
    }
}
