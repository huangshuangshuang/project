package com.edu.dao;

import com.edu.domain.User;
import com.edu.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */
@Repository
public interface UserMapper {

   public User insert(User user);

   public List<User> findAll(Page page);

   public List<User> findBySex(Page page);
}
