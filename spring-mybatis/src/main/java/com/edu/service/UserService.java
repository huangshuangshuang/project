package com.edu.service;

import com.edu.domain.User;
import com.edu.util.Page;
import com.edu.util.ResultDo;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */
public interface UserService {

    public User insert(User user);

    public Page findAll(String name,Page page);

    public ResultDo<Page> findBySex(User user,Page page);
}
