package com.edu.dao;

import com.edu.domain.User;
import com.edu.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

   public User insert(User user);

   public List<User> findAll(Page page);

   public List<User> findBySex(Page page);
}
