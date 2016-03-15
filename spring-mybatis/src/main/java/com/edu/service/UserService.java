package com.edu.service;

import com.edu.domain.User;
import com.edu.util.Page;
import com.edu.util.ResultDo;


public interface UserService {

    User insert(User user);

    Page findAll(String name, Page page);

    ResultDo<Page> findBySex(User user, Page page);
}
