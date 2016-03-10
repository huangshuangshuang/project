package com.edu.serviceImpl;

import com.edu.dao.UserMapper;
import com.edu.domain.User;
import com.edu.service.UserService;
import com.edu.util.Page;
import com.edu.util.ResultDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Page findAll(String name,Page page) {
        page.setQuery(name);
        page.setData(userMapper.findAll(page));
        return page;
    }

    @Override
    public ResultDo<Page> findBySex(User user, Page page) {
        ResultDo<Page> resultDo=new ResultDo<Page>();
        page.setQuery(user);
        page.setData(userMapper.findBySex(page));
        resultDo.setData(page);
        System.out.println(resultDo.isSuccess());
        return resultDo;
    }
}
