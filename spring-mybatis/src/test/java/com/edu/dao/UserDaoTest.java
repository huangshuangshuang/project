package com.edu.dao;

import com.edu.BaseJunit;
import com.edu.domain.User;
import com.edu.util.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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

