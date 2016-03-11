package com.edu.service;

import com.edu.domain.User;
import com.edu.util.Page;
import com.edu.util.ResultDo;


public interface UserService {

    public User insert(User user);

    public Page findAll(String name,Page page);

    public ResultDo<Page> findBySex(User user,Page page);
}
