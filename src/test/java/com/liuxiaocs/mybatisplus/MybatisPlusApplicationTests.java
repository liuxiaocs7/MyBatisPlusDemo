package com.liuxiaocs.mybatisplus;

import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest  // 自动创建Spring上下文环境
class MybatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
