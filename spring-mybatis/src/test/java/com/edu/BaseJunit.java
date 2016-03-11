package com.edu;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:mybatis.xml",
        "classpath:spring.xml",
        "classpath:spring-mvc.xml"
})
@Transactional
public class BaseJunit {
}
