package com.edu.controller;

import com.edu.domain.User;
import com.edu.service.UserService;
import com.edu.util.JsonP;
import com.edu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */

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
