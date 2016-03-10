package com.edu.dao;

import com.edu.BaseJunit;
import com.edu.domain.User;
import com.edu.util.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */
public class UserDaoTest extends BaseJunit {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        Page page=new Page();
        page.setQuery("张");
        List<User> users=userMapper.findAll(page);
        System.out.println(users.size());
    }

}

