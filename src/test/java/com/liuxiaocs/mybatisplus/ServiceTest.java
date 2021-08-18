package com.liuxiaocs.mybatisplus;

import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:54
 */
@SpringBootTest
public class ServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testCount() {
        int count = userService.count();
        System.out.println("总记录数： " + count);
    }

    /**
     * 批量保存
     */
    @Test
    public void testSaveBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("瓜瓜" + i);
            user.setAge(20 + i);
            users.add(user);
        }
        // INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        boolean result = userService.saveBatch(users);
        System.out.println("插入是否成功: " + result);
    }

    /**
     * 自定义查询方法
     * 根据姓名查询
     */
    @Test
    public void testListAllByName() {
        List<User> users = userService.listAllByName("Tom");
        users.forEach(System.out::println);
    }
}
