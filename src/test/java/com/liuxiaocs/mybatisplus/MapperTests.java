package com.liuxiaocs.mybatisplus;

import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:25
 */
@SpringBootTest
public class MapperTests {

    @Resource
    private UserMapper userMapper;

    /**
     * 添加
     */
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("观海7");
//        user.setAge(400);
        user.setEmail("guanhai1@qq.com");
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
        int result = userMapper.insert(user);
        System.out.println("结果: " + result);
    }

    /**
     * 查询
     */
    @Test
    public void testSelect() {
        // 根据ID查询
        // SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(1L);
        System.out.println(user);

        // 根据多个ID查询
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
        
        // 根据条件查询
        // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "建国");
        map.put("age", 74);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);
    }

    /**
     * 更新，默认情况是一个动态SQL，避免了误修改
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1378662101643534350L);
        user.setAge(77);
        // null, null
        // UPDATE user SET age=? WHERE id=?
        int result = userMapper.updateById(user);
        System.out.println("结果: " + result);
    }

    @Test
    public void testDelete() {
        int result = userMapper.deleteById(2L);
        System.out.println("结果: " + result);
    }

    /**
     * 使用自定义方法进行查询
     */
    @Test
    public void testSelectAllByName() {
        // select id, name, age, email from user where name = ?
        List<User> users = userMapper.selectAllByName("Jack");
        users.forEach(System.out::println);
    }
}
