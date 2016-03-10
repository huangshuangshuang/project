package com.edu.service;

import com.edu.BaseJunit;
import com.edu.domain.User;
import com.edu.util.Page;
import com.edu.util.ResultDo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/8
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/8              00000001        创建文件
 */
public class UserServiceTest extends BaseJunit{
    @Autowired
    private UserService userService;

    @Test
    public void findBySex(){
        Page page=new Page();
        User user=new User();
        user.setSex("女");
        ResultDo<Page> resultDo=userService.findBySex(user, page);
        for (int i=0;i<resultDo.getData().getData().size();i++){
            System.out.println(((User)page.getData().get(i)).getName());
        }

    }

    @Test
    public void findAll(){
        Page page=new Page();
        page=userService.findAll("邹", page);
        for (int i=0;i<page.getData().size();i++){
            System.out.println(((User)page.getData().get(i)).getName());
        }

    }
}
