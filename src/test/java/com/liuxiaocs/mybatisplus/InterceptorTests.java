package com.liuxiaocs.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuxiaocs.mybatisplus.entity.Product;
import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.mapper.ProductMapper;
import com.liuxiaocs.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分页查询测试
 * @author liuxiaocs
 * @date 2021/4/4 20:37
 */
@SpringBootTest
public class InterceptorTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testSelectPage() {
        // 查询第1页，每页5条记录
        Page<User> pageParam = new Page<>(1, 5);
        // 将查询结果放到pageParam里面
        userMapper.selectPage(pageParam, null);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);

        long total = pageParam.getTotal();
        System.out.println(total);

        boolean bn = pageParam.hasNext();
        System.out.println("下一页？" + bn);

        boolean bp = pageParam.hasPrevious();
        System.out.println("上一页？" + bp);
    }

    @Test
    public void testSelectPageByAge() {
        Page<User> pageParam = new Page<>(1, 5);
        // 查询所有年龄大于50的用户
        userMapper.selectPageVo(pageParam, 70);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void testConcurrentUpdate() {

        // 小李取数据
        Product p1 = productMapper.selectById(1L);

        // 小王取数据
        Product p2 = productMapper.selectById(1L);

        // 小李修改 + 50
        p1.setPrice(p1.getPrice() + 50);
        int result = productMapper.updateById(p1);
        System.out.println("小李修改的结果：" + result);

        // 小王修改 - 30
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改的结果：" + result2);
        if(result2 == 0) {
            // 失败重试
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result2 = productMapper.updateById(p2);
        }
        System.out.println("小王修改的结果：" + result2);

        // 老板看价格
        Product p3 = productMapper.selectById(1L);
        System.out.println("老板看到的价格：" + p3.getPrice());
    }
}
