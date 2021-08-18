package com.liuxiaocs.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuxiaocs.mybatisplus.entity.User;

import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:03
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据姓名查询用户
     * @param name 姓名
     * @return  用户列表
     */
    List<User> selectAllByName(String name);

    /**
     * 查询 : 根据年龄查询用户列表，分页显示
     * IPage 是一个接口，Page是IPage的一个实现类
     * MP会自动读取Page对象并将条件追加到查询条件中
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位
     * @param age 年龄
     * @return 分页对象
     */
    IPage<User> selectPageVo(Page<?> page, Integer age);
}
