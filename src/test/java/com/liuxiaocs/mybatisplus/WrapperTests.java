package com.liuxiaocs.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuxiaocs
 * @date 2021/4/4 22:35
 */
@SpringBootTest
public class WrapperTests {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
     */
    @Test
    public void test1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // column: 对应数据库中的列名而不是属性名
//        queryWrapper.like("username", "1");
//        queryWrapper.ge("age", 10);
//        queryWrapper.le("age", 20);
//        queryWrapper.between("age", 51, 60);
//        queryWrapper.isNotNull("email");

        queryWrapper
                .like("username", "1")
                .between("age", 51, 60)
                .isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 组装排序条件
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test3() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 组装排序条件
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("删除的记录数" + result);
    }

    /**
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test4() {

        // 组装查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 组装排序条件，i表示queryWrapper
        queryWrapper
                .like("username", "n")
                .and(i -> i.lt("age", 18).or().isNull("email"));

        // 组装更新条件
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");

        // 执行更新
        int result = userMapper.update(user, queryWrapper);
        System.out.println("更新的记录数" + result);
    }

    @Test
    public void test5() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 组装select语句
        queryWrapper.select("username", "age");

        // selectMaps()返回Map集合列表，通常配合select()使用，避免User对象中没有被查询到的列值为null
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    /**
     * 子查询
     */
    @Test
    public void test6() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 以下三种写法等价
//        queryWrapper.inSql("uid", "select uid from t_user where uid <= 3");
//        queryWrapper.in("uid", 1, 2, 3);
        queryWrapper.le("uid", 3);

        // selectObjs的使用场景：只返回一列
        // SELECT uid AS id,username AS name,age,email,create_time,update_time,is_deleted AS deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 3))
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test7() {

        // 组装查询条件
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 组装排序条件，i表示queryWrapper
        updateWrapper
                .set("age", 18)
                .set("email", "user@atguigu.com")
                .like("username", "n")
                .and(i -> i.lt("age", 18).or().isNull("email"));

        // 组装更新条件
//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@atguigu.com");

        // 执行更新
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("更新的记录数" + result);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test8() {

        // 条件可以为空
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if(StringUtils.isNotBlank(username)) {
//            queryWrapper.like("username", username);
//        }
//
//        if(ageBegin != null) {
//            queryWrapper.ge("age", ageBegin);
//        }
//        if(ageEnd != null) {
//            queryWrapper.le("age", ageEnd);
//        }

        // 使用condition + 串联的写法
        queryWrapper
                .like(StringUtils.isNotBlank(username),"username", username)
                .ge(ageBegin != null,"age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test9() {

        // 条件可以为空
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 使用condition + 串联的写法
        queryWrapper
                .like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10() {

        // 组装查询条件
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        // 组装排序条件，i表示queryWrapper
        updateWrapper
                .set(User::getAge, 18)
                .set(User::getEmail, "user@atguigu.com")
                .like(User::getName, "n")
                .and(i -> i.lt(User::getAge, 18).or().isNull(User::getEmail));
        
        // 执行更新
        User user = new User();
        int result = userMapper.update(user, updateWrapper);
        System.out.println("更新的记录数" + result);
    }
}
