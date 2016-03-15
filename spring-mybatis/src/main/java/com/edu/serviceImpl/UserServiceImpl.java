package com.edu.serviceImpl;

import com.edu.dao.UserMapper;
import com.edu.domain.User;
import com.edu.service.UserService;
import com.edu.util.Page;
import com.edu.util.ResultDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User insert(User user) {
        return userMapper.insert(user);
    }

    public Page findAll(String name,Page page) {
        page.setQuery(name);
        page.setData(userMapper.findAll(page));
        return page;
    }

    public ResultDo<Page> findBySex(User user, Page page) {
        ResultDo<Page> resultDo=new ResultDo<Page>();
        page.setQuery(user);
        page.setData(userMapper.findBySex(page));
        resultDo.setData(page);
        System.out.println(resultDo.isSuccess());
        return resultDo;
    }
}
